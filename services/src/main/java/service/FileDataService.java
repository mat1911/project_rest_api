package service;

import exceptions.AppException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;


public class FileDataService {

    private int NUMBER_OF_WORDS = 3000; // in file englishWords.txt

    public List<String> getDataFromFile(String fileName, int numberOfLines) {

        List<String> dataFromFIle = null;
        try (
                Stream<String> lines = Files.lines(Path.of(fileName))
        ) {
            dataFromFIle = readFromFile(lines, numberOfLines);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AppException("Problem with reading from file: " + fileName);
        }

        return dataFromFIle;
    }

    private List<String> readFromFile(Stream<String> lines, int numberOfLines) {
        List<String> dataFromFile = new ArrayList<>(numberOfLines);
        List<Integer> indexesOfWords = getWordsIndexes(numberOfLines);
        AtomicInteger counter = new AtomicInteger();

        lines.forEach(line -> {
            if(indexesOfWords.size() != 0 && counter.getAndIncrement() == indexesOfWords.get(0)){
                indexesOfWords.remove(0);
                dataFromFile.add(line);
            }
        });

        return dataFromFile;
    }

    private List<Integer> getWordsIndexes(int numberOfWords){
        List<Integer> indexes = new ArrayList<>();
        Random random = new Random();
        int result, i = 0;

        while(i < numberOfWords){
            result = random.nextInt(NUMBER_OF_WORDS);
            if(!indexes.contains(result)){
                indexes.add(result);
                i++;
            }
        }
        indexes.sort(Comparator.comparingInt(Integer::intValue));

        return indexes;
    }

}
