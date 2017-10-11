/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * ChessPosition.java, 04/10/2017 Luu Thanh Sang
 */
package test_caro.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import test_caro.view.ChessBoard;

/**
 * Class xử lí lấy file thế cờ
 * 
 * @author sanglt
 *
 */
public class ChessPositionUtil {
	/**
	 * Hàm lấy file thế cờ và đọc vào 1 mảng, trả về mảng chứa các thế cờ
	 * 
	 * @param path : đường dẫn đến file thế cờ
	 * @return chessPosition : mảng các thế cờ
	 */
	@SuppressWarnings("finally")
	public static ArrayList<ChessPosition> getChessPostion(String path) {
		ArrayList<ChessPosition> chessPositions = new ArrayList<ChessPosition>(); // Khai báo khởi tạo mảng các thế cờ
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path)); // Tạo đối tượng để đọc file thế cờ
			String currentLine; // Dòng đang đọc trong file
//			int chessPositionCount = 0; // Chỉ số của từng thế cờ
			int matrixRow = 0; // Biến: số hàng của ma trận thế cờ
			int matrixCol = 0; // Biến: số cột của ma trận thế cờ
			String[][] tempArr = new String[5][5]; // Biến tạm lưu ma trận thế cờ
			while ((currentLine = br.readLine().trim()) != null) { // Đọc từng dòng trong file cho đến hết
				
				if ("".equals(currentLine.trim())) { // Bỏ qua dòng trống
					continue; // Đi đến vòng while kế tiếp
				}
				if (currentLine.length() > 5) { // Nếu kích thước của 1 dòng lớn hơn 5
					ChessBoard.showMessage("Error in format of chess position file."); // Sai định dạng file => show lỗi
				}
				for (matrixCol = 0; matrixCol < 5; matrixCol++) { // Duyệt qua mỗi hàng gồm 5 cột của ma trận thế cờ
					// Đổ dữ liệu vào từng ô ma trận của hàng đang duyệt  
					tempArr[matrixRow][matrixCol] = String.valueOf(currentLine.charAt(matrixCol));
				}
				matrixRow++; // Tăng đến hàng kế tiếp của ma trận
				if (matrixRow == 5) { // Reset chỉ số hàng của ma trận thế cờ nếu vượt quá 4
					chessPositions.add(new ChessPosition(tempArr)); // Thêm từng đối tượng thế cờ vào mảng thế cờ
					tempArr = new String[5][5]; // Reset biến tạm
					matrixRow = 0; // Reset chỉ số hàng của ma trận về 0 
//					chessPositionCount++; // Tăng chỉ số của thế cờ tiếp theo sẽ được đọc
				}
			}
			
		} catch (IOException e) { // Bắt ngoại lệ xảy ra khi đọc file
			ChessBoard.showMessage("File chess position not found."); // Show thông báo lỗi
		} finally {
			return chessPositions; // Trả về mảng các thế cờ
		}
	}
	
	public static void main(String[] args) {
		ArrayList<ChessPosition> a = getChessPostion("./src/model/chess_positions.txt");
		for (ChessPosition b : a) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					System.out.print(b.getChessPosition()[i][j]);
					if (j == 4) {
						System.out.print("\n");
					}
				}
			}
		}
	}
}