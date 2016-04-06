package farmer.solution.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import farmer.solution.entities.Product;
import farmer.solution.entities.User;
import farmer.solution.repositories.ProductRepository;
import farmer.solution.services.ProductService;
import farmer.solution.services.UserService;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;

	// @RequestMapping(value="",method = RequestMethod.GET)
	// public String showAllProducts(Model model){
	// List<Product> productList = productRepository.findAll();
	//// byte[] imageBytes = productList.get(0).getImage();
	//// String encodedImage = Base64.getEncoder().encodeToString(imageBytes);
	//// model.addAttribute("image",encodedImage);
	// model.addAttribute("productList",productList);
	// return "product/productList";
	// }

	// @RequestMapping(value="/images/{id}",method=RequestMethod.GET,produces=MediaType.IMAGE_PNG_VALUE)
	// public @ResponseBody byte[] getImage(@PathVariable("id") Long id){
	// Product product = productRepository.findById(id);
	//// System.out.println("Image Length "+product.getImage().length);
	// byte[] image = product.getImage();
	// HttpHeaders httpHeaders = new HttpHeaders();
	// httpHeaders.setContentType(MediaType.IMAGE_PNG);
	//
	// ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(image,
	// httpHeaders,HttpStatus.CREATED);
	// System.out.println("Length "+responseEntity.getBody().length);
	// return Base64.encodeBase64URLSafe(image);
	// }
	//

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createProductPage() {
		return "product/createProduct";
	}

	// creates a product
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Product createProduct(@ModelAttribute Product product, @RequestParam("token") String token,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.toString());
		}
		User user = userService.loadUserByToken(token);
		if (user == null) {
			return null;
		} else {
			try {
				user.getProductList().add(product);
				userService.saveUser(user);
			} catch (Exception e) {
				System.out.println("Problem saving product!! " + e.toString());
			}
		}

		return product;
	}

	// find product paginated (by range)
	// param start is page start index, param size is page size
	@RequestMapping(value = "/range", method = RequestMethod.GET)
	@ResponseBody
	public List<Product> getProductByRange(@RequestParam Map<String, String> map) {
		int start = Integer.parseInt(map.get("start"));
		int size = Integer.parseInt(map.get("size"));
		return productService.getProductByRange(start, size);
	}

	// find product by category
	@RequestMapping(value = "/category/{categoryName}", method = RequestMethod.GET)
	@ResponseBody
	public List<Product> getProductByCategory(@PathVariable("categoryName") String categoryName) {
		return productService.loadProductByCategory(categoryName);
	}

}
