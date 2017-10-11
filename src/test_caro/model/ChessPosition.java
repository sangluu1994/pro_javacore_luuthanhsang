/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * ChessPosition.java, 06/10/2017 Luu Thanh Sang
 */
package test_caro.model;

/**
 * Class đối tượng thế cờ
 * 
 * @author Luu Thanh Sang
 *
 */
public class ChessPosition {
	// Khai báo thuộc tính: ma trận thế cờ 5x5
	private String[][] chessPosition = new String[5][5];
	
	/**
	 * Constructor: Hàm khởi tạo
	 * 
	 * @param chessPosition : ma trận thế cờ 5x5
	 */
	public ChessPosition(String[][] chessPosition) {
		this.chessPosition = chessPosition;
	}
	
	/**
	 * Setter: thiết lập giá trị của ma trận thế cờ
	 * @param chessPosition : ma trận thế cờ 5x5
	 */
	public void setChessPosition(String[][] chessPosition) {
		this.chessPosition = chessPosition;
	}
	
	/**
	 * Getter: lấy thuộc tính thế cờ
	 * 
	 * @return chessPosition: ma trận thế cờ
	 */
	public String[][] getChessPosition() {
		return chessPosition;
	}
}
