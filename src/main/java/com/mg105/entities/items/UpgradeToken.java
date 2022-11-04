package com.mg105.entities.items;

import com.mg105.entities.Item;
import com.mg105.utils.ItemConstants;

public class UpgradeToken extends Item {

    /**
     * An item that is used upgrade a characters stat
     *
     * <p>One upgrade token is equal to a boost a single stat of a single character</p>
     */

    public UpgradeToken() {
        super(ItemConstants.UPGRADE_TOKEN_NAME, ItemConstants.UPGRADE_TOKEN_DESCRIPTION);
    }
}
