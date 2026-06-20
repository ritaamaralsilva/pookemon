package entities;

import items.TrainerItem;
import java.util.ArrayList;
import java.util.Collections; // para usar o shuffle

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
        ArrayList<TrainerItem> randomItems = new ArrayList<>(availableItemsToBuy);
        Collections.shuffle(randomItems);
        // subList(0, 5) pega nos primeiros 5 após o shuffle
        // como a lista foi baralhada, os primeiros 5 são sempre diferentes a cada visita
        if (randomItems.size() >= 5) {
            return new ArrayList<>(randomItems.subList(0, 5));
        }
        return randomItems;
    }

    public String getName() {
        return name;
    }
}
