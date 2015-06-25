package english.dao;

import english.domain.Category;
import english.domain.User;
import english.domain.Word;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.OrderBy;
import javax.persistence.criteria.Order;
import java.util.*;

/**
 * Created by serg on 03.04.15.
 */
@Repository
public class WordDaoImpl implements WordDao {
    @Autowired
    private SessionFactory factory;


    @Override
    public Long addWord(String eng, String ukr, String transcription, Date dateIn, User user, Category category) {
        Word testWord = (Word) factory.getCurrentSession().createCriteria(Word.class)
                .add(Restrictions.eq("englishWord",eng))
                .add(Restrictions.eq("ukrainianWord",ukr))
                .uniqueResult();
        if(testWord!=null) {
            return null;
        }
        if(dateIn==null){
            dateIn=new Date();
        }

        Long wordId = (Long) factory.getCurrentSession().save(new Word(eng,ukr,transcription,dateIn,user,category));

        Set<Word> wordSet = new HashSet<>();
        List<Word> words = factory.getCurrentSession().createCriteria(Word.class)
                .add(Restrictions.eq("category",category))
                .list();
        wordSet.addAll(words);
        category.setWords(wordSet);
        category = (Category) factory.getCurrentSession().merge(category);
        factory.getCurrentSession().update(category);

        List<Word> wordsUser = factory.getCurrentSession().createCriteria(Word.class)
                .add(Restrictions.eq("user",user))
                .list();
        wordSet = new HashSet<>();
        wordSet.addAll(wordsUser);
        user.setWords(wordSet);
        user = (User) factory.getCurrentSession().merge(user);
        factory.getCurrentSession().update(user);
        return wordId;
    }

    @Override
    public List<Word> findAll() {
        return factory.getCurrentSession().createCriteria(Word.class)
                .addOrder(org.hibernate.criterion.Order.asc("englishWord"))
                .list();
    }

    @Override
    public List<Word> getWordsByPortion(int portion, int startFrom) {
        return factory.getCurrentSession().createCriteria(Word.class)
                .setMaxResults(portion)
                .setFirstResult(startFrom)
                .list();
    }

    @Override
    public List<Word> getRandomWords(int cntWords) {
        List<Word> allWords = factory.getCurrentSession().createCriteria(Word.class)
                .list();
        if(cntWords>allWords.size()){
            cntWords=allWords.size();
            return allWords;
        }

        List<Integer> indexes = new ArrayList<>();
        for(int i = 0;i<allWords.size();i++){
            indexes.add(i);
        }
        Double cntRandom =  allWords.size()*1.0;
        List<Integer> lottery = new ArrayList<>();
        for(int i = 0;i<cntWords;i++){
            int random = (int) (Math.random()*cntRandom);

            lottery.add(indexes.get(random));
            indexes.remove(random);
            cntRandom--;
        }

        List<Word> lotteryWords = new ArrayList<>();
        for(int i = 0;i<cntWords;i++){
            lotteryWords.add(allWords.get(lottery.get(i)));
        }
        return lotteryWords;
    }

    @Override
    public boolean wordEdit(Long wordId, String eng, String ukr, String transcription, Date dateIn, User user,
                            Category category) {
        Word testWord = (Word) factory.getCurrentSession().createCriteria(Word.class)
                .add(Restrictions.eq("englishWord",eng))
                .add(Restrictions.eq("ukrainianWord",ukr))
                .uniqueResult();
        if(testWord!=null && !testWord.getWordId().equals(wordId)) {
            return false;
        }
        Word word = getWordById(wordId);
        User userBefore = word.getUser();
        Category categoryBefore = word.getCategory();
        word.setEnglishWord(eng);
        word.setUkrainianWord(ukr);
        word.setTranscription(transcription);
        if(dateIn!=null){
            word.setDateIn(dateIn);
        }
        word.setUser(user);
        word.setCategory(category);
        factory.getCurrentSession().update(word);

        if(!user.equals(userBefore)){
            List<Word> wordsBefore = factory.getCurrentSession().createCriteria(Word.class)
                    .add(Restrictions.eq("user", userBefore))
                    .list();
            Set<Word> wordSet = new HashSet<>();
            wordSet.addAll(wordsBefore);
            userBefore.setWords(wordSet);
            userBefore = (User) factory.getCurrentSession().merge(userBefore);
            factory.getCurrentSession().update(userBefore);

            List<Word> words = factory.getCurrentSession().createCriteria(Word.class)
                    .add(Restrictions.eq("user", user))
                    .list();
            wordSet = new HashSet<>();
            wordSet.addAll(words);
            user.setWords(wordSet);
            user = (User) factory.getCurrentSession().merge(user);
            factory.getCurrentSession().update(user);
        }
        if(!category.equals(categoryBefore)){
            List<Word> wordsBefore = factory.getCurrentSession().createCriteria(Word.class)
                    .add(Restrictions.eq("category", categoryBefore))
                    .list();
            Set<Word> wordSet = new HashSet<>();
            wordSet.addAll(wordsBefore);
            categoryBefore.setWords(wordSet);
            categoryBefore = (Category) factory.getCurrentSession().merge(categoryBefore);
            factory.getCurrentSession().update(categoryBefore);

            List<Word> words = factory.getCurrentSession().createCriteria(Word.class)
                    .add(Restrictions.eq("category", category))
                    .list();
            wordSet = new HashSet<>();
            wordSet.addAll(words);
            category.setWords(wordSet);
            category = (Category) factory.getCurrentSession().merge(category);
            factory.getCurrentSession().update(category);
        }
        return true;
    }

    @Override
    public Word getWordById(Long wordId) {
        return (Word) factory.getCurrentSession().get(Word.class,wordId);
    }
}
