package modelo;

import java.util.List;

abstract class Question {
    protected String text;
    protected QuestionScorer scorer;
    protected List<Option> options;
    protected Points points;

    public void score(Player player) {}

    public void useDoubleMultiplicator(){
        this.useMultiplicator(2);
    }

    public void useTripleMultiplicator(){
        this.useMultiplicator(3);
    }

    public void useMultiplicator(Integer multiplicator){
        scorer.multiplicate(this.points, multiplicator);
    }
}

