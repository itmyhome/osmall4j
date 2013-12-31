//package com.os.util;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
///**
// * Servlet implementation class ProgressServlet
// */
//public class ProgressServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public ProgressServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doPost(request, response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html");
//		response.setHeader("Cache-Control", "no-cache");
//		PrintWriter out = response.getWriter();
//
//		HttpSession session = request.getSession(true);
//		if (session == null) {
//		out.println("Sorry, session is null"); // just to be safe
//		return;
//		}
//
//		ProgressEntity progressEntity = (ProgressEntity) session.getAttribute("upload_ps");
//		if (progressEntity == null) {
//		out.println("ProgressEntity is null");
//		return;
//		}
//		
//		System.out.println("new ->:"+progressEntity.getPercent());
//		out.println(progressEntity.getPercent());
//
//	}
//
//}
