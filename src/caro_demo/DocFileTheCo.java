package caro_demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * xu lý đọc file, so sánh ma trận 5x5 dưới dạng mảng 1 chiều
 * @author VaiO
 *
 */
public class DocFileTheCo {
	
	/**
	 * hàm đọc file
	 */
	public static String[] docFileTheCo(String path) {
		// khai báo mảng 1 chiều result kiểu String lưu giữ nội dung file thế cờ
		String[] result = null;
		// tạo file
		File file = new File(path);
		// khai báo chuôi temp1 bằng rỗng
		String temp1 = "";
		String temp2 = "";
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			temp1 = bufferedReader.readLine();
			//đọc dữ liệu từng ròng
			while((temp1 = bufferedReader.readLine()) != null) {
				//kiểm tra điều kiện độ dài của chuỗi là 1 dòng của 1 ma trận thế cờ 5x5 
				if(temp1.length() > 6 && temp1.length() < 12) {
					temp2 += temp1 + " ";
				}
			}
			temp2 = temp2.trim();
			//các chuỗi nhỏ được phân cách nhau bởi kí tự khoảng trắng trong chuỗi temp3 bị cắt rời nhau và lưu vào mảng result
			result = temp2.split(" ");
		} catch (FileNotFoundException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public int soSanhHaiMang(String[] file, String[] banco) {
		int result = -1;
		int count = 0;
		for(int i = 0; i < 25; i++) {
			if(banco[i].equals(file[i]) || ("G".equals(file[i]))) {
				count++;
			}
			if("+".equals(banco[i]) && "D".equals(file[i])) {
				result = i;
			}
		}
		if(count != 24) {
			result = -1;
		}
		return result;
	}
}
