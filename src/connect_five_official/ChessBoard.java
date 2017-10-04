/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * ChessBoard.java, 04/10/2017 sanglt
 */
package connect_five_official;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Class Bàn cờ
 * 
 * @author sanglt
 *
 */
public class ChessBoard extends JPanel implements ActionListener {
	// Khai báo thuộc tính số hàng và số cột của bàn cờ
	public static int row = 20; // số hàng
	public static int column = 20; // số cột
	
	// Khởi tạo mảng các ô cờ
	public static JButton squares[][] = new JButton[row][column];
	// Khởi tạo khung bàn cờ
	public JFrame chessBoardFrame = new JFrame();
	
	// Khai báo biến thế cờ
	public String[][][] chessPositions = ChessPosition.getChessPostion("./src/connect_five_official/chess_positions.txt");
	// Khai báo đối tượng xử lí đánh cờ
	Controller handle;
	
//	/**
//	 * Hàm khởi tạo
//	 * @param row : số hàng của bàn cờ
//	 * @param column : số cột của bàn cờ
//	 */
//	@SuppressWarnings("static-access")
//	public ChessBoard(int row, int column) {
//		this.row = row;
//		this.column = column;
//	}
	
	/**
	 * Hàm vẽ bàn cờ
	 */
	public void drawChessBoard() {
		chessBoardFrame.setLayout(new GridLayout(row, column)); // Thiết lập bố cục cho bàn cờ
		// Thêm các ô cờ vào bàn cờ
		for (int i = 0; i < row; i++) { // duyệt qua từng hàng trong bàn cờ
			for (int j = 0; j < column; j++) {
				String v = i + " " + j;
				squares[i][j] = new JButton(""); // Tạo, gán giá trị rỗng cho từng ô cờ
				squares[i][j].setActionCommand(v); // Thiết lập chuỗi command gửi đi khi ô cờ được đánh, giúp xác định tọa độ ô cờ
				squares[i][j].addActionListener(this); // Thêm sự kiện khi ô cờ được đánh
				chessBoardFrame.add(squares[i][j]); // Thêm ô cờ vào bàn cờ
			}
		}
		chessBoardFrame.setSize(900, 900); // Thiết lập kích thước bàn cờ
		chessBoardFrame.setVisible(true); // Hiển thị bàn cờ
	}
	
	/**
	 * Hàm xử lí sự kiện đánh vào ô cờ
	 * 
	 * @param e : đối tượng sự kiện của ô cờ được đánh
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int xPosition, yPosition; // Khởi tạo tung độ, hoành độ của ô cờ được đánh
		String positionVal = e.getActionCommand(); // Lấy tọa độ ô cờ được đánh
		String[] positionArr = positionVal.split(" "); // Lấy tung độ, hoành độ
		handle = new Controller(); // Khởi tạo đối tượng xử lí chơi cờ
		xPosition = Integer.parseInt(positionArr[0]); // Lấy hoành độ ô cờ được đánh
		yPosition = Integer.parseInt(positionArr[1]); // Lấy tung độ ô cờ được đánh
		// Kiểm tra ô cờ đánh có hợp lệ không
		if ("".equals(squares[xPosition][yPosition].getText())) { // Nếu ô cờ được đánh vẫn trống
			
			squares[xPosition][yPosition].setText("O"); // Gán giá trị ô cờ được đánh là O
			// Kiếm tra thắng thua
			if (handle.checkWin(new Position(xPosition, yPosition))) { // Nếu người thắng
				showMessage("You win!"); // Hiện thông báo thắng cuộc
				handle.resetChessBoard(); // Tạo ván chơi mới
			} else {
				handle.getComputerTurn(chessPositions); // Nếu không thắng thì tới lượt máy đánh
			}
		}
	}
	
	/**
	 * Hàm hiện pop-up thông báo
	 * @param msg : thông báo
	 */
	public static void showMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg); // Hiện thông báo với tham số truyền vào
	}
	
}
