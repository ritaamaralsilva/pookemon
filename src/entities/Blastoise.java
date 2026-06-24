package entities;

import java.util.Random;

public class Blastoise extends Pokemon {
    public Blastoise(int maxHp, int hp, int strength, int specialAttack, int level, int exp) {
        super("Blastoise", maxHp, hp, strength, specialAttack,
                1 + (level / 10),
                1 + (level / 10),
                level, exp);
    }

    public int applySpecialAttack (Pokemon enemy) {
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

