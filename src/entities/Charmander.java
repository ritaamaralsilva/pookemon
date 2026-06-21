package entities;
import java.util.Random;

public class Charmander extends Pokemon {
    public Charmander(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft,  int level, int exp) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp);
    }

    @Override
    public int applySpecialAttack(Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2;
        Random random = new Random();
        if (random.nextInt(3) == 0) { // 33% de chance de queimar
            enemy.setStatusEffect(StatusEffect.BURNED);
        }
        System.out.println(this.getName() + " usou Ember!");
        return damage;
    }
    @Override
    public Pokemon evolve() {
        if (this.getLevel() == 16) {
            System.out.println(" Charmander evoluiu para CHARMELEON! ");
            Charmeleon charmeleon = new Charmeleon( //  construtor do ivysaur e o que ele herda do bulbasaur
                    this.getMaxHp() + 20, // boost de vida ao evoluir
                    this.getHp() + 20,
                    this.getStrength() + 15, // boost de força (ataque normal)
                    this.getSpecialAttack() + 15, // boost de special attack
                    this.getLevel(),
                    this.getExp()
            );
            charmeleon.resetSpecialAttackUses(); // começa com PP a cheio
            return charmeleon;
        }
        return null;
    }


}
