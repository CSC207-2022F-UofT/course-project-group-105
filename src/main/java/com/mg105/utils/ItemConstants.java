package com.mg105.utils;


import com.mg105.entities.items.HealthPotion;

public class ItemConstants {

    public static final String UPGRADE_TOKEN_NAME = "Upgrade Token";
    public static final String UPGRADE_TOKEN_DESCRIPTION = "One use increases all stats on " +
        "a specific character by one.";

    public static final String HEALTH_POTION_NAME = "Health Potion";
    public static final String HEALTH_POTION_DESCRIPTION = "This potion will heal a character by" +
        String.valueOf(HealthPotion.getHealingPoints()) + "points";

    // Names and items should be parallel list that where index of the name has the same index in the description
    public static final String[] ALL_ITEM_NAMES = {UPGRADE_TOKEN_NAME, HEALTH_POTION_NAME};

    public static final String[] ALL_ITEM_DESCRIPTIONS = {UPGRADE_TOKEN_DESCRIPTION, HEALTH_POTION_DESCRIPTION};

    public static final String[] ALL_USABLE_ITEM_NAMES = {UPGRADE_TOKEN_NAME, HEALTH_POTION_NAME};

}
