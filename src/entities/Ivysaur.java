package entities;

import game.FileTools;

import java.io.FileNotFoundException;

import static java.lang.Thread.sleep;

/**
 * Representa o estágio evolutivo intermédio da linhagem de Planta, o Ivysaur.
 * <p>
 * Estende {@link Pokemon}, atuando como a ponte de evolução intermédia a partir do nível 16.
 * Atualiza o seu ataque especial para "Razor Leaf", que incrementa o dano base e potencia
 * o retorno de cura, vigiando o nível limite para a transformação final.
 * </p>
 */
public class Ivysaur extends Pokemon { // evolucao do bulbasaur nivel 16
    public Ivysaur(int maxHp, int hp, int strength, int specialAttack, int level, int exp) {
        super("Ivysaur", maxHp, hp, strength, specialAttack,
                1 + (level / 10),
                1 + (level / 10),
                level, exp);
    }

    /**
     * Executa o ataque especial de estágio intermédio "Razor Leaf" contra o oponente.
     * <p>
     * Amplifica a ofensiva ao somar metade de {@code specialAttack} a um bónus fixo de
     * 10 pontos de dano. Mantém a propriedade de roubo de vida, curando a própria
     * instância em metade do valor total de dano calculado para o turno.
     * </p>
     *
     * @param enemy O {@link Pokemon} atingido pelas folhas cortantes.
     * @return O dano matemático total a aplicar no HP do oponente.
     */
    @Override
    public int applySpecialAttack(Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2 + 10;
        int healAmount = damage / 2;
        this.heal(healAmount);
        System.out.println(this.getName() + " usou Razor Leaf! Absorveu " + healAmount + " HP de " + enemy.getName() + "!");
        return damage;
    }

    /**
     * Avalia e executa o processo de transição para o último estágio evolutivo da linhagem.
     * <p>
     * Ao atingir o <b>nível 32</b>, o motor de jogo imprime a arte final guardada em
     * {@code resources/art/starters/venusaur.txt} e gera uma instância definitiva de
     * {@link Venusaur}, aplicando um bónus robusto à matriz estatística (+20 MaxHP/HP,
     * +20 Strength, +20 SpecialAttack) e repondo totalmente os PP do ataque especial.
     * </p>
     *
     * @return A instância final de {@link Venusaur} se o nível for suficiente;
     *         {@code null} caso contrário.
     */
    @Override
    public Pokemon evolve() {
        if (this.getLevel() >= 32) {
            try {
                FileTools.printFile("resources/art/starters/venusaur.txt");
                sleep(1500);
            } catch (FileNotFoundException | InterruptedException e) {
                System.out.println("Aviso: Imagem do Venusaur não encontrada.");
            }
            //System.out.println("Ivysaur evoluiu para VENUSAUR! ");
            Venusaur venusaur = new Venusaur( //  construtor do ivysaur e o que ele herda do bulbasaur
                    this.getMaxHp() + 20,
                    this.getHp() + 20,
                    this.getStrength() + 20,
                    this.getSpecialAttack() + 20,
                    this.getLevel(),
                    this.getExp()
            );
            venusaur.resetSpecialAttackUses();
            return venusaur;
        }
        return null;
    }
}
