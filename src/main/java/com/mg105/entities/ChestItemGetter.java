package com.mg105.entities;

public class ChestItemGetter {

    public ChestItemGetter() {


    }

    /**
     * Adds the item in an adjacent chest (if one exists) to the inventory,
     * then notifies DisplayText to show the user text describing what happened.
     */
    public void getChestItem() {

        // there are issues here with static and nonstatic methods that I don't fully understand how to resolve lmao

        TreasureChest chest = ChestVerifier.verifyChest();
        // will this be an issue if the chest is null??

        if (chest != null) {
            boolean failure = InventoryInteractor.isInventoryFull();
            if (!failure){
                Item reward = chest.open();
                InventoryInteractor.addItem(reward);
                TextGenerator.generateText("chest", reward);
                // get TextGenerator to write something about how a chest was opened and this item was added to inventory
            }
            else {
                TextGenerator.generateText("inventory full");
                // get TextGenerator to write something about how the chest can't be opened because the inventory is full

            }

        }

        //problem??? what if there are multiple adjacent chests? how do we stop that from happening?

    }


}
