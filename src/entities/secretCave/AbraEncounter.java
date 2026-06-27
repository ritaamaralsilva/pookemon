package entities.secretCave;

import entities.StatusEffect; // enum
import items.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Representa um evento aleatório de benefício na zona da SecretCave: o encontro com um Abra.
 * <p>
 * Sendo o Abra um Pookémon associado ao teletransporte e à raridade, este evento funciona
 * como uma recompensa de exploração (<i>Lucky Drop</i>). Quando despoletado, o sistema gera
 * uma quantia massiva de moedas e sorteia um conjunto de itens de tier elevado a partir de
 * uma tabela de drops exclusivos.
 * </p>
 */
public class AbraEncounter {
    private int coinsDropped;

    /**
     * Construtor que instancia o evento e calcula de imediato o prémio monetário base.
     * <p>
     * O valor das moedas caídas ({@code coinsDropped}) é parametrizado dinamicamente via {@link Random},
     * garantindo um intervalo flutuante e equilibrado **entre 1500 e 2500 moedas**.
     * </p>
     */
    public AbraEncounter() {
        Random random = new Random();
        // Gera coins aleatórias (entre 1500 a 2500)
        this.coinsDropped = 1500 + random.nextInt(1001);
    }

    /**
     * Obtém o valor total de moedas deixado para trás pelo Abra em fuga.
     *
     * @return O valor inteiro guardado no atributo {@link #coinsDropped}.
     */
    public int getCoinsDropped() {
        return this.coinsDropped;
    }

    /**
     * Instancia o catálogo de itens do Abra e extrai uma sublista de itens sorteados sem repetição de índice.
     * <p>
     * O método povoa uma lista temporária com consumíveis raros (incluindo o exclusivo <i>Champion Elixir</i>
     * e os artefactos de alteração de estado <i>Chaos Orb</i> e <i>Doom Orb</i>). Em seguida, baralha o vetor
     * recorrendo a {@link Collections#shuffle(java.util.List)} e filtra a quantidade requisitada pelo motor
     * de jogo, protegendo o fluxo contra transbordo de tamanho (Index Out of Bounds).
     * </p>
     *
     * @param numberOfItemsToDrop A quantidade máxima de artigos que o jogador conseguiu resgatar no encontro.
     * @return Um {@link ArrayList} preenchido polimorficamente com as instâncias de {@link TrainerItem} sorteadas.
     */
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
