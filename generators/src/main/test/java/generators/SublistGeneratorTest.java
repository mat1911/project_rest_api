package generators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SublistGeneratorTest {

    @Test
    public void test1(){

        List<Integer> inputList = List.of(1, 2, 3, 4, 5, 6, 7);
        SublistGenerator<Integer> sublistGenerator = new SublistGenerator<>();

        List<Integer> sublist = sublistGenerator.generateSubList(inputList, 4);


        Assertions.assertEquals(4, sublist.size());
        Assertions.assertTrue(inputList.containsAll(sublist));
    }

    @Test
    public void test2(){

        List<String> inputList = List.of("1", "2", "3", "4", "5", "6", "7");
        SublistGenerator<String> sublistGenerator = new SublistGenerator<>();

        List<String> sublist = sublistGenerator.generateSubList(inputList, 2);


        Assertions.assertEquals(2, sublist.size());
        Assertions.assertTrue(inputList.containsAll(sublist));
    }
}
