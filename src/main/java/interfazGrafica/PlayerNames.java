package interfazGrafica;

import interfazGrafica.Eventos.BotonEnviarEventHandler;
import interfazGrafica.Eventos.BotonLimpiarEventHandler;
import interfazGrafica.Eventos.TextoEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import modelo.game.Game;

public class PlayerNames {
    static void start(Stage stage, Game game, Play nextScene) {

        Text bienvenida = new Text("Bienvenido a Kahoot!");
        Stop[] stops = new Stop[] { new Stop(0, Color.BLUE), new Stop(1, Color.RED)};
        LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        bienvenida.setFill(lg1);
        bienvenida.setFont(Font.font("Brush Script MT", FontWeight.BOLD, FontPosture.REGULAR, 65));
        bienvenida.setStrokeWidth(3);
        bienvenida.setStroke(Color.BLACK);
        HBox hBoxBienvenida = new HBox(bienvenida);
        hBoxBienvenida.setAlignment(Pos.CENTER);

        TextField texto = new TextField();
        texto.setPromptText("Escriba aqui");

        Label etiqueta = new Label();

        Button botonEnviar = new Button();
        botonEnviar.setText("Enviar");

        Button botonLimpiarTexto = new Button();
        botonLimpiarTexto.setText("Limpiar texto");

        HBox contenedorHorizontal = new HBox(botonEnviar, botonLimpiarTexto);
        contenedorHorizontal.setSpacing(10);

        VBox contenedorPrincipal = new VBox(hBoxBienvenida, texto, contenedorHorizontal, etiqueta);
        contenedorPrincipal.setSpacing(10);
        contenedorPrincipal.setPadding(new Insets(20));

        BotonLimpiarEventHandler botonLimpiarEventHandler = new BotonLimpiarEventHandler(texto);
        botonLimpiarTexto.setOnAction(botonLimpiarEventHandler);

        BotonEnviarEventHandler botonEnviarEventHandler = new BotonEnviarEventHandler(texto, etiqueta, nextScene, game);
        botonEnviar.setOnAction(botonEnviarEventHandler);

        TextoEventHandler textoEventHandler = new TextoEventHandler(botonEnviar);
        texto.setOnKeyPressed(textoEventHandler);

        Scene scene = new Scene(contenedorPrincipal, 900, 600);
        contenedorPrincipal.setStyle("-fx-background-color: lightblue;");

        stage.setScene(scene);
    }
}
