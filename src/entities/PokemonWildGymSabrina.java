package entities;

import java.util.Random;

/**
 * Representa os Pookémons especializados pertencentes à Líder de Ginásio Sabrina.
 * <p>
 * Estende {@link PokemonWild}, especializando o comportamento ofensivo para
 * refletir a tipagem Psíquica de Saffron City, introduzindo o movimento "Psychic"
 * com capacidade de distorcer os atributos ofensivos do oponente.
 * </p>
 */
public class PokemonWildGymSabrina extends PokemonWild{
    public PokemonWildGymSabrina(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp, int coins) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp, coins);
    }

    /**
     * Executa o ataque especial temático "Psychic" contra o Pookémon do jogador.
     * <p>
     * O cálculo do dano baseia-se na metade do atributo {@code specialAttack} adicionado
     * de um bónus fixo de 10 pontos. Adicionalmente, possui uma probabilidade de 33%
     * (1 em 3 via {@link Random}) de quebrar a mente do alvo, reduzindo permanentemente
     * ou temporariamente o seu poder físico através de {@code enemy.revertStrength(40)}.
     * </p>
     *
     * @param enemy O {@link Pokemon} do jogador que receberá o impacto e a possível redução de força.
     * @return O valor de dano matemático a ser deduzido no HP do oponente.
     */
    public int applySpecialAttack(Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2 + 10;
        if (new Random().nextInt(3) == 0) {
            enemy.revertStrength(40);
            System.out.println(this.getName() + " usou Psychic! " + enemy.getName() + " perdeu 10 de Strength!");
        } else {
            System.out.println(this.getName() + " usou Psychic!");
        }
        return damage;
    }
}
