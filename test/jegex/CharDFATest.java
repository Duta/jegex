package jegex;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class CharDFATest {
    private CharDFA charDFA;

    @BeforeMethod
    public void setUp() {
        // a*(b|cd?)+
        charDFA = new CharDFA();
        charDFA.addTransition(0, 'a', 1);
        charDFA.addTransition(0, 'b', 2);
        charDFA.addTransition(0, 'c', 3);
        charDFA.addTransition(1, 'a', 1);
        charDFA.addTransition(1, 'b', 2);
        charDFA.addTransition(1, 'c', 3);
        charDFA.addTransition(2, 'b', 2);
        charDFA.addTransition(2, 'c', 3);
        charDFA.addTransition(3, 'b', 2);
        charDFA.addTransition(3, 'c', 3);
        charDFA.addTransition(3, 'd', 4);
        charDFA.addTransition(4, 'b', 2);
        charDFA.addTransition(4, 'c', 3);
        charDFA.addAcceptingStates(2, 3, 4);
    }

    @DataProvider
    public Object[][] dfaProvider() {
        return new Object[][] {
                {"b", true},
                {"c", true},
                {"cd", true},
                {"abcd", true},
                {"aaaaaaaabcbcdbbbccbbcdcdcd", true},
                {"", false},
                {"a", false},
                {"d", false},
                {"aaaaaabcdbd", false},
                {"bbcdcccdbbaaaa", false}
        };
    }

    @Test(dataProvider = "dfaProvider")
    public void testAcceptsString(String test, boolean shouldAccept) {
        assertEquals(charDFA.accepts(test), shouldAccept);
    }
}
