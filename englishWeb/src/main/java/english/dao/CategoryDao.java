package english.dao;

import english.domain.Category;

import java.util.List;

/**
 * Created by serg on 06.04.15.
 */
public interface CategoryDao {
    Long addCategory(String name);
    List<Category> findAllCategories();
    Category getCategoryById(Long id);
    Category getCategoryByName(String categoryName);
    List<Category> getCategoryByPortion(String portion, String startFrom);
    boolean editCategory(Long categoryId, String name);
}
