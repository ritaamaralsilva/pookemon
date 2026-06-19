package entities;

import java.util.Random;

public class PokemonWildGymBrock extends PokemonWild{
    public PokemonWildGymBrock(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp, int coins) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp, coins);
    }
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
