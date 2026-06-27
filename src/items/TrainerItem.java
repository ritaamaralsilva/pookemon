package items;

import entities.Pokemon;

/**
 * Superclasse abstrata que define a matriz e o contrato comportamental de todos os itens do jogo.
 * <p>
 * Funciona como a fundação polimórfica para o sistema de inventário. Obriga todas as subclasses
 * (como poções ou modificadores de atributos) a implementar o método {@link #use(Pokemon)},
 * permitindo que a mochila do treinador armazene e execute qualquer consumível de forma genérica.
 * </p>
 */
public abstract class TrainerItem {
    private String name;
    private int price;

    public TrainerItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }

    /**
     * Executa o efeito de ativação específico do item sobre uma criatura alvo.
     * <p>
     * Por ser polimórfico, o alvo ({@code target}) pode referir-se tanto a um Pokémon do jogador
     * (cura/buffs) como a um Pokémon inimigo (debuffs/danos), dependendo da subclasse concreta.
     * </p>
     *
     * @param target O {@link Pokemon} que receberá o impacto mecânico ou medicinal do item.
     */
    public abstract void use(Pokemon target);
}