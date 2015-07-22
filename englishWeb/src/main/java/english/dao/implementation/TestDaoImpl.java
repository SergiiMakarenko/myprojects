package english.dao.implementation;

import english.dao.interfaces.TestDao;
import english.domain.Test;
import english.domain.TestVerb;
import english.domain.User;
import english.results.ResultIrregularVerbs;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Sergii Makarenko
 */
@Repository
public class TestDaoImpl implements TestDao {

    @Autowired
    private SessionFactory factory;

    @Override
    public Long createTest(String testName, Date testDate, User user) {
        return (Long) factory.getCurrentSession().save(new Test(testName,testDate,user));
    }

    @Override
    public List<Test> findAllTest() {
        return factory.getCurrentSession().createCriteria(Test.class)
                .list();
    }

    @Override
    public Test getTestById(Long id) {
        return (Test) factory.getCurrentSession().get(Test.class,id);
    }

    @Override
    public List<Test> getTestsByPortion(int portion, int startFrom) {
        return factory.getCurrentSession().createCriteria(Test.class)
                .setFirstResult(startFrom)
                .setMaxResults(portion)
                .list();
    }

    @Override
    public boolean updateTest(Long id, String testName, Date testDate, User user) {
        return false;
    }

    @Override
    public boolean updateTest(Test test) {
        factory.getCurrentSession().update(test);
        return true;
    }

    @Override
    public List<Test> getTestByUser(User user) {
        return factory.getCurrentSession().createCriteria(Test.class)
                .add(Restrictions.eq("user",user))
                .list();
    }

    @Override
    public List<ResultIrregularVerbs> getTestResults(String testName, User user, Date dateFrom, Date dateTo) {
        List<ResultIrregularVerbs> results = new ArrayList<>();

        for (Object object : factory.getCurrentSession().createCriteria(TestVerb.class, "testVerb")
                .createAlias("test", "testAlias")
                .add(Restrictions.eq("testAlias.user", user))
                .add(Restrictions.between("testAlias.testDate", dateFrom, dateTo))
                .setProjection(Projections.projectionList()
                        .add(Projections.count("testVerbId"))
                        .add(Projections.sum("pastSimpleResult"))
                        .add(Projections.sum("pastParticipleResult"))
                        .add(Projections.avg("pastSimpleResult"))
                        .add(Projections.avg("pastParticipleResult"))
                        .add(Projections.groupProperty("test"))
                        .add(Projections.groupProperty("testAlias.testDate")))
                .addOrder(Order.desc("testAlias.testDate"))
                .list()) {

            Object[] objects = (Object[]) object;
            Test test = (Test) objects[5];
            Long countWordTest = (Long) objects[0];
            Long correctPastSimpleCount = (Long) objects[1];
            Double pastSimpleEffectiveness = (Double) objects[3];
            Long correctPastParticipleCount = (Long) objects[2];
            Double pastParticipleEffectiveness = (Double) objects[4];
            Double effectiveness = (pastParticipleEffectiveness + pastSimpleEffectiveness) / 2;
            String string = String.format("%8.2f", effectiveness);
            effectiveness = Double.parseDouble(string);
            string = String.format("%8.2f", pastSimpleEffectiveness);
            pastSimpleEffectiveness = Double.parseDouble(string);
            string = String.format("%8.2f", pastParticipleEffectiveness);
            pastParticipleEffectiveness = Double.parseDouble(string);

            results.add(new ResultIrregularVerbs(test, countWordTest, correctPastSimpleCount,
                    pastSimpleEffectiveness,
                    correctPastParticipleCount, pastParticipleEffectiveness, effectiveness));
        }

        return results;
    }
}
