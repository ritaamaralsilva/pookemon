package items;

import entities.Pokemon;
import entities.StatusEffect;

/**
 * Representa consumíveis de cariz ofensivo concebidos para perturbar o estado do oponente durante o combate.
 * <p>
 * Ao contrário dos consumíveis benéficos, esta classe especializa o método {@link #use(Pokemon)}
 * para injetar diretamente debuffs e condições prejudiciais continuadas (como veneno, queimadura ou paralisia)
 * na entidade adversária definida como alvo.
 * </p>
 */
public class BattleConsumable extends TrainerItem { // esta subclasse representa itens que causem dano direto em combate no inimigo
    private StatusEffect effect;

    public BattleConsumable(String name, int price, StatusEffect effect) {
        super(name, price);
        this.effect = effect;
    }

    /**
     * Injeta diretamente a aflição de estado configurada na entidade alvo selecionada.
     * <p>
     * Em termos de fluxo de jogo, este método é executado passando o Pokémon inimigo como
     * argumento, forçando a alteração do seu estado através de {@code target.setStatusEffect(effect)}.
     * </p>
     *
     * @param target O {@link Pokemon} adversário que sofrerá a penalização de estado.
     */
    @Override
    public void use(Pokemon target) {
        target.setStatusEffect(effect);
    }
}