package modelo.game;

import modelo.options.Option;

public class SelectableGroup implements OptionGroup {

    public void addPlayerAnswer (Turn turn, Option option) {
        turn.addPlayerAnswer(option);
    }

}
