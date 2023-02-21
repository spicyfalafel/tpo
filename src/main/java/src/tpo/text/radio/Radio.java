package tpo.text.radio;

import tpo.text.Human;

import java.util.ArrayList;

//В рубке "Золотого Сердца" громко играла музыка: Зафод искал по суб-эфирному радио новости о себе.
//        Ему это с трудом удавалось. Многие годы радио настраивали, нажимая кнопки и вращая рукоятки.
//        Позже технология стала сложнее, и управление сделали сенсорным, -- достаточно было касаться панелей пальцами.
//        Теперь же нужно было просто помахивать рукой в направлении аппаратуры и надеяться, что попал.
//        Это, конечно, экономило расход мышечной энергии, но если вы хотели слушать одну и ту же программу,
//        то приходилось сидеть почти неподвижно.
public abstract class Radio {
    protected ArrayList<RadioProgram> radioProgram;

    public int x, y, z;
    public final double maxDistanceToUse;

    public double countDistance (int x, int y){
        return Math.pow(Math.pow((this.x - x), 2) + Math.pow((this.y - y), 2), 0.5);
    }
    public boolean canBeUsed(Human h){
        if (h.getZ() == this.z){
            return (this.countDistance(h.getX(), h.getY()) < this.maxDistanceToUse);
        } else return false;
    }
    public Radio(int x, int y, int z, double maxDistanceToUse) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.maxDistanceToUse = maxDistanceToUse;
        this.radioProgram = new ArrayList<>();
        radioProgram.add(new RadioProgram("Europa Plus", 106.2));
        radioProgram.add(new RadioProgram("Retro FM", 88.3));
        radioProgram.add(new RadioProgram("Shanson", 103.0));
    }

    protected boolean isTurnedOn;
    public boolean turnOn(Human h){
        if (canBeUsed(h) && !this.isTurnedOn) {
            this.isTurnedOn = true;
            return true;
        }
        return false;
    }
    public boolean turnOff(Human h){
        if (canBeUsed(h) && this.isTurnedOn) {
            this.isTurnedOn = false;
            return true;
        }
        return false;
    }
    public abstract RadioProgram nextProgram();
    public abstract RadioProgram previousProgram();
}