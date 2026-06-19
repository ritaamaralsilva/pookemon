package entities;

import java.util.Random;

public class Squirtle extends Pokemon {
    public Squirtle(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft,  int level, int exp) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp);
    }

    public int applySpecialAttack(Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2;
        Random random = new Random();
        if (random.nextInt(3) == 0) { // 33% de chance de baixar strength do inimigo
            enemy.revertStrength(5);
            System.out.println(enemy.getName() + " perdeu 5 de Strength!");
        }
        System.out.println(this.getName() + " usou Water Gun!");
        return damage;
    }

//    @Override
//    public boolean pokemonBattle(Pokemon opponent) {
//        //metodo de ataque do squirtle, esta entre o bulbasaur e o charmander
//        return false;
//    }
}
