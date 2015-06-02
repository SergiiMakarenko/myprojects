package english.service;

import english.domain.IrregularVerb;
import english.domain.Test;
import english.domain.TestVerb;
import english.domain.User;
import english.results.ResultIrregularVerbs;

import java.util.Date;
import java.util.List;

/**
 * Created by serg on 14.05.15.
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
    List<ResultIrregularVerbs> getTestResults(String testName, User user, Date dateFrom,Date dateTo);

}
