package sample.Java;

public class Monster {
    private String name;
    private int damage;
    private String spritePath;

    public Monster(String name) {
        if (name.equals("Rayquaza")) {
            damage = 10;
            spritePath = "@../../img/rayquaza.png";
        } else if (name.equals("Darkrai")) {
            damage = 8;
            spritePath = "@../../img/darkrai.png";
        } else if (name.equals("Dialga")) {
            damage = 6;
            spritePath = "@../../img/dialga.png";
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
            return (int) (Math.random() * damage) + 3;
        } else if (name.equals("Bow")) {
            return (int) (Math.random() * damage) + 4;
        } else {
            return (int) (Math.random() * damage) + 5;
        }
    }

    public String getSpritePath() {
        return spritePath;
    }

    public void setSpritePath(String spritePath) {
        this.spritePath = spritePath;
    }

}