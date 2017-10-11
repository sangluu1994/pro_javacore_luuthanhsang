/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * CaroLogic.java, 11/10/2017 SangLT
 */
package connect_five.logic;

import java.util.ArrayList;
import java.util.Random;
import connect_five.config.CommonConfig;
import connect_five.entity.ChessPosition;
import connect_five.entity.Square;
import connect_five.view.ChessBoard;

/**
 * Class xử lí đánh cờ
 * 
 * @author Luu Thanh Sang
 *
 */
public class CaroLogic {
	
	/**
	 * Hàm xử lí máy đánh
	 * 
	 * @param chessPositions : mảng các đối tượng thế cờ
	 */
	public static void getComputerTurn(ArrayList<ChessPosition> chessPositions) {
		ChessBoard.humanTurn = false; // Chuyển lượt đánh sang cho máy
		
		// Tìm nước đi cho máy:
		Square square = findPosition(chessPositions);
		
		// Máy đánh vào ô cờ tìm được:
		fillSquare(CommonConfig.X_CHAR, square);
		
		// Kiểm tra kết thúc: thắng hoặc hòa
		if (checkEnd(square)) { // Nếu rơi vào trường hợp thắng hoặc hòa
			getNewGame(); // Tạo ván chơi mới
		} else { // Nếu chưa thắng hoặc hòa
			ChessBoard.humanTurn = true; // Chuyển lượt đánh về cho người
		}
	}
	
	/**
	 * Hàm tìm vị trí ô cờ cần đánh cho máy
	 * 
	 * @param chessPositions : arraylist các thế cờ
	 * @return square : ô cờ cần đánh
	 */
	public static Square findPosition(ArrayList<ChessPosition> chessPositions) {
//		int chessPositionCount = 0;
		Square square = null; // Khai báo nước đi cho máy
		for (ChessPosition chessPosition : chessPositions) { // Duyệt tất cả các thế cờ
//			chessPositionCount++;
			square = findChessPosition(chessPosition); // So khớp từng thế cờ với các ma trận trên bàn cờ
			if (square != null) { // Nếu ô cờ trả về khác null
				break; // Thoát vòng lặp
			} 
			
		}
		if (square == null) { // Nếu không so khớp thế cờ nào
			square = randomPosition(); //Gọi hàm đánh ngẫu nhiên
		}
//		System.out.println(chessPositionCount);
		return square; // Trả về ô cờ cần đánh
	}
	
	/**
	 * Hàm so khớp ma trận thế cờ với tất cả các ma trận con trên bàn cờ
	 * 
	 * @param chessPosition : đối tượng thế cờ
	 * @return square : ô cờ cần đánh
	 */
	public static Square findChessPosition(ChessPosition chessPosition) {
		Square square = null; // Khởi tạo ô cờ
		// Giá trị biên để duyệt các ma trận con trong bàn cờ
		int maxRow = CommonConfig.CHESS_BOARD_ROW - CommonConfig.CHESS_POSITION_MATRIX_ROW; // Số hàng tối đa
		int maxColumn = CommonConfig.CHESS_BOARD_COLUMN - CommonConfig.CHESS_POSITION_MATRIX_COLUMN; // Số cột tối đa
		// Duyệt các ma trận con trong bàn cờ và so sánh với thế cờ
		for (int i = 0; i <= maxRow; i++) { // Duyệt từng hàng
			for (int j = 0; j <= maxColumn; j++) { // Duyệt từng cột trong hàng đang duyệt
				square = matchChessPosition(i, j, chessPosition); // So khớp với thế cờ
				if (square != null) { // Nếu thế cờ so khớp với ma trận con
					// Trả về vị trí ô cờ cần đánh
					return ChessBoard.squares[square.getXPosition() + i][square.getYPosition() + j];
				}
			}
		}
		return null; // Trả về null nếu không so khớp được bất kì thế cờ nào
	}
	
//	/**
//	 * Hàm lấy ma trận con 5x5 trên bàn cờ
//	 * 
//	 * @param x : hoành độ của ô cờ mốc của ma trận con
//	 * @param y : tung độ của ô cờ mốc của ma trận con
//	 * @return chessBoardUnitMatrix : đối tượng ma trận con 5x5
//	 */
//	public static ChessPosition getChessBoardUnitMatrix (int x, int y) {
//		// Khai báo, khởi tạo thế cờ trả về:
//		ChessPosition chessBoardUnitMatrix = null; 
//		// Khai báo mảng tạm chứa thế cờ:
//		String[][] tempArr = new String[CommonConfig.CHESS_POSITION_MATRIX_ROW][CommonConfig.CHESS_POSITION_MATRIX_COLUMN];
//		// Lấy biên của ma trận con 5x5
//		int xMax = x + CommonConfig.CHESS_POSITION_MATRIX_ROW; // Biên hoành độ
//		int yMax = y + CommonConfig.CHESS_POSITION_MATRIX_COLUMN; // Biên tung độ
//		for (int i = x; i < xMax; i++) { // Duyệt theo hàng
//			for (int j = y; j < yMax; j++) { // Duyệt theo từng cột trong hàng
//				tempArr[i - x][j - y] = ChessBoard.squares[i][j].getValue(); // Đổ giá trị của quân cờ vào mảng tạm
//			}
//		}
//		chessBoardUnitMatrix = new ChessPosition(tempArr); // Tạo đối tượng thế cờ với tham số là ma trận 5x5 vừa nhận được
////		System.out.print(x + "-" + y + "|");
//		return chessBoardUnitMatrix; // Trả về ma trận 5x5
//	}
	
	/**
	 * Hàm so khớp 1 : 1 giữa ma trận thế cờ và ma trận con đơn lẻ
	 * 
	 * @param xPosition : hoành độ trên bàn cờ của điểm mốc
	 * @param yPosition : tung độ trên bàn cờ của điểm mốc
	 * @param chessPosition : đối tượng thế cờ
	 * @return square : ô cờ cần đánh
	 */
	public static Square matchChessPosition(int xPosition, int yPosition, ChessPosition chessPosition) {
		Square square = null; // Khởi tạo ô cờ cần đánh
		String[][] chessPositionMatrix = chessPosition.getChessPositionMatrix(); // Lấy ma trận thế cờ cần so khớp
//		String[][] chessBoardUnitMatrix = chessBoardUnit.getChessPositionMatrix(); // Lấy ma trận con 5x5 trên bàn cờ 
		// Duyệt ma trận con 5x5 của bàn cờ
		for (int i = 0; i < CommonConfig.CHESS_POSITION_MATRIX_ROW; i++) { // Duyệt từng hàng của ma trận
			for (int j = 0; j < CommonConfig.CHESS_POSITION_MATRIX_COLUMN; j++) { // Duyệt từng cột của hàng đang duyệt
				// Kiểm tra giá trị các quân cờ trong ma trận con, nếu không khớp với thế cờ, return null:
				if (CommonConfig.X_CHAR.equals(chessPositionMatrix[i][j]) && !CommonConfig.X_CHAR.equals(ChessBoard.squares[i + xPosition][j + yPosition].getValue())) { // Xét trường hợp ô cờ chứa quân X
					return null; // Trả về null
				}
				if (CommonConfig.O_CHAR.equals(chessPositionMatrix[i][j]) && !CommonConfig.O_CHAR.equals(ChessBoard.squares[i + xPosition][j + yPosition].getValue())) { // Xét trường hợp ô cờ chứa quân O
					return null; // Trả về null
				}
				if (CommonConfig.EMPTY_CHAR.equals(chessPositionMatrix[i][j]) && !"".equals(ChessBoard.squares[i + xPosition][j + yPosition].getValue())) { // Xét trường hợp ô cờ trống
					return null; // Trả về null
				}
				if (CommonConfig.ERROR_CHAR.equals(chessPositionMatrix[i][j])) { // Nếu xuất hiện kí hiệu xác định thế cờ lỗi
					return null; // Trả về null
				}
				// Thiết lập tọa độ ô cờ cần đánh nếu so khớp với giá trị PICK_CHAR: "D"
				if (CommonConfig.PICK_CHAR.equals(chessPositionMatrix[i][j]) && "".equals(ChessBoard.squares[i + xPosition][j + yPosition].getValue())) { // So khớp với giá trị PICK_CHAR: "D"
					square = new Square(i, j); // Thiết lập tọa độ ô cờ
				}
			}
		}
		return square; // Trả về ô cờ cần đánh
	}
	
	
	
	/**
	 * Hàm kiểm tra thắng thua
	 * 
	 * @param square : đối tượng ô cờ vừa đánh
	 * @return true | false : thắng | chưa thắng
	 */
	public static boolean checkWin(Square square) {
		int xPosition = square.getXPosition(); // Lấy hoành độ ô cờ
		int yPosition = square.getYPosition(); // Lấy tung độ ô cờ
		String value = square.getValue(); // Lấy giá trị quân cờ
//		if ("O".equals(value)) {
//			System.out.println("value O: " + value);
//		} else {
//			System.out.println("value X: " + value);
//		}
		// Kiểm tra thắng thua theo 4 hướng: ngang, dọc, chéo phải, chéo trái
		if (checHorizontalkWin(value, xPosition, yPosition)) { // Kiểm tra theo hàng ngang
			return true; // Trả về true
		}
		if (checkVerticalWin(value, xPosition, yPosition)) { // Kiểm tra theo hàng dọc
			return true; // Trả về true
		}
		if (checkRightDiagonalWin(value, xPosition, yPosition)) { // Kiểm tra theo đường chéo phải
			return true; // Trả về true
		}
		if (checkLeftDiagonalWin(value, xPosition, yPosition)) { // Kiểm tra theo đường chéo trái
			return true; // Trả về true
		}
		
		return false; // Trả về false nếu không thỏa mãn cả 4 trường hợp kiểm tra
	}
	
	/**
	 * Hàm kiểm tra thắng thua theo chiều dọc
	 * 
	 * @param value : giá trị quân cờ (rỗng | X | O)
	 * @param xPosition : hoành độ ô cờ
	 * @param yPosition : tung độ ô cờ
	 * @return true | false : thắng | chưa thắng
	 */
	public static boolean checkVerticalWin(String value, int xPosition, int yPosition) {
		int count = 0; // Biến đếm số lượt trùng giá trị quân cờ

		for (int i = -CommonConfig.CHECK_WIN_LENG; i <= CommonConfig.CHECK_WIN_LENG; i++) { // Lấy ô cờ vừa đánh làm trung tâm, duyệt 8 ô cờ trên dưới theo chiều dọc
			if (xPosition + i >= 0 && xPosition + i < CommonConfig.CHESS_BOARD_ROW) { // Các ô cờ được duyệt 2 bên phải có hoành độ >= 0 và < số hàng của bàn cờ
			
				if (ChessBoard.squares[xPosition + i][yPosition].getValue().equals(value)) { // Nếu những ô cờ được xét trùng giá trị với ô cờ vừa đánh

					count++; // Tăng biến đếm
					if (count == CommonConfig.WIN_CHAIN_LENG) { // Nếu quân cùng loại liền mạch nhau đủ điều kiện thắng
						return true; // Trả về true
					}
				} else { // Nếu xuất hiện 1 quân khác loại
					count = 0; // Reset biến đếm = 0;
				}
			}
		}
		return false; // Trả về false
	}

	/**
	 * Hàm kiểm tra thắng theo chiều ngang
	 * 
	 * @param value : giá trị quân cờ (rỗng | X | O)
	 * @param xPosition : hoành độ ô cờ 
	 * @param yPosition : tung độ ô cờ
	 * @return true | false : thắng | chưa thắng
	 */
	public static boolean checHorizontalkWin(String value, int xPosition, int yPosition) {
		int count = 0; // Biến đếm số lượt trùng giá trị quân cờ

		for (int j = -CommonConfig.CHECK_WIN_LENG; j <= CommonConfig.CHECK_WIN_LENG; j++) { // Lấy ô cờ vừa đánh làm trung tâm, duyệt 8 ô cờ 2 bên theo chiều ngang
			if (yPosition + j >= 0 && yPosition + j < CommonConfig.CHESS_BOARD_COLUMN) { // Các ô cờ được duyệt 2 bên phải có tung độ >= 0 và < số cột của bàn cờ 
				if (ChessBoard.squares[xPosition][yPosition + j].getValue().equals(value)) { // Nếu những ô cờ được xét 2 bên trùng giá trị với ô cờ vừa đánh

					count++; // Tăng biến đếm
					if (count == CommonConfig.WIN_CHAIN_LENG) { // Nếu có quân cùng loại liền mạch nhau thỏa mãn
						return true; // Trả về true
					}
				} else { // Nếu xuất hiện 1 quân khác loại
					count = 0; // Reset count =0;
				}
			}
		}
		return false; // Trả về false
	}

	/**
	 * Hàm kiểm tra thắng thua theo chiều chéo phải
	 * 
	 * @param value : giá trị quân cờ (rỗng | X | O)
	 * @param xPosition : hoành độ ô cờ 
	 * @param yPosition : tung độ ô cờ
	 * @return true | false : thắng | chưa thắng
	 */
	public static boolean checkRightDiagonalWin(String value, int xPosition, int yPosition) {
		int count = 0; // Biến đếm
		for (int j = -CommonConfig.CHECK_WIN_LENG; j <= CommonConfig.CHECK_WIN_LENG; j++) { // Duyệt 8 hàng chéo phải xung quanh ô cờ vừa đánh
		
			if (yPosition + j >= 0 && yPosition + j < CommonConfig.CHESS_BOARD_COLUMN && xPosition + j >= 0 && xPosition + j < CommonConfig.CHESS_BOARD_ROW) { // Hàng và cột được duyệt phải >= 0 và < row, column
			
				if (ChessBoard.squares[xPosition + j][yPosition + j].getValue().equals(value)) { // Nếu ô cờ vừa đánh trùng loại quân với ô được xét

					count++; // Tăng biến đếm
					if (count == CommonConfig.WIN_CHAIN_LENG) { // Nếu có 5 quân liên tiếp thắng hàng
						return true; // Trả về true
					}
				} else { // Nếu xuất hiện quân khác loại
					count = 0; // Reset biến đếm về 0
				}
			}
		}
		return false; // Trả về false

	}

	/**
	 * Hàm kiểm tra thắng thua theo chiều chéo trái
	 * 
	 * @param value : giá trị quân cờ (rỗng | X | O)
	 * @param xPosition : hoành độ ô cờ 
	 * @param yPosition : tung độ ô cờ
	 * @return true | false : thắng | chưa thắng
	 */
	public static boolean checkLeftDiagonalWin(String value, int xPosition, int yPosition) {
		int count = 0; // Biến đếm

		for (int j = -CommonConfig.CHECK_WIN_LENG; j <= CommonConfig.CHECK_WIN_LENG; j++) { // Duyệt 8 hàng chéo trái xung quanh ô cờ vừa đánh
		
			if (yPosition - j >= 0 && yPosition - j < CommonConfig.CHESS_BOARD_COLUMN && xPosition + j >= 0 && xPosition + j < CommonConfig.CHESS_BOARD_ROW) { // Hàng và cột được duyệt phải >= 0 và < row, column
			
				if (ChessBoard.squares[xPosition + j][yPosition - j].getValue().equals(value)) { // Nếu ô cờ vừa đánh trùng loại quân với ô được xét
				
					count++; // Tăng biến đếm
					if (count == CommonConfig.WIN_CHAIN_LENG) { // Nếu có 5 quân liên tiếp thắng hàng
						return true; // Trả về true
					}
				} else { // Nếu xuất hiện quân khác loại
					count = 0; // Reset biến đếm về 0
				}
			}
		}

		return false; // Trả về false
	}

	/**
	 * Hàm đánh ngẫu nhiên
	 * 
	 * @return square : ô cờ ngẫu nhiên
	 */
	public static Square randomPosition() {
		Square square = null; // Khai báo, khởi tạo tọa độ ô cờ
		while (true) { // Vòng sinh tọa độ ngẫu nhiên
			int x = new Random().nextInt(CommonConfig.CHESS_BOARD_ROW); // Hoành độ ngẫu nhiên
			int y = new Random().nextInt(CommonConfig.CHESS_BOARD_COLUMN); // Tung độ ngẫu nhiên
			if ("".equals(ChessBoard.squares[x][y].getValue())) { // Nếu ô đánh ngẫu nhiên còn trống
				square = new Square(x, y); // Khởi tạo ô cờ có tọa độ ngẫu nhiên
				break; // Thoát vòng lặp
			}
		}
		return ChessBoard.squares[square.getXPosition()][square.getYPosition()]; // Trả về ô cờ ngẫu nhiên

	}
	
	/**
	 * Hàm đánh cờ: gán giá trị và icon cho quân cờ, tăng tổng số nước đã đánh trên bàn cờ thêm 1
	 * 
	 * @param value : giá trị quân cờ (rỗng | X | O)
	 * @param square : đối tượng ô cờ
	 */
	public static void fillSquare(String value, Square square) {
		square.setValue(value); // Gán giá trị cho ô cờ
		// Gán icon cho ô cờ
		if (CommonConfig.X_CHAR.equals(value)) { // Nếu là quân cờ X
			square.setIcon(CommonConfig.xIcon); // Gán icon của quân X
		} else { // Nếu là quân cờ O
			square.setIcon(CommonConfig.oIcon); // Gán icon của quân O
		}
		ChessBoard.turnCount++; // Tăng tổng số nước đã đánh trên bàn cờ thêm 1
	}
	
	/**
	 * Hàm tạo bàn cờ mới
	 */
	public static void resetChessBoard() {
		for (int i = 0; i < CommonConfig.CHESS_BOARD_ROW; i++) { // Duyệt lần lượt các hàng trong bàn cờ
			for (int j = 0; j < CommonConfig.CHESS_BOARD_COLUMN; j++) { // Duyệt lần lượt các cột trong hàng đang duyệt
				ChessBoard.squares[i][j].setValue(""); // Thiết lập giá trị rỗng cho các ô cờ
				ChessBoard.squares[i][j].setIcon(CommonConfig.emptyIcon); // Thiết lập icon quân cờ rỗng
			}
		}
	}
	
	/**
	 * Hàm thiết lập chế độ máy chơi trước
	 */
	public static void getComputerFirstMode() {
		// Thay đổi lại giao diện tương ứng với chế độ máy đánh trước trên menu:
		ChessBoard.lblMenu.setText(CommonConfig.COMPUTER_FIRST_MODE_LABEL); // Đổi nhãn cho menu
		ChessBoard.btnHuman.setText(CommonConfig.O_CHAR); // Thiết lập kí hiệu cho button btnComputer khi được chọn
		ChessBoard.btnComputer.setText("[" + CommonConfig.X_CHAR + "]"); // Thay đổi kí hiệu không được chọn cho button btnHuman
		
		// Reset lại bàn cờ
		resetChessBoard(); // Tạo bàn cờ mới
		ChessBoard.turnCount = 0; // Reset biến đếm số nước đã đánh trên bàn cờ
		
		// Máy đánh nước đầu tiên:
		ChessBoard.squares[9][9].setValue(CommonConfig.X_CHAR); // Máy đánh vào ô cờ giữa bàn cờ
		ChessBoard.squares[9][9].setIcon(CommonConfig.xIcon); // Thiết lập icon quân cờ của máy: X
		ChessBoard.turnCount++; // Tăng tổng số nước đã đánh trên bàn cờ thêm 1
		
		// Chuyển lượt đánh về cho người:
		ChessBoard.humanTurn = true; // Chuyển lượt đánh cho người
	}
	
	/**
	 * Hàm thiết lập chế độ người đánh trước
	 */
	public static void getHumanFirstMode()	{
		// Thay đổi lại giao diện tương ứng với chế độ người đánh trước trên menu:
		ChessBoard.lblMenu.setText(CommonConfig.HUMAN_FIRST_MODE_LABEL); // Đổi nhãn cho menu
		ChessBoard.btnHuman.setText("[" + CommonConfig.O_CHAR + "]"); // Thiết lập kí hiệu cho button btnHuman
		ChessBoard.btnComputer.setText(CommonConfig.X_CHAR); // Thay đổi kí hiệu không được chọn cho button btnComputer 
		
		resetChessBoard(); // Tạo bàn cờ mới
		ChessBoard.turnCount = 0; // Reset biến đếm số nước đã đánh trên bàn cờ
		
		ChessBoard.humanTurn = true; // Chuyển lượt đánh cho người
	}
	
	/**
	 * Hàm tạo game mới
	 */
	public static void getNewGame() {
		// Kiểm tra chế độ chơi đang chọn:
		if (("[" + CommonConfig.O_CHAR + "]").equals(ChessBoard.btnHuman.getText())) { // Nếu chọn chế độ người đánh trước:
			getHumanFirstMode(); // Gọi chế độ người chơi trước
		} else { // Nếu chọn chế độ máy đánh trước:
			getComputerFirstMode(); // Gọi chế độ máy chơi trước
		}
	}
	
//	public static void printChessBoard() {
//		for (int i = 0; i < 20; i++) {
//			for (int j = 0; j < 20; j++) {
//				String value = ChessBoard.squares[i][j].getValue();
//				if ("".equals(value)) {
//					System.out.print("_|");
//				} else {
//					System.out.print(value + "|");
//				}
//				if (j == 19) {
//					System.out.print("\n");
//				}
//			}
//		}
//		System.out.println();
//	}
	
	/**
	 * Hàm kiểm tra kết thúc ván cờ: thắng, hòa
	 * 
	 * @param square : ô cờ vừa đánh
	 */
	public static boolean checkEnd(Square square) {
		String msg = ""; // Thông báo khi thắng
		if (CommonConfig.O_CHAR.equals(square.getValue())) { // Nếu là người đánh
			msg = CommonConfig.HUMAN_WIN_MSG; // Thông báo khi người thắng
		} else {
			msg = CommonConfig.COMPUTER_WIN_MSG; // Thông báo khi máy thắng
		}
		// Kiểm tra thắng thua, hòa:
		if (checkWin(square)) { // Nếu máy thắng
			ChessBoard.showMessage(msg); // Hiện thông báo máy thắng
			return true; // Trả về true
		} else if (ChessBoard.turnCount == CommonConfig.CHESS_BOARD_ROW * CommonConfig.CHESS_BOARD_COLUMN) { // Nếu hòa nhau:
			ChessBoard.showMessage(CommonConfig.DRAW_MSG); // Hiện thông báo hòa
			return true;
		} else { // Nếu không thắng hay hòa:
			return false; // Trả về false
		}
			
	}
	
}