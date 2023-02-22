package tpo.text;

import lombok.Getter;
import lombok.Setter;

public class Hand {
    @Getter
    @Setter
    private String name;
    @Getter
    private int fingerNumber;

    public void setFingerNumber(int fingerNumber) throws NotEnoughFingersException, TooManyFingersException {
        if (fingerNumber < 0)
            throw new NotEnoughFingersException();
        if (fingerNumber > 5)
            throw new TooManyFingersException();
        this.fingerNumber = fingerNumber;
    }

    public Hand(String name){
        this.name = name;
        this.fingerNumber = 5;
    }
    public Hand(){
        this.name = "Some hand";
        this.fingerNumber = 5;
    }
    public Hand(int fingerNumber) throws TooManyFingersException, NotEnoughFingersException {
        this.name = "Some hand";
        setFingerNumber(fingerNumber);
    }
    public Hand(String name, int fingerNumber) throws TooManyFingersException, NotEnoughFingersException {
        this.name = "Some hand";
        setFingerNumber(fingerNumber);
    }

    public void wave(){
        System.out.println("Hand " + name + "waved!");
    }
}
