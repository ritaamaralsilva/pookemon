package entities;

/**
 * Representa os Pookémons especializados pertencentes ao Líder de Ginásio e Chefe da Team Rocket, Giovanni.
 * <p>
 * Estende {@link PokemonWild}, especializando o comportamento ofensivo para
 * refletir a tipagem de Terra/Solo de Viridian City, introduzindo o movimento de
 * alto impacto "Earthquake".
 * </p>
 */
public class PokemonWildGymGiovani extends PokemonWild{
    public PokemonWildGymGiovani(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp, int coins) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp, coins);
    }

    /**
     * Executa o ataque especial de assinatura "Earthquake" contra o Pookémon do jogador.
     * <p>
     * Este movimento representa o pico de dificuldade dos líderes de ginásio, calculando
     * o dano com base na metade do atributo {@code specialAttack} adicionado de um bónus
     * fixo esmagador de 50 pontos, priorizando o dano bruto em área em vez de efeitos de estado.
     * </p>
     *
     * @param enemy O {@link Pokemon} do jogador que sofrerá o impacto do sismo.
     * @return O valor total de dano matemático a ser deduzido no HP do oponente.
     */
    public int applySpecialAttack(Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2 + 50;
        System.out.println(this.getName() + " usou Earthquake! A terra tremeu!");
        return damage;
    }
}
