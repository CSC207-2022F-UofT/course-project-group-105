package com.mg105.utils;


import com.mg105.entities.items.HealthPotion;
import com.mg105.entities.items.MegaPotion;

/**
 * A class containing the constants for items in the game.
 */
public class ItemConstants {

    /**
     * The constant for the name of UpgradeToken.
     */
    public static final String UPGRADE_TOKEN_NAME = "Upgrade Token";

    /**
     * The constant for the description of UpgradeToken.
     */
    public static final String UPGRADE_TOKEN_DESCRIPTION = "One use increases all stats on " +
        "a specific character by one.";

    /**
     * The constant for the name of HealthPotion.
     */

    public static final String HEALTH_POTION_NAME = "Health Potion";
    public static final String HEALTH_POTION_DESCRIPTION = "This potion will heal a character by " +
        HealthPotion.getHealingPoints() + " points";

    /**
     * The constant for the name of MegaPotion.
     */
    public static final String MEGA_POTION_NAME = "Mega Health Potion";

    public static final String MEGA_POTION_DESCRIPTION = "This potion will heal a character by " +
        MegaPotion.getHealingPoints() + " points";

    // Names and items should be parallel list that where index of the name has the same index in the description
    /**
     * The constant for the list of all item names.
     */
    public static final String[] ALL_ITEM_NAMES = {UPGRADE_TOKEN_NAME, HEALTH_POTION_NAME, MEGA_POTION_NAME};

    /**
     * The constant for the list of all item descriptions.
     */
    public static final String[] ALL_ITEM_DESCRIPTIONS = {UPGRADE_TOKEN_DESCRIPTION, HEALTH_POTION_DESCRIPTION, MEGA_POTION_DESCRIPTION};

    /**
     * The constant for the list of all usable item names.
     */
    public static final String[] ALL_USABLE_ITEM_NAMES = {UPGRADE_TOKEN_NAME, HEALTH_POTION_NAME, MEGA_POTION_NAME};

}
