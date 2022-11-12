package com.mg105.use_cases;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.GameState;
import com.mg105.entities.Inventory;

public class ReplayGenerator {

    //   No need for constructor as we would not initialize an instance for ReplayGenerator

    public BattleCharacter AttributeInheritance(BattleCharacter battleCharacter)
    {
        BattleCharacter reincarnation_character = new BattleCharacter(battleCharacter.getHp(), battleCharacter.getName(), battleCharacter.getDmg(), battleCharacter.getSpeed(), battleCharacter.getMoveOne(), battleCharacter.getMoveTwo());

        return reincarnation_character;
    }

    public void InventoryClean(Inventory inventory)
    {
        inventory.removeAll();
    }

    public void Replay(GameState state, BattleCharacter battleCharacter)
    {
    // incomplete remake, need to amend later. exactly implementation should depend on other use cases' implementation
        MapGenerator isekai = new MapGenerator(state);
        BattleCharacter reincarnationPlayer = AttributeInheritance(battleCharacter);
    }
}
