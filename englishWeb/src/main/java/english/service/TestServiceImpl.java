package english.service;

import english.dao.IrregularVerbDao;
import english.dao.TestDao;
import english.dao.TestVerbDao;
import english.dao.UserDao;
import english.domain.IrregularVerb;
import english.domain.Test;
import english.domain.TestVerb;
import english.domain.User;
import english.results.ResultIrregularVerbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by serg on 14.05.15.
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Autowired
    private TestVerbDao testVerbDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private IrregularVerbDao irregularVerbDao;

    @Override
    @Transactional
    public Long createTest(String testName, Date testDate, User user) {
        return testDao.createTest(testName, testDate, user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Test> findAllTest() {
        return testDao.findAllTest();
    }

    @Override
    @Transactional(readOnly = true)
    public Test getTestById(Long id) {
        return testDao.getTestById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Test> getTestsByPortion(int portion, int startFrom) {
        return testDao.getTestsByPortion(portion, startFrom);
    }

    @Override
    @Transactional
    public boolean updateTest(Long id, String testName, Date testDate, User user) {
        return testDao.updateTest(id, testName, testDate, user);
    }

    @Override
    @Transactional
    public Long createTestVerb(String pastSimpleTest, String pastParticipleTest, Long pastSimpleResult,
                               Long pastParticipleResult, Test test, IrregularVerb verb) {
        return testVerbDao.createTestVerb(pastSimpleTest, pastParticipleTest, pastSimpleResult, pastParticipleResult,
                test, verb);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestVerb> findAllTestVerb() {
        return testVerbDao.findAllTestVerb();
    }

    @Override
    @Transactional(readOnly = true)
    public TestVerb getTestVerbById(Long id) {
        return testVerbDao.getTestVerbById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestVerb> getTestVerbsByPortion(int portion, int startFrom) {
        return testVerbDao.getTestVerbsByPortion(portion, startFrom);
    }

    @Override
    @Transactional
    public boolean updateTestVerb(Long testVerbId, String pastSimpleTest, String pastParticipleTest,
                                  Long pastSimpleResult, Long pastParticipleResult, Test test, IrregularVerb verb) {
        return testVerbDao.updateTestVerb(testVerbId, pastSimpleTest, pastParticipleTest, pastSimpleResult,
                pastParticipleResult, test, verb);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestVerb> getTestVerbsByTest(Test test) {
        return testVerbDao.getTestVerbsByTest(test);
    }

    @Override
    @Transactional
    public boolean updateTest(Test test) {
        return testDao.updateTest(test);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Test> getTestByUser(User user) {
        return testDao.getTestByUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestVerb> getTestVerbsByIrregularVerb(IrregularVerb irregularVerb) {
        return testVerbDao.getTestVerbsByIrregularVerb(irregularVerb);
    }

    @Override
    @Transactional
    public boolean saveIrregularVerbTest(String userName, String[] verbListId, String[] pastSimple, String[] pastParticiple,
                                         String[] pastSimpleResult, String[] pastParticipleResult) {

        if(userName.equals("Guest")){
            return false;
        }

        User user =  userDao.getUserByLogin(userName);
        Long newTestId = createTest("Irregular verbs (random)", new Date(), user);
        Test test = getTestById(newTestId);

        for(int i = 0; i<verbListId.length;i++){
            createTestVerb(pastSimple[i], pastParticiple[i], Long.parseLong(pastSimpleResult[i]),
                    Long.parseLong(pastParticipleResult[i]), test,
                    irregularVerbDao.getVerbById(Long.parseLong(verbListId[i])));
        }

        List<TestVerb> testVerbs = getTestVerbsByTest(test);
        Set<TestVerb> testVerbSet = new HashSet<>();
        testVerbSet.addAll(testVerbs);
        test.setTestVerbSet(testVerbSet);
        updateTest(test);

        List<Test> tests = getTestByUser(user);
        Set<Test> testSet = new HashSet<>();
        testSet.addAll(tests);
        user.setTestSet(testSet);
        userDao.updateUser(user);

        for(int i = 0; i<verbListId.length;i++) {
            IrregularVerb verb = irregularVerbDao.getVerbById(Long.parseLong(verbListId[i]));
            List<TestVerb> testVerbList = getTestVerbsByIrregularVerb(verb);
            Set<TestVerb> testVerbsSet = new HashSet<>();
            testVerbsSet.addAll(testVerbList);
            verb.setTestVerbSet(testVerbsSet);
            irregularVerbDao.updateVerb(verb);
        }
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResultIrregularVerbs> getTestResults(String testName, User user, Date dateFrom, Date dateTo) {
        return testDao.getTestResults(testName, user, dateFrom, dateTo);
    }


}
