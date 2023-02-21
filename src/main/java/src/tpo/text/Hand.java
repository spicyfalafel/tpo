package tpo.text;

public class Hand {
    private String name;

    public String getName() {
        return name;
    }

    public Hand(String name){
        this.name = name;
    }
    public void wave(){
        System.out.println("Hand " + name + "waved!");
    }
}
