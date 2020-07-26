package modelo;

abstract class Question {
    protected String text;
    protected Option[] answers;

    public void compareAnswersFromPlayers(Integer[] answers, Player[] players) {
        int i = 0;
        for (Integer aInteger: answers) {
            this.answers[aInteger].scorePlayer(players[i]);
            i++;
        }
    }

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
}
