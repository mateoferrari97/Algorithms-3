
import com.sun.jndi.ldap.sasl.LdapSasl;
import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QuestionTest {

    @Test
    public void testCreateATrueFalseResponseQuestionCanCheckTheCorrectAnswer(){
        String text = "aprobaste algoritmos 3?";
        Boolean response = false;
        Boolean correctAnswer = false;
        Boolean failAnsert = true;
        Question question = new Question(text,response);

        Assert.assertEquals(question.answer(),correctAnswer);
        Assert.assertNotEquals(question.answer(),failAnsert);
    }

    @Test
    public void testCreateATrueFalseResponseQuestionCanCheckAListOfAnswers(){

    }
}
