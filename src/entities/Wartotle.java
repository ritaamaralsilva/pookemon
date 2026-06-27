package entities;

import game.FileTools;

import java.io.FileNotFoundException;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Representa o estágio evolutivo intermédio da linhagem de Água, o Wartotle.
 * <p>
 * Estende {@link Pokemon}, servindo como ponte de progressão a partir do nível 16.
 * Atualiza o seu reportório com o movimento "Bubble Beam", duplicando a capacidade
 * de debuff do estágio anterior, e monitoriza o teto crítico para atingir a forma final.
 * </p>
 */
public class Wartotle extends Pokemon{ // evolucao do squirtle no nivel 16
    public Wartotle(int maxHp, int hp, int strength, int specialAttack, int level, int exp) {
        super("Wartotle", maxHp, hp, strength, specialAttack,
                1 + (level / 10),
                1 + (level / 10),
                level, exp);
    }

    /**
     * Executa o ataque especial de estágio intermédio "Bubble Beam" contra o oponente.
     * <p>
     * Causa um dano base equivalente a 50% de {@code specialAttack}. Retém a probabilidade
     * tática de 33% (1 em 3) de sucesso, mas duplica a severidade do debuff, drenando
     * 10 pontos da força física do adversário.
     * </p>
     *
     * @param enemy O {@link Pokemon} alvo do ataque de bolhas.
     * @return O dano absoluto calculado para este turno.
     */
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

    /**
     * Avalia e executa o processo de transição para o último estágio evolutivo da linhagem.
     * <p>
     * Quando o Wartotle atinge o <b>nível 32</b>, o motor de jogo renderiza a arte final em
     * {@code resources/art/starters/blastoise.txt} e instancia a deidade dos canhões de água,
     * {@link Blastoise}, aplicando um bónus robusto de combate (+20 MaxHP/HP, +25 Strength,
     * +15 SpecialAttack) e restaurando integralmente os pontos de movimento (PP).
     * </p>
     *
     * @return A instância definitiva de {@link Blastoise} se o nível for suficiente;
     *         {@code null} caso contrário.
     */
    @Override
    public Pokemon evolve() {
        if (this.getLevel() >= 32) { // quando Wartotle chega a nivel 32 evolui para Blastoise
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
