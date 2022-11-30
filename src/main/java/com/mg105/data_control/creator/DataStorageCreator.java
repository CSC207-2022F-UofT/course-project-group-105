package com.mg105.data_control.creator;

import com.mg105.use_cases.set_up.data_system_creator.CreateDataStorage;
import com.opencsv.CSVWriter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * This class should be extended for aspect of the game's state that need to have storage systems created
 *
 * Really the only reason for this class is get rid of the duplicate code warnings (the CreateDataStorage)
 * interface is really enough for OOP
 */

public abstract class DataStorageCreator implements CreateDataStorage {

    private final String PATH;

    private final String[][] INITIAL_DATA;

     public DataStorageCreator(@NotNull String path, @NotNull String[][] initialData){
         this.PATH = path;
         this.INITIAL_DATA = initialData;
     }

    /**
     * Creates a data storage system for the specific aspect of the game this class pertains to
     */

    @Override
    public void createDataStorage() {
        File file = new File(this.PATH);

        try {

            // true iff new file created
            if (file.createNewFile()) {

                CSVWriter writer = new CSVWriter(new FileWriter(this.PATH), ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

                writer.writeAll(Arrays.stream(INITIAL_DATA).toList());
                writer.flush();
                writer.close();

            }
        }
        catch(IOException e) {
            e.getStackTrace();
        }
    }
}
