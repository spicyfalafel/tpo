package tpo.text;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class HumanV2 extends Human{
    public HumanV2(Hand leftHand, Hand rightHand, ArrayList<Hand> extraHands,
                   int x, int y, int z) {
        super(leftHand, rightHand, x, y, z);
       setMaxHands(10);
    }
}
