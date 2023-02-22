package tpo.text.radio;

import tpo.text.Human;

/*
    Позже технология стала сложнее, и управление сделали сенсорным,
    -- достаточно было касаться панелей пальцами.
 */
public class RadioV2 extends Radio{
    public RadioV2(int x, int y, int z) {
        super(x, y, z, 2);
    }

    @Override
    public boolean canBeUsed(Human h) {
        return super.canBeUsed(h) && h.getAllHands().stream().anyMatch(hh -> hh.getFingerNumber() >= 0);
    }

    @Override
    public boolean turnOn(Human h) {
        if (super.turnOn(h)) {
            System.out.println("RadioV2 is turned on!");
            return true;
        }
        return false;
    }

    @Override
    public boolean turnOff(Human h) {
        if (super.turnOff(h)) {
            System.out.println("RadioV2 is turned off!");
            return true;
        }
        return false;
    }
}
