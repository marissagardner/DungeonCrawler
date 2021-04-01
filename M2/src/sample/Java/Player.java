package sample.Java;

public class Player {
    private Weapon weapon;
    private Exit lastExit;
    private int health;

    public Player(Weapon weapon) {
        this.weapon = weapon;
        this.health = 100;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void dealDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public Exit getLastExit() {
        return lastExit;
    }

    public void setLastExit(Exit lastExit) {
        this.lastExit = lastExit;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}