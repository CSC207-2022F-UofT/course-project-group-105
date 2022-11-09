package com.mg105.entities;

public class ChestItemGetter {

    public ChestItemGetter() {


    }

    public void getChestItem() {
        /**
         * Adds the item in an adjacent chest (if one exists) to the inventory,
         * then notifies DisplayText to show the user text describing what happened.
         */
        TreasureChest chest = ChestVerifier.verifyChest();

        if (chest != null) {
            Item reward = chest.open();
            InventoryInteractor.addItem(reward);
            TextGenerator.generateText('chest', reward);
            //get TextGenerator to write something about how a chest was opened and this item was added to inventory
        }

        //problem??? what if there are multiple adjacent chests? how do we stop that from happening?

    }


}
