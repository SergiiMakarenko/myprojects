package english.dao.implementation;

import english.dao.interfaces.CategoryDao;
import english.domain.Category;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sergii Makarenko
 */
@Repository
public class CategoryDaoImpl implements CategoryDao {
    @Autowired
    private SessionFactory factory;

    @Override
    public Long addCategory(String name) {
        Category testCategory = (Category) factory.getCurrentSession().createCriteria(Category.class)
                .add(Restrictions.eq("categoryName",name))
                .uniqueResult();
        if(testCategory==null){
            Category category = new Category(name);
            return (Long) factory.getCurrentSession().save(category);
        }
        System.out.println("This category contains in base");
        return null;
    }

    @Override
    public List<Category> findAllCategories() {
        return factory.getCurrentSession().createCriteria(Category.class)
                .list();
    }

    @Override
    public Category getCategoryById(Long id) {
        return (Category) factory.getCurrentSession().get(Category.class,id);
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return (Category) factory.getCurrentSession().createCriteria(Category.class)
                .add(Restrictions.eq("categoryName",categoryName))
                .uniqueResult();
    }

    @Override
    public List<Category> getCategoryByPortion(int portion, int startFrom) {
        return factory.getCurrentSession().createCriteria(Category.class)
                .setFirstResult(startFrom)
                .setMaxResults(portion)
                .list();
    }

    @Override
    public boolean editCategory(Long categoryId, String name) {
        Category testCategory = (Category) factory.getCurrentSession().createCriteria(Category.class)
                .add(Restrictions.eq("categoryName",name))
                .uniqueResult();
        if(testCategory!=null && testCategory.getCategoryId()!=categoryId){
            return false;
        }
        Category category = getCategoryById(categoryId);
        category.setCategoryName(name);
        factory.getCurrentSession().update(category);
        return true;
    }
}
