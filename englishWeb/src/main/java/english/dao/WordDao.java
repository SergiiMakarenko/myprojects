package english.dao;

import english.domain.Category;
import english.domain.User;
import english.domain.Word;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by serg on 03.04.15.
 */
@Repository
public interface WordDao {
    Long addWord(String eng, String ukr, String transcription, Date dateIn, User user, Category category);
    List<Word> findAll();
    List<Word> getWordsByPortion(int portion, int startFrom);
    List<Word> getRandomWords(int cntWords);
    boolean wordEdit(Long wordId, String eng, String ukr, String transcription, Date dateIn, User user, Category category);
    Word getWordById(Long wordId);

}
