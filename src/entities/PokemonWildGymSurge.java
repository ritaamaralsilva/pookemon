package entities;

import java.util.Random;

/**
 * Representa os Pookémons especializados pertencentes ao Líder de Ginásio Lt. Surge.
 * <p>
 * Estende {@link PokemonWild}, especializando o comportamento ofensivo para
 * refletir a tipagem Elétrica e o ambiente militar de Vermilion City, introduzindo
 * o movimento de assinatura "Thunderbolt".
 * </p>
 */
public class PokemonWildGymSurge extends PokemonWild{
    public PokemonWildGymSurge(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp, int coins) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp, coins);
    }

    /**
     * Executa o ataque especial de assinatura "Thunderbolt" contra o Pookémon do jogador.
     * <p>
     * Garante o desafio do terceiro ginásio ao calcular o dano com a metade do
     * atributo {@code specialAttack} incrementado por um bónus fixo de 15 pontos.
     * Adicionalmente, possui uma probabilidade calibrada de 25% (1 em 4 via {@link Random})
     * de infligir o estado alterado {@link StatusEffect#PARALYZED} no alvo, aumentando a
     * tensão tática do combate através do bloqueio potencial de ações.
     * </p>
     *
     * @param enemy O {@link Pokemon} do jogador que receberá o impacto e a possível paralisia.
     * @return O valor total de dano matemático a ser deduzido no HP do oponente.
     */
    public int applySpecialAttack(Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2 + 15;
        if (new Random().nextInt(4) == 0) {
            enemy.setStatusEffect(StatusEffect.PARALYZED);
            System.out.println(this.getName() + " usou Thunderbolt! " + enemy.getName() + " ficou paralisado!");
        } else {
            System.out.println(this.getName() + " usou Thunderbolt!");
        }
        return damage;
    }
}
