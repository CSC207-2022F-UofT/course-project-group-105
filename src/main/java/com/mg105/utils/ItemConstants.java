package com.mg105.utils;


public class ItemConstants {

    public static final String UPGRADE_TOKEN_NAME = "Upgrade Token";
    public static final String UPGRADE_TOKEN_DESCRIPTION = "One use increases a " + "specific stat on a specific character by one.";

    public static final String HEALTH_POTION_NAME = "Health Potion";
    public static final String HEALTH_POTION_DESCRIPTION = "This potion will heal a character by a certain amount";


    public static String[] allItemNames() {
        String[] names = {UPGRADE_TOKEN_NAME, HEALTH_POTION_NAME};
        return names;
    }
}
