/*package interfazGrafica;

import consumables.Multiplicator;
import exceptions.InvalidSizeException;
import interfazGrafica.Draggable.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Game;
import modelo.options.CorrectOptionScorer;
import modelo.options.IncorrectOptionScorer;
import modelo.options.Option;
import modelo.questions.GroupChoiceQuestion;
import modelo.questions.Question;
import modelo.scorers.BooleanScorer;
import modelo.scorers.QuestionScorer;

import java.util.Arrays;
import java.util.List;

public class GroupChoice {
    public static void start(Stage stage, Game game, Play nextScene) throws InvalidSizeException {
        stage.setTitle("java-buddy.blogspot.com");
        VBox root = new VBox();
        Scene scene = new Scene(root, 800, 800);

        List<Option> options = Arrays.asList(
                new Option("A", new CorrectOptionScorer()),
                new Option("B", new CorrectOptionScorer()),
                new Option("C", new CorrectOptionScorer()),
                new Option("1", new IncorrectOptionScorer()),
                new Option("2", new IncorrectOptionScorer()));


        QuestionScorer scorer = new BooleanScorer();
        Question question = new GroupChoiceQuestion("Separar en numeros y letras", options, scorer, new Multiplicator());

        Label pregunta = new Label();
        pregunta.setTextFill(Color.BLACK);
        pregunta.setFont(new Font("Arial", 30));
        pregunta.setText("WIPIIIII");

        Text text1 = new Text("TEXT 1");
        text1.setScaleX(2.0);
        text1.setScaleY(2.0);

        Text text2 = new Text("TEXT 2");
        text2.setScaleX(2.0);
        text2.setScaleY(2.0);

        Text text3 = new Text("TEXT 3");
        text3.setScaleX(2.0);
        text3.setScaleY(2.0);

        Text text4 = new Text("TEXT 4");
        text4.setScaleX(2.0);
        text4.setScaleY(2.0);

        Text grupoATexto = new Text("Grupo A");
        grupoATexto.setScaleX(2.0);
        grupoATexto.setScaleY(2.0);

        Text grupoBTexto = new Text("Grupo B");
        grupoBTexto.setScaleX(2.0);
        grupoBTexto.setScaleY(2.0);


        setAsDraggable(text1, question.getOptions());
        setAsDraggable(text2, question.getOptions());
        setAsDraggable(text3, question.getOptions());
        setAsDraggable(text4, question.getOptions());

        VBox grupoA = new VBox();
        VBox grupoB = new VBox();

        setAsTarget(grupoATexto, grupoA);
        setAsTarget(grupoBTexto, grupoB);

        ///////////////////////////////////////////////////////////////
        HBox contenedorPregunta = new HBox(pregunta);
        contenedorPregunta.setAlignment(Pos.CENTER);
        ///////////////////////////////////////////////////////////////
        HBox contenedorOpciones = new HBox();
        createAnswerOptionsButtons(contenedorOpciones, question);
        ///////////////////////////////////////////////////////////////
        HBox contenedorGrupos = new HBox();
        VBox contenedorGrupoA = new VBox();
        VBox contenedorGrupoB = new VBox();
        contenedorGrupos.getChildren().add(contenedorGrupoA);
        contenedorGrupos.getChildren().add(contenedorGrupoB);
        contenedorGrupoA.getChildren().add(grupoATexto);
        contenedorGrupoA.getChildren().add(grupoA);
        contenedorGrupoB.getChildren().add(grupoBTexto);
        contenedorGrupoB.getChildren().add(grupoB);
        contenedorGrupos.setAlignment(Pos.CENTER);
        contenedorGrupos.setSpacing(200);
        contenedorGrupoA.setSpacing(15);
        contenedorGrupoB.setSpacing(15);
        grupoA.setSpacing(10);
        grupoB.setSpacing(10);
        ////////////////////////////////////////////////////

        root.getChildren().add(contenedorPregunta);
        root.getChildren().add(contenedorOpciones);
        root.getChildren().add(contenedorGrupos);


        root.setSpacing(30);
        stage.setScene(scene);
        stage.show();
        stage.show();


    }
    static void setAsDraggable(Text texto, List<Option> options) {
        texto.setOnDragDetected(new DragDetectedEvenHandler(texto));
        texto.setOnDragDone(new DragDoneEventHandler(texto, options));
    }

    static void setAsTarget(Text target, VBox vBox) {
        target.setOnDragOver(new DragOverEventHandler());
        target.setOnDragEntered(new DragEnteredEventHandler(target));
        target.setOnDragExited(new DragExitedEventHandler(target));
        target.setOnDragDropped(new DragDroppedEventHandler(vBox));
    }

    private static void createAnswerOptionsButtons(HBox contenedorOpciones, Question question) {
        Text[] opciones = getQuestionOptions(question);
        HBox buttonsContainer = new HBox();
        for(Text aText : opciones){
            contenedorOpciones.getChildren().add(aText);
        }
        contenedorOpciones.setAlignment(Pos.CENTER);
        contenedorOpciones.setSpacing(100);
    }

    private static Text[] getQuestionOptions(Question question) {
        String[] answerOptions = question.getAnswerOptions();
        Text[] opciones = new Text[answerOptions.length];
        int i = 0;
        for(String aString : answerOptions){
            opciones[i] = new Text(aString);
            (opciones[i]).setScaleX(2.0);
            (opciones[i]).setScaleY(2.0);
            setAsDraggable(opciones[i], question.getOptions());
            i++;
        }
        return opciones;
    }

}
*/