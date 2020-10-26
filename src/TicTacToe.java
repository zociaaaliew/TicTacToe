import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
	static boolean endGame = false;
	static String player1, player2;
	static ArrayList<Integer> playerOnePos = new ArrayList<Integer>();
	static ArrayList<Integer> playerTwoPos = new ArrayList<Integer>();
	static char[][] gameBoard = { { '1', '|', '2', '|', '3' }, { '-', '-', '-', '-', '-' }, { '4', '|', '5', '|', '6' },
			{ '-', '-', '-', '-', '-' }, { '7', '|', '8', '|', '9' } };

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		requestPlayersName();
		printGameBoard(gameBoard);

		while (endGame != true) {

			try {
				playerOneTurn();
			} catch (Exception e) {
				System.out.println("You have entred an invalid box.");

				try {
					playerOneTurn();
				} catch (Exception miss) {
					System.out
							.println("You have entred an invalid box again. " + player1 + ", You will miss a turn. \n");
				}
			}

			if (endGame != true) {
				try {
					playerTwoTurn();
				} catch (Exception e) {
					System.out.println("You have entred an invalid box.");

					try {
						playerTwoTurn();
					} catch (Exception miss) {
						System.out.println(
								"You have entred an invalid box again. " + player2 + ", You will miss a turn. \n");
					}
				}

			}

		}
	}

	public static void printGameBoard(char[][] gameBoard) {

		for (char[] row : gameBoard) {
			for (char c : row) {
				System.out.print(c);
			}
			
			System.out.println();
		}
		
		System.out.println();
	}
	
	public static void requestPlayersName() {
		
		Scanner scanNames = new Scanner(System.in);
		System.out.print("Enter name for Player 1:");
		if (scanNames.hasNextLine()) {
			player1 = scanNames.nextLine();
		}

		System.out.print("Enter name for Player 2:");
		if (scanNames.hasNextLine()) {
			player2 = scanNames.nextLine();
		}
	}

	public static void playerOneTurn() {

		Scanner scanMove1 = new Scanner(System.in);
		System.out.print(player1 + ", choose a box to place an your move:");
		int move1 = scanMove1.nextInt();

		while (playerTwoPos.contains(move1) || playerTwoPos.contains(playerOnePos) || playerOnePos.contains(move1)) {
			System.out.println("The box is taken. " + player1 + ", please enter another box number:");
			move1 = scanMove1.nextInt();
		}

		updateMoves(gameBoard, move1, player1);
		printGameBoard(gameBoard);

		String playerOneWon = checkWinner();
		if (playerOneWon.length() > 0) {
			System.out.println(playerOneWon);
		}
	}

	public static void playerTwoTurn() {

		Scanner scanMove2 = new Scanner(System.in);
		System.out.print(player2 + ", choose a box to place an your move:");
		int move2 = scanMove2.nextInt();

		while (playerOnePos.contains(move2) || playerOnePos.contains(playerTwoPos) || playerTwoPos.contains(move2)) {
			System.out.println("The box is taken. Please enter another box number:");
			move2 = scanMove2.nextInt();
		}
		updateMoves(gameBoard, move2, player2);
		printGameBoard(gameBoard);

		String playerTwoWon = checkWinner();
		if (playerTwoWon.length() > 0) {
			System.out.println(playerTwoWon);
		}
	}

	public static void updateMoves(char[][] gameBoard, int move, String player) {

		char piece = ' ';

		if (player.equals(player1)) {
			piece = 'X';
			playerOnePos.add(move);
		} else if (player.equals(player2)) {
			piece = 'O';
			playerTwoPos.add(move);
		}

		switch (move) {
		case 1:
			gameBoard[0][0] = piece;
			break;

		case 2:
			gameBoard[0][2] = piece;
			break;

		case 3:
			gameBoard[0][4] = piece;
			break;

		case 4:
			gameBoard[2][0] = piece;
			break;

		case 5:
			gameBoard[2][2] = piece;
			break;

		case 6:
			gameBoard[2][4] = piece;
			break;

		case 7:
			gameBoard[4][0] = piece;
			break;

		case 8:
			gameBoard[4][2] = piece;
			break;

		case 9:
			gameBoard[4][4] = piece;
			break;
		}
	}

	public static String checkWinner() {

		List<Integer> row1 = Arrays.asList(1, 2, 3);
		List<Integer> row2 = Arrays.asList(4, 5, 6);
		List<Integer> row3 = Arrays.asList(7, 8, 9);
		List<Integer> col1 = Arrays.asList(1, 4, 7);
		List<Integer> col2 = Arrays.asList(2, 5, 8);
		List<Integer> col3 = Arrays.asList(3, 6, 9);
		List<Integer> diag1 = Arrays.asList(1, 5, 9);
		List<Integer> diag2 = Arrays.asList(3, 5, 7);

		List<List> winningConditions = new ArrayList<List>();
		winningConditions.add(row1);
		winningConditions.add(row2);
		winningConditions.add(row3);
		winningConditions.add(col1);
		winningConditions.add(col2);
		winningConditions.add(col3);
		winningConditions.add(diag1);
		winningConditions.add(diag2);

		for (List l : winningConditions) {
			if (playerOnePos.containsAll(l)) {
				endGame = true;
				String playerWon = ("Congratulations " + player1 + "! You have won.");
				return (playerWon);
			} else if (playerTwoPos.containsAll(l)) {
				endGame = true;
				String playerWon = ("Congratulations " + player2 + "! You have won.");
				return (playerWon);
			} else if (playerOnePos.size() + playerTwoPos.size() == 9) {
				endGame = true;
				String playerTie = ("It's a tie!");
				return (playerTie);
			}
		}

		return "";
	}

}
