package modelo;

abstract class Question {
    protected String text;
    protected Option[] answers;
    protected ScoreBehavior score;

    abstract void compareAnswersFromPlayers(Integer[] answers, Player[] players);

    public String getCorrectOption(){
        int i = 0;
        for(Option aOption : answers){
            String answer = answers[i].getCorrectAnswer();
            if(answer != null){
                return answer;
            }
            i++;
        }
        return null;
    }

    public abstract void compareAnswersFromPlayers(Integer[] playerOneAnswers, Integer[] playerTwoAnswers, Player[] players);
}
