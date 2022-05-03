package service;

import java.util.ArrayList;

import dao.BoardDao;
import dto.Board;

public class BoardService {
	
	private BoardDao bdao = new BoardDao();

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

	public ArrayList<Board> getBoardList() {
		System.out.println("BoardService.getBoardList() 호출");
		ArrayList<Board> bdList = bdao.selectBoardList();
		return bdList;
	}

}
