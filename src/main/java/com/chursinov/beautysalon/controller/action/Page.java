package com.chursinov.beautysalon.controller.action;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Page {

	private static final Logger logger = Logger.getLogger(Page.class);


	private HttpServletRequest request;
	private HttpServletResponse response;

	public Page(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public void navigate(ActionResult result) {
		try {
			if (result.getResponseType().equals(ResponseType.REDIRECT)) {
				response.sendRedirect(result.getOutput());
				} else {
				request.getRequestDispatcher(result.getOutput()).forward(request, response);
			}
		} catch (IOException | ServletException ex) {
			logger.error("Dispatcher failed: ", ex);
		}
	}
}
