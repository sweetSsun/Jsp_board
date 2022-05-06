package service;

import java.io.File;
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
	public Board getBoardInfo(int bno, boolean reCheck) {
		System.out.println("BoardService.getBoardInfo() 호출");
		// 글 정보 조회
		Board board = bdao.selectBoardInfo(bno);
		if (reCheck) { // 상세페이지에서만 작동하도록, 수정페이지에서는 개행문자 변환 X
			String bcontents = board.getBcontents();
			System.out.println("변경 전 : " + bcontents);
			bcontents = bcontents.replaceAll(" ", "&nbsp;");
			bcontents = bcontents.replaceAll("\r\n", "<br>");
			System.out.println("변경 후 : " + bcontents);
			board.setBcontents(bcontents);			
		}
		return board;
	}

	// 글 삭제 기능
	public int deleteBoard(int delBno, String savePath, String delBfilename ) {
		System.out.println("BoardService.deleteBoard() 호출");
		// 파일 삭제, 저장경로, 파일명
		int delResult = bdao.deleteBoard(delBno);
		if (delResult > 0) {
//			if(delBfilename != null) { // 파일명은 없으면 null값일 것 같은데 왜 길이가 0인걸까요????????????
			if(delBfilename.length() > 0) {
				File delFile = new File(savePath, delBfilename);
				delFile.delete();							
			}
		}		
		return delResult;
	}

	public int boardModify(Board board) {
		System.out.println("BoardService.boardModify() 호출");
		int updateResult = bdao.boardModify(board);
		return updateResult;
	}

	public ArrayList<Board> searchBoard(String searchText, String searchType) {
		System.out.println("BoardService.searchBoard() 호출");
		ArrayList<Board> searchList = bdao.searchBoard(searchText, searchType);
		return searchList;
	}

}
