package items;

import entities.Pokemon;

public abstract class TrainerItem {
    private String name;
    private int price;

    public TrainerItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }

    public abstract void use(Pokemon target);
}