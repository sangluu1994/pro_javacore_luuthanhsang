/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * DivisionDemo.java, 2017/09/22 SangLT
 */
package testjavacore_bai1;

/**
 * Division Demo Class
 * 
 * @author luuthanhsang
 *
 */
public class DivisionDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Double inputA, inputB, result;
		inputA = NumberUtil.getNumber("A");
		inputB = NumberUtil.getNumber("B");
		result = NumberUtil.divideNumber(inputA, inputB);
		System.out.println("Thương số của phép chia A/B = " + result);
	}

}