package interfazGrafica;

import javafx.application.Application;
import javafx.stage.Stage;
import modelo.game.Game;

import static constantes.Constantes.GAME_TITLE;


public class Pantalla extends Application{
    private Game game = new Game();

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(GAME_TITLE);
        this.game.init();
        Play nextScene = new Play(stage);
        PlayerNames.start(stage, this.game,nextScene);
        stage.show();
    }
}
