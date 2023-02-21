package tpo.text.radio;

public class RadioProgram {
    private String name;
    private double freq;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFreq() {
        return freq;
    }

    public void setFreq(double freq) {
        this.freq = freq;
    }

    public RadioProgram(String name, double freq) {
        this.name = name;
        this.freq = freq;
    }
}
