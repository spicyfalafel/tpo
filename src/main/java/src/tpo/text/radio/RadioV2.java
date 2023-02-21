package tpo.text.radio;

/*
    Позже технология стала сложнее, и управление сделали сенсорным,
    -- достаточно было касаться панелей пальцами.
 */
public class RadioV2 extends Radio{
    public RadioV2(int x, int y, int z) {
        super(x, y, z, 2);
    }

    @Override
    public boolean turnOn(int x, int y, int z) {
        if (super.turnOn(x, y, z)) {
            System.out.println("RadioV2 is turned on!");
            return true;
        }
        return false;
    }

    @Override
    public boolean turnOff(int x, int y, int z) {
        if (super.turnOff(x, y, z)) {
            System.out.println("RadioV2 is turned off!");
            return true;
        }
        return false;
    }

    @Override
    public RadioProgram nextProgram() {
        return null;
    }

    @Override
    public RadioProgram previousProgram() {
        return null;
    }
}
