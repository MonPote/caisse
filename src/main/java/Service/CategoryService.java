package Service;

import dao.CategoryDAO;
import model.CategoryEntity;

import java.util.List;

/**
 * Created by nea on 30/09/16.
 */
public class CategoryService {
    private CategoryDAO categoryDAO;

    public CategoryService() {
        categoryDAO = new CategoryDAO();
    }

    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public List<CategoryEntity> listCategories() {
        return this.categoryDAO.listCategories();
    }

    public List<CategoryEntity> listCategoriesByName(String name) {
        return this.categoryDAO.listCategoriesByName(name);
    }

    public List<CategoryEntity> listCategoriesById(int id) {
        return this.categoryDAO.listCategoriesById(id);
    }

    public void addCategory(CategoryEntity s) throws Exception {
        this.categoryDAO.addCategory(s);
    }

    public void removeCategory(CategoryEntity s) throws Exception {
        this.categoryDAO.removeCategory(s);
    }
    public void updateCategor(CategoryEntity s) throws Exception {
        this.categoryDAO.updateCategory(s);
    }
}
