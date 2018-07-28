package by.grsu.edu.store.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.grsu.edu.store.api.dao.exceptions.DaoException;
import by.grsu.edu.store.api.service.IProductService;
import by.grsu.edu.store.responce.MyResponse;
import by.grsu.edu.store.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	private static final String TOKEN = "token";

	private static Logger logger = LogManager.getLogger(UserController.class);

	private IProductService productService;

	public ProductController() {
		try {
			productService = new ProductService();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			logger.log(Level.INFO, e.getMessage(), e);
		}
	}

	@GetMapping
	public Object getAll() throws SQLException, DaoException {
		return new MyResponse(productService.getAll());
	}
	
	@DeleteMapping("/{id}")
	public Object delete(@PathVariable Long id, HttpServletRequest request) throws SQLException, DaoException {
		
		Long currentUserId = TokenMap.getTokenMap().get(TOKEN);
		productService.delete(id, currentUserId);
		return new MyResponse();
	}

}
