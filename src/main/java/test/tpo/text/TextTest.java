package tpo.text;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import tpo.text.radio.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TextTest {
    private static HumanV2 threeHandsHumanV2;
    private static Human oneHandHuman;
    private static Human emptyHuman;
    private static Radio r1;
    private static Radio r2;
    private static Radio r3;

    private static ArrayList<Radio> radios;

    @BeforeAll
    static void init() {
        ArrayList<Hand> extraHands = new ArrayList<Hand>();
        extraHands.add(new Hand("third one"));
        threeHandsHumanV2 = new HumanV2(new Hand("left"), new Hand("right"), extraHands, 1, 1, 1);
        r1 =  new RadioV1(1,1,1);
        r2 =  new RadioV2(1,1,1);
        r3 =  new RadioV3(1,1,1);
        radios = new ArrayList<>();
        radios.add(r1);
        radios.add(r2);
        radios.add(r3);
        oneHandHuman = new Human(new Hand(), null, 1,1,1);
        emptyHuman = new Human();

    }

    @DisplayName("Hand can have from 0 to 5 fingers")
    @Test
    void fingersNumberTest() {
        assertThrows(TooManyFingersException.class, () -> new Hand("bad hand", 6));
        assertThrows(NotEnoughFingersException.class, () -> new Hand("bad hand2", -1));
    }

    @DisplayName("Radio counts the right distance")
    @ParameterizedTest(name = "{index}. ")
    @CsvSource({ // radio x y: 1, 1
            "1, 1, 0",
            "0, 0, 1.4",
            "-1, 0, 2.2",
            "2, 2, 1.4",
            "3, 3, 2.8",
    })
    void radioDistance(int x, int y, double expectedDistance) {
        assertEquals(expectedDistance, r1.countDistance(x, y), 0.1);
    }

    @DisplayName("radio can be used if human is near it")
    @ParameterizedTest(name = "{index}. ")
    @CsvSource({ // radio x y z: 1, 1, 1
            "1, 1, 1, true",
            "1, 1, 0, false",
            "-1, -1, 1, false",
            "2, 2, 1, true",
            "3, 3, 1, false",
    })
    void radioCanBeUsedIfNear(int x, int y, int z, boolean canBeUsed) {
        assertEquals(canBeUsed, r1.canBeUsed(new Human(x, y, z)));
    }
    @DisplayName("radio v1 can be used if human has a hand with 2 or more fingers")
    @ParameterizedTest(name = "{index}. ")
    @CsvSource({ // radio x y z: 1, 1, 1
            "0, false",
            "1, false",
            "2, true",
            "5, true"
    })
    void humanHandRadioV1(int fingers, boolean canBeUsed) throws TooManyFingersException, NotEnoughFingersException {
        oneHandHuman.getLeftHand().setFingerNumber(fingers);
        assertEquals(canBeUsed, r1.canBeUsed(oneHandHuman));
    }

    @DisplayName("radio v2 can be used if human has a hand with any number of fingers")
    @ParameterizedTest(name = "{index}. ")
    @CsvSource({ // radio x y z: 1, 1, 1
            "0, true",
            "1, true",
            "2, true",
            "5, true"
    })
    void humanHandRadioV2(int fingers, boolean canBeUsed) throws TooManyFingersException, NotEnoughFingersException {
        oneHandHuman.getLeftHand().setFingerNumber(fingers);
        assertEquals(canBeUsed, r2.canBeUsed(oneHandHuman));
    }

    @DisplayName("radio v3 can be used if human has a hand with 5 fingers")
    @ParameterizedTest(name = "{index}. ")
    @CsvSource({ // radio x y z: 1, 1, 1
            "0, false",
            "1, false",
            "2, false",
            "5, true"
    })
    void humanHandRadioV3(int fingers, boolean canBeUsed) throws TooManyFingersException, NotEnoughFingersException {
        oneHandHuman.getLeftHand().setFingerNumber(fingers);
        assertEquals(canBeUsed, r3.canBeUsed(oneHandHuman));
    }

    @DisplayName("Human class handles nulls in hands.")
    @Test
    void humanHandsTest(){
        assertEquals(1, oneHandHuman.getAllHands().size());
        assertEquals(3, threeHandsHumanV2.getAllHands().size());

        Human h1 = new Human(null, null, 1, 2, 3);
        assertEquals(0, h1.getAllHands().size());
        Human h2 = new Human(null, new Hand(), 1, 2, 3);
        assertEquals(1, h2.getAllHands().size());
    }

    @DisplayName("Human class can't have more than 2 hands.")
    @CsvSource({
            "0, true",
            "1, true",
            "2, true",
            "3, false",
            "5, false"
    })
    @ParameterizedTest
    void humanCantHave3Hands(int handsNum, boolean canHave){
        ArrayList<Hand> hands = new ArrayList<>();
        for (int i = 0; i<handsNum; i++) hands.add(new Hand());
        assertEquals(canHave, emptyHuman.setAllHands(hands));
    }

    @DisplayName("HumanV2 class can't have more than 10 hands.")
    @CsvSource({
            "0, true",
            "1, true",
            "2, true",
            "3, true",
            "10, true",
            "11, false",
            "12, false",
    })
    @ParameterizedTest
    void humanV2CantHave11Hands(int handsNum, boolean canHave){
        ArrayList<Hand> hands = new ArrayList<>();
        for (int i = 0; i<handsNum; i++) hands.add(new Hand());
        assertEquals(canHave, threeHandsHumanV2.setAllHands(hands));
    }

    @DisplayName("radio programs can be added if freq is differs at least by 3Гц")
    @CsvSource({
            "106.3, false",
            "107.2, false",
            "108.2, false",
            "109.2, false",
            "100.2, false",
            "109.3, true",
    })
    @ParameterizedTest
    void radioAddProgramWorks(double freq, boolean added){
        // by default there are 3 programs in radio:
//        radioPrograms.add(new RadioProgram("Europa Plus", 106.2));
//        radioPrograms.add(new RadioProgram("Retro FM", 88.3));
//        radioPrograms.add(new RadioProgram("Shanson", 103.0));
        RadioProgram pr = new RadioProgram("abc", freq);
        assertEquals(added, r1.addRadioProgram(pr, oneHandHuman));
        if (added){
            ArrayList<RadioProgram> prs = r1.getRadioPrograms();
            assertEquals(pr, prs.get(prs.size() - 1));
        }
    }
}