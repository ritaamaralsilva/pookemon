package entities;

public abstract class Pokemon {
    private String name;
    private int maxHp;
    private int hp;
    private int strength;
    private int level; // nivel do Pokemon

    public Pokemon(String name, int maxHp, int hp, int strength, int level) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = hp;
        this.strength = strength;
        this.level = level;
    }

    public abstract boolean pokemonBattle (Pokemon opponent);

    public void showDetails () {
        System.out.println("********** Pokemon Details *********");
        System.out.println("Name: " + name);
        System.out.println("HP: " + hp);
        System.out.println("Strength: " + strength);
    }
}