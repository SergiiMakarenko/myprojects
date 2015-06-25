package english.service;

import english.domain.IrregularVerb;
import english.domain.User;
import english.results.VerbsUserEffect;

import java.util.List;

/**
 * Created by serg on 06.05.15.
 */
public interface IrregularVerbService {
    Long addVerb(IrregularVerb verb);
    List<IrregularVerb> findAllIrregularVerbs();
    List<IrregularVerb> getVerbsByPortion(int portion, int startFrom);
    List<VerbsUserEffect> getRandomIrregularVerbs(int cntWords,User user, Double effectiveness);
    boolean verbEdit(Long id, String infinitive, String pastSimple, String pastParticiple);
    IrregularVerb getVerbById(Long verbId);
    boolean updateVerb(IrregularVerb irregularVerb);
    List<VerbsUserEffect> getVerbsByPortionByUser(int portion, int startFrom, User user, Double effectiveness);
    int getSizeVerbsByUserEff(User user, Double effectiveness);
}
