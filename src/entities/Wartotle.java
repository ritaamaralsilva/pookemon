package entities;

import game.FileTools;

import java.io.FileNotFoundException;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Wartotle extends Pokemon{ // evolucao do squirtle no nivel 16
    public Wartotle(int maxHp, int hp, int strength, int specialAttack, int level, int exp) {
        super("Wartotle", maxHp, hp, strength, specialAttack,
                1 + (level / 10),
                1 + (level / 10),
                level, exp);
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

    @Override
    public Pokemon evolve() {
        if (this.getLevel() == 32) { // quando Wartotle chega a nivel 32 evolui para Blastoise
            try {
                FileTools.printFile("resources/art/starters/blastoise.txt");
                sleep(1500);
            } catch (FileNotFoundException | InterruptedException e) {
                System.out.println("Aviso: Imagem do Blastoise não encontrada.");
            }

            Blastoise blastoise = new Blastoise(
                    this.getMaxHp() + 20, // boost de vida ao evoluir
                    this.getHp() + 20,
                    this.getStrength() + 25, // boost de força (ataque normal)
                    this.getSpecialAttack() + 15, // boost de special attack
                    this.getLevel(),
                    this.getExp()
            );
            blastoise.resetSpecialAttackUses(); // começa com PP a cheio
            return blastoise;
        }
        return null;
    }
}
