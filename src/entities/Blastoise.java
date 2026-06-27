package entities;

import java.util.Random;

/**
 * Representa o pico evolutivo da linha de tipo Água, o Blastoise.
 * <p>
 * Esta classe concreta manifesta o potencial máximo de combate da árvore Squirtle,
 * introduzindo o devastador ataque de assinatura "Hydro Pump". Sendo o estágio final,
 * o seu método de evolução cessa, focando-se inteiramente no poder bruto e no controlo
 * opressivo de atributos do adversário.
 * </p>
 */
public class Blastoise extends Pokemon {
    public Blastoise(int maxHp, int hp, int strength, int specialAttack, int level, int exp) {
        super("Blastoise", maxHp, hp, strength, specialAttack,
                1 + (level / 10),
                1 + (level / 10),
                level, exp);
    }

    /**
     * Executa o ataque especial de água "Hydro Pump" contra o oponente.
     * <p>
     * O cálculo do dano é fortemente amplificado, somando metade do valor de
     * {@code specialAttack} a um bónus fixo devastador de 15 pontos. Adicionalmente,
     * a mestria de estágio final eleva a probabilidade para 50% (1 em 2 via {@link Random})
     * de esmagar a ofensiva do alvo, forçando um decréscimo massivo de 20 pontos de
     * força através de {@code enemy.revertStrength(20)}.
     * </p>
     *
     * @param enemy O {@link Pokemon} adversário que sofrerá a torrente de água.
     * @return O valor total de dano matemático a aplicar na vida do inimigo.
     */
    public int applySpecialAttack (Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2 + 15;
        if (new Random().nextInt(2) == 0) { // 50% — Hydro Pump mais potente
            enemy.revertStrength(20); // retira 30 de forca ao ataque normal do oponente
            System.out.println(this.getName() + " usou Hydro Pump! " + enemy.getName() + " perdeu 20 de força!");
        } else {
            System.out.println(this.getName() + " usou Hydro Pump!");
        }
        return damage;
    }
}

