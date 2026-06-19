package entities;

import items.BattleConsumable;
import items.Consumable;
import items.Potion;
import items.TrainerItem;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public abstract class Pokemon {
    private String name;
    private int maxHp;
    private int hp;
    private int strength;
    private int level; // nivel do Pokemon
    private int exp; // isto representa os pontos que o pokemon do jogador ganha de outro pokemon rival em battles, mas tambem uso para calcular como o pokemonInUse evolui de nivel
    private StatusEffect statusEffect; // null = sem efeito | se pokemon tiver um estado alterado devido a um consumivel
    private int sleepTurns; // se pokemon tiver sob efeito de um consumivel de sono

    public Pokemon(String name, int maxHp, int hp, int strength, int level, int exp) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = hp;
        this.strength = strength;
        this.level = level;
        this.exp = exp;
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

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public StatusEffect getStatusEffect() { return statusEffect; }

    public void setStatusEffect(StatusEffect effect) {
        if (this.statusEffect == null) {
            this.statusEffect = effect;
            this.sleepTurns = effect == StatusEffect.ASLEEP ? 2 : 0;
            System.out.println(this.name + " ficou " + effect + "!");
        } else {
            System.out.println(this.name + " já tem um status effect!");
        }
    }

    public void clearStatusEffect() {
        this.statusEffect = null;
        this.sleepTurns = 0;
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

    public boolean pokemonBattle(Pokemon enemy, ArrayList<TrainerItem> itemsBag) {
        Scanner input = new Scanner(System.in);

        System.out.println("Batalha entre " + this.getName() + " e " + enemy.getName() + "!");

        while (this.getHp() > 0 && enemy.getHp() > 0) {

            // escolha do jogador no início do turno
            System.out.println("\nO que vais fazer?");
            System.out.println("1. Atacar");
            System.out.println("2. Usar Potion/Consumable");
            System.out.println("3. Usar BattleConsumable no inimigo");
            int choice = input.nextInt();

            boolean usedItem = false;
            int xAttackBoost = 0;

            switch (choice) {
                case 2:
                    // filtrar itens Potion e Consumable da mochila
                    ArrayList<TrainerItem> myItems = new ArrayList<>();
                    for (TrainerItem item : itemsBag) {
                        if (item instanceof Potion || item instanceof Consumable) {
                            myItems.add(item);
                        }
                    }
                    if (myItems.isEmpty()) {
                        System.out.println("Não tens Potions ou Consumables na mochila!");
                        break;
                    }
                    System.out.println("Escolhe um item:");
                    for (int i = 0; i < myItems.size(); i++) {
                        System.out.println((i + 1) + ". " + myItems.get(i).getName());
                    }
                    int itemChoice = input.nextInt();
                    if (itemChoice > 0 && itemChoice <= myItems.size()) {
                        TrainerItem chosen = myItems.get(itemChoice - 1);
                        chosen.use(this); // aplica no meu pokemon
                        itemsBag.remove(chosen); // remove da mochila após usar
                    }
                    break;
                case 3:
                    // filtrar BattleConsumables da mochila
                    ArrayList<TrainerItem> battleItems = new ArrayList<>();
                    for (TrainerItem item : itemsBag) {
                        if (item instanceof BattleConsumable) {
                            battleItems.add(item);
                        }
                    }
                    if (battleItems.isEmpty()) {
                        System.out.println("Não tens BattleConsumables na mochila!");
                        break;
                    }
                    System.out.println("Escolhe um item para usar no inimigo:");
                    for (int i = 0; i < battleItems.size(); i++) {
                        System.out.println((i + 1) + ". " + battleItems.get(i).getName());
                    }
                    int battleItemChoice = input.nextInt();
                    if (battleItemChoice > 0 && battleItemChoice <= battleItems.size()) {
                        TrainerItem chosen = battleItems.get(battleItemChoice - 1);
                        chosen.use(enemy); // aplica no enemy
                        itemsBag.remove(chosen); // remove da mochila após usar
                    }
                    break;
            }

            // determinar quem ataca primeiro
            boolean myPokemonFirst = this.hasSpeedBoost() || this.getLevel() >= enemy.getLevel();
            // reverter XSpeed após decidir ordem
            this.setSpeedBoost(false);

            // ataques
            // pokemon do jogador ataca primeiro
            if (myPokemonFirst) {
                int damage = this.getStrength() / 2;
                enemy.takeLife(damage);
                System.out.println(this.getName() + " causou " + damage + " de dano! "
                        + enemy.getName() + " ficou com " + enemy.getHp() + " HP.");

                if (enemy.getHp() <= 0) break;

                // verifica se enemy pode atacar
                if (enemyCanAttack(enemy)) {
                    damage = enemy.getStrength() / 2;
                    this.takeLife(damage);
                    System.out.println(enemy.getName() + " causou " + damage + " de dano! "
                            + this.getName() + " ficou com " + this.getHp() + " HP.");
                }
            } else {
                // pokemon inimigo ataca primeiro (verifica se consegue atacar por causa de consumiveis de combate)
                if (enemyCanAttack(enemy)) {
                    int damage = enemy.getStrength() / 2;
                    this.takeLife(damage);
                    System.out.println(enemy.getName() + " causou " + damage + " de dano! "
                            + this.getName() + " ficou com " + this.getHp() + " HP.");

                    if (this.getHp() <= 0) break; // caso o pokemon inimigo morra, o ciclo da pokemonBattle para
                }
                // pokemon do jogador ataca sempre
                int damage = this.getStrength() / 2;
                enemy.takeLife(damage);
                System.out.println(this.getName() + " causou " + damage + " de dano! " + enemy.getName() + " ficou com " + enemy.getHp() + " HP.");
            }

            // efeitos de status no enemy no fim do turno
            applyStatusEffect(enemy);
        }
        if (this.getHp() <= 0) {
            System.out.println("O teu Pookémon foi derrotado... Game Over!");
            return false;
        } else {
            System.out.println(enemy.getName() + " foi derrotado!");
            this.gainExp(enemy.getExp());
            //
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

        System.out.println(this.name + " subiu para o nível " + this.level + "!");
        System.out.println("  HP: +" + hpGain + " (max agora: " + this.maxHp + ")");
        System.out.println("  Força: +" + strengthGain + " (agora: " + this.strength + ")");
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
        System.out.println(this.name + " foi curado! HP restaurado para " + this.maxHp + ".");
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

    public void showDetails () {
        System.out.println("********** Pokemon Details *********");
        System.out.println("Name: " + name);
        System.out.println("HP: " + hp);
        System.out.println("Strength: " + strength);
        System.out.println("Level: " + level);
        System.out.println("Exp para próximo nível: " + (expToNextLevel() - exp));
    }
}