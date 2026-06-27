package items;

import entities.Pokemon;

/**
 * Representa um item medicinal focado na restauração direta de pontos de vida.
 * <p>
 * Estende {@link TrainerItem} e especializa o método de utilização para invocar os algoritmos
 * de cura biológica do ecossistema Pokémon, operando de forma segura em qualquer entidade alvo.
 * </p>
 */
public class Potion extends TrainerItem {
    private int healAmount;

    public Potion(String name, int price, int healAmount) {
        super(name, price);
        this.healAmount = healAmount;
    }

    /**
     * Aplica o efeito restaurador de saúde na criatura selecionada como alvo.
     * <p>
     * Invoca o método {@code target.heal(healAmount)} herdado pela entidade alvo,
     * garantindo compatibilidade de uso tanto para os Pokémon do jogador como para
     * potenciais curas executadas pela IA de Líderes de Ginásio.
     * </p>
     *
     * @param target O {@link Pokemon} beneficiário do tratamento medicinal.
     */
    @Override
    public void use(Pokemon target) { // pokemon que recebe Potion (uso target em vez de myPokemon, porque nos gyms, os pokemon podem usar potions e consumiveis)
        target.heal(healAmount);
        System.out.println("Usaste " + getName() + "! " + target.getName() + " recuperou " + healAmount + " HP.");
    }
}