package modelo;

public class Question {
    private String text;
    private Option answer;

    public Question(String text,Boolean answer){
        this.text = text;
        this.answer = new Option(answer);
    }

    public boolean getAnswer(){
        return answer.getCorrectOption();

    }

    public void compareAnswersFromPlayers(State[] answers, Player[] players) {
        int i = 0;
        for (State aState: answers) {
            answer.setState(answers[i]); // lo que hago aca es setiar el estado de la respuesta en la clase pregunta para asi poder con polimorfismo setiar los puntajes a los players
            answer.scorePlayer(players[i]);
            i++;
        }
    }
}
