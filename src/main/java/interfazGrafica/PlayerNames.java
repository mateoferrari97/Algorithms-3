package interfazGrafica;

import interfazGrafica.Eventos.BotonEnviarEventHandler;
import interfazGrafica.Eventos.BotonLimpiarEventHandler;
import interfazGrafica.Eventos.TextoEventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Game;
import modelo.Player;

import java.util.List;

public class PlayerNames {
    static void start(Stage stage, Game game) {
        Text bienvenida = new Text("Bienvenido a Kahoot!");
        bienvenida.setFill(Color.BLACK);
        bienvenida.setStyle("-fx-font: 24 arial;");

        TextField texto = new TextField();
        texto.setPromptText("Escriba aqui");

        Label etiqueta = new Label();

        Button botonEnviar = new Button();
        botonEnviar.setText("Enviar");

        Button botonLimpiarTexto = new Button();
        botonLimpiarTexto.setText("Limpiar texto");

        HBox contenedorHorizontal = new HBox(botonEnviar, botonLimpiarTexto);
        contenedorHorizontal.setSpacing(10);

        VBox contenedorPrincipal = new VBox(bienvenida, texto, contenedorHorizontal, etiqueta);
        contenedorPrincipal.setSpacing(10);
        contenedorPrincipal.setPadding(new Insets(20));

        BotonLimpiarEventHandler botonLimpiarEventHandler = new BotonLimpiarEventHandler(texto);
        botonLimpiarTexto.setOnAction(botonLimpiarEventHandler);

        BotonEnviarEventHandler botonEnviarEventHandler = new BotonEnviarEventHandler(texto, etiqueta, game.getNextPlayer(), stage);
        botonEnviar.setOnAction(botonEnviarEventHandler);

        TextoEventHandler textoEventHandler = new TextoEventHandler(botonEnviar);
        texto.setOnKeyPressed(textoEventHandler);

        Scene scene = new Scene(contenedorPrincipal, 300, 250);
        stage.setScene(scene);
    }
}
