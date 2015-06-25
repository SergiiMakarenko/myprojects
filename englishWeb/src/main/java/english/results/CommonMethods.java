package english.results;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by serg on 22.06.15.
 */
public class CommonMethods <E> {
    public CommonMethods(){

    }

    public List<E> getRandomList(List<E> list, int amountOfWords){

        if(amountOfWords>=list.size())
            return list;

        List<Integer> indexes = new ArrayList<>();
        for(int i = 0;i<list.size();i++){
            indexes.add(i);
        }

        Double maxIndex = list.size()*1.0;
        List<Integer> lottery = new ArrayList<>();
        for(int i = 0;i<amountOfWords;i++){
            int random = (int) (Math.random()*maxIndex);
            lottery.add(indexes.get(random));
            indexes.remove(random);
            maxIndex--;
        }

        List<E> randomE = new ArrayList<>();
        for(int i = 0;i<amountOfWords;i++){
            randomE.add(list.get(lottery.get(i)));
        }
        return randomE;
    }
}
