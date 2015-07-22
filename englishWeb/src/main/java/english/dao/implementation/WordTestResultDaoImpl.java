package english.dao.implementation;

import english.dao.interfaces.WordTestResultDao;
import english.domain.Test;
import english.domain.Word;
import english.domain.WordTestResult;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Sergii Makarenko
 */
@Repository
public class WordTestResultDaoImpl implements WordTestResultDao {

    @Autowired
    private SessionFactory factory;

    @Override
    public Long createWordTestResult(String answer, Long result, Test test, Word word) {
        Long newID = (Long) factory.getCurrentSession().save(new WordTestResult(answer,result,test,word));

        List<WordTestResult> testWords = getWordTestResultByTest(test);
        Set<WordTestResult> testWordSet = new HashSet<>();
        testWordSet.addAll(testWords);
        test.setWordTestResults(testWordSet);
        test = (Test) factory.getCurrentSession().merge(test);
        factory.getCurrentSession().update(test);

        List<WordTestResult> wordTestResults = getWordTestResultByWord(word);
        Set<WordTestResult> wordTestResultSet = new HashSet<>();
        wordTestResultSet.addAll(wordTestResults);
        word.setWordTestResultSet(wordTestResultSet);
        word = (Word) factory.getCurrentSession().merge(word);
        factory.getCurrentSession().update(word);


        return newID;
    }

    @Override
    public List<WordTestResult> getWordTestResultByTest(Test test) {
        return factory.getCurrentSession().createCriteria(WordTestResult.class)
                .add(Restrictions.eq("test",test))
                .list();
    }

    @Override
    public List<WordTestResult> getWordTestResultByWord(Word word) {
        return factory.getCurrentSession().createCriteria(WordTestResult.class)
                .add(Restrictions.eq("word",word))
                .list();
    }
}
