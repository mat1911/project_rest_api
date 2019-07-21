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

public class FileDataService {

    public static final int ALL_LINES = 0;

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

   public CountryCodes getCountryCodesFromJsonFile(String fileName){

       JsonConverter<CountryCodes> converter = new CountryCodeConverter(fileName);

       return converter
               .fromJsonFile()
               .orElseThrow(() -> new AppException("FileDataService - getCountryCodesFromJsonFile() - problem with reading from file: " + fileName));
   }


   public InputWords getEnglishWordsFromJsonFile(String fileName){

       JsonConverter<InputWords> converter = new InputWordsConverter(fileName);

       return converter
               .fromJsonFile()
               .orElseThrow(() -> new AppException("FileDataService - getEnglishWordsFromJsonFile() - problem with reading from file: " + fileName));
   }

    public void saveTranslatedWordsToJsonFile(String fileName, TranslatedWords translatedWords){

        JsonConverter<TranslatedWords> converter = new TranslatedWordsConverter(fileName);

        TranslatedWords dataToSave = getTranslatedWordsFromJsonFile(fileName);

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

}
