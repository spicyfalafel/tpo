package tpo.text.radio;

import tpo.text.Human;

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
    public boolean canBeUsed(Human h) {
        return super.canBeUsed(h) && h.getAllHands().stream().anyMatch(hh -> hh.getFingerNumber() == 5);
    }
    @Override
    public boolean turnOn(Human h) {
        if (super.turnOn(h)) {
            System.out.println("RadioV3 is turned on!");
            return true;
        }
        return false;
    }

    @Override
    public boolean turnOff(Human h) {
        if (super.turnOff(h)) {
            System.out.println("RadioV3 is turned off!");
            return true;
        }
        return false;
    }
}