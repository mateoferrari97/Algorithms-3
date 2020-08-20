package modelo.multiplicators;


import org.junit.Assert;
import org.junit.Test;


public class MultiplicateTest {

    @Test
    public void getText() {
        Multiplicate multiplicate = new Multiplicate("texto", 2);

        String expectedString = "texto";


        Assert.assertEquals(expectedString, multiplicate.getText());
    }
}
