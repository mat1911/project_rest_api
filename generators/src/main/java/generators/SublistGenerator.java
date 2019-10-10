package generators;

import exceptions.AppException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SublistGenerator<T> {

    private Random random = new Random();

    public List<T> generateSubList(List<T> inputList, int newSize){

        if(inputList.size() <= newSize){
            return inputList;
        }

        if(inputList == null){
            throw new AppException("ElementsGenerator - generateSubList() - source list is null");
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
