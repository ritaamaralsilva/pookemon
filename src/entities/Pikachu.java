package entities;

import game.FileTools;

import java.io.FileNotFoundException;

import static java.lang.Thread.sleep;

public class Pikachu extends Pokemon{

    public Pikachu(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp) {

        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp);
    }

    // Thunder Wave — paralisa o inimigo sem causar dano
    // estratégico: sacrificas o dano para garantir que o inimigo perde turnos
    public int applySpecialAttack(Pokemon enemy) {
        System.out.println(this.getName() + " usou Thunder Wave!");

        if (enemy.getStatusEffect() != null) {
            System.out.println(enemy.getName() + " já tem um status effect — Thunder Wave falhou!");
            return 0; // não causa dano nem aplica efeito
        }

        enemy.setStatusEffect(StatusEffect.PARALYZED);
        System.out.println(enemy.getName() + " está paralisado e pode perder turnos!");
        return 0; // thunder wave não causa dano direto
    }

    @Override
    public Pokemon evolve() {
        if (this.getLevel() == 22) { // quando pikachu chega a nivel 22 evolui para Raichu
            try {
                FileTools.printFile("resources/art/starters/raichu.txt");
                sleep(1500);
            } catch (FileNotFoundException | InterruptedException e) {
                System.out.println("Aviso: Imagem do Raichu não encontrada.");
            }

            Raichu raichu = new Raichu( //  construtor do raichu e o que ele herda do pikachu
                    this.getMaxHp() + 15, // boost de vida ao evoluir
                    this.getHp() + 15,
                    this.getStrength() + 15, // boost de força (ataque normal)
                    this.getSpecialAttack() + 15, // boost de special attack
                    this.getLevel(),
                    this.getExp()
            );
            raichu.resetSpecialAttackUses(); // começa com PP a cheio
            return raichu;
        }
        return null;
    }
}
