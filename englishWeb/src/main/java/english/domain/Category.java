package english.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by serg on 03.04.15.
 */
@Entity
@Table(name = "CATEGORIES")
public class Category {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "CATEGORY_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    @JsonIgnore
    @OneToMany
    private Set<Word> words;

    public Category(){}

    public Category(String categoryName) {
        this.categoryName = categoryName;
        this.words = new HashSet<>();
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }

    @Override
    public String toString() {
        return "Category{" +
                "Id=" + categoryId +
                ", Name='" + categoryName +
                '}';
    }
}
