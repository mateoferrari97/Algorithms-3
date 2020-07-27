package modelo;

import java.util.List;

public interface QuestionScorer {
    void score(Player player, List<Option> options);
}
