package entities;

import game.FileTools;

import java.io.FileNotFoundException;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Representa o estágio evolutivo intermédio da linhagem de Fogo, o Charmeleon.
 * <p>
 * Estende {@link Pokemon}, atuando como ponte de progressão a partir do nível 16.
 * Atualiza o seu reportório com o movimento de alto calor "Flamethrower", incrementando
 * o dano base e mantendo o potencial de debuff por queimadura ativo até atingir a forma final.
 * </p>
 */
public class Charmeleon extends Pokemon{ // evolucao do charmander no nivel 16
    public Charmeleon(int maxHp, int hp, int strength, int specialAttack, int level, int exp) {
        super("Charmeleon", maxHp, hp, strength, specialAttack,
                1 + (level / 10),
                1 + (level / 10),
                level, exp);
    }

    /**
     * Executa o ataque especial de estágio intermédio "Flamethrower" contra o oponente.
     * <p>
     * Amplifica a capacidade ofensiva ao somar metade de {@code specialAttack} a um bónus
     * fixo de 10 pontos de dano. Mantém a probabilidade tática de 33% (1 em 3) de infligir
     * o estado de queimadura ({@link StatusEffect#BURNED}) no alvo.
     * </p>
     *
     * @param enemy O {@link Pokemon} atingido pelo jato de chamas.
     * @return O dano matemático total a aplicar no HP do oponente.
     */
    @Override
    public int applySpecialAttack(Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2 + 10;
        if (new Random().nextInt(3) == 0) {
            enemy.setStatusEffect(StatusEffect.BURNED);
            System.out.println(this.getName() + " usou Flamethrower! " + enemy.getName() + " ficou em chamas!");
        } else {
            System.out.println(this.getName() + " usou Flamethrower!");
        }
        return damage;
    }

    /**
     * Avalia e executa o processo de transição para o último estágio evolutivo da linhagem.
     * <p>
     * Protegido contra anomalias de subida de múltiplos níveis via operador {@code >=},
     * despoleta ao atingir o <b>nível 32</b>. O sistema imprime a arte ASCII de
     * {@code resources/art/starters/charizard.txt} e instancia um {@link Charizard} com
     * grandes incrementos de combate (+20 MaxHP/HP, +20 Strength, +20 SpecialAttack),
     * repondo os PP do ataque especial.
     * </p>
     *
     * @return A instância definitiva de {@link Charizard} se o nível for suficiente;
     * {@code null} caso contrário.
     */
    @Override
    public Pokemon evolve() {
        if (this.getLevel() >= 32) { // num teste que fiz, onde o pokemon evoluiu 2niveis seguidos, como a condicao estava como ==, o pokemon nao evoluiu, por isso alterei a condicao para >= para todos os starters para garantir que evoluem
            try {
                FileTools.printFile("resources/art/starters/charizard.txt");
                sleep(1500);
            } catch (FileNotFoundException | InterruptedException e) {
                System.out.println("Aviso: Imagem do Charizard não encontrada.");
            }
            //System.out.println(" Charmeleon evoluiu para CHARIZARD! ");
            Charizard charizard = new Charizard (
                    this.getMaxHp() + 20,
                    this.getHp() + 20,
                    this.getStrength() + 20,
                    this.getSpecialAttack() + 20,
                    this.getLevel(),
                    this.getExp()
            );
            charizard.resetSpecialAttackUses();
            return charizard;
        }
        return null;
    }
}

