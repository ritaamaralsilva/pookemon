package items;

import entities.Pokemon;

public class Potion extends TrainerItem {
    private int healAmount;

    public Potion(String name, int price, int healAmount) {
        super(name, price);
        this.healAmount = healAmount;
    }

    @Override
    public void use(Pokemon target) { // pokemon que recebe Potion (uso target em vez de myPokemon, porque nos gyms, os pokemon podem usar potions e consumiveis)
        target.heal(healAmount);
        System.out.println("Usaste " + getName() + "! " + target.getName() + " recuperou " + healAmount + " HP.");
    }
}