package modelo.questions;

import modelo.consumables.Multiplicator;
import modelo.options.Option;
import modelo.scorers.BooleanScorer;
import modelo.scorers.QuestionScorer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.management.*"})
@PrepareForTest({ArrayList.class})

public class GroupChoiceTest {
    private List<Option> optionsMock = spy(new ArrayList<>());
    @Test
    public void testThrowInvalidSizeExceptionWhenTheOptionsAre6() {
        doReturn(6).when(optionsMock).size();
        try {
            QuestionScorer sc = new BooleanScorer();
            Question question = new GroupChoiceQuestion("Separar en numeros y letras", optionsMock, sc, new Multiplicator());

        } catch (Exception e){
            Assert.assertEquals(e.getMessage(),"invalid options size: want minimum 2, maximum 6. got: 6");
        }
    }
}
