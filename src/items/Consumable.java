package items;

import entities.Pokemon;

/**
 * Representa itens utilitários avançados de alteração estatística ou progressão de nível.
 * <p>
 * Estende {@link TrainerItem} e atua como uma classe de efeitos múltiplos. Conforme os
 * parâmetros injetados na sua construção, pode funcionar como uma fruta restauradora,
 * um catalisador de atributos temporários/permanentes (X Attack, X Speed) ou um item de
 * avanço evolutivo forçado (Rare Candy).
 * </p>
 */
public class Consumable extends TrainerItem {
    private int healAmount;      // para Berries e consumiveis do genero (0 se não aplicável)
    private int strengthBoost;   // para X Attack
    private boolean speedBoost;  // para X Speed (pokemon ataca sempre primeiro, mesmo que tenha nivel mais baixo)
    private boolean levelUp;     // para Rare Candy (pokemon sobe 1 nivel)
    private int decreaseStrength; // para o X Defense , neste caso, este consumivel vai reduzir o strenth do pokemon inimigo
    private int decreaseSpecialAttack; // para o X-Take Special, neste caso o consumivel reduz ataque especial do pokemon inimigo
    private int SpecialAttackBoost; // para o X-Special, incrementa ao special attack do pokemon do jogador

    public Consumable(String name, int price, int healAmount, int strengthBoost, boolean speedBoost, boolean levelUp) {
        super(name, price);
        this.healAmount = healAmount;
        this.strengthBoost = strengthBoost;
        this.speedBoost = speedBoost;
        this.levelUp = levelUp;
    }

    /**
     * Executa de forma sequencial todos os gatilhos estatísticos ativos configurados para este item.
     * <p>
     * O método analisa quais as propriedades ativas (maiores que zero ou verdadeiras) e despacha
     * as mutações diretamente para a API de métodos da instância alvo, como {@code target.levelUp()}
     * ou {@code target.boostStrength()}.
     * </p>
     *
     * @param target O {@link Pokemon} recetor das mutações estatísticas.
     */
    @Override
    public void use(Pokemon target) {
        if (healAmount > 0) target.heal(healAmount);
        if (strengthBoost > 0) target.boostStrength(strengthBoost);
        if (speedBoost) target.setSpeedBoost(true);
        if (levelUp) target.levelUp();
        System.out.println("Usaste " + getName() + "!");
    }

    /** @return O modificador de incremento de força física deste consumível. */
    public int getStrengthBoost() { return strengthBoost; }
    /** @return Verdadeiro se o item contiver o efeito de prioridade de velocidade; falso caso contrário. */
    public boolean hasSpeedBoost() { return speedBoost; }
}