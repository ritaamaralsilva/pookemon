package entities;

import java.util.Random;

public class Wartotle extends Pokemon{ // evolucao do squirtle no nivel 16
    public Wartotle(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp);
    }

    @Override
    public int applySpecialAttack(Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2;
        if (new Random().nextInt(3) == 0) {
            enemy.revertStrength(10);
            System.out.println(this.getName() + " usou Bubble Beam! " + enemy.getName() + " perdeu 10 de força!");
        } else {
            System.out.println(this.getName() + " usou Bubble Beam!");
        }
        return damage;
    }
}
