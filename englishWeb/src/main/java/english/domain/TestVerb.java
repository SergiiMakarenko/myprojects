package english.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @author Sergii Makarenko
 * Entity of results of tests irregular verbs on web site
 */
@Entity
@Table(name = "TESTVERBS")
public class TestVerb {

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "TESTVERB_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "TESTVERB_ID")
    private Long testVerbId;

    @Column (name = "PASTSIMPLETEST")
    private String pastSimpleTest;

    @Column (name = "PASTPARTICIPLETEST")
    private String pastParticipleTest;

    @Column (name = "PASTSIMPLERESULT")
    private Long pastSimpleResult;

    @Column (name = "PASTPARTICIPLERESULT")
    private Long pastParticipleResult;

    @JsonIgnore
    @ManyToOne
    private Test test;

    @JsonIgnore
    @ManyToOne
    private IrregularVerb verb;

    public TestVerb(){}

    public TestVerb(String pastSimpleTest, String pastParticipleTest, Long pastSimpleResult, Long pastParticipleResult,
                    Test test, IrregularVerb verb) {
        this.pastSimpleTest = pastSimpleTest;
        this.pastParticipleTest = pastParticipleTest;
        this.pastSimpleResult = pastSimpleResult;
        this.pastParticipleResult = pastParticipleResult;
        this.test = test;
        this.verb = verb;
    }

    public Long getTestVerbId() {
        return testVerbId;
    }

    public String getPastSimpleTest() {
        return pastSimpleTest;
    }

    public String getPastParticipleTest() {
        return pastParticipleTest;
    }

    public Long getPastSimpleResult() {
        return pastSimpleResult;
    }

    public Long getPastParticipleResult() {
        return pastParticipleResult;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public IrregularVerb getVerb() {
        return verb;
    }

    public void setVerb(IrregularVerb verb) {
        this.verb = verb;
    }

    @Override
    public String toString() {
        return "TestVerb{" +
                "testVerbId=" + testVerbId +
                ", pastSimpleTest='" + pastSimpleTest + '\'' +
                ", pastParticipleTest='" + pastParticipleTest + '\'' +
                ", pastSimpleResult=" + pastSimpleResult +
                ", pastParticipleResult=" + pastParticipleResult +
                ", test=" + test +
                ", verb=" + verb.getPastParticiple() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestVerb testVerb = (TestVerb) o;

        if (pastParticipleResult != null ? !pastParticipleResult.equals(testVerb.pastParticipleResult) :
                testVerb.pastParticipleResult != null)
            return false;
        if (pastParticipleTest != null ? !pastParticipleTest.equals(testVerb.pastParticipleTest) :
                testVerb.pastParticipleTest != null)
            return false;
        if (pastSimpleResult != null ? !pastSimpleResult.equals(testVerb.pastSimpleResult) :
                testVerb.pastSimpleResult != null)
            return false;
        if (pastSimpleTest != null ? !pastSimpleTest.equals(testVerb.pastSimpleTest) : testVerb.pastSimpleTest != null)
            return false;
        if (test != null ? !test.equals(testVerb.test) : testVerb.test != null) return false;
        if (testVerbId != null ? !testVerbId.equals(testVerb.testVerbId) : testVerb.testVerbId != null) return false;
        if (verb != null ? !verb.equals(testVerb.verb) : testVerb.verb != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testVerbId != null ? testVerbId.hashCode() : 0;
        result = 31 * result + (pastSimpleTest != null ? pastSimpleTest.hashCode() : 0);
        result = 31 * result + (pastParticipleTest != null ? pastParticipleTest.hashCode() : 0);
        result = 31 * result + (pastSimpleResult != null ? pastSimpleResult.hashCode() : 0);
        result = 31 * result + (pastParticipleResult != null ? pastParticipleResult.hashCode() : 0);
        result = 31 * result + (test != null ? test.hashCode() : 0);
        result = 31 * result + (verb != null ? verb.hashCode() : 0);
        return result;
    }
}
