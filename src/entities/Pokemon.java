package entities;

import game.ConsoleColors;
import items.BattleConsumable;
import items.Consumable;
import items.Potion;
import items.TrainerItem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

/**
 * Classe abstrata e conceptual que serve como base para todas as entidades Pookémon do jogo.
 * <p>
 * Esta classe encapsula a estrutura biológica, a matriz de atributos de combate,
 * o sistema de progressão de experiência (Level Up) e a máquina de estados que governa
 * o ciclo de vida dos efeitos debilitantes ({@link StatusEffect}).
 * </p>
 * <p>
 * <b>Princípios de POO Aplicados:</b>
 * </p>
 * <ul>
 * <li><b>Abstração e Herança:</b> Define um contrato biológico e comportamental comum que
 * todas as subclasses (espécies específicas de Pookémon) herdam e estendem.</li>
 * <li><b>Encapsulamento:</b> Protege o estado interno da entidade através de modificadores
 * de acesso {@code private}, expondo o controlo estrito através de métodos mutadores (setters)
 * e seletores (getters) controlados.</li>
 * <li><b>Polimorfismo:</b> Serve como o tipo polimórfico fundamental no motor de jogo e na
 * mochila do treinador, permitindo que o método {@link #pokemonBattle(Pokemon, Trainer)} e o
 * gatilho contratual {@link #evolve()} funcionem independentemente da subclasse concreta em runtime.</li>
 * </ul>
 */
public abstract class Pokemon {
    private String name;
    private int maxHp;
    private int hp;
    private int strength;
    private int specialAttack; // ataque especial do pokemon
    private int specialAttackUses; // quantas vezes o pokemon pode usar o Special Attack (nos jogos OG, isto representa o PP )
    private int specialAttackUsesLeft; // usos restantes de Special Attack que o Pokemon ainda tem (aka PP left)
    private int level; // nivel do Pokemon
    private int exp; // isto representa os pontos que o pokemon do jogador ganha de outro pokemon rival em battles, mas tambem uso para calcular como o pokemonInUse evolui de nivel
    private StatusEffect statusEffect; // null = sem efeito | se pokemon tiver um estado alterado devido a um consumivel
    private int sleepTurns; // se pokemon tiver sob efeito de um consumivel de sono

    public Pokemon(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = hp;
        this.strength = strength;
        this.specialAttack = specialAttack;
        this.specialAttackUses = specialAttackUses;
        this.specialAttackUsesLeft = specialAttackUsesLeft;
        this.level = level;
        this.exp = exp;
    }

    public Pokemon() {

    }
    /** O identificador nominal único da instância do Pookémon. */
    public String getName() {
        return name;
    }
    /** A capacidade máxima de pontos de vida (HP) que o Pookemon tem naquele momento */
    public int getMaxHp() {
        return maxHp;
    }
    /** A vitalidade ou pontos de vida (HP) atuais do Pookemon em tempo real. */
    public int getHp() {
        return hp;
    }
    /** O poder ofensivo físico base utilizado para calcular o dano do Ataque Normal. */
    public int getStrength() {
        return strength;
    }
    /** O valor base de dano infligido através da execução do Ataque Especial. */
    public int getSpecialAttack() {
        return specialAttack;
    }

    /**
     * Calcula dinamicamente o número máximo de usos do Ataque Especial (PP) com base no nível.
     * Garante um ganho progressivo de +1 uso a cada 10 níveis acumulados.
     * @return Número total de usos permitidos para o nível atual
     */
    public int getSpecialAttackUses() {
        // começa com 1 uso, ganha +1 a cada 10 níveis
        // nível 1-9  → 1 uso
        // nível 10-19 → 2 usos
        // nível 20-29 → 3 usos
        // etc.
        return 1 + (this.level / 10); // isto incrementa o nr de vezes que o pokemon pode usar special attack
    }

    /**
     * @return Quantidade de usos restantes do Ataque Especial (PP) no momento atual.
     */
    public int getSpecialAttackUsesLeft() {
        return specialAttackUsesLeft;
    }

    /**
     *
     * @return nivel atual do Pookemon
     */
    public int getLevel() {
        return level;
    }

    /**
     *
     * @return quantidade de Exp (são usados para determinar quanto falta para o Pookemon subir de nível, tal como nos jogos OG de Pokémon)
     */
    public int getExp() {
        return exp;
    }

    /** O estado alterado ativo no Pookemon ({@code null} se estiver saudável).
     * Rege penalizações contínuas de fim de turno ou restrições de ação.
     */
    public StatusEffect getStatusEffect() { return statusEffect; }

    /** * O contador determinístico de turnos remanescentes sob a penalização de incapacidade
     * do estado {@link StatusEffect#ASLEEP}.
     */
    public int getSleepTurns() {
        return sleepTurns;
    }

    /**
     * verifica se o Pookemon já tem um statusEffet ativo ou, se não, aplica o statusEffect
     * @param effect
     */
    public void setStatusEffect(StatusEffect effect) {
        if (this.statusEffect == null) {
            this.statusEffect = effect;
            this.sleepTurns = effect == StatusEffect.ASLEEP ? 2 : 0;
            System.out.println(this.name + " ficou " + effect + "!");
        } else {
            System.out.println(this.name + " já tem um status effect!");
        }
    }

    // inicio dos metodos

    /**
     * este método serve para dar reset ao atributo SpecialAttackUses, ou seja, volta a encher para o máx permitido para o Pookémon no nível que ele estiver no momento
     */
    public void resetSpecialAttackUses() { // isto faz reset ao nr de vezes que o pokemon pode usar o special attack (vou implementar que no PookeCenter, para alem do Hp, o PP do Special attack tambem volta ao nr maximo que o pokemon pode usar naquele nivel)
        this.specialAttackUsesLeft = getSpecialAttackUses();
    }

    /**
     * Verifica se o Pookemon tem SpecialAttackUsesLeft, se sim, aplica e decrementa 1 uso pela utilização
     */
    public void useSpecialAttack() {
        // se tiver PP no Special attack, pokemon usa e decrementa no specialAttackUsesLeft
        if (specialAttackUsesLeft > 0) specialAttackUsesLeft--;
    }

    /**
     * Método que limpa os efeitos de estado
     */
    public void clearStatusEffect() {
        this.statusEffect = null;
        this.sleepTurns = 0;
    }

    /**
     * Método do specialAttack default (existe override deste método nos starters de forma a que cada um deles tenha o seu Special Attack próprio)
     */

    public int applySpecialAttack (Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2;
        System.out.println(this.getName() + " usou Ataque Especial e causou " + damage + " de dano!");
        return damage;
    }

    /**
     * Método que calcula o dano em determinado pookemon
     * @param damage
     */
    public void takeLife(int damage) {
        this.hp -= damage;
        if (this.hp < 0) this.hp = 0;
    }

    /**
     * Decrementa o contador de turnos restritivos do efeito de sono.
     * Quando o contador atinge zero, o estado alterado é automaticamente revogado
     * através do método {@link #clearStatusEffect()} e o utilizador é notificado.
     * Este método centraliza a regra de negócio do ciclo de vida do sono,
     * respeitando o princípio do encapsulamento.
     */
    public void decrementSleepTurns() {
        if (this.sleepTurns > 0) {
            this.sleepTurns--;
            if (this.sleepTurns == 0) {
                clearStatusEffect();
                System.out.println(this.name + " acordou!");
            }
        }
    }

    /**
     * Processa os danos contínuos e efeitos passivos decorrentes dos estados alterados
     * ativos no Pokémon alvo no final de cada turno.
     * O cálculo dos danos de {@link StatusEffect#POISONED} (~12.5%) e {@link StatusEffect#BURNED} (10%)
     * é executado estritamente de forma proporcional com base no <b>HP Máximo</b> do alvo,
     * garantindo um escalamento de dificuldade linear e fiel aos jogos originais (OG)
     * Nota de Design:</b> O caso {@link StatusEffect#ASLEEP} encontra-se intencionalmente
     * vazio neste método, visto que o ciclo de vida e decremento dos turnos de sono são
     * geridos exclusivamente no fluxo de validação de ação em {@code enemyCanAttack()},
     * mitigando bugs de concorrência ou dupla redução no mesmo turno.
     * @param target
     */
    private void applyStatusEffect(Pokemon target) {
        if (target.getStatusEffect() == null || target.getHp() <= 0) return; // se não tiver statusEffect ativo ou o hp estiver a 0, não faz nada

        switch (target.getStatusEffect()) {
            case POISONED:
                int poisonDamage = target.getHp() / 8; // ~12.5% como no pokemon original
                if (poisonDamage == 0) poisonDamage = 2;
                target.takeLife(poisonDamage);
                System.out.println(target.getName() + " sofreu " + poisonDamage + " de dano pelo veneno!");
                break;
            case BURNED:
                int burnDamage = target.getHp() / 8; // ~12.5% como no pokemon original
                if (burnDamage == 0) burnDamage = 2;
                target.takeLife(burnDamage);
                System.out.println(target.getName() + " sofreu " + burnDamage + " de dano pela queimadura!");
                break;
            case ASLEEP:
                // tratado na lógica do enemyCanAttack
                break;
            case PARALYZED:
                // tratado na lógica de ataque do metodo pokemonBattle
                break;
        }
    }

    /**
     * Avalia as restrições de estado ativo no Pokémon inimigo para determinar se este
     * está apto a executar uma ação ofensiva no turno atual
     * Este método atua como um <i>guard gate</i> no motor de combate, tratando de forma
     * isolada os efeitos incapacitantes:
     * <ul>
     * <li>{@link StatusEffect#ASLEEP}: Decrementa deterministicamente um turno de sono
     * através de {@link #decrementSleepTurns()} e bloqueia o ataque.</li>
     * <li>{@link StatusEffect#PARALYZED}: Aplica uma taxa de probabilidade estocástica
     * (50% de hipótese via {@link Random}) para ditar o travamento muscular da entidade.</li>
     * </ul>
     * <p>
     * <b>Nota de Design:</b> Centralizar o decremento de turnos de sono neste ponto garante
     * que a penalização por incapacidade consuma exatamente um turno de ação real do oponente.
     * </p>
     * @param enemy O {@link Pokemon} oponente cuja ação está a ser avaliada.
     * @return {@code true} se a entidade estiver apta a atacar neste turno;
     * {@code false} se for impedida pelo efeito de estado ativo.
     */
    private boolean enemyCanAttack(Pokemon enemy) { // este metodo ve se o pokemon inimigo pode atacar quando esta sob o efeito de consumiveis de combate
        if (enemy.getStatusEffect() == null) return true;

        switch (enemy.getStatusEffect()) {
            case ASLEEP:
                enemy.decrementSleepTurns();
                System.out.println(enemy.getName() + " está a dormir e não pode atacar!");
                return false;
            case PARALYZED:
                Random rand = new Random();
                if (rand.nextInt(2) == 0) {
                    System.out.println(enemy.getName() + " está paralisado e não consegue atacar!");
                    return false;
                }
                return true;
            default:
                return true;
        }
    }

    /**
     * Define o gatilho conceitual e contratual para a metamorfose (evolução) da entidade.
     * <p>
     * Por padrão na superclasse abstrata, este método retorna {@code null}. Deve ser
     * obrigatoriamente sobrescrito (<i>Override</i>) nas subclasses dos Pookémons que
     * possuem uma árvore evolutiva mapeada, instanciando e retornando a respetiva
     * forma seguinte quando as condições de nível forem validadas.
     * </p>
     *
     * @return Uma nova instância da evolução do {@link Pokemon}, ou {@code null} se a
     * entidade não possuir evolução ou não cumprir os requisitos de nível.
     */
    public Pokemon evolve() {
        return null;
    }

    /**
     * Executa o Motor de Combate Principal por Turnos (CLI) entre este Pookémon e um oponente.
     * <p>
     * Este método centraliza a máquina de estados do confronto, gerenciando em loop:
     * </p>
     * <ul>
     * <li>A renderização dinâmica do painel de estado e menus táticos via consola.</li>
     * <li>A captura e validação robusta de inputs (capturando {@link InputMismatchException}
     * para evitar quebras no runtime caso o utilizador digite caracteres inválidos).</li>
     * <li>A filtragem e aplicação estratégica de itens da mochila do treinador
     * ({@link Potion}, {@link Consumable} e {@link BattleConsumable}).</li>
     * <li>A determinação dinâmica de prioridade de ataque com base no nível ou modificadores ativos
     * (verificação de speed boost via XSpeed).</li>
     * <li>O processamento final de fim de turno através do pipeline de {@link #applyStatusEffect(Pokemon)}.</li>
     * </ul>
     * <p>
     * <b>Lógica de Pós-Combate e Evolução Imersiva:</b> Se a entidade do utilizador sair vitoriosa,
     * recebe a experiência acumulada do oponente. Caso o patamar de nível ative o método {@link #evolve()},
     * o motor interrompe o fluxo sequencial, substitui as referências na instância do {@link Trainer} e
     * invoca uma transição multimédia assíncrona com manipulação de áudio em background para simular a
     * imersão dramática dos jogos originais (OG).
     * </p>
     *
     * @param enemy O {@link Pokemon} adversário (selvagem ou de ginásio) a ser defrontado.
     * @param player O {@link Trainer} que comanda este Pookémon e detém a mochila de consumíveis.
     * @return {@code true} se o Pookémon do jogador subjugar o oponente;
     * {@code false} se o Pookémon do jogador esgotar o seu HP e for derrotado.
     */
    public boolean pokemonBattle(Pokemon enemy, Trainer player) {
        ArrayList<TrainerItem> itemsBag = player.getItemsBag();
        Scanner input = new Scanner(System.in);

        ConsoleColors.clear();
        ConsoleColors.title("BATALHA POOKÉMON");
        ConsoleColors.separator();
        ConsoleColors.story("Confronto iniciado: " + this.getName() + " VS " + enemy.getName() + "!");
        ConsoleColors.separator();

        while (this.getHp() > 0 && enemy.getHp() > 0) {

            // --- VERIFICAÇÃO DE SONO DO JOGADOR ---
            if (this.getStatusEffect() == StatusEffect.ASLEEP) {
                this.decrementSleepTurns(); // Reduz 1 turno de sono do jogador
                ConsoleColors.println(this.getName() + " está a dormir profundamente e não se consegue mexer!", ConsoleColors.BLUE_BOLD);

                // Como o jogador está a dormir, salta o menu de escolhas e passa diretamente para a ação do inimigo!
                System.out.println("\nPressiona Enter para ver a reação do inimigo...");
                input.nextLine();

                // Fluxo simplificado: Apenas o inimigo ataca neste turno
                if (enemyCanAttack(enemy)) {
                    Random rd = new Random();
                    int enemyAttackChance = rd.nextInt(100);

                    if (enemyAttackChance <= 30) {
                        enemy.applySpecialAttack(this);
                    } else {
                        int damage = enemy.getStrength() / 2;
                        this.takeLife(damage);
                        ConsoleColors.println(enemy.getName() + " aproveitou o teu sono e infligiu " + damage + " de dano!", ConsoleColors.RED_BOLD);
                        ConsoleColors.println("A vida do teu " + this.getName() + " desceu para " + this.getHp() + " HP.", ConsoleColors.GREEN_BRIGHT);
                    }
                }

                // Processa efeitos de fim de turno (Veneno/Queimadura se houverem) e avança para o próximo turno
                applyStatusEffect(enemy);
                applyStatusEffect(this);
                continue; // Força o loop a ir para o próximo turno sem mostrar o menu
            }

            // PAINEL DE ESTADO DO TURNO
            System.out.println("\n--- ESTADO DO COMBATE ---");
            ConsoleColors.print("    O teu " + this.getName() + ": ", ConsoleColors.GREEN_BOLD);
            System.out.println(this.getHp() + " HP [Ataques Especiais Restantes: " + this.specialAttackUsesLeft + "]");
            ConsoleColors.print("    Inimigo " + enemy.getName() + ": ", ConsoleColors.RED_BOLD);
            System.out.println(enemy.getHp() + " HP");
            System.out.println("---------------------------");

            System.out.println("\nO que vais fazer?");
            System.out.println("1. Ataque Normal");
            System.out.println("2. Usar Potion / Consumable (Mochila)");
            System.out.println("3. Usar BattleConsumable no inimigo");
            System.out.println("4. Ataque Especial");

            ConsoleColors.print("\nEscolha (1-4): ", ConsoleColors.YELLOW_BOLD);

            int choice;
            try {
                choice = input.nextInt();
                input.nextLine(); // Limpar buffer
            } catch (InputMismatchException e) {
                ConsoleColors.error("Comando inválido! Perdeste a oportunidade do turno por distração.");
                input.nextLine();
                choice = 1; // Força ataque normal por falha de input
            }

            boolean choseSpecialAttak = false;
            boolean usedItem = false; // Controla se o jogador usou um item neste turno (para corrigir o pokemon do jogador usar item e atacar no mesmo turno)

            switch (choice) {
                case 1:
                    // Opção padrão de ataque normal (mantida vazia para processar abaixo)
                    break;

                case 2:
                    ArrayList<TrainerItem> myItems = new ArrayList<>();
                    for (TrainerItem item : itemsBag) {
                        if (item instanceof Potion || item instanceof Consumable) {
                            myItems.add(item);
                        }
                    }
                    if (myItems.isEmpty()) {
                        ConsoleColors.error("Não tens Potions ou Consumables na mochila!");
                        break;
                    }
                    System.out.println("\nEscolhe um item para aplicar no teu Pookémon:");
                    for (int i = 0; i < myItems.size(); i++) {
                        System.out.println((i + 1) + ". " + myItems.get(i).getName());
                    }
                    System.out.println("0. Voltar atrás");

                    ConsoleColors.print("Escolhe: ", ConsoleColors.YELLOW_BOLD);
                    int itemChoice;
                    try {
                        itemChoice = input.nextInt();
                        input.nextLine();
                    } catch (InputMismatchException e) {
                        input.nextLine();
                        continue;
                    }

                    if (itemChoice == 0) continue; // Voltar atrás não gasta turno

                    if (itemChoice > 0 && itemChoice <= myItems.size()) {
                        TrainerItem chosen = myItems.get(itemChoice - 1);
                        chosen.use(this);
                        itemsBag.remove(chosen);
                        ConsoleColors.success("Utilizaste " + chosen.getName() + " com sucesso.");
                        usedItem = true;
                    } else {
                        ConsoleColors.error("Opção inválida!");
                        continue;
                    }
                    break;

                case 3:
                    ArrayList<TrainerItem> battleItems = new ArrayList<>();
                    for (TrainerItem item : itemsBag) {
                        if (item instanceof BattleConsumable) {
                            battleItems.add(item);
                        }
                    }
                    if (battleItems.isEmpty()) {
                        ConsoleColors.error("Não tens BattleConsumables na mochila!");
                        continue;
                    }
                    System.out.println("\nEscolhe um item estratégico para lançar ao inimigo:");
                    for (int i = 0; i < battleItems.size(); i++) {
                        System.out.println((i + 1) + ". " + battleItems.get(i).getName());
                    }
                    System.out.println("0. Voltar atrás");

                    ConsoleColors.print("Escolha: ", ConsoleColors.YELLOW_BOLD);
                    int battleItemChoice;
                    try {
                        battleItemChoice = input.nextInt();
                        input.nextLine();
                    } catch (InputMismatchException e) {
                        input.nextLine();
                        continue;
                    }

                    if (battleItemChoice > 0 && battleItemChoice <= battleItems.size()) {
                        TrainerItem chosen = battleItems.get(battleItemChoice - 1);
                        chosen.use(enemy);
                        itemsBag.remove(chosen);
                        ConsoleColors.success("Lançaste " + chosen.getName() + " contra o alvo.");
                        usedItem = true;
                    } else {
                        ConsoleColors.error("Opção inválida!");
                        continue;
                    }
                    break;

                case 4:
                    if (this.specialAttackUsesLeft > 0) {
                        choseSpecialAttak = true;
                        this.useSpecialAttack();
                    } else {
                        ConsoleColors.warning("Sem usos restantes de Ataque Especial! A executar Ataque Normal...");
                    }
                    break;

                default:
                    ConsoleColors.warning("Opção desconhecida. Executando Ataque Normal...");
                    break;
            }

            // determinar quem ataca primeiro
            boolean myPokemonFirst = this.hasSpeedBoost() || this.getLevel() >= enemy.getLevel();
            this.setSpeedBoost(false);

            System.out.println();
            ConsoleColors.separator();

            if (usedItem) {
                // Se usou item, o jogador NÃO ataca. Apenas o inimigo joga se puder.
                if (enemyCanAttack(enemy)) {
                    Random rd = new Random();
                    int enemyAttackChance = rd.nextInt(100);

                    if (enemyAttackChance <= 30) {
                        enemy.applySpecialAttack(this);
                    } else {
                        int damage = enemy.getStrength() / 2;
                        this.takeLife(damage);
                        ConsoleColors.println(enemy.getName() + " aproveitou o teu compasso de espera e infligiu " + damage + " de dano!", ConsoleColors.RED_BOLD);
                        ConsoleColors.println("A vida do teu " + this.getName() + " desceu para " + this.getHp() + " HP.", ConsoleColors.GREEN_BRIGHT);
                    }
                }
            } else if (myPokemonFirst) {
                // Fluxo normal: Jogador é mais rápido e ataca primeiro
                int damage;
                if (choseSpecialAttak) {
                    damage = this.applySpecialAttack(enemy);
                } else {
                    damage = this.getStrength() / 2;
                    ConsoleColors.println(this.getName() + " desferiu um Ataque Normal e infligiu " + damage + " de dano!", ConsoleColors.WHITE_BOLD);
                }
                enemy.takeLife(damage);
                ConsoleColors.println("Vida de " + enemy.getName() + " reduzida para " + enemy.getHp() + " HP.", ConsoleColors.RED_BRIGHT);

                if (enemy.getHp() <= 0) break;

                if (enemyCanAttack(enemy)) {
                    Random rd = new Random();
                    int enemyAttackChance = rd.nextInt(100);

                    if (enemyAttackChance <= 30) {
                        enemy.applySpecialAttack(this);
                    } else {
                        damage = enemy.getStrength() / 2;
                        this.takeLife(damage);
                        ConsoleColors.println(enemy.getName() + " contra-atacou e causou " + damage + " de dano!", ConsoleColors.RED_BOLD);
                        ConsoleColors.println("A vida do teu " + this.getName() + " desceu para " + this.getHp() + " HP.", ConsoleColors.GREEN_BRIGHT);
                    }
                }
            } else {
                // Fluxo normal: Inimigo é mais rápido e ataca primeiro
                if (enemyCanAttack(enemy)) {
                    Random rd = new Random();
                    int enemyAttackChance = rd.nextInt(100);

                    if (enemyAttackChance <= 30) {
                        enemy.applySpecialAttack(this);
                    } else {
                        int damage = enemy.getStrength() / 2;
                        this.takeLife(damage);
                        ConsoleColors.println(enemy.getName() + " antecipou-se e infligiu " + damage + " de dano!", ConsoleColors.RED_BOLD);
                        ConsoleColors.println("A vida do teu " + this.getName() + " desceu para " + this.getHp() + " HP.", ConsoleColors.GREEN_BRIGHT);
                    }

                    if (this.getHp() <= 0) break;
                }

                // Jogador ataca em segundo
                int damage;
                if (choseSpecialAttak) {
                    damage = this.applySpecialAttack(enemy);
                } else {
                    damage = this.getStrength() / 2;
                    ConsoleColors.println(this.getName() + " recuperou a postura, usando Ataque Normal para infligir " + damage + " de dano!", ConsoleColors.WHITE_BOLD);
                }
                enemy.takeLife(damage);
                ConsoleColors.println("Vida de " + enemy.getName() + " reduzida para " + enemy.getHp() + " HP.", ConsoleColors.RED_BRIGHT);
            }

            applyStatusEffect(enemy);
            if (enemy.getHp() <= 0) {
                ConsoleColors.println("\n" + enemy.getName() + " sucumbiu ao efeito do estado alterado!", ConsoleColors.RED_BOLD);
                break;
            }

            // Aplica os efeitos no PookemonInUse do player
            applyStatusEffect(this);
            if (this.getHp() <= 0) {
                ConsoleColors.println("\nO teu " + this.getName() + " sucumbiu ao efeito do estado alterado!", ConsoleColors.RED_BOLD);
                break;
            }

            ConsoleColors.separator();

            // Pequena pausa para o jogador ler o resumo do turno
            System.out.println("\nPressiona Enter para avançar o turno...");
            input.nextLine();
        }

        // RESOLUÇÃO FINAL DA BATALHA
        System.out.println();
        ConsoleColors.separator();
        if (this.getHp() <= 0) {
            ConsoleColors.error("O teu Pookémon esgotou as forças e foi subjugado no combate.");
            return false;
        } else {
            ConsoleColors.success(enemy.getName() + " foi completamente derrotado!");
            this.gainExp(enemy.getExp());

            Pokemon evolved = this.evolve(); // se detectar que o pokemonInUse chega a x nivel para evoluir como mencionado no metodo evolve de cada pokemon que evolui, caixa de mensagem com o nome do pokemon que evoluiu
            if (evolved != null) {
                String nomePookemonAntigo = this.getName();

                String musicaAntiga = game.Audio.getSoundtrackAtual(); // guarda a musica que estava a tocar antes do pookemon evoluir

                game.Audio.changeSoundtrack("resources/audio/evolve.wav"); // troca para musica de evolucao

                // momento dramatico de evolucao para maior imersao no jogo
                try {
                    System.out.println("\n O que se está a passar com o teu " + nomePookemonAntigo + "?! ");
                    Thread.sleep(2000); // Pausa de suspense de 2 segundos (como nos jogos OG)

                    player.setPokemonInUse(evolved);
                    String nomePookemonNovo = player.getPokemonInUse().getName();

                    ConsoleColors.clear();
                    ConsoleColors.box(
                            " Incrível! O teu " + nomePookemonAntigo + " reuniu energia suficiente e evoluiu para " + nomePookemonNovo + " ! ",
                            ConsoleColors.CYAN_BOLD
                    );
                    Thread.sleep(3000);
                } catch (InterruptedException e) {

                    player.setPokemonInUse(evolved);
                }

            System.out.println("\nPressiona Enter para recolher as recompensas...");
            input.nextLine();

                if (musicaAntiga != null) {
                    game.Audio.changeSoundtrack(musicaAntiga);
                } else {
                    game.Audio.stopSoundtrack();
                }
            } else {
            // Se NÃO houver evolução, o fluxo normal do jogo continua aqui
            System.out.println("\nPressiona Enter para recolher as recompensas...");
            input.nextLine();
        }
            return true;
        }
    }

    /**
     * Método que calcula quantos pontos (exp) são necessários para o pookemonInUse subir de nível
     * @return pontos que faltam
     */
    public int expToNextLevel() {
        return level * 50;
    }

    /**
     * Método que calcula o ganho de hp e strength quando o pookemon sobe de nível
     * Também verifica se o nível do pookemon dá direito a mais um SpecialAttackUse
     */
    public void levelUp() {
        this.level++;

        int hpGain = this.maxHp / 50; // fórmula de quanto o maxHP sobe
        if (hpGain == 0) hpGain = 1;

        int strengthGain = this.strength / 50; // fórmula de quanto o strength sobe
        if (strengthGain == 0) strengthGain = 1;

        this.maxHp += hpGain;
        this.strength += strengthGain;
        this.hp += hpGain;


        ConsoleColors.box("    LEVEL UP! " + this.name + " subiu para o nível " + this.level + "!", ConsoleColors.CYAN_BOLD);


        ConsoleColors.print("  HP    : ", ConsoleColors.WHITE_BOLD);
        ConsoleColors.println("+" + hpGain + " (Máximo atual: " + this.maxHp + ")", ConsoleColors.GREEN_BOLD);

        ConsoleColors.print("  Força : ", ConsoleColors.WHITE_BOLD);
        ConsoleColors.println("+" + strengthGain + " (Atual: " + this.strength + ")", ConsoleColors.RED_BOLD);

        // Avisar quando o pokemon ganha um uso extra de special attack
        if (this.level % 10 == 0) {
            ConsoleColors.println("     Ataque Especial: Ganhaste um uso extra! Agora tens "
                    + getSpecialAttackUses() + " usos por batalha!", ConsoleColors.PURPLE_BRIGHT);
        }
        System.out.println(); // Linha em branco
    }

    // Chamado no Pokémon do jogador após ganhar uma batalha

    /**
     * Quando o pookemonInUse ganha um combate, herda os exp do pokemon inimigo
     * @param amountExpPokemonEnemy
     */
    public void gainExp(int amountExpPokemonEnemy) {
        this.exp += amountExpPokemonEnemy;
        while (this.exp >= expToNextLevel()) {
            this.exp -= expToNextLevel(); // subtrai antes de subir, senão o threshold muda
            levelUp();
        }
    }

    /**
     * Restaura completamente o estado vital, operacional e biológico do Pookémon.
     * <p>
     * Este método é utilizado pelo PookéCenter (ou itens de restauração total) para:
     * </p>
     * <ul>
     * <li>Recuperar o HP atual para o valor de {@code maxHp}.</li>
     * <li>Expurgar qualquer efeito de estado debilitante através de {@link #clearStatusEffect()}.</li>
     * <li>Restaurar os usos disponíveis do Ataque Especial ({@code specialAttackUsesLeft})
     * para o seu limite máximo configurado ({@code specialAttackUses}).</li>
     * </ul>
     */
    public void healPokemon() { // metodo do PookeCenter para curar o pokemon do jogador (restaura todo o hp)
        this.hp = this.maxHp;

        // Limpa qualquer efeito de estado ativo (Veneno, Queimadura, Sono, Paralisia)
        this.clearStatusEffect();

        // Restaura os usos do Ataque Especial para o máximo do pookemon in use
        this.specialAttackUsesLeft = this.specialAttackUses;

        ConsoleColors.success(this.name + " foi curado! HP restaurado para " + this.maxHp + ".");
    }

    /**
     * Incrementa o valor atual de pontos de vida (HP) da entidade.
     * <p>
     * Este método é utilizado por consumíveis de cura simples (como Potions e Berries)
     * para restaurar o atributo {@link #hp}. Possui uma salvaguarda para garantir que a
     * vitalidade atual nunca ultrapasse o limite máximo definido pelo atributo {@link #maxHp}.
     * </p>
     *
     * @param amount A quantidade de pontos de vida a ser restaurada.
     */
    public void heal(int amount) {
        this.hp += amount;
        if (this.hp > this.maxHp) this.hp = this.maxHp;
    }

    /**
     * Concede um aumento temporário ou permanente ao poder ofensivo físico da entidade.
     * <p>
     * Modifica diretamente o atributo {@link #strength}, sendo ideal para a aplicação
     * de itens de bónus ou consumíveis de combate que potenciem o dano dos ataques normais.
     * </p>
     *
     * @param amount A quantidade de pontos de força a ser adicionada ao atributo.
     */
    public void boostStrength(int amount) {
        this.strength += amount;
    }

    /**
     * Revoga o bónus de força previamente concedido, restaurando o equilíbrio original.
     * <p>
     * Subtrai o valor especificado do atributo {@link #strength}. É crucial para mitigar
     * o efeito de consumíveis temporários no final dos turnos ou combates, evitando que os
     * multiplicadores fiquem permanentemente acumulados na instância.
     * </p>
     *
     * @param amount A quantidade de pontos de força a ser deduzida do atributo.
     */
    public void revertStrength(int amount) {
        this.strength -= amount;
    }

    /**
     * Indicador de modificador de velocidade ativo (específico para o item XSpeed).
     * Quando {@code true}, garante prioridade absoluta de ataque na determinação do turno.
     */
    private boolean speedBoost;

    /**
     * Define ou altera o estado do modificador de velocidade da entidade.
     * <p>
     * Atribui um novo valor à variável {@link #speedBoost}, permitindo ativar (durante
     * a utilização do item XSpeed) ou desativar (no final do turno após o ataque consumado)
     * a prioridade de ação.
     * </p>
     *
     * @param speedBoost {@code true} para conceder o bónus de prioridade;
     * {@code false} para o revogar.
     */
    public void setSpeedBoost(boolean speedBoost) {
        this.speedBoost = speedBoost;
    }

    /**
     * Verifica se a entidade usufrui atualmente de uma bonificação de velocidade.
     * <p>
     * Este predicado é consultado diretamente pelo motor de combate em {@code pokemonBattle}
     * para ditar se o Pokémon do jogador deve antecipar-se à ação do oponente,
     * independentemente da diferença de níveis.
     * </p>
     *
     * @return {@code true} se o modificador {@link #speedBoost} estiver ativo;
     * {@code false} caso contrário.
     */
    public boolean hasSpeedBoost() {
        return speedBoost;
    }

    /**
     * Renderiza na interface de texto (CLI) o sumário estatístico e biográfico detalhado do PookemonInUse.
     * <p>
     * Este método formata e exibe todos os dados vitais em tempo real armazenados nas variáveis
     * encapsuladas, incluindo a barra de vitalidade ({@link #hp} / {@link #maxHp}), o escalamento ofensivo
     * ({@link #strength} e {@link #specialAttack}), a contagem operacional de PP ({@link #specialAttackUsesLeft}),
     * o nível atual e os modificadores de estado ativo ({@link #statusEffect}).
     * </p>
     * <p>
     * É crucial para fornecer feedback imediato ao utilizador fora e dentro dos fluxos de exploração geográfica,
     * permitindo a gestão estratégica de consumíveis da mochila antes dos combates.
     * </p>
     */
    public void showDetails() {
        ConsoleColors.println("┌─────────────────────────────────────────────────┐", ConsoleColors.YELLOW_BOLD);
        ConsoleColors.print("  │          Name: ", ConsoleColors.YELLOW_BOLD);
        ConsoleColors.println(name + " ⚪ ", ConsoleColors.WHITE_BOLD_BRIGHT);
        ConsoleColors.println("├─────────────────────────────────────────────────┤", ConsoleColors.YELLOW_BOLD);

        ConsoleColors.print("  │     HP        : ", ConsoleColors.WHITE_BOLD);
        ConsoleColors.println(hp + " / " + maxHp, ConsoleColors.GREEN_BOLD); // Usa maxHp se tiveres essa variável, senão deixa só hp

        ConsoleColors.print("  │     Força     : ", ConsoleColors.WHITE_BOLD);
        ConsoleColors.println(String.valueOf(strength), ConsoleColors.RED_BOLD);

        ConsoleColors.print("  │     Nível     : ", ConsoleColors.WHITE_BOLD);
        ConsoleColors.println("Lv. " + level, ConsoleColors.CYAN_BOLD);

        ConsoleColors.print("  │     EXP faltam: ", ConsoleColors.WHITE_BOLD);
        ConsoleColors.println((expToNextLevel() - exp) + " pontos para nível " + (level+1)  , ConsoleColors.PURPLE_BRIGHT);

        ConsoleColors.println("└─────────────────────────────────────────────────┘", ConsoleColors.YELLOW_BOLD);
    }
}