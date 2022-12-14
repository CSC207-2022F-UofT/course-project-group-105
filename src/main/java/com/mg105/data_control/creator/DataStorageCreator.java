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
 * Really the only reason for this class is get rid of the duplicate code warnings (the CreateDataStorage)
 * interface is really enough for OOP
 */

public abstract class DataStorageCreator implements CreateDataStorage {

    @NotNull
    private final String path;

    @NotNull
    private final String[][] initialData;

    /**
     * Creates a new instance of DataStorageCreator
     *
     * @param path        the file path to create data to
     * @param initialData the inital data to create
     */

    public DataStorageCreator(@NotNull String path, @NotNull String[][] initialData) {
        this.path = path;
        this.initialData = initialData;
    }

    /**
     * Creates a data storage system for the specific aspect of the game this class pertains to
     */

    @Override
    public void createDataStorage() {
        File file = new File(this.path);

        try {

            // true iff new file created
            if (file.createNewFile()) {

                CSVWriter writer = new CSVWriter(new FileWriter(this.path), ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

                writer.writeAll(Arrays.stream(initialData).toList());
                writer.flush();
                writer.close();

            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
