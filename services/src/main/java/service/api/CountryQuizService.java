package service.api;

import exceptions.AppException;
import model.countriesapi.Country;
import service.UserDataService;

import java.lang.reflect.Field;
import java.util.*;

public class CountryQuizService {

    private static final int NUMBER_OF_ELEMENTS_TO_SHOW = 2;
    private UserDataService userDataService;
    private List<Country> countries;
    private Set<Country> correctAnswers;
    private Set<Country> wrongAnswers;

    public CountryQuizService(List<Country> countries) {
        this.countries = countries;
        this.userDataService = new UserDataService();
        this.correctAnswers = new HashSet<>();
        this.wrongAnswers = new HashSet<>();
    }

    public void takeUserAnswers(){
        System.out.println("Fill missing fields");
        countries.forEach(this::takeAndCheckAnswer);
    }


    public void showWrongAnswers(){

        showAnswers(wrongAnswers, "==============================WRONG ANSWERS===============================");
    }

    public void showCorrectAnswers(){

        showAnswers(correctAnswers, "==============================CORRECT ANSWERS=============================");
    }

    private void takeAndCheckAnswer(Country country){

        Class countryCLass = Country.class;
        List<Field> fieldsToAsk = new ArrayList<>(Arrays.asList(countryCLass.getDeclaredFields()));
        List<Field> fieldsToShow = getElementsToShow(fieldsToAsk);

        fieldsToAsk.removeAll(fieldsToShow);

        showHint(fieldsToShow, country);
        getAnswers(fieldsToAsk, country);

    }

    private List<Field> getElementsToShow(List<Field> fields){

        int i = 0, index = 0;
        Random random = new Random();
        List<Field> fieldsToShow = new ArrayList<>(NUMBER_OF_ELEMENTS_TO_SHOW);

        while (i < NUMBER_OF_ELEMENTS_TO_SHOW){

            index = random.nextInt(fields.size());

            if(!fieldsToShow.contains(fields.get(index))){
                fieldsToShow.add(fields.get(index));
                i++;
            }
        }

        return fieldsToShow;
    }

    private void showHint(List<Field> fieldsToShow, Country country){

        fieldsToShow.forEach(field -> {
            field.setAccessible(true);
            try {
                System.out.println(field.getName() + ": " + field.get(country));
            } catch (IllegalAccessException e) {
                throw new AppException("Problem with preparing quiz - CountryQuizService - showHint");
            }
        });
    }

    private void getAnswers(List<Field> fieldsToAsk, Country country){

        fieldsToAsk.forEach(field -> {
            String answer = userDataService.getString(field.getName() + " -> ");

            field.setAccessible(true);
            try {

                if(answer.toLowerCase().equals(field.get(country).toString().toLowerCase())){
                    correctAnswers.add(country);
                }else {
                    wrongAnswers.add(country);
                }

            } catch (IllegalAccessException e) {
                throw new AppException("Problem with checking the answer - CountryQuizService - getAnswers");
            }
        });
    }

    private void showAnswers(Set<Country> answers, String s) {

        if(answers.isEmpty()){
            return;
        }

        System.out.println("==========================================================================");
        System.out.println(s);
        System.out.println("==========================================================================");
        answers.forEach(System.out::println);

    }
}
