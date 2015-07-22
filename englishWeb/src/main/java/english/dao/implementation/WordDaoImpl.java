package english.dao.implementation;

import english.dao.interfaces.WordDao;
import english.domain.Category;
import english.domain.User;
import english.domain.Word;
import english.results.CommonMethods;
import english.results.WordUserEffect;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author Sergii Makarenko
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
                .add(Restrictions.eq("user",user))
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
    public List<Word> findAllByUser(User user, User admin) {
        return factory.getCurrentSession().createCriteria(Word.class)
                .add(Restrictions.or(
                        Restrictions.eq("user", user),
                        Restrictions.eq("user", admin)))
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
    public List<Word> getWordsByPortionUser(int portion, int startFrom, User user, User admin) {

        return factory.getCurrentSession().createCriteria(Word.class)
                .add(Restrictions.or(
                        Restrictions.eq("user", user),
                        Restrictions.eq("user", admin)))
                .setMaxResults(portion)
                .setFirstResult(startFrom)
                .list();
    }

    @Override
    public List<Word> getRandomWords(int cntWords) {
        List<Word> allWords = factory.getCurrentSession().createCriteria(Word.class)
                .list();
        if(cntWords>allWords.size())
            return allWords;

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
                .add(Restrictions.eq("user",user))
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

    @Override
    public List<WordUserEffect> getWordsByPortionByUserByEffect(int portion, int startFrom, User user, User admin,
                                                                Double effectiveness) {
        Long adminId = admin==null ? 0:admin.getUserId();
        Long userIdWord = user==null ? 0:user.getUserId();
        Long userIdResult = user==null ? 0:user.getUserId();

        Iterator iterator = factory.getCurrentSession().createSQLQuery(
                "SELECT word_id, amountEngUkr, effEngUkr, amountUkrEng, effUkrEng FROM WORDS W\n" +
                "LEFT JOIN\n" +
                "(SELECT word_word_id, COUNT(wordtest_id) AS amountEngUkr, AVG(result) AS effEngUkr \n" +
                "FROM WORDTESTRESULT W\n" +
                "LEFT JOIN TESTS T ON W.test_test_id=T.test_id\n" +
                "WHERE testname='Translate Eng-Ukr' AND user_user_id=" + userIdResult +"\n" +
                "GROUP BY word_word_id) E ON W.word_id=E.word_word_id\n" +
                "LEFT JOIN\n" +
                "(SELECT word_word_id, COUNT(wordtest_id) AS amountUkrEng, AVG(result) AS effUkrEng \n" +
                "FROM WORDTESTRESULT W\n" +
                "LEFT JOIN TESTS T ON W.test_test_id=T.test_id\n" +
                "WHERE testname='Translate Ukr-Eng' AND user_user_id=" + userIdResult +"\n" +
                "GROUP BY word_word_id) U ON W.word_id=U.word_word_id\n" +
                "WHERE (effEngUkr<= " + effectiveness + " OR effEngUkr IS NULL) AND user_user_id=" + userIdWord +
                        " OR user_user_id=" + adminId + "ORDER BY englishword"
                        )
                .setMaxResults(portion)
                .setFirstResult(startFrom)
                .list()
                .iterator();

        List<WordUserEffect> wordUserEffects = new ArrayList<>();
        while (iterator.hasNext()){
            Object[] objects = (Object[]) iterator.next();

            Word word = (Word) factory.getCurrentSession().get(Word.class,Long.parseLong(objects[0].toString()));
            Long amountEngUkr = objects[1]==null ? null:Long.parseLong(objects[1].toString());
            Double effectivenessEngUkr = objects[2]==null ? null:Double.parseDouble(objects[2].toString());
            Long amountUkrEng = objects[3]==null ? null:Long.parseLong(objects[3].toString());
            Double effectivenessUkrEng = objects[4]==null ? null:Double.parseDouble(objects[2].toString());
            wordUserEffects.add(new WordUserEffect(word,amountEngUkr,effectivenessEngUkr,
                    amountUkrEng,effectivenessUkrEng));
        }
        return wordUserEffects;
    }

    @Override
    public List<WordUserEffect> getRandomWordsByUserEffect(int cntWords, User user, User admin, Double effectiveness) {
        List<WordUserEffect> allWords = getWordsByPortionByUserByEffect(Integer.MAX_VALUE,0,user,admin,effectiveness);

        return new CommonMethods().getRandomList(allWords,cntWords);
    }
}
