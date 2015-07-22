package english.service;

import english.domain.Category;
import english.domain.User;
import english.domain.Word;
import english.domain.WordTestResult;
import english.results.WordUserEffect;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Sergii Makarenko
 */
public interface WordService {
    Long addCategory(String name);
    List<Category> findAllCategories();
    Category getCategoryById(Long id);
    Category getCategoryByName(String categoryName);
    List<Category> getCategoryByPortion(int portion, int startFrom);
    boolean editCategory(Long categoryId, String name);

    Long addWord(String eng, String ukr, String transcription, Date dateIn, User user, Category category);
    List<Word> findAllWords();
    List<Word> getWordsByPortion(int portion, int startFrom);
    List<Word> getRandomWords(int cntWords);
    boolean wordEdit(Long wordId, String eng, String ukr, String transcription, Date dateIn, User user, Category category);
    Word getWordById(Long wordId);
    List<Word> getWordsByPortionUser(int portion, int startFrom, User user, User admin);
    List<Word> findAllWordsByUser(User user, User admin);
    List<WordUserEffect> getWordsByPortionByUserByEffect(int portion, int startFrom, User user, User admin,
                                                         Double effectiveness);
    List<WordUserEffect> getRandomWordsByUserEffect(int cntWords, User user, User admin, Double effectiveness);

}
