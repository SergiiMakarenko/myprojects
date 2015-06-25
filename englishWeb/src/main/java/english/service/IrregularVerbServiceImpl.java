package english.service;

import english.dao.IrregularVerbDao;
import english.domain.IrregularVerb;
import english.domain.User;
import english.results.VerbsUserEffect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by serg on 06.05.15.
 */
@Service
public class IrregularVerbServiceImpl implements IrregularVerbService {
    @Autowired
    private IrregularVerbDao irregularVerbDao;

    @Override
    @Transactional
    public Long addVerb(IrregularVerb verb) {
        return irregularVerbDao.addVerb(verb);
    }

    @Override
    @Transactional(readOnly = true)
    public List<IrregularVerb> findAllIrregularVerbs() {
        return irregularVerbDao.findAllIrregularVerbs();
    }

    @Override
    @Transactional(readOnly = true)
    public List<IrregularVerb> getVerbsByPortion(int portion, int startFrom) {
        return irregularVerbDao.getVerbsByPortion(portion, startFrom);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VerbsUserEffect> getRandomIrregularVerbs(int cntWords,User user, Double effectiveness) {
        return irregularVerbDao.getRandomIrregularVerbs(cntWords, user, effectiveness);
    }

    @Override
    @Transactional
    public boolean verbEdit(Long id, String infinitive, String pastSimple, String pastParticiple) {
        return irregularVerbDao.verbEdit(id, infinitive, pastSimple, pastParticiple);
    }

    @Override
    @Transactional(readOnly = true)
    public IrregularVerb getVerbById(Long verbId) {
        return irregularVerbDao.getVerbById(verbId);
    }

    @Override
    @Transactional
    public boolean updateVerb(IrregularVerb irregularVerb) {
        return irregularVerbDao.updateVerb(irregularVerb);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VerbsUserEffect> getVerbsByPortionByUser(int portion, int startFrom, User user, Double effectiveness) {
        return irregularVerbDao.getVerbsByPortionByUser(portion, startFrom, user,effectiveness);
    }

    @Override
    @Transactional(readOnly = true)
    public int getSizeVerbsByUserEff(User user, Double effectiveness) {
        return irregularVerbDao.getSizeVerbsByUserEff(user, effectiveness);
    }
}
