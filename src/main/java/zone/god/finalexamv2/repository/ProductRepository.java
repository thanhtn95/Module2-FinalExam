package zone.god.finalexamv2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import zone.god.finalexamv2.model.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product,Integer> {
    Page<Product> findAllByNameContaining(String search, Pageable pageable);
}
