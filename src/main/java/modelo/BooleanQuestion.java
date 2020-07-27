package modelo;
import java.util.ArrayList;
import java.util.List;

public class BooleanQuestion extends Question {
    private List<Option> options = new ArrayList<>();

    public BooleanQuestion(String text, Option correctOption, Option incorrectOption) {
        super();
        this.options.add(correctOption);
        this.options.add(incorrectOption);
        this.text = text;
    }
}
