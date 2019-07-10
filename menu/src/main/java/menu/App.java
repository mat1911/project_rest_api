package menu;


import generators.ApiPathGenerator;
import service.http.AbstractHttpService;
import service.http.HttpCountriesService;

public class App {
    public static void main(String[] args) {

/*        AbstractHttpService httpService = new HttpCountriesService();
        System.out.println(httpService.get(ApiPathGenerator.getPathToCountry("pl")).body());*/

        MenuService menuService = new MenuService();
        menuService.mainMenu();
    }
}
