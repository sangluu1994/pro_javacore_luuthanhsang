package caro_demo;
public class TestCaro {
	public static void main(String[] args) {
		System.out.println("Game Cờ Caro");
		TaoBanCo banco = new TaoBanCo();
		banco.createBanCo();
		HandlePlay handlePlay = new HandlePlay();
		int[] user = new int[2];
		int[] may = new int[2];
		int count = 0;
		do {
			user = handlePlay.getUserClick();
			handlePlay.checkUserClick(user[0], user[1]);
			count++;
			banco.drawBoard();
			if(banco.checkWin(user[0], user[1])) {
				System.out.println("bạn win");
				break;
			}
			may = handlePlay.getComputerClick();
			banco.drawBoard();
			if(banco.checkWin(may[0], may[1])) {
				System.out.println("máy win");
				break;
			}
			if(count == 200) {
				System.out.println("hòa");
				break;
			}
		} while (count < 200);
	}
}
