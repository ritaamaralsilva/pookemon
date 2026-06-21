package entities;

import java.util.Random;

public class Charizard extends Pokemon { // evolucao do charmeleon no nivel 36
    public Charizard(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp);
    }

    @Override
    public int applySpecialAttack(Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2 + 20;
        if (new Random().nextInt(2) == 0) { // 50% — Fire Blast mais potente
            enemy.setStatusEffect(StatusEffect.BURNED);
            System.out.println(this.getName() + " usou Fire Blast! " + enemy.getName() + " ficou em chamas!");
        } else {
            System.out.println(this.getName() + " usou Fire Blast!");
        }
        return damage;
    }
}

