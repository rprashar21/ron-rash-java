package datastructures.arrays.twodimarrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArrayList2d {
    public static void main(String[] args) {

        List<List<Integer>> list= new ArrayList<>(10);
        int j=0;
        int changedValue=2;
        // initilaize the arraylist
        for(int i=0;i<3;i++)
        {
            list.add(new ArrayList<>());
        }
        for(int row =0;row< 3;row++)
        {
            for(int col=0;col<list.size();col++)
            {
                Random random = new Random();
                list.get(row).add(random.nextInt(10));
                changedValue++;
            }
        }

        System.out.println(list);//---> [[0, 1, 2], [3, 4, 5], [6, 7, 8]]
    }

    // So list<List>
}
