/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * CommonConfig.java, 11/10/2017 SangLT
 */
package connect_five.config;

import javax.swing.ImageIcon;

/**
 * Class chứa các thông tin cấu hình chung
 * 
 * @author Luu Thanh Sang
 *
 */
public class CommonConfig {
	public static final String CHESS_POSITION_FILE_PATH = "./src/connect_five/chess_positions.txt"; // Đường dẫn của file thế cờ
	public static final int SQUARE_SIZE = 30; // Kích thước mỗi chiều của ô cờ
	public static final int CHESS_BOARD_ROW = 20; // Số hàng tối đa của bàn cờ
	public static final int CHESS_BOARD_COLUMN = 20; // Số cột tối đa của bàn cờ
	public static final int CHESS_POSITION_MATRIX_ROW = 5; // Số hàng của một ma trận thế cờ
	public static final int CHESS_POSITION_MATRIX_COLUMN = 5; // Số cột của một ma trận thế cờ
	public static final int WINDOW_WIDTH = 750; // Chiều rộng màn hình chương trình
	public static final int WINDOW_HEIGHT = 725; // Chiều cao màn hình chương trình
	
	public static final int NORMAL_FILE_STATUS = 0; // Mã trạng thái file thế cờ không lỗi
	public static final int EMPTY_FILE_STATUS = 1; // Mã trạng thái file thế cờ rỗng
	public static final int ERROR_FORMAT_FILE_STATUS = 2; // Mã trạng thái file thế cờ bị lỗi định dạng
	public static final int NO_FILE_STATUS = 3; // Mã trạng thái file thế cờ không tồn tại
	public static final int EXCEPTION_STATUS = 4; // Mã trạng thái xảy ra exception khi đọc file
	public static final String NORMAL_FILE_MSG = "Normal file."; // Thông báo trạng thái file thế cờ không lỗi
	public static final String EMPTY_FILE_MSG = "Empty file.";// Thông báo trạng thái file thế cờ rỗng
	public static final String ERROR_FORMAT_FILE_MSG = "Error in format file."; // Thông báo trạng thái file thế cờ bị lỗi định dạng
	public static final String NO_FILE_MSG = "No file."; // Thông báo trạng thái file thế cờ không tồn tại
	public static final String EXCEPTION_MSG = "Exception in reading file.";// Thông báo trạng thái xảy ra exception khi đọc file
	
	public static final ImageIcon emptyIcon = new ImageIcon("./src/connect_five/asset/o1.jpg"); // Icon quân cờ rỗng
	public static final ImageIcon oIcon = new ImageIcon("./src/connect_five/asset/o2.jpg"); // Icon quân cờ O
	public static final ImageIcon xIcon = new ImageIcon("./src/connect_five/asset/o3.jpg"); // Icon quân cờ X
	public static final String ERROR_CHAR = "E"; // Kí hiệu đại diện cho những dòng lỗi trong file thế cờ
	public static final String X_CHAR = "X"; // Kí hiệu quân cờ X
	public static final String O_CHAR = "O"; // Kí hiệu quân cờ O
	public static final String EMPTY_CHAR = "T"; // Kí hiệu quân cờ trống
	public static final String ANY_CHAR = "G"; // Kí hiệu quân cờ bất kì
	public static final String PICK_CHAR = "D"; // Kí hiệu vị trí cần đánh vào
	
	public static final String HUMAN_FIRST_MODE_LABEL = "Người đánh trước"; // Nhãn của menu trong chế độ người đánh trước
	public static final String COMPUTER_FIRST_MODE_LABEL = "Máy đánh trước"; // Nhãn của menu trong chế độ máy đánh trước
	public static final String HUMAN_BUTTON_NAME = "human"; // Tên button chọn chế độ người đánh trước
	public static final String COMPUTER_BUTTON_NAME = "computer"; // Tên button chọn chế độ máy đánh trước
	public static final String HUMAN_WIN_MSG = "You win!"; // Thông báo khi người thắng
	public static final String COMPUTER_WIN_MSG = "Computer win!"; // Thông báo khi máy thắng
	public static final String DRAW_MSG = "Draw!"; // Thông báo khi hòa nhau
	public static final String CRASH_MSG = "Crash! Please restart program!"; // Thông báo khi chương trình gặp lỗi bất thường
	
	public static final int WIN_CHAIN_LENG = 5; // Số quân cờ liền mạch để thắng
	public static final int CHECK_WIN_LENG = 4; // Biên độ dài để kiểm tra thắng khi một quân cờ được đánh
}