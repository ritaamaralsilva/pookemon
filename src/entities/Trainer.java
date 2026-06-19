package entities;

import items.Consumable;
import items.TrainerItem;

import java.util.ArrayList;

public class Trainer { // classe do jogador inicializada no inicio do jogo
    private String nome;
    private String sexo;
    private int coins;
    protected ArrayList<TrainerItem> itemsBag; // items da mochila do treinador
    protected Pokemon pokemonInUse; // pokemon atual do jogador
    private int gymBadge; // contador de crachãs dos ginásios.


    public Trainer(String nome, String sexo, int coins,  Pokemon pokemonInUse) {
        this.nome = nome;
        this.sexo = sexo;
        this.coins = 1000; // inicia com 1000 moedas
        this.pokemonInUse = pokemonInUse;
        this.gymBadge = 0; //inicia a 0, à medida que o jogador ganhe batalhas nos gyms, o contador gymBadge incrementa
        this.itemsBag = new ArrayList<TrainerItem>(); // mochila vazia

        System.out.println(this.nome + " foi criado com sucesso!\n");
    }

    public Pokemon getPokemonInUse() {
        return pokemonInUse;
    }

    public void setPokemonInUse(Pokemon pokemonInUse) {
        this.pokemonInUse = pokemonInUse;
    }

    public void addGymBadge() { // metodo para incrementar crachas ao jogador quando ele ganha contra um gym
        this.gymBadge++;
        System.out.println("Tens agora " + gymBadge + " crachá(s)!");
    }

    public void addCoins(int amountCoins) { // metodo para incrementar o dinheiro
        this.coins += amountCoins;
        System.out.println("Recebeste " + amountCoins+ " coins! Total: " + this.coins + " coins.");
    }

    public int getCoins() {
        return coins;
    }

    public void removeCoins(int amountCoins) {
        this.coins -= amountCoins;
    }

    public void addItemToBag(TrainerItem itemsBag) {
        this.itemsBag.add(itemsBag);
    }

    public ArrayList<TrainerItem> getItemsBag() {
        return itemsBag;
    }

    public void showDetails() {
        System.out.println("*********** Trainer Details **************");
        System.out.println("Nome : " + this.nome);
        System.out.println("Sexo : " + this.sexo);
        System.out.println("Coins : " + this.coins);
        System.out.println("Gym Badges : " + this.gymBadge);
        this.pokemonInUse.showDetails();
        System.out.println("*********** ***************");
    }
}



