package hotciv.variants;

import hotciv.framework.AgeingStrategy;

public class WorldAgeingBeta implements AgeingStrategy {
    @Override
    public int ageing(int age) {
        if(-4000 <= age && age < -100){
            return age + 100;
        } else if(-100 == age) {
            return -1;
        }else if(-1 == age ) {
            return 1;
        } else if(1 == age){
            return 50;
        } else if(50 <= age && age < 1750){
            return age + 50;
        } else if(1750 <= age && age < 1900){
           return age + 25;
        } else if(1900 <= age && age < 1970){
            return age + 5;
        } else if(1970 <= age){
            return age + 1;
        }
        return 0;
    }
}


