package tpo.text.radio;

/*
    Теперь же нужно было просто помахивать рукой в направлении аппаратуры и надеяться, что попал.
    Это, конечно, экономило расход мышечной энергии, но если вы хотели слушать одну и ту же программу,
    то приходилось сидеть почти неподвижно.
 */
public class RadioV3 extends Radio{
    public RadioV3(int x, int y, int z) {
        super(x, y, z, 20);
    }

    @Override
    public boolean turnOn(int x, int y, int z) {
        if (super.turnOn(x, y, z)) {
            System.out.println("RadioV3 is turned on!");
            return true;
        }
        return false;
    }

    @Override
    public boolean turnOff(int x, int y, int z) {
        if (super.turnOff(x, y, z)) {
            System.out.println("RadioV3 is turned off!");
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