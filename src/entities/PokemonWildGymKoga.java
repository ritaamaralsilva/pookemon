package entities;

/**
 * Representa os Pookémons especializados pertencentes ao Líder de Ginásio Koga.
 * <p>
 * Estende {@link PokemonWild}, especializando o comportamento ofensivo para
 * refletir a temática Ninja e a tipagem de Veneno de Fuchsia City, introduzindo
 * o movimento tático e debilitante "Toxic".
 * </p>
 */
public class PokemonWildGymKoga extends PokemonWild{
    public PokemonWildGymKoga(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp, int coins) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp, coins);
    }

    /**
     * Executa o ataque de estado de assinatura "Toxic" contra o Pookémon do jogador.
     * <p>
     * Este movimento simula a guerra de desgaste típica do líder Koga: não inflige
     * dano imediato (retorna {@code 0}), mas garante 100% de eficácia ao aplicar o
     * estado alterado {@link StatusEffect#POISONED}. Isto força o alvo a perder uma
     * percentagem fixa do seu HP Máximo no final de cada turno através do pipeline
     * de {@code applyStatusEffect()}.
     * </p>
     *
     * @param enemy O {@link Pokemon} do jogador que será gravemente envenenado.
     * @return O valor de dano imediato, que neste movimento de desgaste é estritamente {@code 0}.
     */
    public int applySpecialAttack(Pokemon enemy) {
        enemy.setStatusEffect(StatusEffect.POISONED);
        System.out.println(this.getName() + " usou Toxic! " + enemy.getName() + " ficou gravemente envenenado!");
        return 0;
    }
}
