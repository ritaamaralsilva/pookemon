package entities;

import java.util.Random;

public class Blastoise extends Pokemon {
    public Blastoise(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp);
    }

    public int applySpecialAttack (Pokemon enemy) { //evolucao do wartotle no nivel 36
        int damage = this.getSpecialAttack() / 2 + 15;
        if (new Random().nextInt(2) == 0) { // 50% — Hydro Pump mais potente
            enemy.revertStrength(20); // retira 30 de forca ao ataque normal do oponente
            System.out.println(this.getName() + " usou Hydro Pump! " + enemy.getName() + " perdeu 20 de força!");
        } else {
            System.out.println(this.getName() + " usou Hydro Pump!");
        }
        return damage;
    }
}

