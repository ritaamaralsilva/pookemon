package entities;

import java.util.Random;

/**
 * Representa o pico evolutivo da linha de tipo Fogo, o Charizard.
 * <p>
 * Esta classe concreta manifesta o potencial máximo de destruição térmica da árvore Charmander,
 * introduzindo o devastador ataque de assinatura "Fire Blast". Sendo o estágio final,
 * o seu ciclo evolutivo estabiliza, concentrando o seu poder em dano bruto e numa elevadíssima
 * eficácia de queimadura.
 * </p>
 */
public class Charizard extends Pokemon { // evolucao do charmeleon no nivel 36
    public Charizard(int maxHp, int hp, int strength, int specialAttack, int level, int exp) {
        super("Charizard", maxHp, hp, strength, specialAttack,
                1 + (level / 10),
                1 + (level / 10),
                level, exp);
    }

    /**
     * Executa o derradeiro ataque especial de fogo "Fire Blast" contra o oponente.
     * <p>
     * O cálculo do dano é severamente expandido, somando a metade do valor de
     * {@code specialAttack} a um bónus fixo de +20 pontos. A mestria deste estágio final
     * eleva a probabilidade para 50% (1 em 2 via {@link Random}) de deixar o alvo em chamas
     * através de {@link StatusEffect#BURNED}, maximizando a eficácia de desgaste contínuo.
     * </p>
     *
     * @param enemy O {@link Pokemon} adversário que receberá a explosão de fogo.
     * @return O valor total de dano matemático a ser deduzido no HP do oponente.
     */
    @Override
    public int applySpecialAttack(Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2 + 20;
        if (new Random().nextInt(2) == 0) { // 50% — Fire Blast mais potente
            enemy.setStatusEffect(StatusEffect.BURNED);
            System.out.println(this.getName() + " usou Fire Blast! " + enemy.getName() + " ficou em chamas!");
        } else {
            System.out.println(this.getName() + " usou Fire Blast!");
        }
        return damage;
    }
}

