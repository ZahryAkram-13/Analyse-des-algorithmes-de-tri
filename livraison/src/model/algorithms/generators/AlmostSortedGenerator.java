package model.algorithms.generators;


import java.util.ArrayList;
import java.util.List;

import model.CustomList;




public class AlmostSortedGenerator extends Generator{

    public static final int CHOAS_PERCENT = 30;
    public static final String ALMOST_SORTED_GENERATOR = "almost sorted generator";

    public AlmostSortedGenerator(){
        super(ALMOST_SORTED_GENERATOR);
    }

    @Override
    public CustomList<Integer> generate(int n){
        CustomList<Integer> list = this.generateIncreasingIntegerSerie(n);
        this.swapsBetween(list);
        return list;
    }

    public void swapsBetween(CustomList<Integer> list){
        int percent = this.percent(list.getSize(), CHOAS_PERCENT);
        int i = 0;
        int rest = list.getSize() - percent;
        while(i < percent*3 ){
            int a = this.generateRandomIntegerBetween(rest, list.getSize()-1);
            int b = this.generateRandomIntegerBetween(rest, list.getSize()-1);
            //System.out.println(a + " " + b);
            if(a != b) {
                list.swap(a, b);
                i++;
            }
        }
    }
}

