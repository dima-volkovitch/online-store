package by.grsu.edu.store.handler;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import by.grsu.edu.store.responce.MyResponse;

public class GlobalExceptionHandler {

	private static Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public @ResponseBody MyResponse handleException(Exception e) {
		logger.log(Level.INFO, e.getMessage(), e);
		return new MyResponse(e.getMessage(), false);
	}
}
