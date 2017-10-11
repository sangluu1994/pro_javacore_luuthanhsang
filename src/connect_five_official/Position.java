/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * Position.java, 05/10/2017 sanglt
 */
package connect_five_official;

/**
 * Class tạo đối tượng tọa độ ô cờ
 * 
 * @author sanglt
 *
 */
public class Position {
	int x; // hoành độ của ô cờ
	int y; // tung độ của ô cờ
	
	/**
	 * Constructor: hàm khởi tạo, gán tọa độ cho ô cờ
	 * @param x : hoành độ
	 * @param y : tung độ
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Constructor
	 */
	public Position() {
		
	}
	
	/**
	 * Getter: lấy hoành độ
	 * @return x : hoành độ
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Getter: lấy tung độ
	 * @return y : tung độ
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Setter: thiết lập hoành độ
	 * @param x : hoành độ
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Setter: thiết lập tung độ
	 * @param y : tung độ
	 */
	public void setY(int y) {
		this.y = y;
	}
}

