package Group;

import modelo.Turn;
import modelo.options.Option;

public interface OptionGroup {
    public void addPlayerAnswer(Turn turn, Option option);
}
