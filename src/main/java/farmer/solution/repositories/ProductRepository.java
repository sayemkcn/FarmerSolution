package farmer.solution.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import farmer.solution.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	Product findById(Long id);
	List<Product> findByCategory(String category);
}
