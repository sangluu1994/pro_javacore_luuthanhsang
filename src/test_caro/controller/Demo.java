/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * Demo.java, 04/10/2017 Luu Thanh Sang
 */
package test_caro.controller;

import test_caro.view.ChessBoard;

/**
 * Class Demo
 * 
 * @author sanglt
 *
 */
public class Demo {

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ChessBoard chessBoard = new ChessBoard(); // Khai báo đối tượng bàn cờ
		chessBoard.drawChessBoard();; // Vẽ bàn cờ
	}

}