package english.results;

import english.domain.IrregularVerb;

/**
 * @author Sergii Makarenko
 * Efectiveness of irregular verbs tests by user
 */
public class VerbsUserEffect {
    private IrregularVerb verb;
    private Long amount;
    private Double effectivenessPS;
    private Double effectivenessPP;

    public VerbsUserEffect(IrregularVerb verb, Long amount, Double effectivenessPS, Double effectivenessPP) {
        this.verb = verb;
        this.amount = amount;
        this.effectivenessPS = effectivenessPS;
        this.effectivenessPP = effectivenessPP;
    }

    public IrregularVerb getVerb() {
        return verb;
    }

    public void setVerb(IrregularVerb verb) {
        this.verb = verb;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Double getEffectivenessPS() {
        return effectivenessPS;
    }

    public Double getEffectivenessPP() {
        return effectivenessPP;
    }

    @Override
    public String toString() {
        return "VerbsUserEffect{" +
                "verb=" + verb +
                ", amount=" + amount +
                ", effectivenessPS=" + effectivenessPS +
                ", effectivenessPP=" + effectivenessPP +
                '}';
    }
}
