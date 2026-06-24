package entities;

public class Raichu extends Pokemon { // evolução do Pikachu no nível 22
    public Raichu(int maxHp, int hp, int strength, int specialAttack, int level, int exp) {
        super("Raichu", maxHp, hp, strength, specialAttack,
                1 + (level / 10),
                1 + (level / 10),
                level, exp);
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
