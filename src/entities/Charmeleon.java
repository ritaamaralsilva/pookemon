package entities;

import java.util.Random;

public class Charmeleon extends Pokemon{ // evolucao do charmander no nivel 16
    public Charmeleon(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp);
    }

    @Override
    public int applySpecialAttack(Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2 + 10;
        if (new Random().nextInt(3) == 0) {
            enemy.setStatusEffect(StatusEffect.BURNED);
            System.out.println(this.getName() + " usou Flamethrower! " + enemy.getName() + " ficou em chamas!");
        } else {
            System.out.println(this.getName() + " usou Flamethrower!");
        }
        return damage;
    }
}

