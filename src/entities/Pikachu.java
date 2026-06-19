package entities;

public class Pikachu extends Pokemon{

    public Pikachu(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp) {

        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp);
    }

    // Thunder Wave — paralisa o inimigo sem causar dano
    // estratégico: sacrificas o dano para garantir que o inimigo perde turnos
    public int applySpecialAttack(Pokemon enemy) {
        System.out.println(this.getName() + " usou Thunder Wave!");

        if (enemy.getStatusEffect() != null) {
            System.out.println(enemy.getName() + " já tem um status effect — Thunder Wave falhou!");
            return 0; // não causa dano nem aplica efeito
        }

        enemy.setStatusEffect(StatusEffect.PARALYZED);
        System.out.println(enemy.getName() + " está paralisado e pode perder turnos!");
        return 0; // thunder wave não causa dano direto
    }

//    @Override
//    public boolean pokemonBattle(Pokemon opponent) {
//        //metodo de ataque do pikachu, tem um boost de maxHp
//        return false;
//    }

}
