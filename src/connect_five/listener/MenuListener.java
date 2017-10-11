/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * MenuListener.java, 11/10/2017 SangLT
 */
package connect_five.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import connect_five.config.CommonConfig;
import connect_five.logic.CaroLogic;
import connect_five.view.ChessBoard;

/**
 * Class xử lí các sự kiện khi click vào menu
 * 
 * @author Luu Thanh Sang
 *
 */
public class MenuListener implements ActionListener {

	/**
	 * Hàm xử lí sự kiện trên menu
	 * 
	 * @param e : đối tượng sự kiện trên menu
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String actionVal = e.getActionCommand(); // Lấy giá trị của button được chọn
			
			// Kiểm tra giá trị button được chọn:
			if (CommonConfig.COMPUTER_BUTTON_NAME.equals(actionVal)) { // Nếu click vào nút máy đánh trước:
				CaroLogic.getComputerFirstMode(); // Gọi chế độ máy chơi trước
				
			} else { // Nếu click vào nút người đánh trước:
				CaroLogic.getHumanFirstMode(); // Gọi chế độ người chơi trước
				
			} 
		} catch (Exception exp) {
			ChessBoard.showMessage(CommonConfig.CRASH_MSG); // Hiện thông báo lỗi ra màn hình nếu gặp lỗi
		}
	}

}
