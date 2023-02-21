package tpo.text;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tpo.functions.Sec;
import tpo.text.radio.Radio;

import java.util.ArrayList;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TextTest {
    private static HumanlikeEntity h;
    private static Radio r;

    @BeforeAll
    static void init() {
        ArrayList<Hand> extraHands = new ArrayList<Hand>();
        extraHands.add(new Hand("third one"));
        h = new HumanlikeEntity(new Hand("left"), new Hand("right"), extraHands);
    }

//    @DisplayName()
//    @ParameterizedTest(name = "{index}.")
//    @ValueSource(doubles = {
//    })
//    void test(double x) {
//    }

}