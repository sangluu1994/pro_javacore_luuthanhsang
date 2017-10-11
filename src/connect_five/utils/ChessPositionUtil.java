/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * ChessPositionUtil.java, 11/10/2017 SangLT
 */
package connect_five.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import connect_five.config.CommonConfig;
import connect_five.entity.ChessPosition;

/**
 * Class xử lí lấy file thế cờ
 * 
 * @author Luu Thanh Sang
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
	public static ArrayList<ChessPosition> readChessPostionsFile(String path) {
		int status = CommonConfig.NORMAL_FILE_STATUS; // Khai báo mã trạng thái của file thế cờ
		ArrayList<ChessPosition> chessPositions = new ArrayList<ChessPosition>(); // Khai báo khởi tạo mảng các thế cờ
		BufferedReader br = null; // Khai báo BufferedReader đọc file
		try {
			br = new BufferedReader(new FileReader(path)); // Tạo đối tượng để đọc file thế cờ
			String currentLine; // Dòng đang đọc trong file
			int chessPositionCount = 0; // Biến đếm số thế cờ đọc được
			int matrixRow = 0; // Biến: số hàng của ma trận thế cờ
			int matrixCol = 0; // Biến: số cột của ma trận thế cờ
			String[][] tempArr = new String[CommonConfig.CHESS_POSITION_MATRIX_ROW][CommonConfig.CHESS_POSITION_MATRIX_COLUMN]; // Biến tạm lưu ma trận thế cờ
			while ((currentLine = br.readLine().trim()) != null) { // Đọc từng dòng trong file cho đến hết
				
				if ("".equals(currentLine.trim())) { // Bỏ qua dòng trống
					continue; // Đi đến vòng while kế tiếp
				}
				if (currentLine.length() != CommonConfig.CHESS_POSITION_MATRIX_COLUMN) { // Nếu kích thước của 1 dòng lớn hơn 5
					for (matrixCol = 0; matrixCol < CommonConfig.CHESS_POSITION_MATRIX_COLUMN; matrixCol++) { // Duyệt qua mỗi hàng gồm 5 cột của ma trận thế cờ
						// Đặt các kí hiệu lỗi vào dòng sai định dạng 
						tempArr[matrixRow][matrixCol] = CommonConfig.ERROR_CHAR;
						status = CommonConfig.ERROR_FORMAT_FILE_STATUS; // Đặt trạng thái file về: file lỗi định dạng
					}
				} else {
					for (matrixCol = 0; matrixCol < CommonConfig.CHESS_POSITION_MATRIX_COLUMN; matrixCol++) { // Duyệt qua mỗi hàng gồm 5 cột của ma trận thế cờ
						// Đổ dữ liệu vào từng ô ma trận của hàng đang duyệt  
						tempArr[matrixRow][matrixCol] = String.valueOf(currentLine.charAt(matrixCol));
					}
				}
				matrixRow++; // Tăng đến hàng kế tiếp của ma trận
				if (matrixRow == CommonConfig.CHESS_POSITION_MATRIX_ROW) { // Reset chỉ số hàng của ma trận thế cờ nếu vượt quá 4
					chessPositions.add(new ChessPosition(tempArr)); // Thêm từng đối tượng thế cờ vào mảng thế cờ
					tempArr = new String[CommonConfig.CHESS_POSITION_MATRIX_ROW][CommonConfig.CHESS_POSITION_MATRIX_COLUMN]; // Reset biến tạm
					matrixRow = 0; // Reset chỉ số hàng của ma trận về 0 
					chessPositionCount++; // Tăng chỉ số của arraylist thế cờ
				}
				
			}
			if (chessPositionCount == 0) { // Kiếm tra nội dung file trống
				chessPositions = null; // Thiết lập giá trị trả về null
				status = CommonConfig.EMPTY_FILE_STATUS; // Thay đổi trạng thái file thế cờ thành file rỗng
			}
			
		} catch (IOException e) { // Bắt ngoại lệ xảy ra khi đọc file
			status = CommonConfig.EXCEPTION_STATUS; // Thay đổi trạng thái file về
		} finally {
			closeBufferedReader(br); // Đóng BufferedReader
			showFileStatusLog(status); // Hiện trạng thái file ra console
			return chessPositions; // Trả về mảng các thế cờ
		}
	}
	
	/**
	 * Hàm hiện thông báo trạng thái file thế cờ ra console
	 * 
	 * @param status : mã trạng thái file
	 */
	public static void showFileStatusLog(int status) {
		switch (status) { // Duyệt trạng thái được truyền vào
		case CommonConfig.NORMAL_FILE_STATUS: // Nếu là mã file không lỗi
			System.out.println(CommonConfig.NORMAL_FILE_MSG); // Hiện thông báo tương ứng ra console
			break; // Thoát
		case CommonConfig.NO_FILE_STATUS: // Nếu là mã file không tồn tại
			System.out.println(CommonConfig.NO_FILE_MSG); // Hiện thông báo tương ứng ra console
			break; // Thoát
		case CommonConfig.ERROR_FORMAT_FILE_STATUS: // Nếu là mã file lỗi định dạng
			System.out.println(CommonConfig.ERROR_FORMAT_FILE_MSG); // Hiện thông báo tương ứng ra console
			break; // Thoát
		case CommonConfig.EXCEPTION_STATUS: // Nếu là mã bị exception trong khi đọc file
			System.out.println(CommonConfig.EXCEPTION_MSG); // Hiện thông báo tương ứng ra console
			break; // Thoát
		case CommonConfig.EMPTY_FILE_STATUS: // Nếu là mã file rỗng
			System.out.println(CommonConfig.EMPTY_FILE_MSG); // Hiện thông báo tương ứng ra console
			break; // Thoát
		}
	}
	
	/**
	 * Hàm đóng BufferedReader đọc file
	 * 
	 * @param br : đối tượng BufferedReader
	 */
	public static void closeBufferedReader(BufferedReader br) {
		try {
			br.close(); // Đóng đối tượng BufferedReader
		} catch (Exception e) { // Nếu gặp lỗi
			System.out.println(e.getMessage()); // In thông báo lỗi ra console 
		}
	}
	
//	public static void main(String[] args) {
//		ArrayList<ChessPosition> a = getChessPostion("./src/chess_positions.txt");
//		int count = 0;
//		for (ChessPosition b : a) {
//			for (int i = 0; i < 5; i++) {
//				for (int j = 0; j < 5; j++) {
//					System.out.print(b.getChessPositionMatrix()[i][j]);
//					if (j == 4) {
//						System.out.print("\n");
//					}
//				}
//			}
//			count++;
//		}
//		System.out.println(count);
//	}
}