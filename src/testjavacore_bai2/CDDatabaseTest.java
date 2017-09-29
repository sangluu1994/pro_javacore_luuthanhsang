/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * CDDatabaseTest.java, 25/09/2017 luuthanhsang
 */
package testjavacore_bai2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Class test CDDatabase
 * 
 * @author luuthanhsang
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
				+ " 4. Find by artist\n" + "'Exit' để kết thúc.";
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
					System.out.print("Hãy nhập [Tên nghệ sĩ]: ");
					String insertedArtist = getInput(br);
					System.out.print("Hãy nhập [Tác phẩm]: ");
					String insertedTitle = getInput(br);
					// Insert into database
					CDDatabase.insertCD(new CD(insertedArtist, insertedTitle));
					break;
				case "2": // Function removeCD
					// Get removed input infomations
					System.out.print("Hãy nhập tên nghệ sĩ của CD cần xóa: ");
					String removedArtist = br.readLine().trim();
					System.out.print("Hãy nhập tên tác phẩm của CD cần xóa: ");
					String removedTitle = br.readLine().trim();
					// Remove from database
					CDDatabase.removeCD(new CD(removedArtist, removedTitle));
					break;
				case "3": // Function findByTitle
					// Get condition search
					System.out.print("Hãy nhập tên tác phẩm cần tìm: ");
					String titleSearch = br.readLine().trim();
					// Fetch record(s)
					ArrayList<CD> titleSearchRs = CDDatabase.findByTitle(titleSearch);
					if (titleSearchRs == null) { // If connection error
						break;
					} else if (titleSearchRs.isEmpty()) { // If find no record
						System.out.println("Không tìm thấy bản ghi.");
						break;
					} else { // Print results
						showRecord(titleSearchRs);
						break;
					}
				case "4": // Function findByArtist
					// Get condition search
					System.out.print("Hãy nhập tên nghệ sĩ cần tìm: ");
					String artistSearch = br.readLine().trim();
					// Fetch record(s)
					ArrayList<CD> artistSearchRs = CDDatabase.findByArtist(artistSearch);
					if (artistSearchRs == null) { // If connection error
						break;
					} else if (artistSearchRs.isEmpty()) { // If find no record
						System.out.println("Không tìm thấy bản ghi.");
						break;
					} else { // Print results
						showRecord(artistSearchRs);
						break;
					}
				case "exit":
					return;
				default: // Input value is not in {"1", "2", "3", "4", "exit"}
					System.out.println("Hãy nhập giá trị từ 1 đến 4.");
					break;
				}
			} catch (Exception e) {
				// Print inputting error
				System.out.println("Error input.");
			}
		} while (true);
	}

	/**
	 * Method shows result records
	 * 
	 * @param resultObjs
	 */
	public static void showRecord(ArrayList<CD> resultObjs) {
		int objCount = 1;
		System.out.println("STT || Artist || Title");
		for (CD obj : resultObjs) {
			System.out.println(objCount + " || " + obj.getArtist() + " || " + obj.getTitle());
			objCount++;
		}
	}

	/**
	 * Method get input of insert function
	 * 
	 * @param br
	 * @param type
	 * @return inputVal
	 */
	@SuppressWarnings("finally")
	public static String getInput(BufferedReader br) {
		// Message of get insert information
		String[] msgOfInsert = { "Không được nhập giá trị rỗng. Hãy nhập lại: ",
				"Không được nhập giá trị lớn hơn 255 kí tự. Hãy nhập lại: " };
		// Initialize return result
		String inputVal = "";
		try {
			inputVal = br.readLine().trim();
			// Check empty artist input
			int inputValLeng = inputVal.length();
			while (true) {
				if (inputValLeng == 0) { // If inputVal is empty
					System.out.print(msgOfInsert[0]);
					inputVal = br.readLine().trim();
				} else if (inputValLeng > 255) { // If inputVal > 255 characters
					System.out.print(msgOfInsert[1]);
					inputVal = br.readLine().trim();
				} else {
					break;
				}
			}
		} catch (Exception e) {
			// Show error in inputting
			System.out.println("Input error!");
		} finally {
			// Return
			return inputVal;
		}
	}

}
