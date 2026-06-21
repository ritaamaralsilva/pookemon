package entities;

public class Venusaur extends Pokemon{ // evolucao do ivysaur nivel 36
    public Venusaur(int maxHp, int hp, int strength, int specialAttack, int level, int exp) {
        super("Venusaur", maxHp, hp, strength, specialAttack,
                1 + (level / 10),
                1 + (level / 10),
                level, exp);
    }

    public int applySpecialAttack (Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2 + 20;
        int healAmount = damage / 2 + 20;
        this.heal(healAmount);
        System.out.println(this.getName() + " usou Solar Beam! Absorveu " + healAmount + " HP de " + enemy.getName() + "!");
        return damage;
    }
}

