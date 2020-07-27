package modelo;

import java.util.List;

abstract class Question {
    protected String text;

    public abstract void score(Player player, List<Option> options);
}
