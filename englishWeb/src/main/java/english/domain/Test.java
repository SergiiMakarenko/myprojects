package english.domain;

import javax.persistence.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by serg on 14.05.15.
 */
@Entity
@Table(name = "TESTS")
public class Test {

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "TEST_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "TEST_ID")
    private Long testId;

    @Column(name = "TESTNAME")
    private String testName;

    @Column(name = "TESTDATE")
    private Date testDate;

    @ManyToOne
    private User user;

    @OneToMany
    private Set<TestVerb> testVerbSet;

    public Test(){}

    public Test(String testName, Date testDate, User user) {
        this.testName = testName;
        this.testDate = testDate;
        this.user = user;
        this.testVerbSet = new HashSet<>();
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestDate() {
        String string = String.format("%1$tF",testDate);
        return string;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<TestVerb> getTestVerbSet() {
        return testVerbSet;
    }

    public void setTestVerbSet(Set<TestVerb> testVerbSet) {
        this.testVerbSet = testVerbSet;
    }

    @Override
    public String toString() {
        return "Test{" +
                "testId=" + testId +
                ", testName='" + testName + '\'' +
                ", testDate=" + testDate +
                ", user=" + user.getUserLogin() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test test = (Test) o;

        if (testDate != null ? !testDate.equals(test.testDate) : test.testDate != null) return false;
        if (testId != null ? !testId.equals(test.testId) : test.testId != null) return false;
        if (testName != null ? !testName.equals(test.testName) : test.testName != null) return false;
        if (user != null ? !user.equals(test.user) : test.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testId != null ? testId.hashCode() : 0;
        result = 31 * result + (testName != null ? testName.hashCode() : 0);
        result = 31 * result + (testDate != null ? testDate.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
