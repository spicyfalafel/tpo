package tpo.text.radio;

/*
   Многие годы радио настраивали, нажимая кнопки и вращая рукоятки.
 */
public class RadioV1 extends Radio{
    public RadioV1(int x, int y, int z) {
        super(x, y, z, 2);
    }

    @Override
    public boolean turnOn(int x, int y, int z) {
        if (super.turnOn(x, y, z)) {
            System.out.println("RadioV1 is turned on!");
            return true;
        }
        return false;
    }

    @Override
    public boolean turnOff(int x, int y, int z) {
        if (super.turnOff(x, y, z)) {
            System.out.println("RadioV1 is turned off!");
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
