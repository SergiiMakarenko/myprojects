package english.dao.interfaces;

import english.domain.Test;
import english.domain.User;
import english.results.ResultIrregularVerbs;

import java.util.Date;
import java.util.List;

/**
 * @author Sergii Makarenko
 */
public interface TestDao {
    Long createTest(String testName, Date testDate, User user);
    List<Test> findAllTest();
    Test getTestById(Long id);
    List<Test> getTestsByPortion(int portion, int startFrom);
    boolean updateTest(Long id, String testName, Date testDate, User user);
    boolean updateTest(Test test);
    List<Test> getTestByUser(User user);
    List<ResultIrregularVerbs> getTestResults(String testName, User user, Date dateFrom,Date dateTo);
}
