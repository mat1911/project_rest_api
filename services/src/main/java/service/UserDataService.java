package service;

import exceptions.AppException;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

// @UtilityClass
public class UserDataService {

    protected Scanner scanner = new Scanner(System.in);

    public int getInt(String message) {

        System.out.println(message);
        String text = scanner.nextLine();

        if (!text.matches("\\d+")) {
            throw new AppException("INT VALUE IS NOT CORRECT: " + text);
        }

        return Integer.parseInt(text);

    }

    public BigDecimal getBigDecimal(String message){
        System.out.println(message);
        String text = scanner.nextLine();

        if(!text.matches("[0-9]+(.*[0-9]+)*")){
            throw new AppException("BIGDECIMAL VALUE IS NOT CORRECT: " + text);
        }

        return new BigDecimal(text);
    }

    public String getStringOfLetters(String message, Boolean upperCase){
        System.out.println(message);

        String text = scanner.nextLine();

        if(upperCase == null && !text.matches("([A-Za-z]+ *)+")){
            throw new AppException("STRING IS NOT CORRECT: " + text);
        }
        else if(upperCase && !text.matches("[A-Z]+")){
            throw new AppException("STRING IS NOT CORRECT: " + text);
        }
        else if(!upperCase && !text.matches("[a-z]+")){
            throw new AppException("STRING IS NOT CORRECT: " + text);
        }

        return text;
    }

    public String getString(String message){
        System.out.println(message);

        String text = scanner.nextLine();

        return text;
    }

    public LocalDate getDate(String message, String format){

        System.out.println(message);

        String date = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        try {
            LocalDate result = LocalDate.parse(date, formatter);
            return result;
        }catch (Exception e){
            throw new AppException("DATE IS NOT CORRECT - EXPECTED FORMAT: " + format + " ENTERED: " + date);
        }
    }

    public boolean getBoolean(String message) {

        System.out.println(message + "[y / n]?");

        String result = scanner.nextLine();

        if(result == null){
            throw new AppException("ANSWER IS NOT CORRECT - NULL");
        }

        return Character.toLowerCase(result.charAt(0)) == 'y';
    }

    public LocalTime getTime(String message, String format){

        System.out.println(message);

        String time = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        try {
            LocalTime result = LocalTime.parse(time, formatter);
            return result;
        }catch (Exception e){
            throw new AppException("TIME IS NOT CORRECT - EXPECTED FORMAT: " + format + " ENTERED: " + time);
        }
    }

    public void getAnyKey(String message){

        System.out.println(message);

        scanner.nextLine();
    }
}
