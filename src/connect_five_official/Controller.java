/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * Controller.java, 04/10/2017 sanglt
 */
package connect_five_official;

import java.util.Random;
import javax.swing.JButton;

/**
 * Class xử lí đánh cờ
 * 
 * @author sanglt
 *
 */
public class Controller {
	// Khai báo, khởi tạo ma trận bàn cờ
	JButton[][] squares = ChessBoard.squares;
	// Khai báo hoành độ, tung độ tối đa
	int row = ChessBoard.row; // Hoành độ tương ứng với bàn cờ
	int column = ChessBoard.column; // Tung độ tương ứng với bàn cờ
	
	/**
	 * Hàm xử lí máy đánh
	 * 
	 * @param chessPositions : mảng các thế cờ
	 */
	public void getComputerTurn(String[][][] chessPositions) {
		int chessPositionsLeng = chessPositions.length; // Lấy kích thước mảng thế cờ phục vụ vòng lặp từng thế cờ
		boolean match = false; // Biến đánh dấu so khớp thế cờ
		for (int i = 0; i < chessPositionsLeng; i++) { // Duyệt tất cả các thế cờ
			Position position = findChessPosition(chessPositions[i]); // So khớp từng thế cờ với các ma trận trên bàn cờ
			if (position != null) { // Nếu ô cờ trả về khác null
				squares[position.getX()][position.getY()].setText("X"); // Gán giá trị quân cờ vào ô cờ nhận được: X
				if (checkWin(position)) { // Kiểm tra thắng thua
					ChessBoard.showMessage("You lose"); // Nếu máy thắng, show pop-up
					resetChessBoard(); // Reset lại bàn cờ
				}
				match = true; // Thay đổi biến đánh dấu so khớp thế cờ
				break; // Thoát vòng lặp
			} 
			
		}
		if (!match) { // Nếu không so khớp thế cờ nào
			if (checkWin(randomPosition())) { // Gọi hàm đánh ngẫu nhiên và kiểm tra thắng thua
				ChessBoard.showMessage("You lose"); // Nếu máy thắng, show pop-up
				resetChessBoard(); // Reset lại bàn cờ
			}
		}
	}
	
	/**
	 * Hàm so khớp ma trận thế cờ với tất cả các ma trận con trên bàn cờ
	 * 
	 * @param chessPosition : ma trận thế cờ
	 * @return position : vị trí tọa độ ô cờ cần đánh
	 */
	public Position findChessPosition(String[][] chessPosition) {
		Position position = null; // Khởi tạo ô cờ
		// Giá trị biên để duyệt các ma trận con trong bàn cờ
		int maxRow = row - 5; // Số hàng tối đa
		int maxColumn = column - 5; // Số cột tối đa
		// Duyệt các ma trận con trong bàn cờ và so sánh với thế cờ
		for (int i = 0; i < maxRow; i++) { // Duyệt từng hàng
			for (int j = 0; j < maxColumn; j++) { // Duyệt từng cột trong hàng đang duyệt
				position = matchChessPosition(i, j, chessPosition); // So khớp với thế cờ
				if (position != null) { // Nếu thế cờ so khớp với ma trận con
					return position; // Trả về vị trí ô cờ cần đánh
				}
			}
		}
		return null; // Trả về null
	}
	
	/**
	 * Hàm so khớp 1 : 1 giữa ma trận thế cờ và ma trận con đơn lẻ
	 * @param row : hoành độ của ô cờ mốc
	 * @param column : tung độ của ô cờ mốc
	 * @param chessPosition : ma trận thế cờ
	 * @return position : vị trí tọa độ ô cờ cần đánh
	 */
	public Position matchChessPosition(int row, int column, String[][] chessPosition) {
		Position position = null; // Khởi tạo ô cờ cần đánh
		// Duyệt ma trận con 5x5 của bàn cờ
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				String squareVal = squares[row + i][column + j].getText(); // Lấy giá trị quân cờ
				// Kiểm tra giá trị các quân cờ trong ma trận con, nếu không khớp với thế cờ, return null
				if ("X".equals(chessPosition[i][j]) && !"X".equals(squareVal)) { // Xét trường hợp ô cờ chứa quân X
					return null; // Trả về null
				}
				if ("O".equals(chessPosition[i][j]) && !"O".equals(squareVal)) { // Xét trường hợp ô cờ chứa quân O
					return null; // Trả về null
				}
				if ("T".equals(chessPosition[i][j]) && !"".equals(squareVal)) { // Xét trường hợp ô cờ trống
					return null; // Trả về null
				}
				// Thiết lập tọa độ ô cờ cần đánh nếu so khớp với giá trị "D"
				if ("D".equals(chessPosition[i][j]) && "".equals(squareVal)) { // So khớp với giá trị "D"
					position = new Position(i + row, j + column); // Thiết lập tọa độ ô cờ
				}
			}
		}
		return position; // Trả về tọa độ ô cờ cần đánh
	}
	
	/**
	 * Hàm kiểm tra thắng thua
	 * 
	 * @param position : vị trí ô cờ vừa đánh
	 * @return true | false : thắng | chưa thắng
	 */
	public boolean checkWin(Position position) {
		// Kiểm tra thắng thua theo 4 hướng: ngang, dọc, chéo phải, chéo trái
		if (checHorizontalkWin(position)) { // Kiểm tra theo hàng ngang
			return true; // Trả về true
		}
		if (checkVerticalWin(position)) { // Kiểm tra theo hàng dọc
			return true; // Trả về true
		}
		if (checkRightDiagonalWin(position)) { // Kiểm tra theo đường chéo phải
			return true; // Trả về true
		}
		if (checkLeftDiagonalWin(position)) { // Kiểm tra theo đường chéo trái
			return true; // Trả về true
		}
		
		return false; // Trả về false nếu không thỏa mãn cả 4 trường hợp kiểm tra
	}
	
	/**
	 * Hàm kiểm tra thắng thua theo chiều dọc
	 * 
	 * @param position : vị trí ô cờ vừa đánh
	 * @return true | false : thắng | chưa thắng
	 */
	public boolean checkVerticalWin(Position position) {
		int x = position.getX(); // Lấy hoành độ ô cờ vừa đánh
		int y = position.getY(); // Lấy tung độ ô cờ vừa đánh
		String value = squares[x][y].getText(); // Lấy giá trị ô cờ vừa đánh
		int count = 0; // Biến đếm số lượt trùng giá trị quân cờ

		for (int i = -4; i <= 4; i++) { // Lấy ô cờ vừa đánh làm trung tâm, duyệt 8 ô cờ trên dưới theo chiều dọc
			if (x + i >= 0 && x + i < row) { // Các ô cờ được duyệt 2 bên phải có hoành độ >= 0 và < số hàng của bàn cờ
			
				if (squares[x + i][y].getText().equals(value)) { // Nếu những ô cờ được xét trùng giá trị với ô cờ vừa đánh

					count++; // Tăng biến đếm
					if (count == 5) { // Nếu có 5 quân cùng loại liền mạch nhau
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
	 * @param position : vị trí ô cờ vừa được đánh 
	 * @return true | false : thắng | chưa thắng
	 */
	public boolean checHorizontalkWin(Position position) {
		int x = position.getX(); // Lấy hoành độ ô cờ vừa đánh
		int y = position.getY(); // Lấy tung độ ô cờ vừa đánh
		String value = squares[x][y].getText(); // Lấy giá trị ô cờ vừa đánh: O hoặc X
		int count = 0; // Biến đếm số lượt trùng giá trị quân cờ

		for (int j = -4; j <= 4; j++) { // Lấy ô cờ vừa đánh làm trung tâm, duyệt 8 ô cờ 2 bên theo chiều ngang
			if (y + j >= 0 && y + j < column) { // Các ô cờ được duyệt 2 bên phải có tung độ >= 0 và < số cột của bàn cờ 
				if (squares[x][y + j].getText().equals(value)) { // Nếu những ô cờ được xét 2 bên trùng giá trị với ô cờ vừa đánh

					count++; // Tăng biến đếm
					if (count == 5) { // Nếu có 5 quân cùng loại liền mạch nhau
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
	 * @param position : vị trí ô cờ vừa đánh
	 * @return true | false : thắng | chưa thắng
	 */
	public boolean checkRightDiagonalWin(Position position) {
		int x = position.getX(); // Lấy hoành độ ô cờ vừa đánh
		int y = position.getY(); // Lấy tung độ ô cờ vừa đánh
		String value = squares[x][y].getText(); // Lấy giá trị loại quân của ô cờ: X hoặc O
		int count = 0; // Biến đếm
		for (int j = -4; j <= 4; j++) { // Duyệt 8 hàng chéo phải xung quanh ô cờ vừa đánh
		
			if (y + j >= 0 && y + j < column && x + j >= 0 && x + j < row) { // Hàng và cột được duyệt phải >= 0 và < row, column
			
				if (squares[x + j][y + j].getText().equals(value)) { // Nếu ô cờ vừa đánh trùng loại quân với ô được xét

					count++; // Tăng biến đếm
					if (count == 5) { // Nếu có 5 quân liên tiếp thắng hàng
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
	 * @param position : vị trí ô cờ vừa đánh
	 * @return true | false : thắng | chưa thắng
	 */
	public boolean checkLeftDiagonalWin(Position position) {

		int x = position.getX(); // Lấy hoành độ ô cờ vừa đánh
		int y = position.getY(); // Lấy tung độ ô cờ vừa đánh
		String value = squares[x][y].getText(); // Lấy giá trị loại quân của ô cờ: X hoặc O
		int count = 0; // Biến đếm

		for (int j = -4; j <= 4; j++) { // Duyệt 8 hàng chéo trái xung quanh ô cờ vừa đánh
		
			if (y - j >= 0 && y - j < column && x + j >= 0 && x + j < row) { // Hàng và cột được duyệt phải >= 0 và < row, column
			
				if (squares[x + j][y - j].getText().equals(value)) { // Nếu ô cờ vừa đánh trùng loại quân với ô được xét
				
					count++; // Tăng biến đếm
					if (count == 5) { // Nếu có 5 quân liên tiếp thắng hàng
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
	 * @return position : tọa độ ngẫu nhiên
	 */
	public Position randomPosition() {
		Position position = null; // Khai báo, khởi tạo tọa độ ô cờ
		while (true) { // Vòng sinh tọa độ ngẫu nhiên
			int x = new Random().nextInt(20); // Hoành độ ngẫu nhiên
			int y = new Random().nextInt(20); // Tung độ ngẫu nhiên
			if (squares[x][y].getText().equals("")) { // Nếu ô đánh ngẫu nhiên còn trống
				position = new Position(x, y); // Gán tọa độ ngẫu nhiên cho giá trị trả về
				squares[x][y].setText("X"); // Thiết lập giá trị quân cờ: X
				break; // Thoát vòng lặp
			}
		}
		return position; // Trả về tọa độ ngẫu nhiên vừa sinh

	}
	
	/**
	 * Hàm tạo bàn cờ mới
	 */
	public void resetChessBoard() {
		for (int i = 0; i < row; i++) { // Duyệt lần lượt các hàng trong bàn cờ
			for (int j = 0; j < column; j++) { // Duyệt lần lượt các cột trong hàng đang duyệt
				squares[i][j].setText(""); // Thiết lập giá trị rỗng cho các ô cờ
			}
		}
	}
	
}
