package service;

import converters.json.CountryCodeConverter;
import converters.json.InputWordsConverter;
import converters.json.JsonConverter;
import converters.json.TranslatedWordsConverter;
import exceptions.AppException;
import model.countriesapi.json.CountryCodes;
import model.systranapi.json.InputWords;
import model.systranapi.json.TranslatedWords;

import java.io.*;
import java.util.*;

public class FileDataService {

    public void createJsonFiles(String... fileNames){

        for(String fileName : fileNames){
            File file = new File(fileName);

            if(!file.exists()){

                try (PrintWriter printWriter = new PrintWriter(file)){

                    file.createNewFile();
                    printWriter.println("{");
                    printWriter.println("}");

                } catch (IOException e) {
                    throw new AppException("Problem with creating file: " + fileName);
                }

            }
        }

    }

   public CountryCodes getCountryCodesFromJsonFile(String fileName){

       JsonConverter<CountryCodes> converter = new CountryCodeConverter(fileName);

       return converter
               .fromJsonFile()
               .orElseThrow(() -> new AppException("FileDataService - getCountryCodesFromJsonFile() - problem with reading from file: " + fileName));
   }

    public void saveEnglishWordsToJsonFile(String fileName, InputWords dataToSave, boolean append){

        JsonConverter<InputWords> converter = new InputWordsConverter(fileName);

        if(append){

            Set<String> wordsFromFile = getEnglishWordsToAppend(fileName);

            wordsFromFile.addAll(dataToSave.getWords());
            dataToSave = new InputWords(wordsFromFile);
        }

        converter.toJsonFile(dataToSave);
    }

   public InputWords getEnglishWordsFromJsonFile(String fileName){

       JsonConverter<InputWords> converter = new InputWordsConverter(fileName);

       return converter
               .fromJsonFile()
               .orElseThrow(() -> new AppException("FileDataService - getEnglishWordsFromJsonFile() - problem with reading from file: " + fileName));
   }

    public void saveTranslatedWordsToJsonFile(String fileName, TranslatedWords translatedWords){

        JsonConverter<TranslatedWords> converter = new TranslatedWordsConverter(fileName);

        TranslatedWords dataToSave = getTranslatedWordsToAppend(fileName);

        dataToSave.getTranslatedWords().putAll(translatedWords.getTranslatedWords());

        converter.toJsonFile(dataToSave);
    }

    public TranslatedWords getTranslatedWordsFromJsonFile(String fileName){

        JsonConverter<TranslatedWords> converter = new TranslatedWordsConverter(fileName);

        TranslatedWords dataToSave = converter
                .fromJsonFile()
                .orElseThrow(() -> new AppException("FileDataService - getTranslatedWordsFromJsonFile() - problem with reading from file: " + fileName));

        return dataToSave.getTranslatedWords() != null ? dataToSave : new TranslatedWords();
    }

    private Set<String> getEnglishWordsToAppend(String fileName){

        InputWords inputWords = getEnglishWordsFromJsonFile(fileName);

        return inputWords.getWords() != null ? new HashSet<>(inputWords.getWords()) : new HashSet<>();
    }

    private TranslatedWords getTranslatedWordsToAppend(String fileName){

        TranslatedWords translatedWords = getTranslatedWordsFromJsonFile(fileName);

        if(translatedWords != null && translatedWords.getTranslatedWords() != null){
            return translatedWords;
        }

        return new TranslatedWords();
    }

}
