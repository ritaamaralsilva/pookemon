package entities;

import game.FileTools;

import java.io.FileNotFoundException;

import static java.lang.Thread.sleep;

/**
 * Representa a forma inicial da linha evolutiva de tipo Planta, o Bulbasaur.
 * <p>
 * Sendo o primeiro estágio, introduz a mecânica identitária de autossustentação da linha
 * através do movimento "Leech Seed", combinando dano e dreno de vida (<i>Lifesteal</i>).
 * Controla os requisitos para a transição para o estágio intermédio.
 * </p>
 */
public class Bulbasaur extends Pokemon {
    public Bulbasaur(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp);
    }

    /**
     * Executa o ataque especial de estágio inicial "Leech Seed" contra o oponente.
     * <p>
     * O dano base equivale à metade do atributo {@code specialAttack}. Manifesta o efeito
     * de dreno ao calcular metade do dano infligido e convertê-lo em pontos de vida
     * para si próprio através do método {@link #heal(int)}.
     * </p>
     *
     * @param enemy O {@link Pokemon} que sofrerá o dreno de sementes.
     * @return O dano absoluto a ser deduzido no HP do oponente.
     */
    public int applySpecialAttack (Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2;
        int healAmount = damage / 2; // bulbasaur rouba metade do dano causado
        this.heal(healAmount);
        System.out.println(this.getName() + " usou Leech Seed! Roubou "
                + healAmount + " HP de " + enemy.getName() + ".");
        return damage;
    }

    /**
     * Avalia e executa o processo de evolução biológica para o estágio intermédio.
     * <p>
     * Quando o Bulbasaur alcança ou ultrapassa o <b>nível 16</b>, o sistema renderiza a arte
     * contida em {@code resources/art/starters/ivysaur.txt}, suspende o fluxo brevemente
     * e instancia um {@link Ivysaur}. Os atributos base acumulam um acréscimo de progressão
     * (+15 MaxHP/HP, +15 Strength, +15 SpecialAttack), preservando o nível e a experiência.
     * </p>
     *
     * @return A nova instância evoluída de {@link Ivysaur} se cumprir os requisitos;
     *         {@code null} caso contrário.
     */
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
