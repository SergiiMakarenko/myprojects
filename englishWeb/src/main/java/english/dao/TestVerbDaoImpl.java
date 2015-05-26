package english.dao;

import english.domain.IrregularVerb;
import english.domain.Test;
import english.domain.TestVerb;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by serg on 14.05.15.
 */
@Repository
public class TestVerbDaoImpl implements TestVerbDao {

    @Autowired
    private SessionFactory factory;

    @Override
    public Long createTestVerb(String pastSimpleTest, String pastParticipleTest, Long pastSimpleResult,
                               Long pastParticipleResult, Test test, IrregularVerb verb) {
        return (Long) factory.getCurrentSession().save(new TestVerb(pastSimpleTest,pastParticipleTest,pastSimpleResult,
                pastParticipleResult,test,verb));
    }

    @Override
    public List<TestVerb> findAllTestVerb() {
        return factory.getCurrentSession().createCriteria(TestVerb.class)
                .list();
    }

    @Override
    public TestVerb getTestVerbById(Long id) {
        return (TestVerb) factory.getCurrentSession().get(TestVerb.class,id);
    }

    @Override
    public List<TestVerb> getTestVerbsByPortion(String portion, String startFrom) {
        return factory.getCurrentSession().createCriteria(TestVerb.class)
                .setMaxResults(Integer.parseInt(portion))
                .setFirstResult(Integer.parseInt(startFrom))
                .list();
    }

    @Override
    public boolean updateTestVerb(Long testVerbId, String pastSimpleTest, String pastParticipleTest,
                                  Long pastSimpleResult, Long pastParticipleResult, Test test, IrregularVerb verb) {
        return false;
    }

    @Override
    public List<TestVerb> getTestVerbsByTest(Test test) {
        return factory.getCurrentSession().createCriteria(TestVerb.class)
                .add(Restrictions.eq("test",test))
                .list();
    }

    @Override
    public List<TestVerb> getTestVerbsByIrregularVerb(IrregularVerb irregularVerb) {
        return factory.getCurrentSession().createCriteria(TestVerb.class)
                .add(Restrictions.eq("verb",irregularVerb))
                .list();
    }
}
