package com.mg105.use_cases.set_up.data_system_creator;

/**
 * This is a class that uses other class to create the data persistence or data storage system of this
 * game
 *
 * This facade and builder like pattern
 */

public class DataStorageSystemCreator {

    private final CreateDataStorage[] builders;

    public DataStorageSystemCreator(CreateDataStorage[] builders){
        this.builders = builders;
    }

    public void create(){
        for(CreateDataStorage builder: this.builders){
            builder.createDataStorage();
        }
    }

}
