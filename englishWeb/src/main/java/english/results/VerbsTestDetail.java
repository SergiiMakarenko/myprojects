package english.results;

/**
 * Created by serg on 05.06.15.
 */
public class VerbsTestDetail {
    private String infinitive;
    private String pastSimpleTest;
    private String pastParticipleTest;
    private Long pastSimpleResult;
    private Long pastParticipleResult;

    public VerbsTestDetail(){}

    public VerbsTestDetail(String infinitive, String pastSimpleTest, String pastParticipleTest, Long pastSimpleResult,
                           Long pastParticipleResult) {
        this.infinitive = infinitive;
        this.pastSimpleTest = pastSimpleTest;
        this.pastParticipleTest = pastParticipleTest;
        this.pastSimpleResult = pastSimpleResult;
        this.pastParticipleResult = pastParticipleResult;
    }

    public String getInfinitive() {
        return infinitive;
    }

    public void setInfinitive(String infinitive) {
        this.infinitive = infinitive;
    }

    public String getPastSimpleTest() {
        return pastSimpleTest;
    }

    public void setPastSimpleTest(String pastSimpleTest) {
        this.pastSimpleTest = pastSimpleTest;
    }

    public String getPastParticipleTest() {
        return pastParticipleTest;
    }

    public void setPastParticipleTest(String pastParticipleTest) {
        this.pastParticipleTest = pastParticipleTest;
    }

    public Long getPastSimpleResult() {
        return pastSimpleResult;
    }

    public void setPastSimpleResult(Long pastSimpleResult) {
        this.pastSimpleResult = pastSimpleResult;
    }

    public Long getPastParticipleResult() {
        return pastParticipleResult;
    }

    public void setPastParticipleResult(Long pastParticipleResult) {
        this.pastParticipleResult = pastParticipleResult;
    }

    @Override
    public String toString() {
        return "VerbsDetail{" +
                "infinitive='" + infinitive + '\'' +
                ", pastSimpleTest='" + pastSimpleTest + '\'' +
                ", pastParticipleTest='" + pastParticipleTest + '\'' +
                ", pastSimpleResult=" + pastSimpleResult +
                ", pastParticipleResult=" + pastParticipleResult +
                '}';
    }
}
