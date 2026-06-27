package entities;

import items.TrainerItem;
import java.util.ArrayList;
import java.util.Collections; // para usar o shuffle

/**
 * Representa a Pokéshop (loja do jogo) onde o utilizador pode adquirir mantimentos.
 * <p>
 * Esta classe gere o catálogo global de artigos disponíveis para comercialização e
 * implementa uma mecânica de rotação de stock. Através de um algoritmo de baralhamento,
 * a loja expõe uma montra dinâmica de itens a cada interação, simulando uma experiência
 * de mercado orgânica e imprevisível.
 * </p>
 */
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

    /**
     * Gera e devolve uma montra rotativa contendo até 5 itens selecionados de forma pseudoaleatória.
     * <p>
     * O método duplica a lista mestre, aplica o algoritmo de permutação {@link Collections#shuffle(java.util.List)}
     * e extrai uma sublista estável dos primeiros 5 elementos. Este comportamento garante que o stock
     * visível mude dinamicamente a cada visita do jogador, prevenindo a monotonia mercantil.
     * </p>
     *
     * @return Um {@link ArrayList} com 5 itens baralhados, ou a lista total se o catálogo for menor que 5.
     */
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

    /**
     * Obtém o nome descritivo da loja.
     *
     * @return O valor textual armazenado no atributo {@link #name}.
     */
    public String getName() {
        return name;
    }
}
