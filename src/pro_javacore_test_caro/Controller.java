/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * Controller.java, 04/10/2017 sanglt
 */
package pro_javacore_test_caro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * Class xử lí các hoạt động trên bàn cờ
 * 
 * @author sanglt
 *
 */
public class Controller {
	// Khai báo giá trị hoành độ, tung độ tối đa
	int row = 20; // hoành độ
	int column = 20; // tung độ
	// Khởi tạo và khai báo mảng các button
	JButton[][] btn = ChessBoard.btn;
	
	public Position readFile() {
		Position position = null; // Khởi tạo vị trí tọa độ
		String[][] chessPosition = new String[5][5]; // Thế cờ
		
		try { 
			BufferedReader br = new BufferedReader(new FileReader("./chessPosition.txt")); // Biến reader đọc file thế cờ
			int matrixCol = 0; // khởi tạo tung độ ma trận thế cờ
			int matrixRow = 0; // khởi tạo hoành độ ma trận thế cờ
			String linePoint; // biến đọc từng dòng đơn lẻ
			while ((linePoint = br.readLine()) != null) { // đọc từng dòng trong file thế cờ
				
				if (matrixRow == 5) { // nếu hoành độ ma trận = 5
					matrixRow = 0; // reset hoành độ = 0
					position = findChessPosition(chessPosition); // tìm biến tọa độ
					if (position != null) { // xét tọa độ, nếu khác null
						return position; // trả về tọa độ
					}
					
					System.out.println("");
					continue;
				}
				
				if ("".equals(linePoint.trim())) { // Bỏ qua các dòng trống
					continue;
				}
				
				for (matrixCol = 0; matrixCol < 5; matrixCol++) { // đọc cột 0 đến cột 4 trong 1 hàng
					chessPosition[matrixCol][matrixRow] = String.valueOf(linePoint.charAt(matrixCol));
				}
				
				matrixRow++; // tăng đến hàng tiếp theo trong ma trận
			}
		} catch (IOException e) { // Bắt ngoại lệ khi thao tác với file gặp lỗi
			JOptionPane.showMessageDialog(null, "Chess position book not found!");
		}
		
		return null; // trả về giá trị null
	}
	
	/**
	 * duyệt các mảng con trong bàn cờ để tìm thế cờ tương ứng nếu có
	 * 
	 * @param chessPosition
	 * @return tọa độ
	 */
	public Position findChessPosition(String[][] chessPosition) {
		Position Position = null; // biến tọa độ

		for (int i = 0; i <= 15; i++) // chạy hàng từ 0->15
		{
			for (int j = 0; j <= 15; j++) // chạy cột từ 0->15
			{
				Position = sosanhTC(i, j, chessPosition); // tọa đội = return của hàm sosanhTC()
				if (Position != null) // nếu tọa độ khác null
				{
					return Position; // trả về tọa độ
				}

			}
		}
		return null; // trả về null
	}

	/**
	 * so sánh thế cờ với mảng 5x5 của bàn cờ
	 * 
	 * @param row
	 * @param col
	 * @param chessPosition
	 * @return
	 */
	public Position sosanhTC(int row, int col, String[][] chessPosition) {
		Position Position = null; // khai báo Position
		for (int i = 0; i < 5; i++) // duyệt hàng từ 0->4
		{
			for (int j = 0; j < 5; j++) // duyệt cột từ 0->4
			{
				String value = btn[row + i][col + j].getText(); // khai báo biến giá trị value = giá trị của nút tương
																// ứng
				if (chessPosition[i][j].equals("X") && !value.equals("X")) // nếu như giá trị trong mảng của thế cờ =X và value
																	// != X
				{
					return null; // trả về null
				}
				if (chessPosition[i][j].equals("O") && !value.equals("O")) // nếu như giá trị trong mảng của thế cờ =O và value
																	// != O
				{
					return null; // trả về null
				}
				if (chessPosition[i][j].equals("T") && !value.equals("")) // nếu như giá trị trong mảng của thế cờ =T và value
																	// != rỗng
				{
					return null; // trả về null
				}
				if (chessPosition[i][j].equals("T") && value.equals("")) // nếu như giá trị trong mảng của thế cờ =T và value =
																	// rỗng
				{
					Position = new Position(i + row, j + col); // Position set giá trị hàng và cột tương ứng
				}

			}

		}
		return Position; // trả về tọa độ
	}

	/**
	 * hàm này do để máy đánh O
	 * 
	 * @param td
	 *            tọa độ
	 */
	public void ComputerPlay(Position td) {
		String[][] chessPosition = new String[5][5]; // Tạo ma trận thế cờ 5x5
		int i = td.getX(); // i = gtri hàng của td
		int j = td.getY(); // j = gtri cột của td

		Position Position = readFile(); // Position = giá trị trả về của hàm đọc file
		if (Position != null) { // nếu Position != null
			btn[Position.getX()][Position.getY()].setText("O"); // thì set cho nút có tọa độ tương ứng là "O"
			if (checkWin(Position)) { // kiểm tra win
				JOptionPane.showMessageDialog(null, "you lose"); // hiển thị thông báo "O thắng"
				reset();

			}
			;
		} else {
			// danh random

			if (checkWin(random())) { // nếu không sẽ đánh random và kiểm tra win

				JOptionPane.showMessageDialog(null, "you lose"); // hiển thị O thắng
				reset();
			}
			;
		}

	}

	/**
	 * hàm sinh tọa độ ngẫu nhiên
	 * 
	 * @return
	 */
	public Position random() {
		Position Position = null; // tọa độ
		int dem = 0; // biến check
		while (dem < 1) { // khi mà check <1
			int x = new Random().nextInt(20); // x = 1 số random
			int y = new Random().nextInt(20); // y = 1 số random
			if (btn[x][y].getText().equals("")) // nếu như vị trí nút có tọa độ x y rỗng
			{
				Position = new Position(x, y); // tọa độ set giá trị x,y
				btn[x][y].setText("O"); // nút có tọa đọ x,y sex settext là O
				dem++; // check tăng lên 1
			}
		}

		return Position; // trả về tọa đọ

	}
	
	/**
	 * hàm check win
	 * 
	 * @param td
	 * @return
	 */
	public boolean checkWin(Position td) {
		if (checkWinCheoPhai(td))
			return true; // gọi hàm check chéo phải
		if (checkWinCheoTrai(td))
			return true; // gọi hàm check chéo trái
		if (checkWinDoc(td))
			return true; // gọi hàm check dọc
		if (checkWinNgang(td))
			return true; // gọi hàm check ngang
		return false;
	}
	
	/**
	 * kiểm tra win dọc
	 * 
	 * @param td
	 *            tọa độ
	 * @return đúng sai
	 */
	public boolean checkWinDoc(Position td) {
		int X = td.getX(); // X là tọa độ X lấy từ td
		int Y = td.getY(); // Y là tọa độ Y lấy từ td
		String value = btn[X][Y].getText(); // lấy giá trị text từ td
		int count = 0; // biến đếm

		for (int i = -4; i <= 4; i++) // duyệt 8 hàng xung quanh X
		{
			if (X + i >= 0 && X + i < row) // điều kiện là hàng >0 và hàng <20
			{
				if (btn[X + i][Y].getText().equals(value)) { // nếu giá trị của btn này = value

					count++; // tăng biến đếm

					if (count == 5) // nếu biến đếm =5
					{
						return true; // trả về true
					}
				} else // nếu không
				{
					count = 0; // set biến đếm =0;
				}
			}
		}
		return false; // trả về false
	}

	/**
	 * kiểm tra win ngang
	 * 
	 * @param td
	 *            tọa độ
	 * @return đúng sai
	 */
	public boolean checkWinNgang(Position td) {
		int X = td.getX(); // X là tọa độ X lấy từ td
		int Y = td.getY(); // Y là tọa độ Y lấy từ td
		String value = btn[X][Y].getText(); // lấy giá trị text từ td
		int count = 0; // biến đếm

		for (int j = -4; j <= 4; j++) // duyệt 8 cột xung quanh X
		{
			if (Y + j >= 0 && Y + j < column) // điều kiện là cột >0 và cột <20
			{
				if (btn[X][Y + j].getText().equals(value)) { // nếu giá trị của btn này = value

					count++; // tăng biến đếm

					if (count == 5) // nếu biến đếm =5
					{
						return true; // trả về true
					}
				} else {
					count = 0; // gán count =0;
				}
			}
		}
		return false; // trả về false
	}

	/**
	 * kiểm tra win chéo phải
	 * 
	 * @param td
	 *            tọa độ
	 * @return đúng sai
	 */
	public boolean checkWinCheoPhai(Position td) {

		int X = td.getX(); // X là tọa độ X lấy từ td
		int Y = td.getY(); // Y là tọa độ Y lấy từ td
		String value = btn[X][Y].getText(); // lấy giá trị text từ td
		int count = 0; // biến đếm
		for (int j = -4; j <= 4; j++) // duyệt 8,8 cột xung quanh X
		{
			if (Y + j >= 0 && Y + j < column && X + j >= 0 && X + j < row) // Điều kiện hàng,cột>0 và nhờ hơn 20
			{
				if (btn[X + j][Y + j].getText().equals(value)) { // nếu giá trị của btn này = value

					count++; // tăng biến đếm

					if (count == 5) // nếu biến đếm =5
					{
						return true; // trả về true
					}
				} else {
					count = 0; // trả về 0
				}
			}
		}
		return false; // trả về false

	}

	/**
	 * kiểm tra win trái
	 * 
	 * @param td
	 *            tọa độ
	 * @return đúng sai
	 */
	public boolean checkWinCheoTrai(Position td) {

		int X = td.getX(); // X là tọa độ X lấy từ td
		int Y = td.getY(); // Y là tọa độ Y lấy từ td
		String value = btn[X][Y].getText(); // lấy giá trị text từ td
		int count = 0; // biến đếm

		for (int j = -4; j <= 4; j++) // duyệt các hàng xung quanh
		{
			if (Y - j >= 0 && Y - j < column && X + j >= 0 && X + j < row) // Điều kiện hàng,cột>0 và nhờ hơn 20
			{
				if (btn[X + j][Y - j].getText().equals(value)) // nếu giá trị của btn này = value
				{

					count++; // tăng biến đếm

					if (count == 5) // nếu biến đếm =5
					{
						return true; // trả về true
					}
				} else {
					count = 0; // trả về 0
				}
			}
		}

		return false; // trả về false
	}


	public void reset() {
		for (int i = 0; i < row; i++) // chạy lần lượt các hàng trong mảng
		{
			for (int j = 0; j < column; j++) // chạy lần lượt cột trong mảng
			{

				btn[i][j].setText(""); // set text giá trị rỗng cho từng button

			}
		}

	}
}
