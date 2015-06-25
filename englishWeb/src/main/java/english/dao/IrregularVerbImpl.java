package english.dao;

import english.domain.IrregularVerb;
import english.domain.TestVerb;
import english.domain.User;
import english.results.CommonMethods;
import english.results.VerbsUserEffect;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by serg on 09.04.15.
 */
@Repository
public class IrregularVerbImpl implements IrregularVerbDao {
    @Autowired
    private SessionFactory factory;

//    @Autowired
//    private CommonMethods commonMethods;


    @Override
    public Long addVerb(IrregularVerb verb) {
        IrregularVerb verbTest = (IrregularVerb) factory.getCurrentSession().createCriteria(IrregularVerb.class)
                .add(Restrictions.eq("infinitive",verb.getInfinitive()))
                .uniqueResult();

        if(verbTest==null) {
            return (Long) factory.getCurrentSession().save(verb);
        }
        return null;
    }

    @Override
    public List<IrregularVerb> findAllIrregularVerbs() {
        return factory.getCurrentSession().createCriteria(IrregularVerb.class)
                .list();
    }

    @Override
    public List<IrregularVerb> getVerbsByPortion(int portion, int startFrom) {
        return factory.getCurrentSession().createCriteria(IrregularVerb.class)
                .setMaxResults(portion)
                .setFirstResult(startFrom)
                .addOrder(Order.asc("infinitive"))
                .list();
    }

    @Override
    public List<VerbsUserEffect> getRandomIrregularVerbs(int cntWords,User user, Double effectiveness) {
        List<VerbsUserEffect> allVerbs = getVerbsByPortionByUser(getSizeVerbsByUserEff(user, effectiveness), 0, user,
                effectiveness);
        CommonMethods commonMethods = new CommonMethods();
        return commonMethods.getRandomList(allVerbs,cntWords);
    }

    @Override
    public boolean verbEdit(Long id, String infinitive, String pastSimple, String pastParticiple) {
        IrregularVerb verbTest = (IrregularVerb) factory.getCurrentSession().createCriteria(IrregularVerb.class)
                .add(Restrictions.eq("infinitive",infinitive))
                .uniqueResult();
        if(verbTest!=null && verbTest.getVerbId()!=id) {
            return false;
        }
        IrregularVerb verb = (IrregularVerb) factory.getCurrentSession().get(IrregularVerb.class,id);
        verb.setInfinitive(infinitive);
        verb.setPastSimple(pastSimple);
        verb.setPastParticiple(pastParticiple);
        factory.getCurrentSession().update(verb);
        return true;
    }

    @Override
    public IrregularVerb getVerbById(Long verbId) {
        return (IrregularVerb) factory.getCurrentSession().get(IrregularVerb.class, verbId);
    }

    @Override
    public boolean updateVerb(IrregularVerb irregularVerb) {
        IrregularVerb verbTest = (IrregularVerb) factory.getCurrentSession().createCriteria(IrregularVerb.class)
                .add(Restrictions.eq("infinitive",irregularVerb.getInfinitive()))
                .uniqueResult();
        if(verbTest!=null && !verbTest.getVerbId().equals(irregularVerb.getVerbId())) {
            return false;
        }
        irregularVerb = (IrregularVerb) factory.getCurrentSession().merge(irregularVerb);
        factory.getCurrentSession().update(irregularVerb);
        return true;
    }

    @Override
    public List<VerbsUserEffect> getVerbsByPortionByUser(int portion, int startFrom, User user, Double effectiveness) {
        Iterator iterator =
        factory.getCurrentSession().createSQLQuery("SELECT verbs_id, amountTest, " +
                "CAST(effectivenessPS AS NUMERIC(6,2)) as effectPS," +
                "CAST(effectivenessPP AS NUMERIC(6,2)) as effectPP " +
                "FROM irregular_verbs I " +
                "LEFT JOIN (SELECT verb_verbs_id, AVG(pastsimpleresult) AS effectivenessPS, " +
                "AVG(pastparticipleresult) AS effectivenessPP , COUNT(testverb_id) as amountTest  " +
                "FROM TESTVERBS  " +
                "WHERE test_test_id IN (SELECT test_id FROM TESTS " +
                "WHERE user_user_id = "+user.getUserId()+") " +
                "GROUP BY verb_verbs_id " +
                ") T ON I.verbs_id=T.verb_verbs_id " +
                "WHERE effectivenessPS IS NULL OR ((effectivenessPS+effectivenessPP)/2)<= "+ effectiveness +
                "ORDER BY infinitive ")
                .setMaxResults(portion)
                .setFirstResult(startFrom)
                .list()
                .iterator();
        IrregularVerb irregularVerb;
        List<VerbsUserEffect> verbsUserEffectList = new ArrayList<>();
        while (iterator.hasNext()) {
            Object[] objects = (Object[]) iterator.next();
            irregularVerb = (IrregularVerb) factory.getCurrentSession().get(IrregularVerb.class,
                    Long.parseLong(objects[0].toString()));
            if(objects[1]!=null) {
                verbsUserEffectList.add(new VerbsUserEffect(irregularVerb, Long.parseLong(objects[1].toString())
                        , Double.parseDouble(String.format("%8.2f",objects[2]).toString()),
                        Double.parseDouble(String.format("%8.2f",objects[3]).toString())));
            } else{
                verbsUserEffectList.add(new VerbsUserEffect(irregularVerb,null, null, null));
            }

        }
        return verbsUserEffectList;
    }

    @Override
    public int getSizeVerbsByUserEff(User user, Double effectiveness) {
        List list =
                factory.getCurrentSession().createSQLQuery("SELECT verbs_id, amountTest, " +
                        "CAST(effectivenessPS AS NUMERIC(6,2)) as effectPS," +
                        "CAST(effectivenessPP AS NUMERIC(6,2)) as effectPP " +
                        "FROM irregular_verbs I " +
                        "LEFT JOIN (SELECT verb_verbs_id, AVG(pastsimpleresult) AS effectivenessPS, " +
                        "AVG(pastparticipleresult) AS effectivenessPP , COUNT(testverb_id) as amountTest  " +
                        "FROM TESTVERBS  " +
                        "WHERE test_test_id IN (SELECT test_id FROM TESTS " +
                        "WHERE user_user_id = "+user.getUserId()+") " +
                        "GROUP BY verb_verbs_id " +
                        ") T ON I.verbs_id=T.verb_verbs_id " +
                        "WHERE effectivenessPS IS NULL OR ((effectivenessPS+effectivenessPP)/2)<= "+ effectiveness +
                        "ORDER BY infinitive ")
                        .list();
        return list.size();
    }
}
