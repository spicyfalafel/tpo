package tpo.text;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Human {
    private int maxHands;
    private ArrayList<Hand> allHands;
    private Hand leftHand;
    private Hand rightHand;
    private int x, y, z;
    public final int leftHandIndex = 0;
    public final int rightHandIndex = 1;

    public boolean setAllHands(ArrayList<Hand> allHands) {
        int s = allHands.size();
        if (s > maxHands) return false;
        this.allHands = allHands;
        if (s > leftHandIndex) this.leftHand = allHands.get(leftHandIndex);
        else {
            this.leftHand = null;
        }
        if (s > rightHandIndex) this.rightHand = allHands.get(rightHandIndex);
        else {
            this.rightHand = null;
        }
        return true;
    }

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
        this.y = y;
        this.z = z;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
        allHands = new ArrayList<>();
        if (leftHand != null) allHands.add(leftHand);
        if (rightHand != null) allHands.add(rightHand);
    }
    public Human(int x, int y, int z) {
        this(new Hand(), new Hand(), x, y, z);
    }
    public Human() {
        this(null, null, 0, 0, 0);
    }
}
