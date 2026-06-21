package entities;

public class Ivysaur extends Pokemon { // evolucao do bulbasaur nivel 16
    public Ivysaur(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp);
    }

    @Override
    public int applySpecialAttack(Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2 + 10;
        int healAmount = damage / 2;
        this.heal(healAmount);
        System.out.println(this.getName() + " usou Razor Leaf! Absorveu " + healAmount + " HP de " + enemy.getName() + "!");
        return damage;
    }
}
