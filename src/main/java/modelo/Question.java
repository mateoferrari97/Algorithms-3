package modelo;

import java.util.List;

abstract class Question {
    protected String text;
    protected QuestionScorer scorer;
    protected List<Option> options;
    protected Points points;
    protected Multiplicator multiplicator;

    public void score(Player player) {}


    public void multiplicate(Integer factor){
        this.multiplicator.multiplicate(this.points, factor);
    }

    public abstract void score(Player player, List<Option> playerAnswers);

    public String getText() {
        return this.text;
    }

    public String[] getAnswerOptions() {
        int i = 0;
        String[] answersOptions = new String[this.options.size()];
        for(Option aOption : options){
            answersOptions[i] = aOption.getText();
            i++;
        }
        return answersOptions;
    }

    public List<Option> getOptions(){
        return this.options;
    }
}

