package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestController
 */
@WebServlet("/TestController")
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet 호출");
		String testId = request.getParameter("testId");
		String testPw = request.getParameter("testPw");
		System.out.println("testId : " + testId);
		System.out.println("testPw : " + testPw);
		
		String testData = "테스트 데이터"; // DB랑 연결되지 않아 특정 데이터 변수를 선언
		request.setAttribute("testData01", testData);
		
		// redirect 응답방식
//		response.sendRedirect("TestPage02.jsp");		
		
		// dispatcher 응답방식
		RequestDispatcher dispatcher = request.getRequestDispatcher("TestPage02.jsp");
		dispatcher.forward(request, response);
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doPost 호출");
		doGet(request, response);
	}

}
