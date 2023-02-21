package tpo.text;

import java.util.ArrayList;

public class HumanlikeEntity extends Human{

    private ArrayList<Hand> allHands;
    public HumanlikeEntity(Hand leftHand, Hand rightHand, ArrayList<Hand> extraHands) {
        super(leftHand, rightHand);
       allHands = new ArrayList<>();
       allHands.add(new Hand(leftHand.getName()));
       allHands.add(new Hand(rightHand.getName()));
       for (Hand h : extraHands) {
           allHands.add(new Hand(h.getName()));
       }
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
