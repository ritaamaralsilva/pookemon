package items;

import entities.Pokemon;
import entities.StatusEffect;

public class BattleConsumable extends TrainerItem { // esta subclasse representa itens que causem dano direto em combate no inimigo
    private StatusEffect effect;

    public BattleConsumable(String name, int price, StatusEffect effect) {
        super(name, price);
        this.effect = effect;
    }

    @Override
    public void use(Pokemon target) {
        target.setStatusEffect(effect);
    }
}