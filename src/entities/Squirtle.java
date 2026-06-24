package entities;

import game.FileTools;

import java.io.FileNotFoundException;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Squirtle extends Pokemon {
    public Squirtle(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft,  int level, int exp) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp);
    }

    public int applySpecialAttack(Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2;
        Random random = new Random();
        if (random.nextInt(3) == 0) { // 33% de chance de baixar strength do inimigo
            enemy.revertStrength(5);
            System.out.println(enemy.getName() + " perdeu 5 de Strength!");
        }
        System.out.println(this.getName() + " usou Water Gun!");
        return damage;
    }

    @Override
    public Pokemon evolve() {
        if (this.getLevel() == 16) { // quando squirtle chega a nivel 16 evolui para Wartotle
            try {
                FileTools.printFile("resources/art/starters/wartotle.txt");
                sleep(1500);
            } catch (FileNotFoundException | InterruptedException e) {
                    System.out.println("Aviso: Imagem do Wartotle não encontrada.");
            }

                Wartotle wartotle = new Wartotle(
                    this.getMaxHp() + 15, // boost de vida ao evoluir
                    this.getHp() + 15,
                    this.getStrength() + 15, // boost de força (ataque normal)
                    this.getSpecialAttack() + 15, // boost de special attack
                    this.getLevel(),
                    this.getExp()
            );
            wartotle.resetSpecialAttackUses(); // começa com PP a cheio
            return wartotle;
        }
        return null;
    }

}
