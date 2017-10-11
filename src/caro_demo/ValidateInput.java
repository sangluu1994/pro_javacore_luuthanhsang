package caro_demo;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateInput {
	public static boolean validate(String temp, String style) {
		Pattern pattern = Pattern.compile("\\d*");
		Matcher matcher = pattern.matcher(temp);
		if(("").equals(temp)) {
			System.out.println("Hãy nhập " + style + " cần đánh: \n");
			return false;
		}
		if(!matcher.matches()){
			System.out.println(
					"Giá trị " + style + " phải là giá trị số. Hãy nhập lại " + style + " :\n");
			return false;
		} 
		int number = Integer.parseInt(temp);
		if(number <= 0) {
			System.out.println(
					"" + style + " phải lớn hơn 0. Hãy nhập lại " + style + ":\n");
			return false;
		}
		if (number > 20) {
			System.out.println(
					"" + style + " không được lớn hơn 21. Hãy nhập lại " + style + ":\n");

			return false;
		}
		return true;
	}
	public String input(String style) {
		String temp = (new Scanner(System.in)).nextLine();
		while (!validate(temp, style)) {
			temp = (new Scanner(System.in)).nextLine();	
		}
		return temp;
	}
	
}
