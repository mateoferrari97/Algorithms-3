package interfazGrafica;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
        winner.setFont(new Font("Arial", 20));

        Label loser = new Label();
        loser.setTextFill(Color.RED);
        loser.setFont(new Font("Arial", 20));

        Label draw = new Label();
        draw.setTextFill(Color.BLUE);
        draw.setFont(new Font("Arial", 20));

        if(game.getDraw() == true){
            draw.setText("ES UN EMPATE!, LOS 2 JUGADORES TUVIERON: " + game.getFirstPlayer().getPoints().toString() + " PUNTOS");
        }

        else{
            winner.setText("EL GANADOR ES: " + game.getWinner().getName() + " CON " + game.getWinner().getPoints().toString() + " PUNTOS");
            loser.setText("EL PERDEDOR ES: " + game.getLoser().getName() + " CON " + game.getLoser().getPoints().toString() + " PUNTOS");
        }

        Text finish = new Text();
        finish.setFill(Color.RED);
        finish.setStrokeWidth(0.5);
        finish.setStroke(Color.BLACK);
        finish.setScaleX(5);
        finish.setScaleY(5);
        finish.setText("TERMINÓ EL JUEGO");

        this.getChildren().add(finish);
        this.getChildren().add(draw);
        this.getChildren().add(winner);
        this.getChildren().add(loser);
        this.setSpacing(50);
        this.setAlignment(Pos.CENTER);

        Scene end = new Scene(this,900,600);
        this.setStyle("-fx-background-color: lightgrey;");
        stage.setScene(end);
    }
}
