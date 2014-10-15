package com.wizard.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wizard.util.PingUtil;

public class PingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 3900933460083072897L;
	
	public PingServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getParameter("uri");
		boolean ping=PingUtil.ping(uri);
		PrintWriter out = response.getWriter();
		out.print(ping);
		out.close();
		out = null;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	public void init() throws ServletException {
	}

}
