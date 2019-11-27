package zone.god.finalexamv2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zone.god.finalexamv2.model.Product;
import zone.god.finalexamv2.repository.ProductRepository;

public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(int id) {
        if(productRepository.findById(id).isPresent()){
            return productRepository.findById(id).get();
        }else{
            return null;
        }
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> findAllByNameContaining(String search, Pageable pageable) {
        return productRepository.findAllByNameContaining(search,pageable);
    }
}
