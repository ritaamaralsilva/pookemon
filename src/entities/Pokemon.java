package entities;

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

    public abstract boolean pokemonBattle (Pokemon opponent);

    public void showDetails () {
        System.out.println("********** Pokemon Details *********");
        System.out.println("Name: " + name);
        System.out.println("HP: " + hp);
        System.out.println("Strength: " + strength);
        System.out.println("Level: " + level);
        System.out.println("Exp: " + exp);
    }
}