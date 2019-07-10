package generators;

import com.google.gson.Gson;
import exceptions.AppException;
import model.numbersapi.Number;
import service.http.AbstractHttpService;
import service.http.HttpNumbersService;

import java.time.LocalDate;

public class NumbersCuriosityGenerator {

    public String generateCuriosityAboutNumbers(){

        int month = LocalDate.now().getMonthValue();
        int day = LocalDate.now().getDayOfMonth();


        AbstractHttpService httpService = new HttpNumbersService();
        String apiPath = ApiPathGenerator.getPathToNumbers(month, day);
        Gson gson = new Gson();


        Number number = gson.fromJson(httpService.get(apiPath).body().toString(), Number.class);

        if(number == null || !number.isFound()){
            throw new AppException("Problem with connecting with numbers api - NumbersCuriosityGenerator - generateCuriosityAboutNumbers");
        }

        return "Curiosity about date: " + LocalDate.now() + System.lineSeparator() + number.getText() + " (" + number.getYear() + ")" + System.lineSeparator();
    }
}
