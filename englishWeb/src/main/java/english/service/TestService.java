package english.service;

import english.domain.*;
import english.results.ResultIrregularVerbs;

import java.util.Date;
import java.util.List;

/**
 * @author Sergii Makarenko
 */
public interface TestService {
    Long createTest(String testName, Date testDate, User user);
    List<Test> findAllTest();
    Test getTestById(Long id);
    List<Test> getTestsByPortion(int portion, int startFrom);
    boolean updateTest(Long id, String testName, Date testDate, User user);

    Long createTestVerb(String pastSimpleTest, String pastParticipleTest, Long pastSimpleResult,
                        Long pastParticipleResult, Test test, IrregularVerb verb);
    List<TestVerb> findAllTestVerb();
    TestVerb getTestVerbById(Long id);
    List<TestVerb> getTestVerbsByPortion(int portion, int startFrom);
    boolean updateTestVerb(Long testVerbId, String pastSimpleTest, String pastParticipleTest, Long pastSimpleResult,
                           Long pastParticipleResult, Test test, IrregularVerb verb);
    List<TestVerb> getTestVerbsByTest(Test test);
    boolean updateTest(Test test);
    List<Test> getTestByUser(User user);
    List<TestVerb> getTestVerbsByIrregularVerb(IrregularVerb irregularVerb);
    boolean saveIrregularVerbTest(String userName, String[] verbListId, String[] pastSimple, String[] pastParticiple,
                                  String[] pastSimpleResult, String[] pastParticipleResult);
    boolean saveWordTranslate(String userName, Long[] wordId, String[] answer, Long[] result, String testName);
    List<ResultIrregularVerbs> getTestResults(String testName, User user, Date dateFrom,Date dateTo);
    Long createWordTestResult(String answer, Long result, Test test, Word word);
}
