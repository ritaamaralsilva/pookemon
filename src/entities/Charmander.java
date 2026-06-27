package entities;
import game.FileTools;

import java.io.FileNotFoundException;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Representa a forma inicial da linha evolutiva de tipo Fogo, o Charmander.
 * <p>
 * Sendo o primeiro estágio, foca-se em causar dano e infligir estados alterados
 * contínuos através do movimento "Ember", que possui uma probabilidade de queimar o alvo.
 * Controla os requisitos e transições para a sua primeira evolução.
 * </p>
 */
public class Charmander extends Pokemon {
    public Charmander(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft,  int level, int exp) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp);
    }

    /**
     * Executa o ataque especial de estágio inicial "Ember" contra o oponente.
     * <p>
     * O dano base equivale a metade do atributo {@code specialAttack}. Possui uma
     * probabilidade estocástica de 33% (1 em 3 via {@link Random}) de infligir o estado
     * alterado {@link StatusEffect#BURNED} no adversário, forçando perdas de vida por turno.
     * </p>
     *
     * @param enemy O {@link Pokemon} alvo das faíscas de fogo.
     * @return O dano absoluto a ser deduzido no HP do oponente.
     */
    @Override
    public int applySpecialAttack(Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2;
        Random random = new Random();
        if (random.nextInt(3) == 0) { // 33% de chance de queimar
            enemy.setStatusEffect(StatusEffect.BURNED);
        }
        System.out.println(this.getName() + " usou Ember!");
        return damage;
    }

    /**
     * Avalia e executa o processo de evolução biológica para o estágio intermédio.
     * <p>
     * Utiliza o operador {@code >=} para salvaguardar saltos múltiplos de nível. Caso a criatura
     * atinja ou ultrapasse o <b>nível 16</b>, o motor renderiza a arte contida em
     * {@code resources/art/starters/charmeleon.txt} e gera uma nova instância de {@link Charmeleon},
     * aplicando os respetivos bónus (+20 MaxHP/HP, +15 Strength, +15 SpecialAttack).
     * </p>
     *
     * @return A nova instância evoluída de {@link Charmeleon} se cumprir os requisitos;
     * {@code null} caso contrário.
     */
    @Override
    public Pokemon evolve() {
        if (this.getLevel() >= 16) {
            try {
                FileTools.printFile("resources/art/starters/charmeleon.txt");
                sleep(1500);
            } catch (FileNotFoundException | InterruptedException e) {
                System.out.println("Aviso: Imagem do Charmeleon não encontrada.");
            }
            //System.out.println(" Charmander evoluiu para CHARMELEON! ");
            Charmeleon charmeleon = new Charmeleon( //  construtor do ivysaur e o que ele herda do bulbasaur
                    this.getMaxHp() + 20, // boost de vida ao evoluir
                    this.getHp() + 20,
                    this.getStrength() + 15, // boost de força (ataque normal)
                    this.getSpecialAttack() + 15, // boost de special attack
                    this.getLevel(),
                    this.getExp()
            );
            charmeleon.resetSpecialAttackUses(); // começa com PP a cheio
            return charmeleon;
        }
        return null;
    }
}
