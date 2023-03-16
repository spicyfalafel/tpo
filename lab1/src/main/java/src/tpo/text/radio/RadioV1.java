package tpo.text.radio;

import tpo.text.Human;

/*
   Многие годы радио настраивали, нажимая кнопки и вращая рукоятки.
 */
public class RadioV1 extends Radio{
    public RadioV1(int x, int y, int z) {
        super(x, y, z, 2);
    }

    @Override
    public boolean canBeUsed(Human h) {
        return super.canBeUsed(h) && h.getAllHands().stream().anyMatch(hh -> hh.getFingerNumber() > 1);
    }

    @Override
    public boolean turnOn(Human h) {
        if (super.turnOn(h)) {
            System.out.println("RadioV1 is turned on!");
            return true;
        }
        return false;
    }

    @Override
    public boolean turnOff(Human h) {
        if (super.turnOff(h)) {
            System.out.println("RadioV1 is turned off!");
            return true;
        }
        return false;
    }
}
