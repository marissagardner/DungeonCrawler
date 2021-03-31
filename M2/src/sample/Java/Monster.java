package sample.Java;

public class Monster {
    private String name;
    private int damage;
    private int health;
    private String spritePath;

    public Monster(String name) {
        if (name.equals("Rayquaza")) {
            damage = 10;
            health = 40;
            spritePath = "@../../img/rayquaza.png";
        } else if (name.equals("Darkrai")) {
            damage = 8;
            health = 30;
            spritePath = "@../../img/darkrai.png";
        } else if (name.equals("Dialga")) {
            damage = 6;
            health = 50;
            spritePath = "@../../img/dialga.png";
        }
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void dealDamage(int damage) {
        this.health -= damage;
        if(this.health < 0) {
            this.health = 0;
        }
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        if (name.equals("Sword")) {
            return (int) (Math.random() * damage) + 3;
        } else if (name.equals("Bow")) {
            return (int) (Math.random() * damage) + 7;
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