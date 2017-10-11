/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * ChessBoardListener.java, 11/10/2017 SangLT
 */
package connect_five.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import connect_five.config.CommonConfig;
import connect_five.entity.Square;
import connect_five.logic.CaroLogic;
import connect_five.view.ChessBoard;

/**
 * Class xử lí các sự kiện khi click vào các ô cờ
 * 
 * @author Luu Thanh Sang
 *
 */
public class ChessBoardListener implements ActionListener{
	
	/** 
	 * Hàm xử lí các sự kiện trên bàn cờ
	 * 
	 * @param e : đối tượng sự kiện trên bàn cờ
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Square square = (Square) e.getSource(); // Lấy đối tượng ô cờ được đánh
			
			// Kiểm tra giá trị button ô cờ được chọn:
			if ("".equals(square.getValue()) && ChessBoard.humanTurn){ // Nếu click vào các ô cờ trống trên bàn cờ và đến lượt người đánh:
//					System.out.println(square.getXPosition() + " - " +  square.getYPosition());
				// Đánh vào ô cờ vừa chọn: 
				CaroLogic.fillSquare(CommonConfig.O_CHAR, square);

				// Kiểm tra kết thúc: thắng, hòa
				if (CaroLogic.checkEnd(square)) { // Nếu rơi vào trường hợp thắng hoặc hòa
					CaroLogic.getNewGame(); // Tạo ván chơi mới
				} else { // Nếu chưa thắng hay hòa
					CaroLogic.getComputerTurn(ChessBoard.chessPositionList); // Gọi hàm máy đánh
				}
				
			}
		} catch (Exception exp) {
//			System.out.println(e);
			ChessBoard.showMessage(CommonConfig.CRASH_MSG); // Hiện thông báo lỗi ra màn hình nếu gặp lỗi
		}
	}
	
}
