/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * ChessBoard.java, 04/10/2017 Luu Thanh Sang
 */
package test_caro.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import test_caro.config.CommonConfig;
import test_caro.controller.Listener;
import test_caro.model.ChessPosition;
import test_caro.model.ChessPositionUtil;

/**
 * Class Bàn cờ
 * 
 * @author sanglt
 *
 */
public class ChessBoard extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Khai báo thuộc tính số hàng và số cột của bàn cờ
	public static int row = CommonConfig.CHESS_BOARD_ROW; // số hàng
	public static int column = CommonConfig.CHESS_BOARD_COLUMN; // số cột
	
	// Khởi tạo mảng các ô cờ
	public static JButton squares[][] = new JButton[row][column];
	
	// Khởi tạo các thành phần trên bàn cờ
	public JFrame chessBoardFrame = new JFrame(); // Khung window
	public JPanel board, menu; // Division bàn cờ, menu
	public JButton human, computer; // Nút chọn người hoặc máy đánh trước
	public JLabel lblHuman, lblComputer; // Nhãn cho nút human, computer
	
	// Khai báo mảng đối tượng các thế cờ
	public static ArrayList<ChessPosition> chessPositions = ChessPositionUtil.getChessPostion("./src/model/chess_positions.txt");
	
	/**
	 * Hàm vẽ bàn cờ
	 */
	public void drawChessBoard() {
		
		board = new JPanel(); // Khởi tạo panel bàn cờ
		board.setLayout(new CardLayout()); // Thiết lập layout cho panel bàn cờ
		board.setSize(700, 700); // Thiết lập kích thước
		// Thêm các ô cờ vào bàn cờ
		for (int i = 0; i < row; i++) { // Duyệt qua từng hàng trong bàn cờ
			for (int j = 0; j < column; j++) { // Duyệt qua từng cột trong hàng đang duyệt
//				String positionVal = i + " " + j;
				squares[i][j] = new JButton(); // Tạo, gán giá trị quân cờ rỗng cho từng ô cờ
				squares[i][j].setBounds(i * CommonConfig.SIZE, j * CommonConfig.SIZE, 35, 35); // Thiết lập tọa độ, kích thước ô cờ
//				squares[i][j].setActionCommand(positionVal);
				squares[i][j].addActionListener(new Listener()); // Thêm sự kiện khi ô cờ được đánh
				board.add(squares[i][j]); // Thêm ô cờ vào bàn cờ
			}
		}
		
		menu = new JPanel(); // Tạo panel menu
		lblHuman = new JLabel("Người đánh trước"); // Tạo nhãn cho button human
		human = new JButton("O"); // Thiết lập text cho button human
		human.setActionCommand("human"); // Thiết lập command gửi đi khi xảy ra sự kiện button
		human.addActionListener(new Listener()); // Thiết lập sự kiện cho button human
		lblComputer = new JLabel("Máy đánh trước"); // Tạo nhãn cho button computer
		computer = new JButton("X"); // Thiết lập text cho button computer
		computer.setActionCommand("computer"); // Thiết lập command gửi đi khi xảy ra sự kiện tại button
		computer.addActionListener(new Listener()); // Thiết lập sự kiện cho button computer 
		menu.setSize(200, 700); // Thiết lập kích cỡ cho panel menu
		menu.setLayout(new FlowLayout()); // Thiết lập layout cho menu
		menu.add(lblHuman); // Thêm nhãn button human vào menu
		menu.add(human); // Thêm button human vào menu
		menu.add(lblComputer); // Thêm nhãn button computer vào menu
		menu.add(computer); // Thêm button computer vào menu
		
		chessBoardFrame.add(board, BorderLayout.WEST); // Thêm các ô cờ vào bàn cờ
		chessBoardFrame.add(menu, BorderLayout.EAST); // Thêm menu
		chessBoardFrame.setSize(900, 700); // Thiết lập kích thước bàn cờ
		chessBoardFrame.setVisible(true); // Hiển thị bàn cờ
	}
	
	/**
	 * Hàm hiện pop-up thông báo
	 * @param msg : thông báo
	 */
	public static void showMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg); // Hiện thông báo với tham số truyền vào
	}
	
}