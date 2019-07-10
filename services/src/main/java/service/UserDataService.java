package service;

import exceptions.AppException;

import java.math.BigDecimal;
import java.util.Scanner;

public class UserDataService {

    private Scanner scanner = new Scanner(System.in);

    public int getInt(String message) {

        System.out.println(message);
        String text = scanner.nextLine();

        if (!text.matches("\\d+")) {
            throw new AppException("INT VALUE IS NOT CORRECT: " + text);
        }

        return Integer.parseInt(text);

    }

    public double getDouble(String message) {

        System.out.println(message);
        String text = scanner.nextLine();

        if (!text.matches("[0-9]+.*[0-9]+]")) {
            throw new AppException("DOUBLE VALUE IS NOT CORRECT: " + text);
        }

        return Double.parseDouble(text);

    }

    public boolean getBoolean(String message) {
        System.out.println(message + "[y / n]?");
        return Character.toLowerCase(scanner.nextLine().charAt(0)) == 'y';
    }

    public BigDecimal getBigDecimal(String message){
        System.out.println(message);
        String text = scanner.nextLine();

        if(!text.matches("[0-9]+.*[0-9]+]")){
            throw new AppException("BIGDECIMAL VALUE IS NOT CORRECT: " + text);
        }

        return new BigDecimal(text);
    }

    public String getStringOfLetters(String message, boolean upperCase){
        System.out.println(message);

        String text = scanner.nextLine();

        if(upperCase && !text.matches("[A-Z]+")){
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

        if(!text.matches("([A-Za-z]+ *)+") ){
            throw new AppException("STRING IS NOT CORRECT: " + text);
        }

        return text;
    }
}
