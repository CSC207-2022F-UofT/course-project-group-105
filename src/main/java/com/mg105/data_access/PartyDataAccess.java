package com.mg105.data_access;

import com.mg105.outputds.BattleCharacterDetails;
import com.mg105.outputds.MoveDetails;
import com.mg105.use_cases.PartyDataInterface;
import com.mg105.use_cases.utils.PartyConstants;
import com.mg105.use_cases.utils.StatConstants;
import com.opencsv.*;
import com.opencsv.exceptions.CsvException;
import org.jetbrains.annotations.NotNull;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.mg105.use_cases.utils.DataAccessConstants.BASE_PATH;

public class PartyDataAccess implements PartyDataInterface {

    private final static String PARTY_DATA_FILE = BASE_PATH + "party_data.csv";

    private final static int NUMBER_OF_MOVES_PER_CHARACTER = 2;
    private final MoveDataAccess moveDataAccess;


    public PartyDataAccess(MoveDataAccess moveDataAccess) {
        // Could define a new moveDataAccess here but it is passed in to follow dependency inversion
        this.moveDataAccess = moveDataAccess;
    }


    /**
     * Updates the given stat of the given party member to the value provided
     * <p>
     * Precondition(s): name is the name of some party member and stat is the name of a valid stat
     *
     * @param name  name of the character to change the stat of
     * @param stat  the name of the stat to change
     * @param value the value to change that stat to
     */

    @Override
    public void changeCharacterStat(@NotNull String name, @NotNull String stat, int value) {
        try {
            CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
            CSVReader reader = new CSVReaderBuilder(new FileReader(PARTY_DATA_FILE)).withCSVParser(parser).build();
            List<String[]> partyStats = reader.readAll();

            // Setting which value to change in the file by reading

            for (String[] memberStats : partyStats) {
                if (memberStats[0].equals(name)) {
                    int i = getColumnNumber(stat);
                    System.out.println(i);
                    if (i == -1) {
                        // Occurs when an invalid stat argument is passed
                        reader.close();
                        return;
                    }

                    if (memberStats[i].equals(String.valueOf(value))) {
                        // value of stat to change is the same as the "New" value
                        reader.close();
                        return;
                    }
                    memberStats[i] = String.valueOf(value);
                    break;

                }
            }

            reader.close();

            // Changing that value in the file
            CSVWriter writer = new CSVWriter(new FileWriter(PARTY_DATA_FILE), ',',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);

            // Writing the entire file might be expensive but this works
            writer.writeAll(partyStats);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            System.out.println("Could not find file");
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }

    }

    /**
     * Returns an array of objects that each represent the battle attributes of a single party member
     *
     * @return an array of objects that each represent the battle attributes of a single party member
     * @see BattleCharacterDetails
     */

    @Override
    public @NotNull BattleCharacterDetails[] getPartyBattleDetails() {

        BattleCharacterDetails[] res = new BattleCharacterDetails[PartyConstants.ALL_PARTY_MEMBER_NAMES.length];

        try {
            CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
            CSVReader reader = new CSVReaderBuilder(new FileReader(PARTY_DATA_FILE)).withCSVParser(parser).build();
            List<String[]> partyStats = reader.readAll();

            // Starts at one since first line is just the variable names (same reason for + 1)

            for (int i = 1; i < PartyConstants.ALL_PARTY_MEMBER_NAMES.length + 1; i++) {
                String[] memberAttributes = partyStats.get(i);
                String name = memberAttributes[0];
                int maxHp = Integer.parseInt(memberAttributes[1]);
                int dmg = Integer.parseInt(memberAttributes[2]);
                int speed = Integer.parseInt(memberAttributes[3]);
                MoveDetails[] moveDetails = moveDetails(memberAttributes);
                // All party members are always not opponents so that why false is hard coded
                res[i - 1] = new BattleCharacterDetails(name, maxHp, dmg, speed, false, moveDetails);
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Could not find file");
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }

        return res;

    }

    private @NotNull MoveDetails[] moveDetails(String[] memberAttributes) {

        return getMoveDetails(getMoveNames(memberAttributes));

    }

    private @NotNull String[] getMoveNames(String[] memberAttributes) {
        String[] names = new String[NUMBER_OF_MOVES_PER_CHARACTER];

        System.arraycopy(memberAttributes, memberAttributes.length - 2, names,
            0, NUMBER_OF_MOVES_PER_CHARACTER);

        return names;

    }

    private @NotNull MoveDetails[] getMoveDetails(String[] moveNames) {

        // moveNames should have the same length as NUMBER_OF_MOVES_PER_CHARACTER

        MoveDetails[] details = new MoveDetails[NUMBER_OF_MOVES_PER_CHARACTER];

        for (int i = 0; i < NUMBER_OF_MOVES_PER_CHARACTER; i++) {

            details[i] = moveDataAccess.getMoveDetails(moveNames[i]);

        }

        return details;

    }

    private int getColumnNumber(String rowName) {
        return switch (rowName) {
            case StatConstants.MAX_HP -> 1;
            case StatConstants.DAMAGE -> 2;
            case StatConstants.SPEED -> 3;
            default -> -1;
        };

    }
}
