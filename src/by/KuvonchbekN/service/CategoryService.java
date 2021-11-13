package by.KuvonchbekN.service;

import by.KuvonchbekN.model.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CategoryService implements BaseService<Category, Category, List<Category>> {


    @Override
    public Category add(Category category) {
        BaseService.categoryList.add(category);
        return category;
    }

    @Override
    public Category get(UUID id) {
        int i = 0;
        for ( Category category: BaseService.categoryList) {
            if(category.getId().equals(id)){
                category.setLast(false);
                BaseService.categoryList.set(i, category);
            }
            i++;
        }
        return null;
    }

    @Override
    public Category check(String str) {
        return null;
    }

    @Override
    public List<Category> list(Category category) {
        return null;
    }

    @Override
    public Boolean delete(UUID id) {
        return null;
    }

    public List<Category> getCategoryList(UUID id){
        List<Category> categoryList1 = new ArrayList<>();

        for (Category category: BaseService.categoryList) {
            if(id == null && category.isActive() && category.getParentId() == null || category.getParentId() != null && category.isActive() && category.getParentId().equals(id)){
                categoryList1.add(category);
            }
        }
        return categoryList1;
    }
}