package english.service;

import english.dao.interfaces.CategoryDao;
import english.dao.interfaces.WordDao;
import english.domain.Category;
import english.domain.User;
import english.domain.Word;
import english.results.WordUserEffect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Sergii Makarenko
 */
@Service
public class WordServiceImpl implements WordService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private WordDao wordDao;

    @Override
    @Transactional
    public Long addCategory(String name) {
        return categoryDao.addCategory(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAllCategories() {
        return categoryDao.findAllCategories();
    }

    @Override
    @Transactional(readOnly = true)
    public Category getCategoryById(Long id) {
        return categoryDao.getCategoryById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Category getCategoryByName(String categoryName) {
        return categoryDao.getCategoryByName(categoryName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getCategoryByPortion(int portion, int startFrom) {
        return categoryDao.getCategoryByPortion(portion, startFrom);
    }

    @Override
    @Transactional
    public boolean editCategory(Long categoryId, String name) {
        return categoryDao.editCategory(categoryId, name);
    }

    @Override
    @Transactional
    public Long addWord(String eng, String ukr, String transcription, Date dateIn, User user, Category category) {
        return wordDao.addWord(eng, ukr, transcription, dateIn, user, category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Word> findAllWords() {
        return wordDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Word> getWordsByPortion(int portion, int startFrom) {
        return wordDao.getWordsByPortion(portion, startFrom);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Word> getRandomWords(int cntWords) {
        return wordDao.getRandomWords(cntWords);
    }

    @Override
    @Transactional
    public boolean wordEdit(Long wordId, String eng, String ukr, String transcription, Date dateIn, User user,
                            Category category) {
        return wordDao.wordEdit(wordId, eng, ukr, transcription, dateIn, user, category);
    }

    @Override
    @Transactional(readOnly = true)
    public Word getWordById(Long wordId) {
        return wordDao.getWordById(wordId);
    }

    @Override
    @Transactional
    public List<Word> getWordsByPortionUser(int portion, int startFrom, User user, User admin) {
        return wordDao.getWordsByPortionUser(portion, startFrom, user, admin);
    }

    @Override
    @Transactional
    public List<Word> findAllWordsByUser(User user, User admin) {
        return wordDao.findAllByUser(user, admin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WordUserEffect> getWordsByPortionByUserByEffect(int portion, int startFrom, User user,
                                                                User admin, Double effectiveness) {
        return wordDao.getWordsByPortionByUserByEffect(portion, startFrom, user, admin, effectiveness);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WordUserEffect> getRandomWordsByUserEffect(int cntWords, User user, User admin, Double effectiveness) {
        return wordDao.getRandomWordsByUserEffect(cntWords, user, admin, effectiveness);
    }
}
