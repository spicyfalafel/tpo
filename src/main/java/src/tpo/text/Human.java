package tpo.text;

import java.util.ArrayList;

public class Human {
    private int maxHands;

    public void setMaxHands(int maxHands) {
        this.maxHands = maxHands;
    }

    public int getMaxHands() {
        return maxHands;
    }

    public ArrayList<Hand> getAllHands() {
        return allHands;
    }

    private ArrayList<Hand> allHands;
    private Hand leftHand;
    private Hand rightHand;
    private int x, y, z;
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

    public Human(Hand leftHand, Hand rightHand, int x, int y, int z) {
        this.maxHands = 2;
        this.x = x;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }

    public Hand getLeftHand() {
        return leftHand;
    }

    public Hand getRightHand() {
        return rightHand;
    }

    public void setLeftHand(Hand leftHand) {
        this.leftHand = leftHand;
    }

    public void setRightHand(Hand rightHand) {
        this.rightHand = rightHand;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }
}
