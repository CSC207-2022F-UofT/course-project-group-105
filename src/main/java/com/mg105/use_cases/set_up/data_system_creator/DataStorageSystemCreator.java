package com.mg105.use_cases.set_up.data_system_creator;

import org.jetbrains.annotations.NotNull;

/**
 * This is a class that uses other class to create the data persistence or data storage system of this
 * game.
 * This class uses a facade and builder like pattern
 */

public class DataStorageSystemCreator {

    @NotNull private final CreateDataStorage[] builders;

    /**
     * Creates a new instance of DataStorageSystemCreator
     * @param builders a group of objects that help build certain parts of the data storage system
     */
    public DataStorageSystemCreator(@NotNull CreateDataStorage[] builders){
        this.builders = builders;
    }

    public void create(){
        for(CreateDataStorage builder: this.builders){
            builder.createDataStorage();
        }
    }

}
