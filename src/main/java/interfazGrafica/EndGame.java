package interfazGrafica;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modelo.game.Game;


public class EndGame extends VBox {
    Stage stage;


    public EndGame(Stage stage, Game game) {
        super();

        this.stage = stage;

        game.getNextRound();

        Label winner = new Label();
        winner.setTextFill(Color.GREEN);
        winner.setFont(new Font("Arial", 30));

        Label loser = new Label();
        loser.setTextFill(Color.RED);
        loser.setFont(new Font("Arial", 30));

        Label draw = new Label();
        draw.setTextFill(Color.BLUE);
        draw.setFont(new Font("Arial", 30));

        if(game.getDraw() == true){
            draw.setText("ES UN EMPATE!, LOS 2 JUGADORES TUVIERON: " + game.getFirstPlayer().getPoints().toString() + " PUNTOS");
        }

        else{
            winner.setText("EL GANADOR ES: " + game.getWinner().getName() + " CON " + game.getWinner().getPoints().toString() + " PUNTOS");
            loser.setText("EL PERDEDOR ES: " + game.getLoser().getName() + " CON " + game.getLoser().getPoints().toString() + " PUNTOS");
        }

        Label finish = new Label("END OF GAME");
        finish.setTextFill(Color.BLACK);
        finish.setFont(new Font("Arial", 30));

        this.getChildren().add(finish);
        this.getChildren().add(draw);
        this.getChildren().add(winner);
        this.getChildren().add(loser);
        this.setSpacing(50);
        this.setPadding(new Insets(350));
        Scene end = new Scene(this,300,250);
        stage.setScene(end);
        stage.setFullScreen(true);
    }
}
