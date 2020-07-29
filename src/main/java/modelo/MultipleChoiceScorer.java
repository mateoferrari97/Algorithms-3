package modelo;

import java.util.List;

public class MultipleChoiceScorer implements QuestionScorer {
    public void score(Player player, List<Option> options) {
        Integer currentPlayerScorer = player.getPoints();
        for (Option option : options) {
            option.score(player);
            if (player.getPoints() < currentPlayerScorer) {
                player.setPoints(currentPlayerScorer);
                return;
            }
        }

        player.setPoints(currentPlayerScorer + 1);
    }


    public void score(Player player) {

    }


    public void reward() {

    }

    public void punish() {

    }

    @Override
    public void score(Player player, Points points) {

    }

    @Override
    public void reward(Points points) {

    }

    @Override
    public void punish(Points points) {

    }
}
