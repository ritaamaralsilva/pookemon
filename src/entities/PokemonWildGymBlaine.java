package entities;

import java.util.Random;

/**
 * Representa os Pookémons especializados pertencentes ao Líder de Ginásio Blaine.
 * <p>
 * Estende {@link PokemonWild}, sobrescrevendo o comportamento ofensivo para
 * implementar mecânicas avançadas de bosses, como a execução do ataque especial
 * de elemento Fogo e a aplicação estocástica do estado de queimadura.
 * </p>
 */
public class PokemonWildGymBlaine extends PokemonWild{
    public PokemonWildGymBlaine(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp, int coins) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp, coins);
    }
    /**
     * Executa o ataque especial temático "Fire Blast" contra o Pookémon do jogador.
     * <p>
     * O cálculo do dano baseia-se na metade do atributo {@code specialAttack} incrementado
     * por um bónus fixo de 15 pontos. Adicionalmente, possui uma probabilidade de 33%
     * (1 em 3 via {@link Random}) de infligir o estado alterado {@link StatusEffect#BURNED} no alvo.
     * </p>
     *
     * @param enemy O {@link Pokemon} do jogador que receberá o impacto e a possível queimadura.
     * @return O valor total de dano matemático a ser deduzido no HP do oponente.
     */
    public int applySpecialAttack(Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2 + 15;
        if (new Random().nextInt(3) == 0) {
            enemy.setStatusEffect(StatusEffect.BURNED);
            System.out.println(this.getName() + " usou Fire Blast! " + enemy.getName() + " ficou em chamas!");
        } else {
            System.out.println(this.getName() + " usou Fire Blast!");
        }
        return damage;
    }
}
