//USING JFRAME
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TicTacToeGame extends JFrame {

	private JPanel contentPane;

	public static int count = 0;
	public static int player = 1;
	public static JLabel title = new JLabel("  TicTacToe - Player: X");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicTacToeGame frame = new TicTacToeGame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TicTacToeGame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 379, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton[][]board = new JButton[3][3];

		title.setForeground(Color.WHITE);
		title.setBackground(Color.BLACK);
		title.setOpaque(true);
		title.setFont(new Font("Tahoma", Font.BOLD, 18));
		title.setBounds(10, 10, 345, 38);
		contentPane.add(title);

		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = ((JButton) e.getSource()).getText();
				disableInput(input, board);
				boolean win = checkWin(board);
				if (win) {
					String play = Integer.toString(player);
					if (play.equals("1")) play="X";
					else play="O";
					title.setText("  PLAYER: " + play + " WINS!");
					//disables rest of the buttons so there can't be another win
					for (int c = 0; c < 3; c++) {
						for (int d = 0; d < 3; d++) {
							board[c][d].setEnabled(false);
						}
					}
				}	
				if (count==9 && !win) title.setText("  TIE!");
				//				System.out.println(player);
				player*=-1;
			}
		};

		//draws board
		int b = 66, g = 0;
		for (int c = 0; c < 3; c++) {
			int a =47;
			for (int d = 0; d < 3; d++) {
				String f = Integer.toString(g);
				board[c][d] = new JButton(f);
				board[c][d].setBounds(a, b, 85, 87);	
				contentPane.add(board[c][d]);
				board[c][d].addActionListener(listener);
				board[c][d].setBackground(Color.WHITE);
				board[c][d].setFont(new Font("Tahoma", Font.PLAIN, 47));
				board[c][d].setFocusPainted(false);

				//				board[c][d].setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.0f)));
				a+=95;
				g++;
			}
			b+=105;
		}
	}

	public static void disableInput(String input, JButton[][]board) {

		String play="";
		for (int c = 0; c < 3; c++) {
			for (int d = 0; d < 3; d++) {
				String s = board[c][d].getText();
				if (s.equals(input)) {
					play = Integer.toString(player);
					if (play.equals("1")) play="X";
					else play="O";
					board[c][d].setText(play);
					board[c][d].setEnabled(false);

					if (play.equals("X")) {
						board[c][d].setBackground(Color.CYAN);
					}
					else board[c][d].setBackground(Color.PINK);


					count++;
					break;
				}
			}

			play = Integer.toString(player*-1);
			if (play.equals("1")) play="X";
			else play="O";
			title.setText("  TicTacToe - Player: " + play);
		}
		//			System.out.println(count);
	}

	public static boolean checkWin(JButton[][]board) {

		String check = "";


		//horizontal wins
		for (int c = 0; c < 3; c++) {
			check="";
			for (int d = 0; d < 3; d++) {
				check+=board[c][d].getText();
			}
			//				System.out.println(check);
			if (check.equals("XXX") || check.equals("OOO")) return true;
		}

		//vertical wins
		for (int c = 0; c < 3; c++) {
			check="";
			for (int d = 0; d < 3; d++) {
				check+=board[d][c].getText();
			}
			//				System.out.println(check);
			if (check.equals("XXX") || check.equals("OOO")) return true;
		}

		//diagonal wins (left to right)
		check="";
		for (int c = 0; c < 3; c++) {
			check+=board[c][c].getText();
		}
//		System.out.println(check);
		if (check.equals("XXX") || check.equals("OOO")) return true;
		
		//diagonal wins (right)
		check="";
		int d = 2;
		for (int c = 0; c < 3; c++) {
				check+=board[c][d].getText();
				d--;
			}
//		System.out.println(check);
		if (check.equals("XXX") || check.equals("OOO")) return true;
		
		return false;
	}
}

