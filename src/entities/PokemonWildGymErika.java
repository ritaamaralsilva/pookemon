package entities;

/**
 * Representa os Pookémons especializados pertencentes à Líder de Ginásio Erika.
 * <p>
 * Estende {@link PokemonWild}, especializando o comportamento ofensivo para
 * refletir a tipagem de Planta/Veneno de Celadon City, introduzindo o movimento
 * tático "Sleep Powder".
 * </p>
 */
public class PokemonWildGymErika extends PokemonWild{
    public PokemonWildGymErika(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp, int coins) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp, coins);
    }

    /**
     * Executa o ataque de estado temático "Sleep Powder" contra o Pookémon do jogador.
     * <p>
     * Sendo um movimento de utilidade e controlo puramente estratégico, este ataque
     * possui um fator de dano nulo (retorna {@code 0}), mas garante uma taxa de
     * eficácia de 100% ao infligir diretamente o estado alterado {@link StatusEffect#ASLEEP}
     * no oponente, forçando o despoletar do contador de turnos de restrição.
     * </p>
     *
     * @param enemy O {@link Pokemon} do jogador que será induzido ao estado de sono.
     * @return O valor de dano infligido, que neste movimento tático é estritamente {@code 0}.
     */
    public int applySpecialAttack(Pokemon enemy) {
        enemy.setStatusEffect(StatusEffect.ASLEEP);
        System.out.println(this.getName() + " usou Sleep Powder! " + enemy.getName() + " adormeceu!");
        return 0;
    }
}
