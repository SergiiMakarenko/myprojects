package english.dao;

import english.domain.IrregularVerb;

import java.util.List;

/**
 * Created by serg on 09.04.15.
 */
public interface IrregularVerbDao {
    Long addVerb(IrregularVerb verb);
    List<IrregularVerb> findAllIrregularVerbs();
    List<IrregularVerb> getVerbsByPortion(int portion, int startFrom);
    List<IrregularVerb> getRandomIrregularVerbs(int cntWords);
    boolean verbEdit(Long id, String infinitive, String pastSimple, String pastParticiple);
    IrregularVerb getVerbById(Long verbId);
    boolean updateVerb(IrregularVerb irregularVerb);
}
