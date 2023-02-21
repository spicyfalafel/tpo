package tpo.text;

public class Human {
    private Hand leftHand;
    private Hand rightHand;

    public int leftHandIndex = 0;
    public int rightHandIndex = 1;

    public boolean waveByHand(int handId){
        if (handId == 0 && leftHand != null) {
            leftHand.wave();
        } else if (handId == 1 && leftHand != null) {
            rightHand.wave();
        } else {
            return false;
        }
        return true;
    }

    public Human(Hand leftHand, Hand rightHand) {
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }

    public Hand getLeftHand() {
        return leftHand;
    }

    public Hand getRightHand() {
        return rightHand;
    }
}
