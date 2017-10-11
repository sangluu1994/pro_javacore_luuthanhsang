/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * CDDatabaseTest.java, 2017/09/25 SangLT
 */
package testjavacore_bai2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Class test CDDatabase
 * 
 * @author SangLT
 *
 */
public class CDDatabaseTest {

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Print start message
		String startedMsg = "----------CRUD----------\n" + " 1. Insert CD\n" + " 2. Remove CD\n" + " 3. Find by title\n"
				+ " 4. Find by artist\n";
		System.out.println(startedMsg);
		do {
			try {
				// Create object reads input from user
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				// Message: Please select function
				System.out.print("Mời chọn chức năng: ");
				// Get input and handle
				String inputVal = br.readLine().trim();
				switch (inputVal) {
				case "1": // Function insertCD
					// Get inserted input infomations
					System.out.print("Hãy nhập artist cho đối tượng cần nhập: ");
					String insertedArtist = getInput(br, "insert");
					System.out.print("Hãy nhập title cho đối tượng cần nhập: ");
					String insertedTitle = getInput(br, "insert");
					// Insert into database
					CDDatabase.insertCD(new CD(insertedArtist, insertedTitle));
					break;
				case "2": // Function removeCD
					// Get removed input infomations
					System.out.print("Hãy nhập artist của đối tượng cần xóa: ");
					String removedArtist = getInput(br, "other");
					System.out.print("Hãy nhập title của đối tượng cần xóa: ");
					String removedTitle = getInput(br, "other");
					// Remove from database
					CDDatabase.removeCD(new CD(removedArtist, removedTitle));
					break;
				case "3": // Function findByTitle
					// Get condition search
					System.out.print("Hãy nhập title của đối tượng cần tìm: ");
					String titleSearch = getInput(br, "other");
					// Fetch record(s)
					ArrayList<CD> titleSearchRs = CDDatabase.findByTitle(titleSearch);
					// Print results
					int titleCount = 1;
					System.out.println("STT || Artist || Title");
					for (CD obj : titleSearchRs) {
						System.out.println(titleCount + " || " + obj.getArtist() + " || " + obj.getTitle());
						titleCount++;
					}
					break;
				case "4": // Function findByArtist
					// Get condition search
					System.out.print("Hãy nhập artist của đối tượng cần tìm: ");
					String artistSearch = getInput(br, "other");
					// Fetch record(s)
					ArrayList<CD> artistSearchRs = CDDatabase.findByArtist(artistSearch);
					// Print results
					int artistCount = 1;
					System.out.println("STT || Artist || Title");
					for (CD obj : artistSearchRs) {
						System.out.println(artistCount + " || " + obj.getArtist() + " || " + obj.getTitle());
						artistCount++;
					}
					break;
				default: // Input value is not in {"1", "2", "3", "4"}
					System.out.println("Hãy nhập giá trị từ 1 đến 4.");
					break;
				}
			} catch (Exception e) {
				// Print inputting error
				System.out.println("Error input.");
			}
		} while (true);
	}

	@SuppressWarnings("finally")
	public static String getInput(BufferedReader br, String type) {
		// Message of get insert information
		String[] msgOfInsert = { "Không được nhập giá trị rỗng. Hãy nhập lại: ",
				"Không được nhập giá trị lớn hơn 255 kí tự. Hãy nhập lại: " };
		// Message of get non-insert information
		String[] msgOfOther = { "Không có giá trị rỗng trong cơ sở dữ liệu. Hãy nhập lại: ",
				"Không có giá trị lớn hơn 255 kí tự trong cơ sở dữ liệu. Hãy nhập lại: " };
		// Message
		String inputVal = "";
		try {
			inputVal = br.readLine().trim();
			// Check empty artist input
			int inputValLeng = inputVal.length();
			while (true) {
				if (inputValLeng == 0) {
					System.out.print((type == "insert") ? msgOfInsert[0] : msgOfOther[0]);
					inputVal = br.readLine().trim();
				} else if (inputValLeng > 255) {
					System.out.print((type == "insert") ? msgOfInsert[1] : msgOfOther[1]);
					inputVal = br.readLine().trim();
				} else {
					break;
				}
			}
		} catch (Exception e) {
			// Show error in inputting
			System.out.println("Input error!");
		} finally {
			return inputVal;
		}
	}

}
