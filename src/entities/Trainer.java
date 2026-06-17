package entities;

import items.Consumable;
import items.TrainerItem;

import java.util.ArrayList;

public class Trainer { // classe do jogador inicializada no inicio do jogo
    private String nome;
    private String sexo;
    private String coins;
    protected ArrayList<TrainerItem> itemsBag; // items da mochila do treinador
    protected Pokemon pokemonInUse; // pokemon atual do jogador
    private int gymBadge; // contador de crachãs dos ginásios.


    public Trainer(String nome, String sexo, String coins,  Pokemon pokemonInUse) {
        this.nome = nome;
        this.sexo = sexo;
        this.coins = coins;
        this.pokemonInUse = pokemonInUse;
        this.gymBadge = 0; //inicia a 0, à medida que o jogador ganhe batalhas nos gyms, o contador gymBadge incrementa
        this.itemsBag = new ArrayList<TrainerItem>(); // mochila vazia

        System.out.println(this.nome + " foi criado com sucesso!\n");
    }

    public void showDetails() {
        System.out.println("*********** Trainer Details **************");
        System.out.println("Nome : " + this.nome);
        System.out.println("Sexo : " + this.sexo);
        System.out.println("Coins : " + this.coins);
        System.out.println("Pokemon In Use: " + this.pokemonInUse);
        System.out.println("Gym Badge : " + this.gymBadge);
        System.out.println("*********** ***************");
    }
}



