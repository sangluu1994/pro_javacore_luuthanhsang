/**
 * CoppyRight(C) 2017 Luvina
 * Controller.java Jun 27, 2017 HUYDC
 */
package caro_demo.bai2;

import caro_demo.bai2.BanCo;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class Controller {
	int row = 20; // gtri hàng
	int col = 20; // gtri cột
	JButton[][] btn = BanCo.btn; // mảng các nút

	/**
	 * hàm check win
	 * 
	 * @param td
	 * @return
	 */
	public boolean checkWin(ToaDo td) {
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
	public boolean checkWinDoc(ToaDo td) {
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
	public boolean checkWinNgang(ToaDo td) {
		int X = td.getX(); // X là tọa độ X lấy từ td
		int Y = td.getY(); // Y là tọa độ Y lấy từ td
		String value = btn[X][Y].getText(); // lấy giá trị text từ td
		int count = 0; // biến đếm

		for (int j = -4; j <= 4; j++) // duyệt 8 cột xung quanh X
		{
			if (Y + j >= 0 && Y + j < col) // điều kiện là cột >0 và cột <20
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
	public boolean checkWinCheoPhai(ToaDo td) {

		int X = td.getX(); // X là tọa độ X lấy từ td
		int Y = td.getY(); // Y là tọa độ Y lấy từ td
		String value = btn[X][Y].getText(); // lấy giá trị text từ td
		int count = 0; // biến đếm
		for (int j = -4; j <= 4; j++) // duyệt 8,8 cột xung quanh X
		{
			if (Y + j >= 0 && Y + j < col && X + j >= 0 && X + j < row) // Điều kiện hàng,cột>0 và nhờ hơn 20
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
	public boolean checkWinCheoTrai(ToaDo td) {

		int X = td.getX(); // X là tọa độ X lấy từ td
		int Y = td.getY(); // Y là tọa độ Y lấy từ td
		String value = btn[X][Y].getText(); // lấy giá trị text từ td
		int count = 0; // biến đếm

		for (int j = -4; j <= 4; j++) // duyệt các hàng xung quanh
		{
			if (Y - j >= 0 && Y - j < col && X + j >= 0 && X + j < row) // Điều kiện hàng,cột>0 và nhờ hơn 20
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

	/**
	 * Đọc file
	 * 
	 * @return
	 */
	public ToaDo docFile() {

		ToaDo toaDo = null; // biến tọa độ
		String[][] a = new String[5][5]; // thế cờ a

		try (BufferedReader br = new BufferedReader(new FileReader("./theco.txt"))) { // biến reader
			String sCurrentLine; // dòng hiện tại
			int c, r = 0; // c là cột , r là hàng
			while ((sCurrentLine = br.readLine()) != null) { // thực thi nếu dòng cuối không null

				if (r == 5) { // nếu hàng =5
					r = 0; // set hàng =0
					toaDo = timKiemTC(a); // tìm biến tọa độ
					if (toaDo != null) { // nếu tọa độ khác null
						return toaDo; // trả về tọa độ
					}

					System.out.println("");
					continue;
				}

				if (sCurrentLine.trim().equals("")) { // lọc các dòng rỗng
					continue;
				}

				for (c = 0; c < 5; c++) // duyệt cột từ 0->4
				{
					a[r][c] = String.valueOf(sCurrentLine.charAt(c));
				}
				r++; // tăng giá trị hàng

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null; // trả về giá trị null

	}

	/**
	 * duyệt các mảng con trong bàn cờ để tìm thế cờ tương ứng nếu có
	 * 
	 * @param theCo
	 * @return tọa độ
	 */
	public ToaDo timKiemTC(String[][] theCo) {
		ToaDo toaDo = null; // biến tọa độ

		for (int i = 0; i <= 15; i++) // chạy hàng từ 0->15
		{
			for (int j = 0; j <= 15; j++) // chạy cột từ 0->15
			{
				toaDo = sosanhTC(i, j, theCo); // tọa đội = return của hàm sosanhTC()
				if (toaDo != null) // nếu tọa độ khác null
				{
					return toaDo; // trả về tọa độ
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
	 * @param theCo
	 * @return
	 */
	public ToaDo sosanhTC(int row, int col, String[][] theCo) {
		ToaDo toaDo = null; // khai báo toaDo
		for (int i = 0; i < 5; i++) // duyệt hàng từ 0->4
		{
			for (int j = 0; j < 5; j++) // duyệt cột từ 0->4
			{
				String value = btn[row + i][col + j].getText(); // khai báo biến giá trị value = giá trị của nút tương
																// ứng
				if (theCo[i][j].equals("X") && !value.equals("X")) // nếu như giá trị trong mảng của thế cờ =X và value
																	// != X
				{
					return null; // trả về null
				}
				if (theCo[i][j].equals("O") && !value.equals("O")) // nếu như giá trị trong mảng của thế cờ =O và value
																	// != O
				{
					return null; // trả về null
				}
				if (theCo[i][j].equals("T") && !value.equals("")) // nếu như giá trị trong mảng của thế cờ =T và value
																	// != rỗng
				{
					return null; // trả về null
				}
				if (theCo[i][j].equals("T") && value.equals("")) // nếu như giá trị trong mảng của thế cờ =T và value =
																	// rỗng
				{
					toaDo = new ToaDo(i + row, j + col); // toaDo set giá trị hàng và cột tương ứng
				}

			}

		}
		return toaDo; // trả về tọa độ
	}

	/**
	 * hàm này do để máy đánh O
	 * 
	 * @param td
	 *            tọa độ
	 */
	public void ComputerPlay(ToaDo td) {
		String[][] theCo = new String[5][5]; // Tạo ma trận thế cờ 5x5
		int i = td.getX(); // i = gtri hàng của td
		int j = td.getY(); // j = gtri cột của td

		ToaDo toaDo = docFile(); // toaDo = giá trị trả về của hàm đọc file
		if (toaDo != null) { // nếu toaDo != null
			btn[toaDo.getX()][toaDo.getY()].setText("O"); // thì set cho nút có tọa độ tương ứng là "O"
			if (checkWin(toaDo)) { // kiểm tra win
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
	public ToaDo random() {
		ToaDo toado = null; // tọa độ
		int dem = 0; // biến check
		while (dem < 1) { // khi mà check <1
			int x = new Random().nextInt(20); // x = 1 số random
			int y = new Random().nextInt(20); // y = 1 số random
			if (btn[x][y].getText().equals("")) // nếu như vị trí nút có tọa độ x y rỗng
			{
				toado = new ToaDo(x, y); // tọa độ set giá trị x,y
				btn[x][y].setText("O"); // nút có tọa đọ x,y sex settext là O
				dem++; // check tăng lên 1
			}
		}

		return toado; // trả về tọa đọ

	}

	public void reset() {
		for (int i = 0; i < row; i++) // chạy lần lượt các hàng trong mảng
		{
			for (int j = 0; j < col; j++) // chạy lần lượt cột trong mảng
			{

				btn[i][j].setText(""); // set text giá trị rỗng cho từng button

			}
		}

	}

}