package english.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by serg on 09.04.15.
 */
@Entity
@Table(name = "IRREGULAR_VERBS")
public class IrregularVerb {

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "VERB_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "VERBS_ID")
    private Long verbId;

    @Column(name = "INFINITIVE")
    private String infinitive;

    @Column(name = "PAST_SIMPLE")
    private String pastSimple;

    @Column(name = "PAST_PARTICIPLE")
    private String pastParticiple;

    @OneToMany
    private Set<TestVerb> testVerbSet;

    public IrregularVerb(){

    }

    public IrregularVerb(String infinitive, String pastSimple, String pastParticiple) {
        this.infinitive = infinitive;
        this.pastSimple = pastSimple;
        this.pastParticiple = pastParticiple;
        this.testVerbSet = new HashSet<>();
    }

    public Long getVerbId() {
        return verbId;
    }

    public void setVerbId(Long verbId) {
        this.verbId = verbId;
    }

    public String getInfinitive() {
        return infinitive;
    }

    public void setInfinitive(String infinitive) {
        this.infinitive = infinitive;
    }

    public String getPastSimple() {
        return pastSimple;
    }

    public void setPastSimple(String pastSimple) {
        this.pastSimple = pastSimple;
    }

    public String getPastParticiple() {
        return pastParticiple;
    }

    public void setPastParticiple(String pastParticiple) {
        this.pastParticiple = pastParticiple;
    }

    public Set<TestVerb> getTestVerbSet() {
        return testVerbSet;
    }

    public void setTestVerbSet(Set<TestVerb> testVerbSet) {
        this.testVerbSet = testVerbSet;
    }

    @Override
    public String toString() {
        return "IrregularVerb{" +
                "verbId=" + verbId +
                ", infinitive=" + infinitive +
                ", pastSimple=" + pastSimple +
                ", pastParticiple=" + pastParticiple +
                "}";
    }
}
