public class Question {
    private String textQuestion;
    private Boolean answer;

    public Question(String textQuestion,Boolean answer){
        this.textQuestion = textQuestion;
        this.answer = answer;
    }

    public boolean answer(){
        return answer;
    }
}
