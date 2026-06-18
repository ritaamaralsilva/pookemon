package entities;

import java.util.Scanner;

public abstract class Pokemon {
    private String name;
    private int maxHp;
    private int hp;
    private int strength;
    private int level; // nivel do Pokemon
    private int exp; // isto representa os pontos que o pokemon do jogador ganha de outro pokemon rival em battles, mas tambem uso para calcular como o pokemonInUse evolui de nivel

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

    public void takeLife(int damage) { // método que calcula o dano em determinado pokemon
        this.hp -= damage;
        if (this.hp < 0) this.hp = 0;
    }

    public boolean pokemonBattle(Pokemon enemy) {
        Scanner input = new Scanner(System.in);

        System.out.println("Batalha entre " + this.getName() + " e " + enemy.getName() + "!");

        while (this.getHp() > 0 && enemy.getHp() > 0) {

            // escolha do jogador no início do turno
            System.out.println("\nO que vais fazer?");
            System.out.println("1. Atacar");
            System.out.println("2. Usar Potion");
            System.out.println("3. Usar Consumable");
            int choice = input.nextInt();

            switch (choice) {
                case 2:
                    // usar potion - a implementar
                    break;
                case 3:
                    // usar consumable - a implementar
                    break;
            }

            // após a escolha, ataques por ordem de nível
            if (this.getLevel() >= enemy.getLevel()) {
                // meu pokemon ataca primeiro
                int damage = this.getStrength() / 2;
                enemy.takeLife(damage);
                System.out.println(this.getName() + " causou " + damage + " de dano! "
                        + enemy.getName() + " ficou com " + enemy.getHp() + " HP.");

                if (enemy.getHp() <= 0) break;

                // inimigo ataca a seguir
                damage = enemy.getStrength() / 2;
                this.takeLife(damage);
                System.out.println(enemy.getName() + " causou " + damage + " de dano! "
                        + this.getName() + " ficou com " + this.getHp() + " HP.");

            } else {
                // inimigo ataca primeiro
                int damage = enemy.getStrength() / 2;
                this.takeLife(damage);
                System.out.println(enemy.getName() + " causou " + damage + " de dano! "
                        + this.getName() + " ficou com " + this.getHp() + " HP.");

                if (this.getHp() <= 0) break;

                // meu pokemon ataca a seguir
                damage = this.getStrength() / 2;
                enemy.takeLife(damage);
                System.out.println(this.getName() + " causou " + damage + " de dano! "
                        + enemy.getName() + " ficou com " + enemy.getHp() + " HP.");
            }
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

    public void showDetails () {
        System.out.println("********** Pokemon Details *********");
        System.out.println("Name: " + name);
        System.out.println("HP: " + hp);
        System.out.println("Strength: " + strength);
        System.out.println("Level: " + level);
        System.out.println("Exp para próximo nível: " + (expToNextLevel() - exp));
    }
}