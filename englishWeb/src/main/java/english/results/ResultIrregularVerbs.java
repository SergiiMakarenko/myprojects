package english.results;

import english.domain.Test;

/**
 * @author Sergii Makarenko
 */
public class ResultIrregularVerbs {
    private Test test;
    private Long countWordTest;
    private Long correctPastSimpleCount;
    private Double pastSimpleEffectiveness;
    private Long correctPastParticipleCount;
    private Double pastParticipleEffectiveness;
    private Double effectiveness;

    public ResultIrregularVerbs(Test test, Long countWordTest, Long correctPastSimpleCount,
                                Double pastSimpleEffectiveness,
                                Long correctPastParticipleCount, Double pastParticipleEffectiveness,
                                Double effectiveness) {
        this.test = test;
        this.countWordTest = countWordTest;
        this.correctPastSimpleCount = correctPastSimpleCount;
        this.pastSimpleEffectiveness = pastSimpleEffectiveness;
        this.correctPastParticipleCount = correctPastParticipleCount;
        this.pastParticipleEffectiveness = pastParticipleEffectiveness;
        this.effectiveness = effectiveness;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Long getCountWordTest() {
        return countWordTest;
    }

    public Long getCorrectPastSimpleCount() {
        return correctPastSimpleCount;
    }

    public Double getPastSimpleEffectiveness() {
        return pastSimpleEffectiveness;
    }

    public Long getCorrectPastParticipleCount() {
        return correctPastParticipleCount;
    }

    public Double getPastParticipleEffectiveness() {
        return pastParticipleEffectiveness;
    }

    public Double getEffectiveness() {
        return effectiveness;
    }

    public void setEffectiveness(Double effectiveness) {
        this.effectiveness = effectiveness;
    }

    @Override
    public String toString() {
        return "TestResult{" +
                "test=" + test +
                ", countWordTest=" + countWordTest +
                ", correctPastSimpleCount=" + correctPastSimpleCount +
                ", pastSimpleEffectiveness=" + pastSimpleEffectiveness +
                ", correctPastParticipleCount=" + correctPastParticipleCount +
                ", pastParticipleEffectiveness=" + pastParticipleEffectiveness +
                ", effectiveness=" + effectiveness +
                '}';
    }
}
