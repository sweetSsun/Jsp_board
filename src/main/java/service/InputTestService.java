package service;

import dao.InputTestDao;

public class InputTestService {
// 기존의 manager 클래스와 같은 기능
	
	private InputTestDao itDao = new InputTestDao();
	// 입력 기능 메소드
	public int insertTest(String testData01, String testData02) {
		System.out.println("InputTestService의 insertTest()");
		// DB와 연결		
		int insertResult = itDao.insertTest(testData01, testData02);
		
		return insertResult;
	}
	
	// 조회 기능 메소드
	
	// 수정 기능 메소드
	
}
