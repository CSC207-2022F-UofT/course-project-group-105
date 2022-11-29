package com.mg105.data_access;

import com.mg105.outputds.BattleCharacterDetails;
import com.mg105.outputds.MoveDetails;
import com.mg105.use_cases.PartyDataInterface;
import com.mg105.utils.StatConstants;
import com.opencsv.*;
import com.opencsv.exceptions.CsvException;
import org.jetbrains.annotations.NotNull;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PartyDataAccess implements PartyDataInterface {

    private final static String BASE_PATH = "src/main/java/com/mg105/database/";
    private final static String PARTY_STATS_FILE = BASE_PATH + "party_stats.csv";
    private final static String MOVE_STATS_FILE = BASE_PATH + "move_stats.csv";
    private final static int NUMBER_OF_MOVES = 2;


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
            CSVReader reader = new CSVReaderBuilder(new FileReader(PARTY_STATS_FILE)).withCSVParser(parser).build();
            List<String[]> partyStats = reader.readAll();

            // Setting which value to change in the file by reading

            for (String[] memberStats : partyStats) {
                System.out.println(memberStats[0]);
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

                }
            }

            reader.close();

            // Changing that value in the file
            CSVWriter writer = new CSVWriter(new FileWriter(PARTY_STATS_FILE), ',',
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
        // THIS IS NOT THE IMPLEMENTATION
        MoveDetails f = new MoveDetails("test", 0, 0, true);
        MoveDetails s = new MoveDetails("test", 0, 0, true);
        BattleCharacterDetails[] cd = {new BattleCharacterDetails("test", 0, 0, 0, f, s)};
        return cd;
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
