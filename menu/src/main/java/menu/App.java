package menu;


import service.http.AbstractHttpService;
import service.http.HttpNumbersService;

public class App {
    public static void main(String[] args) {

/*        AbstractHttpService httpService = new HttpNumbersService();
        System.out.println(httpService.get("https://numbersapi.p.rapidapi.com/07/10/date?fragment=true&json=true").body().toString());*/

        MenuService menuService = new MenuService();
        menuService.mainMenu();


    }
}
