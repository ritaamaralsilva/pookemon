package entities;

import java.util.Random;

public class Charmeleon extends Pokemon{ // evolucao do charmander no nivel 16
    public Charmeleon(int maxHp, int hp, int strength, int specialAttack, int level, int exp) {
        super("Charmeleon", maxHp, hp, strength, specialAttack,
                1 + (level / 10),
                1 + (level / 10),
                level, exp);
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
    @Override
    public Pokemon evolve() {
        if (this.getLevel() == 32) {
            System.out.println(" Charmeleon evoluiu para CHARIZARD! ");
            Charizard charizard = new Charizard (
                    this.getMaxHp() + 20,
                    this.getHp() + 20,
                    this.getStrength() + 20,
                    this.getSpecialAttack() + 20,
                    this.getLevel(),
                    this.getExp()
            );
            charizard.resetSpecialAttackUses();
            return charizard;
        }
        return null;
    }
}

