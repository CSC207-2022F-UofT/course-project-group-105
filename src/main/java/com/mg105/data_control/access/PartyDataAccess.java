package com.mg105.data_control.access;

import com.mg105.use_cases.outputds.BattleCharacterDetails;
import com.mg105.use_cases.outputds.MoveDetails;
import com.mg105.use_cases.save.PartyDataInterface;
import com.mg105.utils.PartyConstants;
import com.mg105.utils.StatConstants;
import com.opencsv.*;
import com.opencsv.exceptions.CsvException;
import org.jetbrains.annotations.NotNull;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.mg105.utils.DataAccessConstants.PARTY_DATA_PATH;

/**
 * PartyDataAccess implements the data access layer.
 */
public class PartyDataAccess implements PartyDataInterface {

    private final static int NUMBER_OF_MOVES_PER_CHARACTER = 2;
    private final MoveDataAccess moveDataAccess;

    /**
     * Creates a new instance of PartyDataAccess
     *
     * @param moveDataAccess an object that can access data about usable moves
     */

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
            CSVReader reader = new CSVReaderBuilder(new FileReader(PARTY_DATA_PATH)).withCSVParser(parser).build();
            List<String[]> partyStats = reader.readAll();

            // Setting which value to change in the file by reading

            for (String[] memberStats : partyStats) {
                if (memberStats[0].equals(name)) {
                    int i = getColumnNumber(stat);
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
            CSVWriter writer = new CSVWriter(new FileWriter(PARTY_DATA_PATH), ',',
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
            CSVReader reader = new CSVReaderBuilder(new FileReader(PARTY_DATA_PATH)).withCSVParser(parser).build();
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

    /**
     * Returns objects representing the moves a party member has
     *
     * @param memberAttributes an array of attributes a party member has
     * @return objects representing the moves a party member has
     * @see MoveDetails
     */

    private @NotNull MoveDetails[] moveDetails(String[] memberAttributes) {

        return getMoveDetails(getMoveNames(memberAttributes));

    }

    /**
     * Returns the moves a party member has from a given array of their attributes
     *
     * @param memberAttributes an array of attributes of a party member
     * @return an array of move names
     */
    private @NotNull String[] getMoveNames(String[] memberAttributes) {
        String[] names = new String[NUMBER_OF_MOVES_PER_CHARACTER];

        System.arraycopy(memberAttributes, memberAttributes.length - 2, names,
            0, NUMBER_OF_MOVES_PER_CHARACTER);

        return names;

    }

    /**
     * Returns an array of objects representing moves based on the given move name
     *
     * @param moveNames an array of move names
     * @return an array of objects representing moves
     * @see MoveDetails
     */
    private @NotNull MoveDetails[] getMoveDetails(String[] moveNames) {

        // moveNames should have the same length as NUMBER_OF_MOVES_PER_CHARACTER

        MoveDetails[] details = new MoveDetails[NUMBER_OF_MOVES_PER_CHARACTER];

        for (int i = 0; i < NUMBER_OF_MOVES_PER_CHARACTER; i++) {

            details[i] = moveDataAccess.getMoveDetails(moveNames[i]);

        }

        return details;

    }

    /**
     * Returns the appropriate column number based on the desired stat
     * returns -1 iff provided an invalid stat
     *
     * @param rowName the desired stat
     * @return an integer representing the column that stat is located in the "database"
     */

    private int getColumnNumber(String rowName) {
        return switch (rowName) {
            case StatConstants.MAX_HP -> 1;
            case StatConstants.DAMAGE -> 2;
            case StatConstants.SPEED -> 3;
            default -> -1;
        };

    }
}
