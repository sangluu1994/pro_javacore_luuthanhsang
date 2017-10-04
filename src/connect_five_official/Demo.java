/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * Demo.java, 05/10/2017 sanglt
 */
package connect_five_official;

/**
 * Class Demo
 * 
 * @author sanglt
 *
 */
public class Demo {

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ChessBoard chessBoard = new ChessBoard();
		chessBoard.drawChessBoard();
//		int chessPositionsLeng = chessBoard.chessPositions.length;
//		for (int i = 0; i < chessPositionsLeng; i++) {
//			for (int j = 0; j < 5; j++) {
//				for (int k = 0; k < 5; k++) {
//					System.out.print(chessBoard.chessPositions[i][j][k]);
//					if (k == 4) {
//						System.out.print("\n");
//					}
//				}
//			}
//		}
	}

}
