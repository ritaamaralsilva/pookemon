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

    public String getName() {
        return name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getHp() {
        return hp;
    }

    public int getStrength() {
        return strength;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public int getSpecialAttackUses() {
        // começa com 1 uso, ganha +1 a cada 10 níveis
        // nível 1-9  → 1 uso
        // nível 10-19 → 2 usos
        // nível 20-29 → 3 usos
        // etc.
        return 1 + (this.level / 10); // isto incrementa o nr de vezes que o pokemon pode usar special attack
    }

    public int getSpecialAttackUsesLeft() {
        return specialAttackUsesLeft;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public StatusEffect getStatusEffect() { return statusEffect; }

    public int getSleepTurns() {
        return sleepTurns;
    }

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
    public void resetSpecialAttackUses() { // isto faz reset ao nr de vezes que o pokemon pode usar o special attack (vou implementar que no PookeCenter, para alem do Hp, o PP do Special attack tambem volta ao nr maximo que o pokemon pode usar naquele nivel)
        this.specialAttackUsesLeft = getSpecialAttackUses();
    }

    public void useSpecialAttack() {
        // se tiver PP no Special attack, pokemon usa e decrementa no specialAttackUsesLeft
        if (specialAttackUsesLeft > 0) specialAttackUsesLeft--;
    }

    public void clearStatusEffect() {
        this.statusEffect = null;
        this.sleepTurns = 0;
    }

    // metodo do specialAttack default (depois implemento override nos starters)
    public int applySpecialAttack (Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2;
        System.out.println(this.getName() + " usou Ataque Especial e causou " + damage + " de dano!");
        return damage;
    }

    public void takeLife(int damage) { // método que calcula o dano em determinado pokemon
        this.hp -= damage;
        if (this.hp < 0) this.hp = 0;
    }

    public void decrementSleepTurns() {
        if (this.sleepTurns > 0) {
            this.sleepTurns--;
            if (this.sleepTurns == 0) {
                clearStatusEffect();
                System.out.println(this.name + " acordou!");
            }
        }
    }

    private void applyStatusEffect(Pokemon target) {
        if (target.getStatusEffect() == null) return;

        switch (target.getStatusEffect()) {
            case POISONED:
                int poisonDamage = target.getHp() / 8; // ~12.5% como no pokemon original
                if (poisonDamage == 0) poisonDamage = 1;
                target.takeLife(poisonDamage);
                System.out.println(target.getName() + " sofreu " + poisonDamage + " de dano pelo veneno!");
                break;
            case BURNED:
                int burnDamage = target.getHp() / 16; // ~6% como no pokemon original
                if (burnDamage == 0) burnDamage = 1;
                target.takeLife(burnDamage);
                System.out.println(target.getName() + " sofreu " + burnDamage + " de dano pela queimadura!");
                break;
            case ASLEEP:
                target.decrementSleepTurns();
                System.out.println(target.getName() + " está a dormir!");
                break;
            case PARALYZED:
                // tratado na lógica de ataque do metodo pokemonBattle
                break;
        }
    }
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

    public Pokemon evolve() {
        return null;
    }

    public boolean pokemonBattle(Pokemon enemy, Trainer player) {
        ArrayList<TrainerItem> itemsBag = player.getItemsBag();
        Scanner input = new Scanner(System.in);

        ConsoleColors.clear();
        ConsoleColors.title("BATALHA POOKÉMON");
        ConsoleColors.separator();
        ConsoleColors.story("Confronto iniciado: " + this.getName() + " VS " + enemy.getName() + "!");
        ConsoleColors.separator();

        while (this.getHp() > 0 && enemy.getHp() > 0) {

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

    public int expToNextLevel() { // metodo que define quantos pontos (exp) são necessários para o pokemon subir de nível
        return level * 50;
    }

    public void levelUp() { // método que calcula o ganho de hp e strength quando o pokemon sobe de nível
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
    public void gainExp(int amountExpPokemonEnemy) { // quando o pokemonInUse ganha uma battle, herda os exp do pokemon inimigo
        this.exp += amountExpPokemonEnemy;
        while (this.exp >= expToNextLevel()) {
            this.exp -= expToNextLevel(); // subtrai antes de subir, senão o threshold muda
            levelUp();
        }
    }

    public void healPokemon() { // metodo do PookeCenter para curar o pokemon do jogador (restaura todo o hp)
        this.hp = this.maxHp;
        ConsoleColors.success(this.name + " foi curado! HP restaurado para " + this.maxHp + ".");
    }
    // metodos para Potion e Consumiveis
    // para Potion e Berry
    public void heal(int amount) {
        this.hp += amount;
        if (this.hp > this.maxHp) this.hp = this.maxHp;
    }

    // para XAttack
    public void boostStrength(int amount) {
        this.strength += amount;
    }

    public void revertStrength(int amount) {
        this.strength -= amount;
    }

    // para XSpeed
    private boolean speedBoost;

    public void setSpeedBoost(boolean speedBoost) {
        this.speedBoost = speedBoost;
    }

    public boolean hasSpeedBoost() {
        return speedBoost;
    }

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