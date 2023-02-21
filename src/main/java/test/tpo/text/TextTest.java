package tpo.text;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tpo.text.radio.Radio;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNull;

public class TextTest {
    private static HumanV2 h;
    private static Radio r;

    @BeforeAll
    static void init() {
        ArrayList<Hand> extraHands = new ArrayList<Hand>();
        extraHands.add(new Hand("third one"));
        h = new HumanV2(new Hand("left"), new Hand("right"), extraHands);
    }

    @DisplayName("HumanlikeEntity can wave hands just like human")
    @ParameterizedTest(name = "{index}.")
    @ValueSource(doubles = {
    })
    void test(double x) {
    }

}