package entities;

/**
 * Representa uma entidade de Pookémon Selvagem encontrada nas rotas comuns do jogo.
 * <p>
 * Esta classe estende a superclasse base {@link Pokemon}, servindo como uma implementação
 * intermédia no modelo de herança. Distingue-se em dois níveis operacionais:
 * </p>
 * <ul>
 * <li><b>Instanciação Direta (Selvagens Simples):</b> Representa as criaturas comuns de
 * encontros aleatórios que utilizam apenas o motor de combate básico (ataques normais),
 * recompensando o treinador com moedas ao serem derrotadas.</li>
 * <li><b>Base para Especialização (Ginásios):</b> Atua como a superclasse para os Pookémons
 * dos Líderes de Ginásio (como {@code PokemonWildGymBrock}, {@code PokemonWildGymMisty}, etc.),
 * que herdam a sua estrutura de recompensas mas sobrescrevem e injetam lógica avançada de
 * ataques especiais e efeitos de estado.</li>
 * </ul>
 */
public class PokemonWild extends Pokemon {
    /** A quantidade de moedas concedida como recompensa ao treinador que derrotar esta criatura. */
    private int coins; // o pokemonWild da moedas se for derrotado
    private String specialMoveName; // exemplo: "Water Gun", "Ember", "Bubblebeam"

    public PokemonWild(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp, int coins) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp);
        this.coins = coins;
        this.specialMoveName = specialMoveName;
    }

    /**
     * Obtém o valor de moedas que a instancia de Pokemon wild liberta como recompensa de vitória.
     *
     * @return O total acumulado no atributo {@link #coins}.
     */
    public int getCoins() {
        return coins;
    }

    /**
     * Define ou atualiza dinamicamente a recompensa monetária associada à derrota da criatura.
     *
     * @param coins O novo montante de moedas a atribuir à variável {@link #coins}.
     */
    public void setCoins(int coins) {
        this.coins = coins;
    }
}
