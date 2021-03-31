package sample.Java;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BattleScreenController {
    @FXML
    private ImageView character;

    @FXML
    private ImageView characteraxe;

    @FXML
    private ImageView characterbow;

    @FXML
    private Group man;

    @FXML
    private ImageView monster;

    @FXML
    private Text playerHealth;

    @FXML
    private Text monsterHealth;

    @FXML
    private Text gameCommentary;

    ImageView player;

    int playerDamage;
    int monsterDamage;
    String mHealth;
    String pHealth;
    String commentary;
    boolean battleOver;
    boolean playerTurn;

    @FXML
    public void initialize() {
        if (Settings.getPlayer().getWeapon().getName().equals("Sword")) {
            player = character;
        } else if (Settings.getPlayer().getWeapon().getName().equals("Bow")) {
            player = characterbow;
        } else {
            player = characteraxe;
        }

        pHealth = "Health: " + Settings.getPlayer().getHealth();
        mHealth = "Health: " + Settings.getCurrentRoom().getMonster().getHealth();
        commentary = "You are battling "
                + Settings.getCurrentRoom().getMonster().getName()
                + ". Press space to attack.";
        playerHealth.setText(pHealth);
        monsterHealth.setText(mHealth);
        gameCommentary.setText(commentary);

        battleOver = false;
        playerTurn = true;

        man.getChildren().setAll(player);
        monster.setImage(new Image(Settings.getCurrentRoom().getMonster().getSpritePath()));
    }

    @FXML
    public void move(KeyEvent event) throws IOException, InterruptedException {
        if(event.getCode().equals(KeyCode.SPACE)) {
            if(playerTurn) {
                playerDamage = Settings.getPlayer().getWeapon().getDamage();
                Settings.getCurrentRoom().getMonster().dealDamage(playerDamage);
                mHealth = "Health: " + Settings.getCurrentRoom().getMonster().getHealth();
                monsterHealth.setText(mHealth);

                commentary = "You dealt " + playerDamage
                        + " damage to " + Settings.getCurrentRoom().getMonster().getName() + ".";
                gameCommentary.setText(commentary);

                playerTurn = false;

                PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
                pause.setOnFinished(pEvent -> {
                    monsterTurn(event);
                });
                pause.play();
            }
        }
    }

    public void monsterTurn(KeyEvent event) {
        if(Settings.getCurrentRoom().getMonster().getHealth() == 0) {
            commentary = "You defeated "
                    + Settings.getCurrentRoom().getMonster().getName() + ".";
            gameCommentary.setText(commentary);
            battleOver = true;

            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(pEvent -> {
                try {
                    backToDungeon(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            pause.play();
        }

        if(!battleOver) {
            monsterDamage = Settings.getCurrentRoom().getMonster().getDamage();
            Settings.getPlayer().dealDamage(monsterDamage);
            pHealth = "Health: " + Settings.getPlayer().getHealth();
            playerHealth.setText(pHealth);

            commentary = Settings.getCurrentRoom().getMonster().getName()
                    + " dealt " + monsterDamage + " damage to you.";
            gameCommentary.setText(commentary);

            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(pEvent -> {
                gameOver(event);
            });
            pause.play();
        }
    }

    public void gameOver(KeyEvent event) {
        if(Settings.getPlayer().getHealth() == 0) {
            commentary = "You lost against " + Settings.getCurrentRoom().getMonster().getName() + ".";
            gameCommentary.setText(commentary);

            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(pEvent -> {
                try {
                    gameOverScreen(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            pause.play();
        } else {
            playerTurn = true;
        }
    }

    public void gameOverScreen(KeyEvent event) throws IOException {
        Parent menuParent = FXMLLoader.load(
                getClass().getResource("../FXML/game_over.fxml"));
        Scene menuScene = new Scene(menuParent, 960, 600);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(menuScene);
        window.setMinHeight(600);
        window.setMinWidth(960);
        window.setMaxHeight(600);
        window.setMaxWidth(960);
        window.show();
        menuScene.getRoot().requestFocus();
    }

    public void backToDungeon(KeyEvent event) throws IOException {
        Settings.getCurrentRoom().setDefeated(true);
        Parent menuParent = FXMLLoader.load(
                getClass().getResource(Settings.getCurrentRoom().getRoomPath()));
        Scene menuScene = new Scene(menuParent, 960, 600);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(menuScene);
        window.setMinHeight(600);
        window.setMinWidth(960);
        window.setMaxHeight(600);
        window.setMaxWidth(960);
        window.show();
        menuScene.getRoot().requestFocus();
    }
}
