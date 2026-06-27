package entities;

/**
 * Representa o pico evolutivo da linha de tipo Planta, o Venusaur.
 * <p>
 * Esta classe concreta manifesta a capacidade máxima de sustentação e sobrevivência da árvore
 * Bulbasaur, introduzindo o devastador ataque "Solar Beam". Sendo o estágio final, cessa
 * qualquer lógica de evolução, focando-se em alto dano especial acoplado a uma absorção
 * massiva e sobrecarregada de vida.
 * </p>
 */
public class Venusaur extends Pokemon{ // evolucao do ivysaur nivel 36
    public Venusaur(int maxHp, int hp, int strength, int specialAttack, int level, int exp) {
        super("Venusaur", maxHp, hp, strength, specialAttack,
                1 + (level / 10),
                1 + (level / 10),
                level, exp);
    }

    /**
     * Executa o derradeiro ataque especial de planta "Solar Beam" contra o oponente.
     * <p>
     * O cálculo do dano é expandido de forma agressiva, aplicando metade de {@code specialAttack}
     * com um bónus fixo de +20 pontos. O efeito de cura é criticamente ampliado, absorvendo a
     * metade padrão do dano infligido somada a um bónus fixo de regeneração de +20 pontos
     * via {@link #heal(int)}, tornando o Venusaur extremamente resiliente em combates longos.
     * </p>
     *
     * @param enemy O {@link Pokemon} adversário que servirá de fonte de energia solar.
     * @return O valor total de dano matemático a ser deduzido no HP do oponente.
     */
    public int applySpecialAttack (Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2 + 20;
        int healAmount = damage / 2 + 20;
        this.heal(healAmount);
        System.out.println(this.getName() + " usou Solar Beam! Absorveu " + healAmount + " HP de " + enemy.getName() + "!");
        return damage;
    }
}

