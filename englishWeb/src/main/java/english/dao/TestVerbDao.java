package english.dao;

import english.domain.IrregularVerb;
import english.domain.Test;
import english.domain.TestVerb;

import java.util.List;

/**
 * Created by serg on 14.05.15.
 */
public interface TestVerbDao {
    Long createTestVerb(String pastSimpleTest, String pastParticipleTest, Long pastSimpleResult,
                        Long pastParticipleResult, Test test, IrregularVerb verb);
    List<TestVerb> findAllTestVerb();
    TestVerb getTestVerbById(Long id);
    List<TestVerb> getTestVerbsByPortion(int portion, int startFrom);
    boolean updateTestVerb(Long testVerbId, String pastSimpleTest, String pastParticipleTest, Long pastSimpleResult,
                           Long pastParticipleResult, Test test, IrregularVerb verb);
    List<TestVerb> getTestVerbsByTest(Test test);
    List<TestVerb> getTestVerbsByIrregularVerb(IrregularVerb irregularVerb);
}
