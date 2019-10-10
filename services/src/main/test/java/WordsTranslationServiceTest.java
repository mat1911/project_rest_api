import exceptions.AppException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import service.UserDataService;
import service.api.WordsTranslationService;
import service.api.enums.Answers;

import java.util.Collections;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class WordsTranslationServiceTest {

    @Mock
    private UserDataService userDataService;
    private WordsTranslationService wordsTranslationService;

    @Test
    public void testCorrectUserAnswers(){

        Mockito.when(userDataService.getString("kot -> "))
                .thenReturn("cat");

        Mockito.when(userDataService.getString("pies -> "))
                .thenReturn("dog");

        Mockito.when(userDataService.getString("zabawa -> "))
                .thenReturn(":(");

        Map<String, String> words = Map.of(
                "cat", "kot",
                "dog", "pies",
                "amusement", "zabawa");

        wordsTranslationService = new WordsTranslationService(words, userDataService);

        Map<Answers, Map<String, String>> result = wordsTranslationService.takeUserAnswers();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(2, result.get(Answers.CORRECT).size());
        Assertions.assertEquals(1, result.get(Answers.WRONG).size());
        Assertions.assertTrue(result.get(Answers.WRONG).containsKey("amusement"));
    }

    @Test
    public void testTakeUserAnswersWithNullAndEmptyMaps(){

        Map<String, String> words = Map.of(
                "cat", "kot",
                "dog", "pies",
                "amusement", "zabawa");

        Assertions.assertThrows(AppException.class, () -> new WordsTranslationService(null, userDataService));
        Assertions.assertThrows(AppException.class, () -> new WordsTranslationService(Collections.emptyMap(), userDataService));
        Assertions.assertThrows(AppException.class, () -> new WordsTranslationService(words, null));


    }
}
