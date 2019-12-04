package com.tokopedia.testproject.problems.androidView.waterJugSimulation;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<WaterJugAction> simulateWaterJug(int jug1, int jug2, int target) {
        // TODO, simulate the smallest number of action to do the water jug problem
        // below is stub, replace with your implementation!
        List<WaterJugAction> list = new ArrayList<>();
        list.add(new WaterJugAction(WaterJugActionEnum.FILL, 1));
        list.add(new WaterJugAction(WaterJugActionEnum.POUR, 2));
        list.add(new WaterJugAction(WaterJugActionEnum.FILL, 1));
        list.add(new WaterJugAction(WaterJugActionEnum.POUR, 2));
        list.add(new WaterJugAction(WaterJugActionEnum.EMPTY, 2));
        list.add(new WaterJugAction(WaterJugActionEnum.POUR, 2));
        list.add(new WaterJugAction(WaterJugActionEnum.FILL, 1));
        list.add(new WaterJugAction(WaterJugActionEnum.POUR, 2));
        return list;
    }
}
