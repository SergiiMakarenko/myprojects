package english.results;

import english.domain.IrregularVerb;

/**
 * Created by serg on 18.06.15.
 */
public class VerbsUserEffect {
    private IrregularVerb verb;
    Long amount;
    Double effectivenessPS;
    Double effectivenessPP;

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

    public void setEffectivenessPS(Double effectivenessPS) {
        this.effectivenessPS = effectivenessPS;
    }

    public Double getEffectivenessPP() {
        return effectivenessPP;
    }

    public void setEffectivenessPP(Double effectivenessPP) {
        this.effectivenessPP = effectivenessPP;
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
