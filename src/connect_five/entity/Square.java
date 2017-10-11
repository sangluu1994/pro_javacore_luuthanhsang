/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * Square.java, 11/10/2017 SangLT
 */
package connect_five.entity;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Class các đối tượng ô cờ
 * 
 * @author Luu Thanh Sang
 *
 */
public class Square extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int xPosition; // Hoành độ ô cờ
	private int yPosition; // Tung độ ô cờ
	private String value; // Giá trị quân cờ trên ô cờ: rỗng | X | O
	
	/**
	 * Constructor: Hàm khởi tạo với thiết lập icon cho ô cờ
	 * 
	 * @param icon : icon đại diện cho ô cờ
	 */
	public Square(ImageIcon icon) {
		setIcon(icon); 
	}
	
	/**
	 * Constructor: Hàm khởi tạo với thiết lập tọa độ
	 * 
	 * @param xPosition : hoành độ ô cờ
	 * @param yPosition : tung độ ô cờ
	 */
	public Square(int xPosition, int yPosition) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	/**
	 * Constructor: Hàm khởi tạo mặc định
	 */
	public Square() {
	}
	
	/**
	 * Getter: lấy hoành độ ô cờ
	 * 
	 * @return xPosition : hoành độ ô cờ
	 */
	public int getXPosition() {
		return xPosition; 
	}
	
	/**
	 * Getter: lấy tung độ ô cờ
	 * 
	 * @return yPosition : tung độ ô cờ
	 */
	public int getYPosition() {
		return yPosition;
	}
	
	/**
	 * Getter: lấy giá trị quân cờ trên ô cờ
	 * 
	 * @return value : giá trị quân cờ (rỗng | X | O)
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Setter: thiết lập hoành độ ô cờ
	 * 
	 * @param xPosition : hoành độ ô cờ
	 */
	public void setXPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	
	/**
	 * Setter: thiết lập tung độ ô cờ
	 * 
	 * @param yPosition : tung độ ô cờ
	 */
	public void setYPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	
	/**
	 * Setter: thiết lập giá trị quân cờ
	 * 
	 * @param value : giá trị quân cờ (rỗng | X | O)
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
}
