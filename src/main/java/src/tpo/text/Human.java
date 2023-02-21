package tpo.text;

public class Human {
    private Hand leftHand;
    private Hand rightHand;

    public Hand getLeftHand() {
        return leftHand;
    }

    public Hand getRightHand() {
        return rightHand;
    }

    public Human(Hand leftHand, Hand rightHand) {
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }
}
