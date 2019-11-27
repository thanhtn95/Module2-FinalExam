package zone.god.finalexamv2.service;

import org.springframework.beans.factory.annotation.Autowired;
import zone.god.finalexamv2.model.Category;
import zone.god.finalexamv2.repository.CategoryRepository;

public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }
}
