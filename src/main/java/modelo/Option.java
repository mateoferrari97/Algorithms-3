package modelo;

public class Option {
    private Boolean correct;
    private State state; // es un estilo lo que pasa en "Pokemon" pero aca siempre hay 2 estados (correcto o incorrecto)
    private ScoreBehavior score;

    public  Option(Boolean correctAnswer){
        correct = correctAnswer;
        score = new ScoreBehavior(); // por ahora solo hay uno y es una clase concreta, podemos hacerla abstracta dependiendo de lo que necesite la pregunta
    }

    public boolean getCorrectOption() {
        return correct;
    } // por ahora es para que cumpla el test 1

    public void setState(modelo.State answer) {
        state = answer;
    }

    public void scorePlayer(Player player) {
        state.scorePlayer(score, player); // mando el ScoreBehavior por parametro para indicarle lo que tiene que hacer segun si la respuesta fue correcta o incorrecta
    }
}
