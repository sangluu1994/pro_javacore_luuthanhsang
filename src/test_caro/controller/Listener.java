/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * Listener.java, 06/10/2017 Luu Thanh Sang
 */
package test_caro.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import test_caro.config.CommonConfig;
import test_caro.view.ChessBoard;

/**
 * @author Luu Thanh Sang
 *
 */
public class Listener implements ActionListener{
	/** 
	 * Hàm xử lí các sự kiện trên bàn cờ
	 * 
	 * @param e : đối tượng sự kiện trên bàn cờ
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Khởi tạo mảng các ô cờ
		JButton squares[][] = ChessBoard.squares;
		Controller handle = new Controller(); // Khởi tạo đối tượng xử lí chơi cờ
		int xPosition, yPosition; // Khởi tạo tung độ, hoành độ của ô cờ được đánh
		String positionVal = e.getActionCommand(); // Lấy tọa độ ô cờ được đánh
		if (e.getActionCommand() == "computer") { // Nếu click vào nút máy đánh trước
			handle.resetChessBoard(); // Tạo ván chơi mới
			squares[9][9].setText("X"); // Máy đánh vào ô cờ giữa bàn cờ
		} else if (positionVal == "human") { // Nếu click vào nút người đánh trước
			handle.resetChessBoard(); // Tạo ván chơi mới
		} else { // Nếu click vào các ô cờ trên bàn cờ:
			String[] positionArr = positionVal.split(" ");
			JButton square = (JButton) e.getSource(); // Lấy đối tượng ô cờ được đánh
//			xPosition = Integer.parseInt(positionArr[0]); // Lấy hoành độ ô cờ được đánh
//			yPosition = Integer.parseInt(positionArr[1]); // Lấy tung độ ô cờ được đánh
			xPosition = square.getBounds().y / CommonConfig.SIZE;
			yPosition = square.getBounds().x / CommonConfig.SIZE;
			System.out.println(xPosition + " " + yPosition);
//			// Kiểm tra ô cờ đánh có hợp lệ không
//			if ("".equals(squares[xPosition][yPosition].getText())) { // Nếu ô cờ được đánh vẫn trống
//				
//				squares[xPosition][yPosition].setText("O"); // Gán giá trị ô cờ được đánh là O
//				// Kiếm tra thắng thua
//				if (handle.checkWin(new Position(xPosition, yPosition))) { // Nếu người thắng
//					ChessBoard.showMessage("You win!"); // Hiện thông báo thắng cuộc
//					handle.resetChessBoard(); // Tạo ván chơi mới
//				} else {
//					handle.getComputerTurn(ChessBoard.chessPositions); // Nếu không thắng thì tới lượt máy đánh
//				}
//			}
		}
	}
	
}
