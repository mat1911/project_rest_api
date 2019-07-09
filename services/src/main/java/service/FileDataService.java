package service;

import exceptions.AppException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;


public class FileDataService {

    public static final int ALL_LINES = 0;
    private int NUMBER_OF_WORDS = 3000; // in file englishWords.txt

    public void createFile(String fileName){

        File file = new File(fileName);

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new AppException("Problem with creating file: " + fileName);
            }
        }
    }

    public void saveDataToFile(String fileName, Map<String,String> dataToSave, boolean append){

        try(PrintWriter printWriter = new PrintWriter(new FileOutputStream(fileName, append))){
            dataToSave.forEach((enWord, plWord) -> printWriter.append(enWord + " " + plWord + System.lineSeparator()));
        } catch (FileNotFoundException e) {
            throw new AppException("Problem with saving data to file " + fileName);
        }

    }

    public void saveDataToFile(String fileName, List<String> dataToSave, boolean append){

        try(PrintWriter printWriter = new PrintWriter(new FileOutputStream(fileName, append))){
            dataToSave.forEach(words -> printWriter.append(words + System.lineSeparator()));
        } catch (FileNotFoundException e) {
            throw new AppException("Problem with saving data to file " + fileName);
        }

    }

    public List<String> getDataFromFile(String fileName, int numberOfLines) {

        List<String> dataFromFile = null;
        try (
                Stream<String> lines = Files.lines(Path.of(fileName))
        ) {
            dataFromFile = readFromFile(lines, numberOfLines);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AppException("Problem with reading from file: " + fileName);
        }

        return dataFromFile;
    }

    private List<String> readFromFile(Stream<String> lines, int numberOfLines) {

        List<String> dataFromFile = new ArrayList<>(numberOfLines);
        List<Integer> indexesOfWords = getWordsIndexes(numberOfLines);
        AtomicInteger counter = new AtomicInteger();

        lines.forEach(line -> {

            if(numberOfLines == ALL_LINES){
                dataFromFile.add(line);
            } else if (indexesOfWords.size() != 0 && counter.getAndIncrement() == indexesOfWords.get(0)){
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
