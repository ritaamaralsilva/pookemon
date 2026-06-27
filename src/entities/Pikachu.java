package entities;

import game.FileTools;

import java.io.FileNotFoundException;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Representa a forma inicial da linha evolutiva de tipo Elétrico, o Pikachu.
 * <p>
 * Sendo o primeiro estágio, oferece um desempenho equilibrado para o início do jogo
 * através do movimento "Spark", garantindo dano consistente combinado com uma alta
 * taxa de controlo de estado. Implementa o gatilho automático de evolução obrigatória
 * assim que atinge o patamar de nível estipulado.
 * </p>
 */
public class Pikachu extends Pokemon{

    public Pikachu(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp) {

        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp);
    }

    /**
     * Executa o ataque especial de estágio inicial "Spark" contra o oponente.
     * <p>
     * Garante eficácia ofensiva ao calcular o dano com a metade do {@code specialAttack}
     * incrementado por um bónus fixo seguro de 10 pontos. Possui uma probabilidade
     * elevada de 50% (1 em 2 via {@link Random}) de paralisar o adversário, aplicando
     * o estado alterado {@link StatusEffect#PARALYZED}.
     * </p>
     *
     * @param enemy O {@link Pokemon} alvo das faíscas elétricas.
     * @return O valor de dano absoluto a ser deduzido no HP do oponente.
     */
    public int applySpecialAttack(Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2 + 10; // Bónus de dano seguro

        if (new Random().nextInt(2) == 0) { // 50% de chance de paralisar
            enemy.setStatusEffect(StatusEffect.PARALYZED);
            System.out.println(this.getName() + " usou Spark! " + enemy.getName() + " ficou paralisado!");
        } else {
            System.out.println(this.getName() + " usou Spark!");
        }
        return damage;
    }

    /**
     * Avalia e executa a transição biológica obrigatória para o estágio final da linha.
     * <p>
     * Protegido contra anomalias de subida de múltiplos níveis através do operador {@code >=},
     * despoleta assim que o Pikachu alcança o <b>nível 22</b>. O sistema imprime a arte ASCII de
     * {@code resources/art/starters/raichu.txt} e gera uma instância definitiva de {@link Raichu},
     * aplicando um pico massivo de poder na matriz de atributos (+30 MaxHP/HP, +40 Strength,
     * +40 SpecialAttack) e restaurando os PP do ataque especial.
     * </p>
     *
     * @return A nova instância evoluída de {@link Raichu} se cumprir os requisitos;
     *         {@code null} caso contrário.
     */
    @Override
    public Pokemon evolve() {
        if (this.getLevel() >= 22) { // quando pikachu chega a nivel 22 evolui para Raichu
            try {
                FileTools.printFile("resources/art/starters/raichu.txt");
                sleep(1500);
            } catch (FileNotFoundException | InterruptedException e) {
                System.out.println("Aviso: Imagem do Raichu não encontrada.");
            }

            Raichu raichu = new Raichu( //  construtor do raichu e o que ele herda do pikachu
                    this.getMaxHp() + 30, // boost de vida ao evoluir
                    this.getHp() + 30,
                    this.getStrength() + 40, // boost de força (ataque normal)
                    this.getSpecialAttack() + 40, // boost de special attack
                    this.getLevel(),
                    this.getExp()
            );
            raichu.resetSpecialAttackUses(); // começa com PP a cheio
            return raichu;
        }
        return null;
    }
}
