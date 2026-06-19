package entities;

public class PokemonWildGymGiovani extends PokemonWild{
    public PokemonWildGymGiovani(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp, int coins) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp, coins);
    }
    public int applySpecialAttack(Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2 + 50;
        System.out.println(this.getName() + " usou Earthquake! A terra tremeu!");
        return damage;
    }
}
