package generators;

import com.google.gson.Gson;
import exceptions.AppException;
import model.numbersapi.Number;
import http.AbstractHttpService;
import http.HttpNumbersService;
import http.HttpService;

import java.time.LocalDate;

public class NumbersCuriosityGenerator {

    public String generateCuriosityAboutNumbers(){

        int month = LocalDate.now().getMonthValue();
        int day = LocalDate.now().getDayOfMonth();


        HttpService httpService = new HttpNumbersService();
        String apiPath = ApiPathGenerator.getPathToNumbers(month, day);
        Gson gson = new Gson();


        Number number = gson.fromJson(httpService.get(apiPath).body().toString(), Number.class);

        if(number == null || !number.isFound()){
            throw new AppException("NumbersCuriosityGenerator - generateCuriosityAboutNumbers() - problem with connecting with numbers api");
        }

        return "Curiosity about date: " + LocalDate.now() + System.lineSeparator() + number.getText() + " (" + number.getYear() + ")" + System.lineSeparator();
    }
}
