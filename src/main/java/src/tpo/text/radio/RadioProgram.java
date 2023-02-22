package tpo.text.radio;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class RadioProgram {
    private String name;
    private double freq;

    public RadioProgram(String name, double freq) {
        this.name = name;
        this.freq = freq;
    }
}
