package caro_demo;

public class TaoBanCo {
	public static String[][] banCo = new String[21][21];
	public static void createBanCo() {
		// vòng for duyệt các hàng của bàn cờ
				for(int i = 0; i < 21; i++) {
					// vòng for duyệt các cột ma trận
					for(int j = 0; j < 21; j++) {
						// hàng 0 cột <21
						if(i == 0 && j >0) {
							/*
							 *  giá trị hàng 0 bằng giá trị cột tương ứng
							 *  đánh số hàng cho dễ nhìn khi đánh cờ
							 */
							banCo[i][j] = String.valueOf(j);
							if( j < 10) {
								System.out.print("0"+banCo[i][j]+" ");
							} else {
								System.out.print(banCo[i][j]+" ");
							}
							
							
						}
						// cột 0 hàng < 21
						if(j == 0 && i < 21) {
							/*
							 * giá tri cot 0 bằng giá trị hàng tương ứng
							 * đánh số cột cho dễ nhìn khi chơi cờ
							 */
							banCo[i][j] = String.valueOf(i);
							if(i < 10) {
								System.out.print("0"+banCo[i][j]+" ");
							} else {
								System.out.print(banCo[i][j]+" ");
							}
							
						}
						// i > 0 và j > 0
						if(i > 0 && j > 0) {
							// gán giá trị các ô bằng "=" cho đễ nhìn
							banCo[i][j] = "+";
							System.out.print(" "+banCo[i][j]+" ");
						}
					//	System.out.println();
					}
					System.out.println();
				}
	}
	public boolean checkClick(int hang, int cot) {
		if("+".equals(banCo[hang][cot])) {
			return false;
		}
		return true;
	}
	/**
	 * hàm kiểm tra thắng thua
	 * @param hang
	 * @param cot
	 * @return true nếu là thắng, return false nếu là thua
	 */
	public boolean checkWin(int hang, int cot) {
		int countHang = 1;
		int countCot = 1;
		int countCheoXuoi = 1;
		int countCheoNguoc = 1;
		int tempHang;
		int tempCot;
		// kiểm tra các phần tử trong 1 hang đánh từ trên xuống
		tempHang = hang;
		tempCot = cot;
		while (hang < 20) {
			if(banCo[hang + 1][cot].equals(banCo[hang][cot])) {
				countHang++;
			}
			hang++;
		}
		if(countHang == 5) {
			return true;
		}
		// kiểm tra các phần tử trong 1 cot đánh từ duoi len
			tempHang = hang;
			tempCot = cot;
				while (hang > 0) {
					if(banCo[hang - 1][cot].equals(banCo[hang][cot])) {
						countHang++;
					}
					hang--;
				}
				if(countHang == 5) {
					return true;
				}
		// kiểm tra các phần tử trong 1 cot từ trên xuống
		tempCot = cot;
		while (cot < 20 ) {
			if( banCo[hang][cot + 1].equals(banCo[hang][cot])) {
				countCot++;
			}
			cot++;
		}
		if(countCot == 5) {
			return true;
		}
		// kiểm tra các phần tử trong 1 cot từ dưới lên 
		
		tempCot = cot;
		while (cot > 0 ) {
			if( banCo[hang][cot - 1].equals(banCo[hang][cot])) {
				countCot++;
			}
			cot--;
		}
		if(countCot == 5) {
			return true;
		}
		// Check duong cheo xuoi tu trai qua phải tu tren xuong duoi
		tempHang = hang;
		tempCot = cot;
		while (hang < 20 && cot < 20) {
			if(banCo[hang + 1][cot + 1].equals(banCo[hang][cot])) {
				countCheoXuoi++;
			}
			hang++;
			cot++;
		}
		// Check duong cheo xuoi tu trai qua phải tu duoi len tren
				tempHang = hang;
				tempCot = cot;
				while (hang > 0 && cot > 0) {
					if(banCo[hang - 1][cot - 1].equals(banCo[hang][cot])) {
						countCheoXuoi++;
					}
					hang--;
					cot--;
				}
		// check duong cheo nguoc tu phai qua trai tu tren xuong guoi
		tempHang = hang;
		tempCot = cot;
		while (hang < 20 && cot > 1) {
			if(banCo[hang + 1][cot - 1].equals(banCo[hang][cot])) {
				countCheoNguoc++;
			}
			hang++;
			cot--;
		}
		if(countCheoNguoc == 5) {
			return true;
		}
		// check duong cheo nguoc tu phai qua trai tu tren xuong guoi
				tempHang = hang;
				tempCot = cot;
				while (hang > 0 && cot < 20) {
					if(banCo[hang - 1][cot + 1].equals(banCo[hang][cot])) {
						countCheoNguoc++;
					}
					hang--;
					cot++;
				}
				if(countCheoNguoc == 5) {
					return true;
				}
		return false;
	}
	/**
	 * Hàm lấy về ma trận 5.5 
	 * @param hang hàng bắt đầu lấy
	 * @param cot cột bắt đầu lấy
	 * @return mảng 1 chiều chứa các phần tử của ma trận
	 */
	public static String[] getMatrix(int hang, int cot) {
		// khai báo mảng result có 25 phần tử kiểu String
		String[] result = new String[25];
		// khai báo biến temp1 lưu giữ chỉ số của các phần tử trong mảng result
		int temp1 = 0;
		// lấy các phần tử của ma trận 5x5 lưu vào mảng 1 chiều result
		for (int hang1 = hang; hang1 < hang + 5; hang1++) {
			for (int cot1 = cot; cot1 < cot + 5; cot1++) {
				result[temp1] = banCo[hang1][cot1];
				temp1++;
			}

		}
		// trả về mảng 1 chiều result chứa 25 phần tử của ma trận
		return result;

	}
	// lấy về ma trận
	public static String[] getBanCo(int hang, int cot) {
		String[] result = new String[25];
		int temp = 0;
		for(int i = hang; i < hang + 5; i++) {
			for(int j = cot; j < cot + 5; j++) {
				result[temp] = banCo[i][j];
				temp++;
			}
		}
		return result;
	}
	public void drawBoard() {
		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 21; j++) {
				// in ra hàng 0
				if (i == 0) {
					if(j<10){
						System.out.print("0"+banCo[i][j]+" ");
					}
					else
					{
						System.out.print(banCo[i][j]+" ");
					}
				}
				// in ra cột 0
				if (j == 0 && i > 0) {
					if(i< 10){
						System.out.print("0"+banCo[i][j]);
					} else {
						System.out.print(banCo[i][j]);
					}
					
				}
				// in ra phần từ còn lại
				if (i > 0 && j > 0) {
					System.out.print("  " + banCo[i][j]);
				}
			}
			//xuống dòng
			System.out.println();
		}
		//xuống dòng
		System.out.println();
	}
}
