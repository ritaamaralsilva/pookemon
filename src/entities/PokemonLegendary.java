package entities;

/**
 * Representa a linhagem de entidades divinas, míticas e únicas do jogo.
 * <p>
 * Esta classe estende a superclasse {@link Pokemon}, servindo como base conceptual
 * para criaturas místicas. Ao contrário dos Pookémons selvagens comuns, os Lendários
 * não possuem recompensas monetárias triviais (coins), sendo concebidos como os
 * derradeiros desafios de Boss do jogo devido aos seus reportórios ofensivos imprevisíveis.
 * </p>
 */
public class PokemonLegendary extends Pokemon {
    public PokemonLegendary(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp);
    }
}

