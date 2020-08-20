package modelo.game;

import modelo.options.Option;

public class UnselectableGroup implements OptionGroup {

    public void addPlayerAnswer (Turn turn, Option option) {
        turn.increaseAmountOfCurrentOptions();
    }
}
