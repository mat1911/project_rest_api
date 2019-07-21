package generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ElementsGenerator<T> {

    private Random random;

    public ElementsGenerator() {
        random = new Random();
    }

    public List<T> generateSubList(List<T> inputList, int newSize){

        if(inputList.size() <= newSize){
            return inputList;
        }

        T drawElement;
        int drawIndex = 0, i = 0;
        List<T> result = new ArrayList<>();

        while(i < newSize) {

            drawIndex = random.nextInt(inputList.size());
            drawElement = inputList.get(drawIndex);

            if(!result.contains(drawElement)){
                result.add(drawElement);
                i++;
            }

        }

        return result;
    }
}
