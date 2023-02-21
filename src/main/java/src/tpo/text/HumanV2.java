package tpo.text;

import java.util.ArrayList;

public class HumanV2 extends Human{
    private ArrayList<Hand> allHands;
    public HumanV2(Hand leftHand, Hand rightHand, ArrayList<Hand> extraHands,
                   int x, int y, int z) {
        super(leftHand, rightHand, x, y, z);
       setMaxHands(10);
       allHands = new ArrayList<>();
       allHands.add(new Hand(leftHand.getName()));
       allHands.add(new Hand(rightHand.getName()));
       for (Hand h : extraHands) {
           allHands.add(new Hand(h.getName()));
       }
    }
    @Override
    public boolean waveByHand(int handId) {
        Hand h = allHands.get(handId);
        if (h != null) {
            h.wave();
            return true;
        }
        return false;
    }

    @Override
    public Hand getLeftHand() {
        return allHands.get(0);
    }

    @Override
    public Hand getRightHand() {
        return allHands.get(1);
    }
}
