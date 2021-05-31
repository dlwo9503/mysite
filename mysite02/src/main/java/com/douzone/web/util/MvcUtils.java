package com.douzone.web.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MvcUtils {

	public static void forward(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/" + path + ".jsp"); // 받아온 경로를 포장해주기
		rd.forward(request, response); // 포장한 경로로 이동
	}

	public static void redirect(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(url); // url로 리다이렉트
	}
}