package entities.secretCave;

import entities.StatusEffect; // enum
import items.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class AbraEncounter {
    private int coinsDropped;

    public AbraEncounter() {
        Random random = new Random();
        // Gera coins aleatórias (entre 1500 a 2500)
        this.coinsDropped = 1500 + random.nextInt(1001);
    }

    public int getCoinsDropped() {
        return this.coinsDropped;
    }

    public ArrayList<TrainerItem> getDroppedItems(int numberOfItemsToDrop) {
        ArrayList<TrainerItem> listOfAbraItems = new ArrayList<>();


        listOfAbraItems.add(new Consumable("Rare Candy", 800, 0, 0, false, true));
        listOfAbraItems.add(new Consumable("Rare Candy", 800, 0, 0, false, true));
        listOfAbraItems.add(new Consumable("Champion Elixir", 8000, 999, 50, true, true));
        listOfAbraItems.add(new BattleConsumable("Chaos Orb", 1500, StatusEffect.PARALYZED));
        listOfAbraItems.add(new Potion("Max Potion", 1000, 100));
        listOfAbraItems.add(new BattleConsumable("Doom Orb", 2000, StatusEffect.ASLEEP));

        Collections.shuffle(listOfAbraItems);

        ArrayList<TrainerItem> abraItemsFind = new ArrayList<>();
        for (int i = 0; i < numberOfItemsToDrop && i < listOfAbraItems.size(); i++) {
            abraItemsFind.add(listOfAbraItems.get(i));
        }

        return abraItemsFind;
    }
}
