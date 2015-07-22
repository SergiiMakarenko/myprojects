package english.domain;

import javax.persistence.*;

/**
 * @author Sergii Makarenko
 */

@Entity
@Table(name="WORDTESTRESULT")
public class WordTestResult {

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "WORDTESTRESULT_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "WORDTEST_ID")
    private Long wordTestId;

    @Column(name = "ANSWER")
    private String answer;

    @Column(name = "RESULT")
    private Long result;

    @ManyToOne
    private Test test;

    @ManyToOne
    private Word word;

    public WordTestResult(){};

    public WordTestResult(String answer, Long result, Test test, Word word) {
        this.answer = answer;
        this.result = result;
        this.test = test;
        this.word = word;
    }

    public Long getWordTestId() {
        return wordTestId;
    }

    public String getAnswer() {
        return answer;
    }

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    @Override
    public String toString(){
        return "id= "+wordTestId +"; answer= " + answer + "; result= "+ result + "; test= " + test.getTestName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordTestResult that = (WordTestResult) o;

        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;
        if (result != null ? !result.equals(that.result) : that.result != null) return false;
        if (test != null ? !test.equals(that.test) : that.test != null) return false;
        if (word != null ? !word.equals(that.word) : that.word != null) return false;
        if (wordTestId != null ? !wordTestId.equals(that.wordTestId) : that.wordTestId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = wordTestId != null ? wordTestId.hashCode() : 0;
        result1 = 31 * result1 + (answer != null ? answer.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        result1 = 31 * result1 + (test != null ? test.hashCode() : 0);
        result1 = 31 * result1 + (word != null ? word.hashCode() : 0);
        return result1;
    }
}
