package english.service;

import english.domain.IrregularVerb;

import java.util.List;

/**
 * Created by serg on 06.05.15.
 */
public interface IrregularVerbService {
    Long addVerb(IrregularVerb verb);
    List<IrregularVerb> findAllIrregularVerbs();
    List<IrregularVerb> getVerbsByPortion(String portion, String startFrom);
    List<IrregularVerb> getRandomIrregularVerbs(int cntWords);
    boolean verbEdit(Long id, String infinitive, String pastSimple, String pastParticiple);
    IrregularVerb getVerbById(Long verbId);
    boolean updateVerb(IrregularVerb irregularVerb);
}
