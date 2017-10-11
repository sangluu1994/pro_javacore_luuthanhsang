/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * ChessBoard.java, 11/10/2017 SangLT
 */
package connect_five.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import connect_five.config.CommonConfig;
import connect_five.entity.ChessPosition;
import connect_five.entity.Square;
import connect_five.listener.ChessBoardListener;
import connect_five.listener.MenuListener;
import connect_five.utils.ChessPositionUtil;

/**
 * Class tạo giao diện bàn cờ
 * 
 * @author Luu Thanh Sang
 *
 */
public class ChessBoard extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Khởi tạo mảng các ô cờ:
	public static Square squares[][] = new Square[CommonConfig.CHESS_BOARD_ROW][CommonConfig.CHESS_BOARD_COLUMN];
	// Khai báo margin cho giao diện ô cờ
	private Insets squareMargin = new Insets(0, 0, 0, 0);
	
	// Khởi tạo các thành phần trên bàn cờ:
	private JPanel board, menu; // Division bàn cờ, menu
	public static JLabel lblMenu = new JLabel(); // Nhãn cho menu
	public static JButton btnHuman = new JButton(); // Button người đánh trước
	public static JButton btnComputer = new JButton(); // Button máy đánh trước
	
	// Khai báo mảng đối tượng các thế cờ:
	public static ArrayList<ChessPosition> chessPositionList = null;
	// Khai báo listener cho các sự kiện trên bàn cờ:
	ChessBoardListener chessBoardListener = new ChessBoardListener();
	// Khai báo listener cho các sự kiện trên menu:
	MenuListener menuListener = new MenuListener();
	// Khai báo biến xác định lượt đánh:
	public static boolean humanTurn = true;
	// Khai báo biến đếm tổng số nước đã đánh:
	public static int turnCount = 0;
	
	/**
	 * Constructor: Hàm khởi tạo
	 */
	public ChessBoard() {
		
		// Thiết lập kích thước bàn cờ:
		getContentPane().setPreferredSize(new Dimension(CommonConfig.WINDOW_WIDTH, CommonConfig.WINDOW_HEIGHT)); // Thiết lập kích thước frame
		setLayout(new FlowLayout()); // Thiết lập layout cho khung màn hình
		pack(); // Hiển thị không bị zoom nhỏ
		setResizable(false); // Thiết lập không thay đổi kích thước
		
		// Thiết lập panel menu:
		addMenu();
		
		// Thiết lập bàn cờ 20x20:
		addChessBoardPanel();
		
		// Load thế cờ:
		loadChessPositions();
	}
	
	/**
	 * Hàm thêm menu vào bàn cờ
	 */
	public void addMenu() {
		// Thiết lập panel menu:
		menu = new JPanel(); // Tạo panel menu
		lblMenu.setText(CommonConfig.HUMAN_FIRST_MODE_LABEL); // Thiết lập nhãn menu cho chế độ người đánh trước
		btnHuman.setText("[" + CommonConfig.O_CHAR + "]"); // Thiết lập text cho button human
		btnHuman.setActionCommand(CommonConfig.HUMAN_BUTTON_NAME); // Thiết lập giá trị cho button chọn chế độ người đánh trước
		btnHuman.addActionListener(menuListener); // Thêm đối tượng xử lí sự kiện cho button human
		btnComputer.setText(CommonConfig.X_CHAR); // Thiết lập text cho button computer
		btnComputer.setActionCommand(CommonConfig.COMPUTER_BUTTON_NAME); // Thiết lập giá trị cho button chọn chế độ máy đánh trước
		btnComputer.addActionListener(menuListener); // Thêm đối tượng xử lí sự kiện cho button computer 
		menu.add(lblMenu); // Thêm label cho menu
		menu.add(btnHuman); // Thêm button human vào menu
		menu.add(btnComputer); // Thêm button computer vào menu
		add(menu); // Thêm menu vào bàn cờ
	}
	
	/**
	 * Hàm thêm 400 ô cờ vào bàn cờ
	 */
	public void addChessBoardPanel() {
		// Thiết lập bàn cờ 20x20:
		board = new JPanel(); // Khởi tạo panel bàn cờ
		board.setLayout(new GridLayout(CommonConfig.CHESS_BOARD_ROW, CommonConfig.CHESS_BOARD_COLUMN)); // Thiết lập layout cho bàn cờ
		// Thêm các ô cờ vào bàn cờ:
		for (int i = 0; i < CommonConfig.CHESS_BOARD_ROW; i++) { // Duyệt qua từng hàng trong bàn cờ
			for (int j = 0; j < CommonConfig.CHESS_BOARD_COLUMN; j++) { // Duyệt qua từng cột trong hàng đang duyệt
				squares[i][j] = new Square(i, j); // Khởi tạo ô cờ
				squares[i][j].setValue("");; // Khởi tạo giá trị quân cờ rỗng cho từng ô cờ
				squares[i][j].setIcon(CommonConfig.emptyIcon); // Thiết lập icon cho quân cờ
				squares[i][j].setMargin(squareMargin); // Thiết lâp margin cho ô cờ
				squares[i][j].addActionListener(chessBoardListener); // Thêm sự kiện khi ô cờ được đánh
				board.add(squares[i][j]); // Thêm ô cờ vào bàn cờ
			}
		}
		add(board); // Thêm khung 400 ô cờ vào bàn cờ
	}
	
	/**
	 * Hàm load các thế cờ của máy
	 */
	public void loadChessPositions() {
		// Lấy mảng các thế cờ:
		chessPositionList = ChessPositionUtil.readChessPostionsFile(CommonConfig.CHESS_POSITION_FILE_PATH);
	}
	
	/**
	 * Hàm hiện pop-up thông báo
	 * 
	 * @param msg : thông báo
	 */
	public static void showMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg); // Hiện thông báo với tham số truyền vào
	}
	
}