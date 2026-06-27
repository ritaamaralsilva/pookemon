package entities;

import game.FileTools;

import java.io.FileNotFoundException;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Representa a forma inicial da linha evolutiva de tipo Água, o Squirtle.
 * <p>
 * Sendo o primeiro estágio, possui um ataque de assinatura simples ("Water Gun")
 * focado em causar dano moderado e debilitar ligeiramente o poder físico do oponente.
 * Implementa a mecânica de verificação de nível para despoletar a sua primeira evolução.
 * </p>
 */
public class Squirtle extends Pokemon {
    public Squirtle(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft,  int level, int exp) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp);
    }

    /**
     * Executa o ataque especial de estágio inicial "Water Gun" contra o oponente.
     * <p>
     * O dano equivale à metade do atributo {@code specialAttack}. Possui uma
     * probabilidade estocástica de 33% (1 em 3 via {@link Random}) de perturbar o
     * inimigo, subtraindo 5 pontos à sua força física através de {@code enemy.revertStrength(5)}.
     * </p>
     *
     * @param enemy O {@link Pokemon} alvo do jato de água.
     * @return O dano absoluto a ser deduzido no HP do oponente.
     */
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

    /**
     * Avalia e executa o processo de evolução biológica para o estágio intermédio.
     * <p>
     * Caso o Squirtle atinja ou ultrapasse o <b>nível 16</b>, o sistema carrega a sua arte ASCII
     * a partir de {@code resources/art/starters/wartotle.txt}, aplica um efeito de transição
     * e gera uma nova instância de {@link Wartotle} com bónus de atributos (+15 MaxHP/HP,
     * +15 Strength, +15 SpecialAttack), transferindo o nível e experiência atuais.
     * </p>
     *
     * @return A nova instância evoluída de {@link Wartotle} se cumprir os requisitos;
     *         {@code null} caso contrário.
     */
    @Override
    public Pokemon evolve() {
        if (this.getLevel() >= 16) { // quando squirtle chega a nivel 16 evolui para Wartotle
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
