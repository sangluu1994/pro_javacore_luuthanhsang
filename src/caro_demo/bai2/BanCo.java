/**
 * CoppyRight(C) 2017 Luvina
 * BanCo.java Jun 27, 2017 HUYDC
 */
package caro_demo.bai2;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class BanCo extends JPanel implements ActionListener {

	int ROW = 20; // hàng
	int COL = 20; // cột

	public static JButton btn[][] = new JButton[20][20]; // khởi tạo mảng các
	Controller chucNang;
	public static JFrame frame = new JFrame(); // biến khởi tạo khung

	/**
	 * hàm vẽ bàn cờ gồm các button
	 */
	public void drawBoard() {

		frame.setLayout(new GridLayout(ROW, COL)); // chọn type view cho frame

		for (int i = 0; i < ROW; i++) // chạy lần lượt các hàng trong mảng
		{
			for (int j = 0; j < COL; j++) // chạy lần lượt cột trong mảng
			{
				String v = i + " " + j; // set command để gửi đi

				btn[i][j] = new JButton(""); // set text giá trị rỗng cho từng button
				btn[i][j].setActionCommand(v); // gửi command v
				btn[i][j].addActionListener(this); // tạo sự kiện cho từng nút
				frame.add(btn[i][j]); // add từng nút vào frame
			}
		}
		frame.setSize(900, 900); // thiết lập size cho frame
		frame.setVisible(true); // hiển thị frame

	}

	@Override
	/**
	 * Hàm xử lý khi click vào button
	 */
	public void actionPerformed(ActionEvent e) {

		String s = e.getActionCommand(); // lấy dữ liệu gửi đi
		String[] stmp = s.split(" "); // chia nhỏ chuỗi bằng dấu cách tạo thành mảng chuỗi
		JButton button = (JButton) e.getSource(); // lấy button được click
		chucNang = new Controller(); // khởi tạo controller
		int i = Integer.parseInt(stmp[0]); // i là tọa độ hàng của button lấy từ giá trị đầu tiên của chuỗi gửi đi
		int j = Integer.parseInt(stmp[1]); // j là tọa độ cột của button lấy từ giá trị tiếp theo của chuỗi gửi đi
		if (btn[i][j].getText().equals("")) { // nếu nút được click là rỗng
			btn[i][j].setText("X"); // set giá trị nút đó là X
			if (chucNang.checkWin(new ToaDo(i, j))) // kiểm tra win
			{
				JOptionPane.showMessageDialog(null, "you won"); // thông báo
				chucNang.reset();
			} else {
				chucNang.ComputerPlay(new ToaDo(i, j)); // nếu không win thì gọi hàm này

			}

		}

	}

	public static void main(String args[]) {
		BanCo banCo = new BanCo(); // tạo bàn cờ
		banCo.drawBoard(); // gọi hàm vẽ bàn cờ

	}

}
