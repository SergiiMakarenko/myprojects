package english.dao;

import english.domain.IrregularVerb;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by serg on 09.04.15.
 */
@Repository
public class IrregularVerbImpl implements IrregularVerbDao {
    @Autowired
    private SessionFactory factory;


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
    public List<IrregularVerb> getVerbsByPortion(String portion, String startFrom) {
        return factory.getCurrentSession().createCriteria(IrregularVerb.class)
                .setMaxResults(Integer.parseInt(portion))
                .setFirstResult(Integer.parseInt(startFrom))
                .addOrder(Order.asc("infinitive"))
                .list();
    }

    @Override
    public List<IrregularVerb> getRandomIrregularVerbs(int cntWords) {
        List<IrregularVerb> allVerbs = factory.getCurrentSession().createCriteria(IrregularVerb.class)
                .list();
        if(cntWords>allVerbs.size()){
            cntWords=allVerbs.size();
            return allVerbs;
        }

        List<Integer> indexes = new ArrayList<>();
        for(int i = 0;i<allVerbs.size();i++){
            indexes.add(i);
        }
        Double cntRandom =  allVerbs.size()*1.0;
        List<Integer> lottery = new ArrayList<>();
        for(int i = 0;i<cntWords;i++){
            int random = (int) (Math.random()*cntRandom);

            lottery.add(indexes.get(random));
            indexes.remove(random);
            cntRandom--;
        }

        List<IrregularVerb> lotteryVerbs = new ArrayList<>();
        for(int i = 0;i<cntWords;i++){
            lotteryVerbs.add(allVerbs.get(lottery.get(i)));
        }
        return lotteryVerbs;
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
        if(verbTest!=null && verbTest.getVerbId()!=irregularVerb.getVerbId()) {
            return false;
        }
        irregularVerb = (IrregularVerb) factory.getCurrentSession().merge(irregularVerb);
        factory.getCurrentSession().update(irregularVerb);
        return true;
    }
}
