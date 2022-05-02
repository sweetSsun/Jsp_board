package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.InputTest;
import service.InputTestService;

/**
 * Servlet implementation class inputTest
 */
@WebServlet({"/inputTest", "/selectTest"}) // 호출되는 주소값
public class InputTestController extends HttpServlet {
	// 컨트롤러는 뷰와 모델을 연결해주는 중계자. 직접적인 기능은 수행하지 않음
	// 기능을 수행하는 매니저 클래스(서비스)를 호출, 매니저 클래스(서비스)에서는 기능을 수행하여 컨트롤러로 반환
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InputTestController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = request.getServletPath();
		System.out.println("url : " + url);
		InputTestService itSvc = new InputTestService(); // 입력기능 객체 생성
		
		switch(url) { // url에 따라 어떤 기능을 수행할지 분류
		case "/inputTest":
			String testData01 = request.getParameter("testData01");
			String testData02 = request.getParameter("testData02");
			
			System.out.println("testData01 : " + testData01);
			System.out.println("testData02 : " + testData02);
			
			// service 클래스의 입력 기능 메소드 호출			
			int result = itSvc.insertTest(testData01, testData02); // 입력에 실패하면 0, 성공하면 1 이상
			if (result > 0) {
				// 성공했을 때 보여줄 페이지로 포워딩
				response.sendRedirect("Success.jsp");
			} else {
				// 실패했을 때 보여줄 페이지로 포워딩
				response.sendRedirect("Fail.jsp");
			}
			break;
			
		case "/selectTest":
			System.out.println("데이터 select 기능");
			// 1. 데이터 조회 기능 메소드 호출
			// 2. 리스트를 리턴 받음
			ArrayList<InputTest> testList = itSvc.selectTest();
			System.out.println("testList.size() : " + testList.size());
			
			// 3. 
			if ( testList.size() > 0 ) {
				// 성공했을 때 보여줄 페이지로 포워딩 (dispatcher 방식)
//				System.out.println(testList.toString());
				request.setAttribute("testList", testList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Success.jsp");
				dispatcher.forward(request, response);
				// Success.jsp
			} else {
				// 실패했을 때 보여줄 페이지로 포워딩
				response.sendRedirect("Fail.jsp");
			}
			break;
		}	
		
		
		
		// 이 부분에서 해당 페이지를 만들어서 보여줄 수도 있지만, 만들어져 있는 페이지를 forwarding 해주는게 편함.
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
