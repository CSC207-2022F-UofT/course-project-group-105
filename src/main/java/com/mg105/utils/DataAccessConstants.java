package com.mg105.utils;

/**
 * Constants used for data persistence.
 */
public class DataAccessConstants {
    /**
     * The folder where data is stored.
     */
    public final static String BASE_PATH = System.getProperty("user.dir") + System.getProperty("file.separator");

    /**
     * The file where move data is stored
     */
    public final static String MOVE_DATA_PATH = BASE_PATH + "move_data.csv";

    /**
     * the file where party data is stored
     */
    public final static String PARTY_DATA_PATH = BASE_PATH + "party_data.csv";
    /**
     * The string representation of true
     */
    public final static String IS_TRUE = "TRUE";
    /**
     * The string representation of false
     */
    public final static String IS_FALSE = "FALSE";
}
