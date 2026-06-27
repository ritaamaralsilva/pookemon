package entities;

import java.util.Random;

/**
 * Representa os Pookémons especializados pertencentes ao Líder de Ginásio Brock.
 * <p>
 * Estende {@link PokemonWild}, especializando o comportamento ofensivo para
 * refletir a tipagem de Pedra do ginásio de Pewter City, introduzindo o movimento
 * "Rock Throw" com mecânica de atordoamento/retrocesso (<i>Flinch</i>).
 * </p>
 */
public class PokemonWildGymBrock extends PokemonWild{
    public PokemonWildGymBrock(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp, int coins) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp, coins);
    }

    /**
     * Executa o ataque especial temático "Rock Throw" contra o Pookémon do jogador.
     * <p>
     * O dano baseia-se na metade do atributo {@code specialAttack}. Adicionalmente,
     * simula a mecânica original de <i>Flinch</i> com 50% de probabilidade
     * (via {@link Random}), revogando imediatamente qualquer bónus de velocidade ativo
     * no alvo ({@code enemy.setSpeedBoost(false)}) para que este perca a prioridade de ação.
     * </p>
     *
     * @param enemy O {@link Pokemon} do jogador que sofrerá o impacto e a perda de prioridade.
     * @return O valor de dano matemático a ser deduzido no HP do oponente.
     */
    public int applySpecialAttack(Pokemon enemy){
        int damage = this.getSpecialAttack() / 2;
        Random random = new Random();
        if (random.nextInt(50) == 0) { // 50% flinch — fiel ao OG
            enemy.setSpeedBoost(false); // força enemy a perder prioridade no turno
            System.out.println(this.getName() + " usou Rock Throw! " + enemy.getName() + " ficou esmagado e mal se consegue mexer !");
        } else {
            System.out.println(this.getName() + " usou Rock Throw!");
        }
            return damage;
    }
}
