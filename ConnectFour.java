import java.util.*;

public class ConnectFour {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Board a = new Board();
		String[][] board = a.createBoard();
		boolean notWin = true;
		boolean notTie = true;
		boolean checkBoard = true;
		int player = 1;

		while (notWin) {
			a.getBoard();
			System.out.println("Player: " + player + "'s turn.");
			System.out.print("Select a number: ");
			String tempNum = in.nextLine();
			boolean checkNum = isNumber(tempNum);
			int num;
			if (checkNum) {
				num = Integer.parseInt(tempNum);
			} else
				continue;
			checkBoard = changeBoard(player, board, num, checkBoard);

			notWin = checkWin(board);
			notTie = checkTie(board);

			if (notWin && checkBoard) {
				player *= -1;
			}

			if (!notTie)
				break;
		}
		a.getBoard();
		if (!notWin)
			System.out.println("Player " + player + " wins!");
		if (!notTie)
			System.out.println("TIE!");

		in.close();
	}

	public static boolean isNumber(String tempNum) {
		try {
			int num = Integer.parseInt(tempNum);
			if (num < 7 && num > -1)
				return true;
		} catch (NumberFormatException nfe) {
			System.out.println("Not a valid number.");
		}
		return false;
	}

	public static boolean changeBoard(int player, String[][] board, int num, boolean checkBoard) {
		String sign = "";
		if (player == 1)
			sign = "O";
		else
			sign = "X";

		for (int c = 5; c >= 0; c--) {
			if (board[num][c].equals("O") || board[num][c].equals("X")) {
				// continues
			} else {
				board[num][c] = sign;
				break;
			}
			if (c == 0)
				return false;
		}
		return true;
	}

	public static boolean checkWin(String[][] board) {
		String check = "";

		// horizontal wins
		for (int c = 0; c < 6; c++) {
			check = "";
			for (int d = 0; d < 7; d++) {
				check += board[d][c];
			}
//								System.out.println(check);
			if (check.contains("OOOO") || check.contains("XXXX"))
				return false;
		}

		// vertical wins
		for (int c = 0; c < 7; c++) {
			check = "";
			for (int d = 0; d < 6; d++) {
				check += board[c][d];
			}
//								System.out.println(check);
			if (check.contains("OOOO") || check.contains("XXXX"))
				return false;
		}

		// diagonal wins left to right
		for (int c = 0; c < 7; c++) {
			for (int f = 0; f < 6; f++) {
				int e = 0;
				check = "";
				for (int d = 0; d < 6; d++) {
					if (c + e < 7 && d + f < 6) {
						check += board[c + e][d + f];
						e++;
					} else {
						break;
					}
				}
//						System.out.println(check);
				if (check.contains("OOOO") || check.contains("XXXX"))
					return false;
			}
		}

		// diagonal wins right to left
		for (int c = 0; c < 7; c++) {
			for (int f = 0; f < 6; f++) {
				int e = 0;
				check = "";
				for (int d = 0; d < 6; d++) {
					if ((c - e < 7 && c - e >= 0) && d + f < 6) {
						check += board[c - e][d + f];
						e++;
					} else {
						break;
					}
				}
//						System.out.println(check);
				if (check.contains("OOOO") || check.contains("XXXX"))
					return false;
			}
		}

		return true;
	}

	public static boolean checkTie(String[][] board) {
		int count = 0;

		for (int c = 0; c < 6; c++) {
			String check = "";
			for (int d = 0; d < 7; d++) {
				check += board[d][c];
			}
//			System.out.println(check);
			if (!check.contains("-"))
				count++;
		}
//		System.out.println(count);
		if (count == 6) {
			return false;
		} else {
			return true;
		}
	}
}
