package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.Board;
import service.BoardService;

/**
 * Servlet implementation class BoardController
 */
@WebServlet( {"/Board/boardWrite", "/Board/boardList", "/Board/boardView",
	"/Board/boardDelete", "/Board/boardModifyForm", "/Board/boardModify",
	"/Board/boardSearch"})
// 패턴을 맞추려고 Board를 붙여준 것
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
		
		Board board;
		BoardService bsvc = new BoardService();
		RequestDispatcher dispatcher; // dispatcher를 여러번 사용해야 하기 때문에 선언을 switch문 위에서
		request.setCharacterEncoding("UTF-8");
		
		String savePath = "D:\\Sun_Workspace\\Jsp_board\\src\\main\\webapp\\FileUpload";
		int size = 1024*1024*10; // 최대 업로드 용량 10MB / 1KB*1KB >> 1MB*10개
		
		switch(url) {
		case "/Board/boardWrite":
			System.out.println("글 작성 페이지 데이터 확인");
			// BoardWrite.jsp 에서 보내준 데이터 확인
			// 글작성자, 글제목, 글내용
			/*
			String bwriter = request.getParameter("bwriter");
			String btitle = request.getParameter("btitle");
			String bcontents = request.getParameter("bcontents");
			String bfilename = request.getParameter("bfile");
			System.out.println("글작성자 : " + bwriter);
			System.out.println("글 제목 : " + btitle);
			System.out.println("글 내용 : " + bcontents);
			System.out.println("bfilename : " + bfilename);
			*/
			MultipartRequest multi
			= new MultipartRequest(request, savePath, size, "UTF-8", new DefaultFileRenamePolicy());
			// request가 multi 객체에 담기기 때문에 multi에서 Parameter를 꺼내줌
			String bwriter = multi.getParameter("bwriter");
			String btitle = multi.getParameter("btitle");
			String bcontents = multi.getParameter("bcontents");
			// 파일에서 원본파일명만 추출해서 저장하기 위한 변수
			String bfilename = multi.getOriginalFileName( (String)multi.getFileNames().nextElement() );
			// 파일태그는 name에 해당하는 Element를 따로 찾아주어야 함
			// getOriginalFileName : 원본파일을 찾아주는 get method
			// getFileNames() : form 태그 내부에 담겨있는 type이 file인 name들을 받아온다
			// nextElement() : 받아온 목록 중에 요소 선택 (rs.next()와 비슷한 기능),
			//			       file 타입이 여러 개라면 반복문으로 저장 or 해당 필드를 배열로 선언
			// 원본파일명이기 때문에 중복데이터는 처리 불가
			
			
			System.out.println("bfilename : " + bfilename);
			
			Board boardInfo = new Board();
			boardInfo.setBwriter(bwriter);
			boardInfo.setBtitle(btitle);
			boardInfo.setBcontents(bcontents);
			boardInfo.setBfilename(bfilename);
			System.out.println(boardInfo);
			
			int writeResult = bsvc.boardWrite(boardInfo);
			if(writeResult > 0) {
				// 1. 글작성에 성공했을 경우
				// 글 목록 페이지로 이동
				response.sendRedirect(contextPath+"/Board/boardList");
				// BoardMain.jsp로 이동
				// 절대경로를 만들어주는 것 (페이지가 많아질 경우 절대경로로 하는게 편함)
//				response.sendRedirect(contextPath+"/BoardMain.jsp"); // 절대경로
//				response.sendRedirect("../BoardMain.jsp"); // 상대경로 (해당 페이지에서의 상대경로)
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
			
		case "/Board/boardView":
			System.out.println("글 상세페이지 이동 요청");
			// request에 글번호 파라미터값 확인
			int bno = Integer.parseInt(request.getParameter("bno"));
			System.out.println("상세보기 글번호 : " + bno);
			// 1. boardService 글 조회 기능 호출 및 글 정보 리턴
			board = bsvc.getBoardInfo(bno, true);
			request.setAttribute("board", board);
			System.out.println(board.toString());
			// console 출력 (System.out.println)
			dispatcher = request.getRequestDispatcher("/Board/BoardView.jsp");
			dispatcher.forward(request, response);
			
			// "BoardView.jsp"
			break;
		
		case "/Board/boardDelete":
			System.out.println("글 삭제 요청");
			// request에 글번호 파라미터값 확인
			int delBno = Integer.parseInt(request.getParameter("delBno"));
			System.out.println("삭제할 글번호 : " + delBno);
			String delBtitle = request.getParameter("delBtitle");
			System.out.println("삭제할 글제목 : " + delBtitle);
			
			// 삭제하고자 하는 파일명을 출력
			String delBfilename = request.getParameter("delBfilename");
			System.out.println("삭제할 파일명 : " + delBfilename);
			
			
			// service 글삭제 기능 호출 및 결과값 반환
			int delResult = bsvc.deleteBoard(delBno,savePath,delBfilename);
			System.out.println(delResult + "개의 게시글이 삭제되었습니다.");
			if(delResult > 0) {
				// 글 목록 페이지로 이동
				response.sendRedirect(contextPath+"/Board/boardList?checkMsg=true");
			} else {
				// 글 상세페이지로
//				response.sendRedirect(contextPath+"/Board/BoardView?bno="+delBno);

				// Board/BoardFail.jsp로
				// 실패 페이지로 이동하면서 파라미터값 전송 > script에서 파라미터값 확인하고 안내 후 뒤로가기(history.back();)
				String errorMsg = "No." + delBno + " Delete Fail";
				response.sendRedirect(contextPath+"/Board/BoardFail.jsp?errorMsg="+errorMsg);
			}
			break;
			
		case "/Board/boardModifyForm":
			System.out.println("글 수정페이지 요청");
			int mdBno = Integer.parseInt(request.getParameter("mdBno"));
			System.out.println("수정할 글번호 : " + mdBno);
			Board mdBoard = bsvc.getBoardInfo(mdBno, false);
			request.setAttribute("mdBoard", mdBoard);
			dispatcher = request.getRequestDispatcher("/Board/BoardModifyForm.jsp");
			dispatcher.forward(request, response);
			break;
		
		case "/Board/boardModify":
			System.out.println("글 수정 요청");
			int modiBno = Integer.parseInt(request.getParameter("bno"));
			String modiBtitle = request.getParameter("btitle");
			String modiBcontents = request.getParameter("bcontents");
			
			System.out.println("수정할 글번호 : " + modiBno);
			System.out.println("수정할 글제목 : " + modiBtitle);
			System.out.println("수정할 글내용 : " + modiBcontents);
			
			board = new Board();
			board.setBno(modiBno);
			board.setBtitle(modiBtitle);
			board.setBcontents(modiBcontents);
			int updateResult = bsvc.boardModify(board);
//			int updateResult = 0;
			if (updateResult > 0) {
				//글 상세페이지로
				response.sendRedirect(contextPath+"/Board/boardView?bno="+modiBno);
			} else {
				// BoardFail.jsp
				String errorMsg = modiBno + "번 글 수정에 실패하였습니다.";
				response.sendRedirect(contextPath+"/Board/BoardFail.jsp?errorMsg="
										+URLEncoder.encode(errorMsg, "UTF-8"));
			}			
			break;
		
		case "/Board/boardSearch":
			System.out.println("글 검색 요청");
			String searchText = request.getParameter("searchText");
			String searchType = request.getParameter("searchType"); 
			System.out.println("검색할 단어 : " + searchText);
			System.out.println("검색종류 : " + searchType);
			
			ArrayList<Board> searchList = bsvc.searchBoard(searchText, searchType);
			request.setAttribute("bdList", searchList);
			dispatcher = request.getRequestDispatcher("/Board/BoardList.jsp");
			dispatcher.forward(request, response);
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
