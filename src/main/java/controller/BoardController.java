package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Board;
import service.BoardService;

/**
 * Servlet implementation class BoardController
 */
@WebServlet( {"/Board/boardWrite"})
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getServletPath(); // 요청한 주소
		System.out.println("url : " + url);
		String contextPath = request.getContextPath(); // webapp의 주소
		System.out.println("contextPath : " + contextPath);
//		webapp >> http://localhost:8080/Jsp_board/
		
		BoardService bsvc = new BoardService();
		
		switch(url) {
		case "/Board/boardWrite":
			System.out.println("글 작성 페이지 데이터 확인");
			// BoardWrite.jsp 에서 보내준 데이터 확인
			// 글작성자, 글제목, 글내용
			String bwriter = request.getParameter("bwriter");
			String btitle = request.getParameter("btitle");
			String bcontents = request.getParameter("bcontents");
			System.out.println("글작성자 : " + bwriter);
			System.out.println("글 제목 : " + btitle);
			System.out.println("글 내용 : " + bcontents);
			
			Board boardInfo = new Board();
			boardInfo.setBwriter(bwriter);
			boardInfo.setBtitle(btitle);
			boardInfo.setBcontents(bcontents);
			
			int writeResult = bsvc.boardWrite(boardInfo);
			if(writeResult > 0) {
				// 1. 글작성에 성공했을 경우
				// BoardMain.jsp로 이동
				// 절대경로를 만들어주는 것 (페이지가 많아질 경우 절대경로로 하는게 편함)
				response.sendRedirect(contextPath+"/BoardMain.jsp"); // 절대경로
				response.sendRedirect("../BoardMain.jsp"); // 상대경로
				// 해당 페이지에서의 상대경로
			} else {
				// 2. 글작성에 실패했을 경우
				// BoardWrite.jsp로 이동
				response.sendRedirect(contextPath+"/Board/BoardWrite.jsp?checkMsg=1");
				// BoardWrite.jsp 페이지에 파라미터를 직접 붙여준 것
				// jsp >> jsp로 이동할 때 (?파라미터이름 = 값)
//				response.sendRedirect("BoardWrite.jsp");
			}
			break;
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
