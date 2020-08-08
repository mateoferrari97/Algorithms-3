package interfazGrafica;
import javafx.application.Application;
import javafx.stage.Stage;

public class Pantalla extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Una ventana");

        stage.show();
    }
}
