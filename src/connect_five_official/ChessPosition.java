/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * ChessPosition.java, 04/10/2017 sanglt
 */
package connect_five_official;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class xử lí lấy file thế cờ
 * 
 * @author sanglt
 *
 */
public class ChessPosition {
	/**
	 * Hàm lấy file thế cờ và đọc vào 1 mảng, trả về mảng chứa các thế cờ
	 * 
	 * @param path : đường dẫn đến file thế cờ
	 * @return chessPosition : mảng các thế cờ
	 */
	@SuppressWarnings("finally")
	public static String[][][] getChessPostion(String path) {
		String[][][] chessPositions = new String[1638][5][5]; // Khai báo khởi tạo mảng các thế cờ
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path)); // Tạo đối tượng để đọc file thế cờ
			String currentLine; // Dòng đang đọc trong file
			int chessPositionCount = 0; // Chỉ số của từng thế cờ
			int matrixRow = 0; // Biến: số hàng của ma trận thế cờ
			int matrixCol = 0; // Biến: số cột của ma trận thế cờ
			while ((currentLine = br.readLine().trim()) != null) { // Đọc từng dòng trong file cho đến hết
				
				if ("".equals(currentLine.trim())) { // Bỏ qua dòng trống
					continue; // Đi đến vòng while kế tiếp
				}
				if (currentLine.length() > 5) { // Nếu kích thước của 1 dòng lớn hơn 5
					ChessBoard.showMessage("Error in format of chess position file."); // Sai định dạng file => show lỗi
				}
				if (matrixRow == 5) { // Reset chỉ số hàng của ma trận thế cờ nếu vượt quá 4
					matrixRow = 0; // Reset chỉ số hàng của ma trận về 0 
					chessPositionCount++; // Tăng chỉ số của thế cờ tiếp theo sẽ được đọc
				}
				for (matrixCol = 0; matrixCol < 5; matrixCol++) { // Duyệt qua mỗi hàng gồm 5 cột của ma trận thế cờ
					// Đổ dữ liệu vào từng ô ma trận của hàng đang duyệt  
					chessPositions[chessPositionCount][matrixRow][matrixCol] = String.valueOf(currentLine.charAt(matrixCol));
				}
				matrixRow++; // Tăng đến hàng kế tiếp của ma trận
			}
			
		} catch (IOException e) { // Bắt ngoại lệ xảy ra khi đọc file
			ChessBoard.showMessage("File chess position not found."); // Show thông báo lỗi
		} finally {
			return chessPositions; // Trả về mảng các thế cờ
		}
	}
}
