package entities;

import game.ConsoleColors;

import items.Consumable;
import items.TrainerItem;

import java.util.ArrayList;

/**
 * Representa a entidade do Treinador, controlada diretamente pelo utilizador/jogador.
 * <p>
 * Esta classe centraliza o progresso do utilizador na aventura de Kanto. Faz a gestão
 * das finanças do jogador, controla o progresso na Liga através do contador de crachás,
 * armazena os consumíveis guardados na mochila através de uma lista de {@link TrainerItem},
 * e monitoriza o {@link Pokemon} ativo que irá entrar em combate.
 * </p>
 */
public class Trainer { // classe do jogador inicializada no inicio do jogo
    private String nome;
    private String sexo;
    private int coins;
    protected ArrayList<TrainerItem> itemsBag; // items da mochila do treinador
    protected Pokemon pokemonInUse; // pokemon atual do jogador
    private int gymBadge; // contador de crachãs dos ginásios.

    /**
     * Construtor que inicializa a personagem do jogador com o seu ecossistema base.
     * <p>
     * <b>Nota de Implementação:</b> Independentemente do valor passado no argumento {@code coins},
     * o saldo inicial da personagem é sempre fixado em 1000 moedas por motivos de equilíbrio de jogo.
     * A mochila é instanciada completamente vazia.
     * </p>
     *
     * @param nome Nome do avatar do jogador.
     * @param sexo Género ou designação de identidade do avatar.
     * @param coins Parâmetro de entrada do saldo (atualmente sobreposto para 1000).
     * @param pokemonInUse O Pokémon inicial (starter) escolhido pelo jogador.
     */
    public Trainer(String nome, String sexo, int coins,  Pokemon pokemonInUse) {
        this.nome = nome;
        this.sexo = sexo;
        this.coins = 1000; // inicia com 1000 moedas
        this.pokemonInUse = pokemonInUse;
        this.gymBadge = 0; //inicia a 0, à medida que o jogador ganhe batalhas nos gyms, o contador gymBadge incrementa
        this.itemsBag = new ArrayList<TrainerItem>(); // mochila vazia

        System.out.println(this.nome + " foi criado/a com sucesso!\n");
    }

    /**
     * Obtém o Pokémon que é atualmente o companheiro dp jogador.
     *
     * @return A instância de {@link Pokemon} em uso.
     */
    public Pokemon getPokemonInUse() {
        return pokemonInUse;
    }

    /**
     * Troca o PokemonInUse em caso de evolução para a nova instância ou no caso de captura do PokemonLegendary Mew
     * @param pokemonInUse
     */
    public void setPokemonInUse(Pokemon pokemonInUse) {
        this.pokemonInUse = pokemonInUse;
    }

    /**
     * Contador de crachás que é incrementado a cada vitória num gym
     */
    public void addGymBadge() { // metodo para incrementar crachas ao jogador quando ele ganha contra um gym
        this.gymBadge++;
        System.out.println("Tens agora " + gymBadge + " crachá(s)!");
    }

    /**
     * Obtém o número total de crachás conquistados até ao momento na Liga.
     *
     * @return O valor inteiro guardado no atributo {@link #gymBadge}.
     */
    public int getGymBadge() {
        return gymBadge;
    }

    /**
     * Adiciona um determinado montante monetário ao saldo total do jogador.
     * <p>
     * Utilizado para processar prémios de lutas contra treinadores, ginásios ou venda de espólios.
     * Imprime o extrato e o saldo atualizado no ecrã.
     * </p>
     *
     * @param amountCoins O número de moedas positivas a serem depositadas na carteira.
     */
    public void addCoins(int amountCoins) { // metodo para incrementar o dinheiro
        this.coins += amountCoins;
        System.out.println("Recebeste " + amountCoins+ " coins! Total: " + this.coins + " coins.");
    }

    /**
     * Obtém o saldo atual de moedas disponíveis na conta do treinador.
     *
     * @return O valor inteiro de moedas do atributo {@link #coins}.
     */
    public int getCoins() {
        return coins;
    }

    /**
     * Deduz uma quantia específica de moedas da carteira do jogador.
     * <p>
     * Método invocado pela PokéShop aquando da compra de itens.
     * Pode ser invocado pelo TeamRocketAmbush, evento aleatório na SecretCave
     * </p>
     *
     * @param amountCoins O valor em moedas a ser debitado.
     */
    public void removeCoins(int amountCoins) {
        this.coins -= amountCoins;
    }

    /**
     * Insere um item utilitário dentro do inventário dinâmico do treinador.
     *
     * @param itemsBag O objeto derivado de {@link TrainerItem} que será armazenado na lista.
     */
    public void addItemToBag(TrainerItem itemsBag) {
        this.itemsBag.add(itemsBag);
    }

    /**
     * Recupera a coleção completa de itens armazenados na mochila do jogador.
     *
     * @return Um {@link ArrayList} preenchido com os objetos de tipo {@link TrainerItem}.
     */
    public ArrayList<TrainerItem> getItemsBag() {
        return itemsBag;
    }

    /**
     * Renderiza e exibe na interface de consola um painel formatado com os dados do jogador.
     * <p>
     * Recorre a ferramentas estéticas personalizadas da classe {@code ConsoleColors} para desenhar
     * uma moldura estruturada, detalhando o Nome, Identidade, Saldo, Crachás e, caso exista,
     * redireciona a chamada para expor também os dados analíticos do {@link Pokemon} ativo.
     * </p>
     */
    public void showDetails() {
        ConsoleColors.println("╔═══════════════════════════════════════════╗", ConsoleColors.CYAN_BOLD);
        ConsoleColors.println("║            🎒 DETALHES DO TREINADOR       ║", ConsoleColors.CYAN_BOLD);
        ConsoleColors.println("╠═══════════════════════════════════════════╣", ConsoleColors.CYAN_BOLD);

        ConsoleColors.print("    Nome       : ", ConsoleColors.WHITE_BOLD);
        System.out.println(this.nome);

        ConsoleColors.print("    Identidade : ", ConsoleColors.WHITE_BOLD);
        System.out.println(this.sexo);

        ConsoleColors.print("    Moedas     : ", ConsoleColors.WHITE_BOLD);
        ConsoleColors.println(this.coins + "  ", ConsoleColors.YELLOW_BOLD);

        ConsoleColors.print("    Crachás    : ", ConsoleColors.WHITE_BOLD);
        ConsoleColors.println(this.gymBadge + " ", ConsoleColors.PURPLE_BOLD);

        ConsoleColors.println("╠═══════════════════════════════════════════╣", ConsoleColors.CYAN_BOLD);
        ConsoleColors.println("║               POOKÉMON EM DESTAQUE        ║", ConsoleColors.CYAN_BOLD);
        ConsoleColors.println("╚═══════════════════════════════════════════╝", ConsoleColors.CYAN_BOLD);

        if (this.pokemonInUse != null) {
            this.pokemonInUse.showDetails();
        } else {
            ConsoleColors.warning("  Nenhum Pookémon selecionado de momento.");
        }
    }
}



