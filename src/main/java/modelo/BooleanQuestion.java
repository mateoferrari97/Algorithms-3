package modelo;

public class BooleanQuestion extends Question {
    private Integer penalty = 0;
    private Integer points = 1;

    public BooleanQuestion(String text, CorrectState correct, IncorrectState incorrect) {
        super();
        correct.setPoints(points);
        incorrect.setPenalty(penalty);
        Option optionOne = new Option(correct);
        Option optionTwo = new Option(incorrect);
        answers = new Option[2];
        answers[0] = optionOne;
        answers[1] = optionTwo;
        this.text = text;

    }

}
