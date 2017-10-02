/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * Demo.java, 03/10/2017 sanglt
 */
package pro_javacore_test_caro;

/**
 * @author sanglt
 *
 */
public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] a = { "T", "T", "T", "D", "X", "O" };
		String chuoiA = "";
		int aLeng = a.length;
		for (int i = 0; i < aLeng; i++) {
			if (a[i] == "D") {
				chuoiA += "T";
				continue;
			}
			chuoiA += a[i];
		}
		System.out.println("a: " + chuoiA);

		String[] b = { "T", "T", "T", "T", "X", "O" };
		String chuoiB = "";
		int bLeng = b.length;
		for (int i = 0; i < bLeng; i++) {
			chuoiB += b[i];
		}
		System.out.println("b: " + chuoiB);

		if (chuoiA.equals(chuoiB)) {
			System.out.println("a");
		} else {
			System.out.println("b");
		}
	}

}
