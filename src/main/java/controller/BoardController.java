package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
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
@WebServlet( {"/Board/boardWrite", "/Board/boardList"})
// 패턴을 맞추려고 Board를 붙여주는걸까요????????
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
		RequestDispatcher dispatcher; // dispatcher를 여러번 사용해야 하기 때문에 선언을 switch문 위에서
		
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
				// 글 목록 페이지로 이동
				response.sendRedirect(contextPath+"/Board/boardList");
				// BoardMain.jsp로 이동
				// 절대경로를 만들어주는 것 (페이지가 많아질 경우 절대경로로 하는게 편함)
//				response.sendRedirect(contextPath+"/BoardMain.jsp"); // 절대경로
//				response.sendRedirect("../BoardMain.jsp"); // 상대경로
				// 해당 페이지에서의 상대경로
			} else {
				// 2. 글작성에 실패했을 경우
				// BoardWrite.jsp로 이동
				response.sendRedirect(contextPath+"/Board/BoardWrite.jsp?checkMsg=1");
				// BoardWrite.jsp 페이지에 파라미터를 직접 붙여준 것
				// jsp >> jsp로 이동할 때 (브라우저가 호출) (?파라미터이름 = 값)
//				response.sendRedirect("BoardWrite.jsp");
			}
			break;
			
		case "/Board/boardList":
			// 1. 글 목록 조회 서비스 기능 호출 및 리턴
			System.out.println("글 목록 페이지 이동 요청");
			ArrayList<Board> bdList = bsvc.getBoardList();
			System.out.println(bdList);
			// 2. 리턴 값에 따라 페이지 포워딩
			
			
			// 3-1. 글 목록을 받아왔으면 - request 영역에 저장
			request.setAttribute("bdList", bdList);
			if (bdList.size() > 0 ) {
				// 3-2.dispatcher 방식으로 "BoardList.jsp" 포워딩)
				dispatcher = request.getRequestDispatcher("/Board/BoardList.jsp"); // dispatcher 생성은 여기서
				/* dispatcher 방식 : 서블릿 내에서 서블릿 호출
				 * url 앞에 붙는 / 자체가 초기 경로값(Jsp_Board/)의 역할을 수행
				 * ContextPath+"/Board/BoardList.jsp" :: /Jsp_Board/Jsp_Board/Board/BoardList.jsp  */ 
				
				dispatcher.forward(request, response);				
			} else {
				// 2-2. 글 목록 받아오는데 실패했으면 - 페이지 이동 및 메세지
				response.sendRedirect(contextPath+"/BoardMain.jsp");
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
