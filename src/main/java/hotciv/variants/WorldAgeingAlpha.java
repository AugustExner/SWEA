package hotciv.variants;

import hotciv.framework.AgeingStrategy;

public class WorldAgeingAlpha implements AgeingStrategy {
    @Override
    public int ageing(int age) {
        age+=100;
        return age;
    }
}
