package farmer.solution.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import farmer.solution.entities.Product;
import farmer.solution.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public byte[] converFileIntoByteArray(File file) {
		byte[] b = new byte[(int) file.length()];
		try {
			@SuppressWarnings("resource")
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(b);
			for (int i = 0; i < b.length; i++) {
				System.out.print((char) b[i]);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found.");
			e.printStackTrace();
		} catch (IOException e1) {
			System.out.println("Error Reading The File.");
			e1.printStackTrace();
		}
		return b;
	}

	public void saveProduct(Product product) throws Exception {
		productRepository.save(product);
	}

	// Pagination
	public List<Product> getProductByRange(Integer start, Integer size) {
		Page<Product> page = productRepository.findAll(new PageRequest(start, size));
		return page.getContent();
	}
	
	// find all product by category
	public List<Product> loadProductByCategory(String categoryName){
		return productRepository.findByCategory(categoryName);
	}

}
