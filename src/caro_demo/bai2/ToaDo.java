/**
 * CoppyRight(C) 2017 Luvina
 * .java Jun 27, 2017 HUYDC
 */
package caro_demo.bai2;

/**
 *
 * @author Admin
 */
public class ToaDo {
	int x, y; // tọa độ của ô cờ

	/**
	 * hàm khởi tạo tọa độ
	 * 
	 * @param x
	 *            :hàng
	 * @param y
	 *            : cột
	 */
	public ToaDo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * hàm khỏi tạo không tham số
	 */
	public ToaDo() {
	}

	/**
	 * 
	 * @return tọa độ X
	 */
	public int getX() {
		return x;
	}

	/**
	 * set tọa độ hàng
	 * 
	 * @param x
	 *            tọa độ hàng
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * 
	 * @return tọa độ cột
	 */
	public int getY() {
		return y;
	}

	/**
	 * set tọa độ cột
	 * 
	 * @param y
	 *            tọa độ hàng
	 */
	public void setY(int y) {
		this.y = y;
	}

}
