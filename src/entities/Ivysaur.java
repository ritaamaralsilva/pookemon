package entities;

public class Ivysaur extends Pokemon { // evolucao do bulbasaur nivel 16
    public Ivysaur(int maxHp, int hp, int strength, int specialAttack, int level, int exp) {
        super("Ivysaur", maxHp, hp, strength, specialAttack,
                1 + (level / 10),
                1 + (level / 10),
                level, exp);
    }

    @Override
    public int applySpecialAttack(Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2 + 10;
        int healAmount = damage / 2;
        this.heal(healAmount);
        System.out.println(this.getName() + " usou Razor Leaf! Absorveu " + healAmount + " HP de " + enemy.getName() + "!");
        return damage;
    }

    @Override
    public Pokemon evolve() {
        if (this.getLevel() == 32) {
            System.out.println("✨ Ivysaur evoluiu para VENUSAUR! ✨");
            Venusaur venusaur = new Venusaur( //  construtor do ivysaur e o que ele herda do bulbasaur
                    this.getMaxHp() + 20,
                    this.getHp() + 20,
                    this.getStrength() + 20,
                    this.getSpecialAttack() + 20,
                    this.getLevel(),
                    this.getExp()
            );
            venusaur.resetSpecialAttackUses();
            return venusaur;
        }
        return null;
    }
}
