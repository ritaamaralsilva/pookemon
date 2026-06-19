package entities;

import items.TrainerItem;
import java.util.ArrayList;
import java.util.Random;

public class PokeShop {
    private String name;
    private ArrayList<TrainerItem> availableItemsToBuy;

    public PokeShop(String name) {
        this.name = name;
        this.availableItemsToBuy = new  ArrayList<>(); // array vazio de itens da loja
    }

    public void addItem(TrainerItem item) {
        availableItemsToBuy.add(item); // adiciona item a lista de artigos disponiveis para venda
    }

    // devolve até 10 itens aleatórios sem repetição
    public ArrayList<TrainerItem> getRandomItems() {
        ArrayList<TrainerItem> randomItems = new ArrayList<>();
        ArrayList<TrainerItem> temporaryListOfItems = new ArrayList<>(availableItemsToBuy);
        Random random = new Random();

        int limit = 10;
        if (temporaryListOfItems.size() < 10) limit = temporaryListOfItems.size();

        for (int i = 0; i < limit; i++) {
            int index = random.nextInt(temporaryListOfItems.size());
            randomItems.add(temporaryListOfItems.get(index));
            temporaryListOfItems.remove(index);
        }
        return randomItems;
    }

    public String getName() {
        return name;
    }
}
