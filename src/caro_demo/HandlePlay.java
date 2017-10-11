package caro_demo;

/**
 * Xu lý nước đi người chơi, nước đi của máy
 * 
 * @author VaiO
 *
 */
public class HandlePlay {
	// khai báo mảng theCo lưu nội dung file thế cờ
	
	ValidateInput va = new ValidateInput();
	TaoBanCo taoBanCo = new TaoBanCo();
	DocFileTheCo doc = new DocFileTheCo();
	public static String[] theCo = DocFileTheCo.docFileTheCo("./theco.txt");
	/**
	 * lấy vị trí hàng muốn đánh
	 */
	public int getHangCot(String style) {
		int result = -1;
		String temp = va.input(style);
		result = Integer.parseInt(temp);
		return result;
	}

	public int[] getUserClick() {
		int[] click = new int[2];
		System.out.println("Nhập hàng muốn đánh: ");
		click[0] = getHangCot("Hàng");
		System.out.println("Nhập cột muốn đánh: ");
		click[1] = getHangCot("Cột");
		if (taoBanCo.checkClick(click[0], click[1])) {
			System.out.println("Vị trí [" + click[0] + "],[" + click[1] + "] đã được đánh rồi. Hãy nhập lại: ");
			click = getUserClick();
		}
		return click;
	}
	public void checkUserClick(int row, int col) {
		taoBanCo.banCo[row][col] = "O";

	}
	public int[] getComputerClick() {
		// lưu vị trí nước đi của máy trong mảng thế cớ nếu 2 mảng trùng nhau
		int result = -1;
		// lưu vị trí hàng lấy ma trận 5x5 từ bàn cờ
		int hang = 0;
		// lưu vị trí cột lấy ma trận 5x5 từ bàn cờ
		int cot = 0;
		// lưu vị trí nước đi của máy trên bàn cờ
		int[] kq = new int[2];
		// vòng lặp for lấy ra từng thế cờ
		for (int k = 0; k < theCo.length; k += 25) {
			// khai báo mảng temp1 lưu trữ 1 thế cờ
			String[] temp1 = new String[25];
			// lưu 1 thế cờ từ file thế cờ vào temp1
			for (int h = 0; h < 25; h++) {
				temp1[h] = theCo[k + h];
			}
			// vòng lặp for lấy tất cả ma trận 5x5 từ hàng 1 đến hàng 16
			for (int i = 1; i < 17; i++) {
				// vòng lặp for lấy tất cả ma trận 5x5 từ cột 1 đến cột 16
				for (int j = 1; j < 17; j++) {
					// lấy ma trận 5x5 bắt đầu từ vị trí hàng i cột j
					String[] temp2 = taoBanCo.getMatrix(i, j);
					// so sánh từng ma trận 5x5 của bàn cờ với mỗi ma trận 5x5
					// lấy từ file thế cờ
					result = doc.soSanhHaiMang(temp1, temp2);
					// gán biến hang bằng giá trị hàng lấy ma trận 5x5 của bàn
					// cờ
					hang = i;
					// gán biến cot bằng giá trị cột lấy ma trận 5x5 của bàn cờ
					cot = j;
					// nếu 2 ma trận giống nhau thì break
					if (result != -1) {
						//thoát vòng lặp
						break;
					}

				}
				// nếu 2 ma trận giống nhau thì break
				if (result != -1) {
					// thoát vòng lặp
					break;
				}
			}
			// nếu 2 ma trận giống nhau thì break
			if (result != -1) {
				// thoát vòng lặp
				break;
			}
		}
		// gán vị trí máy cần đánh trên bàn cờ có giá trị bằng "X"
		taoBanCo.banCo[hang + (result / 5)][cot + result - (result / 5) * 5] = "X";
		//lưu vị trí hàng vào kq[0]
		kq[0] = hang + (result / 5);
		//lưu vị trí cột vào kq[1]
		kq[1] = cot + result - (result / 5) * 5;
		//trả về mảng kq
		return kq;
	}
}
