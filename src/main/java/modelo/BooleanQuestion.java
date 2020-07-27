package modelo;

public class BooleanQuestion extends Question {
    private Integer points = 1;

    public BooleanQuestion(String text, int correctOption, int incorrectOption, String[] names) {
        super();
        this.text = text;
        this.score = new BooleanSB();
        answers = new Option[2];
        int chosenOption = correctOption;
        State state = new CorrectState(points);
        for (int i = 0; i <= 1; i++) {
            Option option = new Option(state, names[chosenOption]);
            answers[chosenOption] = option;
            chosenOption = incorrectOption;
            state = new IncorrectState();
        }

    }

    public void compareAnswersFromPlayers(Integer[] answers, Player[] players) {
        int i = 0;
        for (Player aPlayer : players) {
            this.answers[answers[i]].changeScore(this.score);
            this.score.scorePlayer(aPlayer);
            i++;
        }

    }

    @Override
    public void compareAnswersFromPlayers(Integer[] playerOneAnswers, Integer[] playerTwoAnswers, Player[] players) {

    }
}