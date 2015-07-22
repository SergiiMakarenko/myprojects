package english.results;

import english.domain.Word;

/**
 * @author Sergii Makarenko
 * effectiveness of words translate tests by users
 */
public class WordUserEffect {
    private Word word;
    private Long amountEngUkr;
    private Double effectivenessEngUkr;
    private Long amountUkrEng;
    private Double effectivenessUkrEng;

    public WordUserEffect(Word word, Long amountEngUkr, Double effectivenessEngUkr, Long amountUkrEng,
                          Double effectivenessUkrEng) {
        this.word = word;
        this.amountEngUkr = amountEngUkr;
        this.effectivenessEngUkr = effectivenessEngUkr;
        this.amountUkrEng = amountUkrEng;
        this.effectivenessUkrEng = effectivenessUkrEng;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Long getAmountEngUkr() {
        return amountEngUkr;
    }

    public Double getEffectivenessEngUkr() {
        return effectivenessEngUkr;
    }

    public Long getAmountUkrEng() {
        return amountUkrEng;
    }

    public Double getEffectivenessUkrEng() {
        return effectivenessUkrEng;
    }

    @Override
    public String toString() {
        return "WordUserEffect{" +
                "word=" + word +
                ", amountEngUkr=" + amountEngUkr +
                ", effectivenessEngUkr=" + effectivenessEngUkr +
                ", amountUkrEng=" + amountUkrEng +
                ", effectivenessUkrEng=" + effectivenessUkrEng +
                '}';
    }
}
