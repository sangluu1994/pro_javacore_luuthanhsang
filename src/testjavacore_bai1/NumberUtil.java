/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * NumberUtil.java, 2017/09/22 SangLT
 */
package testjavacore_bai1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Class chứa các method lấy, kiểm tra các giá trị số
 * @author luuthanhsang
 *
 */
public class NumberUtil {
	/**
	 * Hàm lấy dữ liệu nhập vào, kiểm tra dữ liệu nhập vào
	 * @param input Tên số bị chia hoặc số chia
	 * @return inputVal Dữ liệu đúng trả về sau khi đã kiểm tra
	 */
	public static Double getNumber(String input) {
		do {
			System.out.print("Giá trị [" + input + "]: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try {
				String inputVal = br.readLine();
				Boolean isNumberInput = isNumber(inputVal);
				if (inputVal.isEmpty()) { // Kiểm tra input rỗng
					System.out.println("Hãy nhập giá trị cho " + input);
				} else if (!isNumberInput || (Double.parseDouble(inputVal) <= 0)) { // Kiểm tra nhỏ hơn 0
					System.out.println("Giá trị của " + input + " phải là số và lớn hơn 0. Hãy nhập lại.");
				} else if (!isNumberInput || (removeNoneDigits(inputVal).length() > 5)) { // Kiểm tra lớn hơn 5 chữ số
					System.out.println("Giá trị của " + input + " không được lớn hơn 5 chữ số. Hãy nhập lại.");
				} else {
					return (Double.parseDouble(inputVal));
				}
			} catch (Exception e) {
				System.out.println("Hãy nhập vào giá trị số.");
			}
		} while (true);
	}

	/**
	 * Hàm kiểm tra input truyền vào có phải là số không
	 * @param input
	 * @return boolean 
	 */
	public static boolean isNumber(String input) {
		return input.matches("-?\\d+(\\.\\d+)?");
	}
	
	/**
	 * Hàm chia 2 số
	 * @param inputA
	 * @param inputB
	 * @return division (division = inputA / inputB)
	 */
	public static Double divideNumber(Double inputA, Double inputB) {
		return (inputA / inputB);
	}
	
	/**
	 * Loại bỏ các kí tự không phải là số trong 1 chuỗi
	 * @param input Chuỗi đầu vào
	 * @return Chuỗi sau khi loại bỏ các kí tự không phải là số
	 */
	public static String removeNoneDigits(String input) {
		return input.replaceAll("\\D", "");
	}
}
