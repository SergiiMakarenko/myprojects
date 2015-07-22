package english.dao.interfaces;

import english.domain.Test;
import english.domain.Word;
import english.domain.WordTestResult;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sergii Makarenko
 */
@Repository
public interface WordTestResultDao {
    Long createWordTestResult(String answer, Long result, Test test, Word word);
    List<WordTestResult> getWordTestResultByTest(Test test);
    List<WordTestResult> getWordTestResultByWord(Word word);
}
