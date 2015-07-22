package english.dao.interfaces;

import english.domain.Category;

import java.util.List;

/**
 * @author Sergii Makarenko
 */
public interface CategoryDao {
    Long addCategory(String name);
    List<Category> findAllCategories();
    Category getCategoryById(Long id);
    Category getCategoryByName(String categoryName);
    List<Category> getCategoryByPortion(int portion, int startFrom);
    boolean editCategory(Long categoryId, String name);
}
