package service;

import java.util.ArrayList;

import dao.BoardDao;
import dto.Board;

public class BoardService {
	
	private BoardDao bdao = new BoardDao();

	// 글 등록 기능
	public int boardWrite(Board boardInfo) {
		System.out.println("BoardService.boardWrite() 호출");
		
		// 글번호 생성
		int maxBno = bdao.getMaxBno();
		System.out.println("maxBno : " + maxBno);
		boardInfo.setBno(maxBno + 1);
		
		// 글 등록
		int insertResult = bdao.insertBoard(boardInfo);
		return insertResult;
	}

	// 글 목록 조회 기능
	public ArrayList<Board> getBoardList() {
		System.out.println("BoardService.getBoardList() 호출");
		ArrayList<Board> bdList = bdao.selectBoardList();
		return bdList;
	}

	// 글 상세 조회 기능
	public Board getBoardInfo(int bno) {
		System.out.println("BoardService.getBoardView() 호출");
		// 글 정보 조회
		Board board = bdao.selectBoardInfo(bno);
		
		String bcontents = board.getBcontents();
		System.out.println("변경 전 : " + bcontents);
		bcontents = bcontents.replaceAll(" ", "&nbsp;");
		bcontents = bcontents.replaceAll("\r\n", "<br>");
		System.out.println("변경 후 : " + bcontents);
		board.setBcontents(bcontents);
		return board;
	}

}
