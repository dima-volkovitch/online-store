package by.grsu.edu.store.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.grsu.edu.store.api.dao.exceptions.DaoException;
import by.grsu.edu.store.api.service.IUserService;
import by.grsu.edu.store.entity.User;
import by.grsu.edu.store.responce.MyResponse;
import by.grsu.edu.store.service.UserService;
import by.grsu.edu.store.utile.Cryptographer;

@RestController
@RequestMapping("/users")
public class UserController {
	private static final String ALREADY_AUTH_MESS = "You arre already authorized";
	private static final String INCORRECT_PASSWORD = "Incorrect password!";
	private static final String TOKEN = "token";

	private static Logger logger = LogManager.getLogger(UserController.class);

	private Cryptographer crypto;

	private IUserService userService;

	public UserController() {
		crypto = new Cryptographer();
		try {
			userService = new UserService();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
			logger.log(Level.INFO, e.getMessage(), e);
		}
	}

	@PostMapping("/signin")
	public Object signIn(@RequestBody User user, HttpServletRequest request, HttpServletResponse response)
			throws SQLException, DaoException {
		String token = request.getHeader(TOKEN);
		if (token != null && TokenMap.getTokenMap().containsToken(token)) {
			return new MyResponse(ALREADY_AUTH_MESS, false);
		}
		System.out.println(user.getNumber() + user.getPassword());
		System.out.println(userService);
		User userr = userService.get(user.getNumber());
		if (!userr.getPassword().equals(user.getPassword())) {
			return new MyResponse(INCORRECT_PASSWORD, false);
		}

		token = crypto.encode(user.getNumber(), user.getPassword());
		TokenMap.getTokenMap().put(token, userr.getId());
		response.setHeader(TOKEN, token);
		System.out.println("WORKS");
		return new MyResponse(token);
	}

	@PostMapping("/signout")
	public Object signOut(HttpServletRequest request) {
		String token = request.getHeader(TOKEN);
		TokenMap.getTokenMap().remove(token);
		return token;
	}
	
	@PostMapping("/signup")
	public Object signUp(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) throws SQLException, DaoException {
		String token = request.getHeader(TOKEN);
		if (token != null && TokenMap.getTokenMap().containsToken(token)) {
			return new MyResponse(ALREADY_AUTH_MESS, false);
		}
		userService.add(user);
		token = crypto.encode(user.getNumber(), user.getPassword());
		TokenMap.getTokenMap().put(token, user.getId());

		return new MyResponse(token);
	}
}
