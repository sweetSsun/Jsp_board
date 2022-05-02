package dto;
// 계층간의 타입을 변환해주는 클래스
// vo (value object) : 값을 담아주는 클래스 (dto보다 한 단계 낮은 수준)
public class InputTest {

	private String testcol1;
	private String testcol2;
	
	public String getTestcol1() {
		return testcol1;
	}
	public void setTestcol1(String testcol1) {
		this.testcol1 = testcol1;
	}
	public String getTestcol2() {
		return testcol2;
	}
	public void setTestcol2(String testcol2) {
		this.testcol2 = testcol2;
	}
	@Override
	public String toString() {
		return "InputTest [testcol1=" + testcol1 + ", testcol2=" + testcol2 + "]";
	}
	
}
