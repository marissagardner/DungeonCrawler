package sample.Java;

public class Player {
    private Weapon weapon;
    private Exit lastExit;
    private int health;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Player(Weapon weapon) {
        this.weapon = weapon;
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