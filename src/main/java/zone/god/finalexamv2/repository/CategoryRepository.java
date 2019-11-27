package zone.god.finalexamv2.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import zone.god.finalexamv2.model.Category;

public interface CategoryRepository extends PagingAndSortingRepository<Category,Integer> {
}
