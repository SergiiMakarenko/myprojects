package english.dao.interfaces;

import english.domain.Category;
import english.domain.User;
import english.domain.Word;
import english.results.WordUserEffect;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Sergii Makarenko
 */
@Repository
public interface WordDao {
    Long addWord(String eng, String ukr, String transcription, Date dateIn, User user, Category category);
    List<Word> findAll();
    List<Word> findAllByUser( User user, User admin);
    List getWordsByPortion(int portion, int startFrom);
    List<Word> getWordsByPortionUser(int portion, int startFrom, User user, User admin);
    List<Word> getRandomWords(int cntWords);
    boolean wordEdit(Long wordId, String eng, String ukr, String transcription, Date dateIn, User user,
                     Category category);
    Word getWordById(Long wordId);
    List<WordUserEffect> getWordsByPortionByUserByEffect(int portion, int startFrom, User user, User admin,
                                                         Double effectiveness);
    List<WordUserEffect> getRandomWordsByUserEffect(int cntWords, User user, User admin, Double effectiveness);
}
