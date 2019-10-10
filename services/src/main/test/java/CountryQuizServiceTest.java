import exceptions.AppException;
import model.countriesapi.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import service.UserDataService;
import service.api.CountryQuizService;
import service.api.enums.Answers;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CountryQuizServiceTest {

    @Mock
    private UserDataService userDataService;
    private CountryQuizService countryQuizService;

    @Test
    @RepeatedTest(6)
    public void testWithCorrectUserAnswer(){

        Mockito.when(userDataService.getString("name -> "))
                .thenReturn("Poland");

        Mockito.when(userDataService.getString("capital -> "))
                .thenReturn("Warsaw");

        Mockito.when(userDataService.getString("region -> "))
                .thenReturn("Europe");

        Mockito.when(userDataService.getString("subregion -> "))
                .thenReturn("Central Europe");

        List<Country> countries = List.of(
                Country.builder()
                        .name("Poland")
                        .capital("Warsaw")
                        .region("Europe")
                        .subregion("Central Europe")
                        .build()
        );

        countryQuizService = new CountryQuizService(countries, userDataService);

        Map<Answers, Set<Country>> result = countryQuizService.takeUserAnswers();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(1, result.get(Answers.CORRECT).size());
        Assertions.assertEquals(0, result.get(Answers.WRONG).size());
        Assertions.assertTrue(result.get(Answers.CORRECT).contains(countries.get(0)));
    }

    @Test
    @RepeatedTest(6)
    public void testWithWrongUserAnswer(){

        Mockito.when(userDataService.getString("name -> "))
                .thenReturn("France");

        Mockito.when(userDataService.getString("capital -> "))
                .thenReturn("Paris");

        Mockito.when(userDataService.getString("region -> "))
                .thenReturn("Europe");

        Mockito.when(userDataService.getString("subregion -> "))
                .thenReturn("Western Europe");

        List<Country> countries = List.of(
                Country.builder()
                        .name("Poland")
                        .capital("Warsaw")
                        .region("Europe")
                        .subregion("Central Europe")
                        .build()
        );

        countryQuizService = new CountryQuizService(countries, userDataService);

        Map<Answers, Set<Country>> result = countryQuizService.takeUserAnswers();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(1, result.get(Answers.WRONG).size());
        Assertions.assertEquals(0, result.get(Answers.CORRECT).size());
        Assertions.assertTrue(result.get(Answers.WRONG).contains(countries.get(0)));
    }

    @Test
    public void testTakeUserAnswersWithNull(){

        List<Country> countries = List.of(
                Country.builder()
                        .name("Poland")
                        .capital("Warsaw")
                        .region("Europe")
                        .subregion("Central Europe")
                        .build()
        );

        Assertions.assertThrows(AppException.class, () -> new CountryQuizService(Collections.emptyList(), userDataService));
        Assertions.assertThrows(AppException.class, () -> new CountryQuizService(countries, null));
    }
}
