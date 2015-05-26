package english.service;

import english.domain.Category;
import english.domain.User;
import english.domain.Word;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by serg on 07.05.15.
 */
public interface WordService {
    Long addCategory(String name);
    List<Category> findAllCategories();
    Category getCategoryById(Long id);
    Category getCategoryByName(String categoryName);
    List<Category> getCategoryByPortion(String portion, String startFrom);
    boolean editCategory(Long categoryId, String name);

    Long addWord(String eng, String ukr, String transcription, Date dateIn, User user, Category category);
    List<Word> findAllWords();
    List<Word> getWordsByPortion(String portion, String startFrom);
    List<Word> getRandomWords(int cntWords);
    boolean wordEdit(Long wordId, String eng, String ukr, String transcription, Date dateIn, User user, Category category);
    Word getWordById(Long wordId);

}
