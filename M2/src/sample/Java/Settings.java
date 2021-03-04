package sample.Java;

public class Settings {
    private static Difficulty gameDifficulty;
    private static int money;
    private static GameState gameState;

    public static Difficulty getGameDifficulty() {
        return gameDifficulty;
    }

    public static void setGameDifficulty(Difficulty gameDifficulty) {
        Settings.gameDifficulty = gameDifficulty;
    }

    public static int getMoney() {
        return money;
    }

    public static void setMoney(int money) {
        Settings.money = money;
    }

    public static GameState getGameState() {
        return gameState;
    }

    public static void setGameState(GameState gameState) {
        Settings.gameState = gameState;
    }
}
