package english.dao;

import english.domain.Test;
import english.domain.User;
import english.results.TestResult;

import java.util.Date;
import java.util.List;

/**
 * Created by serg on 14.05.15.
 */
public interface TestDao {
    Long createTest(String testName, Date testDate, User user);
    List<Test> findAllTest();
    Test getTestById(Long id);
    List<Test> getTestsByPortion(String portion, String startFrom);
    boolean updateTest(Long id, String testName, Date testDate, User user);
    boolean updateTest(Test test);
    List<Test> getTestByUser(User user);
    List<TestResult> getTestResults(String testName, User user, Date dateFrom,Date dateTo);
}
