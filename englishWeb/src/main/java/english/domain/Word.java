package english.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by serg on 03.04.15.
 */
@Entity
@Table(name = "WORDS")
public class Word {

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "WORD_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "WORD_ID")
    private Long wordId;

    @Column(name = "ENGLISHWORD")
    private String englishWord;

    @Column(name = "TRANSCRIPTION")
    private String transcription;

    @Column(name = "UKRAINIANWORD")
    private String ukrainianWord;

    @Column(name = "DATE_IN")
    private Date dateIn;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

    public Word(){

    }

    public Word(String eng, String ukr, String transcription, Date dateIn, User user, Category category){
        this.englishWord=eng;
        this.ukrainianWord=ukr;
        this.dateIn = dateIn;
        this.user = user;
        this.category = category;
        this.transcription = transcription;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public String getUkrainianWord() {
        return ukrainianWord;
    }

    public void setUkrainianWord(String ukrainianWord) {
        this.ukrainianWord = ukrainianWord;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    @Override
    public String toString() {
        return  wordId +": "+ englishWord + " = " + ukrainianWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        if (!englishWord.equals(word.englishWord)) return false;
        if (!ukrainianWord.equals(word.ukrainianWord)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = englishWord.hashCode();
        result = 31 * result + ukrainianWord.hashCode();
        return result;
    }
}
