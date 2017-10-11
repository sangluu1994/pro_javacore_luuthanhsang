/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * ChessPosition.java, 11/10/2017 SangLT
 */
package connect_five.entity;

import connect_five.config.CommonConfig;

/**
 * Class đối tượng thế cờ
 * 
 * @author Luu Thanh Sang
 *
 */
public class ChessPosition {
	// Khai báo thuộc tính: ma trận thế cờ 5x5
	private String[][] chessPositionMatrix = new String[CommonConfig.CHESS_POSITION_MATRIX_ROW][CommonConfig.CHESS_POSITION_MATRIX_COLUMN];
	
	/**
	 * Constructor: Hàm khởi tạo
	 * 
	 * @param chessPositionMatrix : ma trận thế cờ 5x5
	 */
	public ChessPosition(String[][] chessPositionMatrix) {
		this.chessPositionMatrix = chessPositionMatrix;
	}
	
	/**
	 * Setter: thiết lập giá trị của ma trận thế cờ
	 * 
	 * @param chessPositionMatrix : ma trận thế cờ 5x5
	 */
	public void setChessPosition(String[][] chessPositionMatrix) {
		this.chessPositionMatrix = chessPositionMatrix;
	}
	
	/**
	 * Getter: lấy thuộc tính ma trận thế cờ
	 * 
	 * @return chessPositionMatrix: ma trận thế cờ
	 */
	public String[][] getChessPositionMatrix() {
		return chessPositionMatrix;
	}
}
