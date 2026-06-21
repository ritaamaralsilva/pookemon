package entities;

public class Raichu extends Pokemon { // evolução do Pikachu no nível 22
    public Raichu(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp);
    }

    @Override
    public int applySpecialAttack(Pokemon enemy) {
        // Raichu paralisa E causa dano — mais poderoso que o Pikachu
        int damage = this.getSpecialAttack() / 2 + 30;
        if (enemy.getStatusEffect() == null) {
            enemy.setStatusEffect(StatusEffect.PARALYZED); // paralisa sempre o inimigo
            System.out.println(this.getName() + " usou Thunderbolt! " + enemy.getName() + " ficou paralisado!");
        } else {
            System.out.println(this.getName() + " usou Thunderbolt!");
        }
        return damage;
    }
}
