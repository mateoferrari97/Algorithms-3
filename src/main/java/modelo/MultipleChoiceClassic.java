package modelo;

public class MultipleChoiceClassic extends Question {
    private Integer points = 1;

    public MultipleChoiceClassic(String text, Integer[] correctOptions, Integer[] incorrectOptions, String[] names) {
        super();
        this.text = text;
        this.score = new MultipleChoiceClassicSB();
        answers = new Option[correctOptions.length + incorrectOptions.length];
        for(Integer aInteger : correctOptions){
            CorrectState correct    =   new CorrectState(points);
            Option  option  =   new Option(correct,names[aInteger]);
            answers[aInteger]  =   option;
        }
        for(Integer aInteger : incorrectOptions){
            IncorrectState incorrect    =   new IncorrectState();
            Option  option  =   new Option(incorrect, names[aInteger]);
            answers[aInteger]    =   option;
        }
    }

    @Override
    void compareAnswersFromPlayers(Integer[] answers, Player[] players) {

    }

    public void compareAnswersFromPlayers(Integer[] playerOneAnswers, Integer[] playerTwoAnswers, Player[] players) {
        Integer[] playersAnswers = playerOneAnswers;
        for(Player aPlayer : players) {
            for (Integer aInteger : playersAnswers) {
                answers[aInteger].changeScore(score);
            }
            score.scorePlayer(aPlayer);
            playersAnswers = playerTwoAnswers;
        }
    }

}
