package items;

import entities.Pokemon;

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

    @Override
    public void use(Pokemon target) {
        if (healAmount > 0) target.heal(healAmount);
        if (strengthBoost > 0) target.boostStrength(strengthBoost);
        if (speedBoost) target.setSpeedBoost(true);
        if (levelUp) target.levelUp();
        System.out.println("Usaste " + getName() + "!");
    }

    public int getStrengthBoost() { return strengthBoost; }
    public boolean hasSpeedBoost() { return speedBoost; }
}