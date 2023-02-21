package tpo.text;

public class Hand {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public int getFingerNumber() {
        return fingerNumber;
    }

    public void setFingerNumber(int fingerNumber) {
        this.fingerNumber = fingerNumber;
    }

    private int fingerNumber;
    public String getName() {
        return name;
    }

    public Hand(String name){
        this.name = name;
    }
    public Hand(String name, int fingersNumber){
        this.name = name;
        this.fingerNumber = fingersNumber;
    }
    public void wave(){
        System.out.println("Hand " + name + "waved!");
    }
}
