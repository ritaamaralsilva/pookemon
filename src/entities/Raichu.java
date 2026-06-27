package entities;

/**
 * Representa o pico evolutivo da linhagem Elétrica, o Raichu.
 * <p>
 * Esta classe concreta estende {@link Pokemon}, manifestando um enorme potencial destruidor
 * após herdar os bónus críticos de evolução do Pikachu. Introduz o movimento de assinatura
 * "Thunderbolt", especializado em explorar o estado do adversário para infligir danos massivos
 * e exponenciais.
 * </p>
 */
public class Raichu extends Pokemon { // evolução do Pikachu no nível 22
    public Raichu(int maxHp, int hp, int strength, int specialAttack, int level, int exp) {
        super("Raichu", maxHp, hp, strength, specialAttack,
                1 + (level / 10),
                1 + (level / 10),
                level, exp);
    }

    /**
     * Executa o temível ataque especial elétrico "Thunderbolt" contra o oponente.
     * <p>
     * O cálculo do dano baseia-se em metade do atributo {@code specialAttack} com um bónus fixo
     * de 25 pontos. Apresenta uma mecânica avançada de sinergia condicional:
     * </p>
     * <ul>
     * <li>Se o adversário não tiver estados ativos, o ataque garante a aplicação imediata do
     * estado {@link StatusEffect#PARALYZED}.</li>
     * <li>Se o alvo <b>já estiver paralisado</b>, a condutividade elétrica gera uma
     * <b>Sobrecarga Cósmica</b>, multiplicando o dano calculado **por dois ($damage \times 2$)**,
     * permitindo derreter as barras de vida de Pokémon resistentes como o Charizard ou Blastoise.</li>
     * </ul>
     *
     * @param enemy O {@link Pokemon} inimigo que sofrerá a descarga comum ou a sobrecarga duplicada.
     * @return O valor final de dano matemático (potencialmente duplicado) a aplicar ao oponente.
     */
    @Override
    public int applySpecialAttack(Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2 + 25;

        if (enemy.getStatusEffect() == null) {
            enemy.setStatusEffect(StatusEffect.PARALYZED);
            System.out.println(this.getName() + " usou Thunderbolt! " + enemy.getName() + " ficou paralisado!");
        } else if (enemy.getStatusEffect() == StatusEffect.PARALYZED) {
            // Se já estiver paralisado, a eletricidade conduz ainda melhor: DANO DUPLO!
            damage *= 2;
            System.out.println(this.getName() + " usou Thunderbolt! O alvo já estava paralisado e sofreu uma SOBRECARGA de dano duplo!");
        } else {
            System.out.println(this.getName() + " usou Thunderbolt!");
        }
        return damage;
    }
}
