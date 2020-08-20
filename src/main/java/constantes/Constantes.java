package constantes;

public class Constantes {

    // Qestion types
    public static final String BOOLEAN_QUESTION_TYPE = "BooleanQuestion";
    public static final String GROUP_CHOCIE_QUESTION_TYPE = "GroupChoiceQuestion";
    public static final String ORDERED_CHOICE_QUESTION_TYPE = "OrderedChoiceQuestion";
    public static final String MULTIPLE_CHOICE_QUESTION_TYPE = "MultipleChoiceQuestion";

    // scorer type
    public static final String PENALTY_SCORER_TYPE = "PenaltyScorer";
    public static final String MULTIPLE_CHOICE_PARTIAL_SCORER_TYPE = "MultipleChoiceWithPartialScorer";
    public static final String ORDERED_SCORER_TYPE = "OrderedScorer";
    public static final String MULTIPLE_CHOICE_SCORER_TYPE = "MultipleChoiceScorer";
    public static final String BOOLEAN_SCORER_TYPE = "BooleanScorer";

    // json
    public static final String QUESTION_GET_TEXT = "text";
    public static final String QUESTION_GET_SCORER_TYPE = "scorer";
    public static final String QUESTION_GET_TYPE = "type";
    public static final String QUESTION_GET_OPTIONS = "options";

    public static final String QUESTIONS_FILE_PATH = "src/main/java/modelo/game/questions.json";

    public static final String OPTION_GET_TEXT = "text";
    public static final String OPTION_GET_SCORER = "optionScorer";

    // game
    public static final String GAME_TITLE = "Kahoot";
    public static final Integer MAX_PLAYERS = 2;

    // multiplicators
    public static final String SCORE_EXCLUSIVITY_TEXT = "ScoreExclusivity";

}
