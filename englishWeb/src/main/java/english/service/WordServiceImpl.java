package english.service;

import english.dao.CategoryDao;
import english.dao.WordDao;
import english.domain.Category;
import english.domain.User;
import english.domain.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by serg on 07.05.15.
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
    public List<Category> getCategoryByPortion(String portion, String startFrom) {
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
    public List<Word> getWordsByPortion(String portion, String startFrom) {
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
}
