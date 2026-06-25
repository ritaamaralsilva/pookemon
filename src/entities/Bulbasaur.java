package entities;

import game.FileTools;

import java.io.FileNotFoundException;

import static java.lang.Thread.sleep;

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

    @Override
    public Pokemon evolve() {
        if (this.getLevel() >= 16) { // quando bulbasaur chega a nivel 16, evolui para ivysaur, mas os stats do bulbasaur passam para esta evolucao, o que vai mudar depois é o special attack que o ivysaur tem especifico na subclasse dele
            try {
                FileTools.printFile("resources/art/starters/ivysaur.txt");
                sleep(1500);
            } catch (FileNotFoundException | InterruptedException e) {
                System.out.println("Aviso: Imagem do Ivysaur não encontrada.");
            }
            //System.out.println(" Bulbasaur evoluiu para IVYSAUR! ");
            Ivysaur ivysaur = new Ivysaur( //  construtor do ivysaur e o que ele herda do bulbasaur
                    this.getMaxHp() + 15, // boost de vida ao evoluir
                    this.getHp() + 15,
                    this.getStrength() + 15, // boost de força (ataque normal)
                    this.getSpecialAttack() + 15, // boost de special attack
                    this.getLevel(),
                    this.getExp()
            );
            ivysaur.resetSpecialAttackUses(); // começa com PP a cheio
            return ivysaur;
        }
        return null;
    }
}
