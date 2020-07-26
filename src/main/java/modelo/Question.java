package modelo;

abstract class Question {
    protected String text;
    protected Option[] answers;

    public void compareAnswersFromPlayers(Integer[] answers, Player[] players) {
        int i = 0;
        for (Integer aInteger: answers) {
            this.answers[aInteger].scorePlayer(players[i]); // lo que hago aca es setiar el estado de la respuesta en la clase pregunta para asi poder con polimorfismo setiar los puntajes a los players
            i++;
        }
    }

    public Option getCorrectOption(){
        int i = 0;
        for(Option aOption : answers){
            State answeer = answers[i].getCorrectAnswer();
            if(answer != null){
                return ;
            }
            i++;
        }
        return null;
    }
}
