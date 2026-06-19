package entities;

public class Bulbasaur extends Pokemon {
    public Bulbasaur(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp);
    }

    public int applySpecialAttack (Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2;
        int healAmount = damage / 2; // bulbasaur rouba metade do dano causado
        this.heal(healAmount);
        System.out.println(this.getName() + " usou Leech Seed! Roubou "
                + healAmount + " HP de " + enemy.getName() + ".");
        return damage;
    }

//    @Override
//    public boolean pokemonBattle(Pokemon opponent) {
//        // metodo de ataque do bulbasaur, tem mais defesa, age como um tanque
//        return false;
//    }
}
