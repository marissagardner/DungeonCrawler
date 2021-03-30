package sample.Java;

public class Weapon {
    private String name;
    private int damage;

    public Weapon(String name) {
        this.name = name;
        if (name.equals("Sword")) {
            damage = 20;
        } else if (name.equals("Bow")) {
            damage = 14;
        } else if (name.equals("Axe")) {
            damage = 10;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        if (name.equals("Sword")) {
            return (int) (Math.random() * damage) + 10;
        } else if (name.equals("Bow")) {
            return (int) (Math.random() * damage) + 13;
        } else {
            return (int) (Math.random() * damage) + 15;
        }
    }
}