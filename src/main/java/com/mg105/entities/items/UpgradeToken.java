package com.mg105.entities.items;

import com.mg105.entities.Item;
import com.mg105.utils.ItemConstants;

public class UpgradeToken extends Item {

    // Item used to upgrade a character's stat by one

    public UpgradeToken() {
        super(ItemConstants.UPGRADE_TOKEN_NAME, ItemConstants.UPGRADE_TOKEN_DESCRIPTION);
    }
}
