package entities;

import java.util.Random;

/**
 * Representa os Pookémons especializados pertencentes à Líder de Ginásio Misty.
 * <p>
 * Estende {@link PokemonWild}, especializando o comportamento ofensivo para
 * refletir a tipagem de Água de Cerulean City, introduzindo o movimento
 * "Bubblebeam" com chance de induzir debuffs de movimento.
 * </p>
 */
public class PokemonWildGymMisty extends PokemonWild{
    public PokemonWildGymMisty(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp, int coins) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp, coins);
    }

    /**
     * Executa o ataque especial temático "Bubblebeam" contra o Pookémon do jogador.
     * <p>
     * O cálculo do dano baseia-se na metade do atributo {@code specialAttack}. Além disso,
     * possui uma probabilidade de 33% (1 em 3 via {@link Random}) de infligir o estado
     * alterado {@link StatusEffect#PARALYZED} no oponente, ativando o filtro de travamento
     * por paralisia no motor de turnos.
     * </p>
     *
     * @param enemy O {@link Pokemon} do jogador que receberá o impacto e a possível paralisia.
     * @return O valor de dano matemático a ser deduzido no HP do oponente.
     */
    public int applySpecialAttack(Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2;
        if (new Random().nextInt(3) == 0) {
            enemy.setStatusEffect(StatusEffect.PARALYZED);
            System.out.println(this.getName() + " usou Bubblebeam! " + enemy.getName() + " ficou lento!");
        } else {
            System.out.println(this.getName() + " usou Bubblebeam!");
        }
        return damage;
    }
}
