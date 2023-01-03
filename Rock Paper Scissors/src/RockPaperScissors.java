import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Rock Paper Scissors
 * </p>
 * Play rock paper scissors with a computer!
 *
 * @author Ayo Arulogun
 *
 * @version January 3, 2023
 */
public class RockPaperScissors {
    public static final String RESULTS = "You threw %s. Computer threw %s.\n";


    public static void main(String[] args) throws InputMismatchException {
        Scanner sc = new Scanner(System.in);

        int wins = 0;
        int games = 0;
        String playerChoice = "";
        String compChoice = "";

        do {
            System.out.println("Do you want to play rock, paper, scissors?\n1. Yes\n2. No");
            try {
                int play = sc.nextInt();
                if (play == 1) {
                    System.out.println("Rock, paper, or scissors? 1 for rock, 2 for paper, 3 for scissors.");
                    int choice = sc.nextInt();
                    int comp = (int) (Math.random() * 3) + 1;

                    switch (choice) { //Change based on player's pick
                        case 1 -> playerChoice = "ROCK";
                        case 2 -> playerChoice = "PAPER";
                        case 3 -> playerChoice = "SCISSORS";
                    }
                    switch (comp) { //Change based on computer's pick
                        case 1 -> compChoice = "ROCK";
                        case 2 -> compChoice = "PAPER";
                        case 3 -> compChoice = "SCISSORS";
                    }
                    System.out.printf(RESULTS, playerChoice, compChoice);

                    if (choice == comp) {
                        System.out.println("It's a tie!");
                    } else if ((choice == 1 && comp != 2) || (choice == 2 && comp != 3) || (choice == 3 && comp != 1)) {
                        wins++;
                        System.out.println("You won :)");
                    } else {
                        System.out.println("You lost :(");
                    }
                    games++;
                } else if (play == 2) {
                    if (wins != 0)
                        System.out.printf("You won %d/%d games this session. I had fun playing!", wins, games);
                    else
                        System.out.print("Maybe we can play some other time!");
                    break;
                } else {
                    System.out.println("Invalid option!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid option. Please enter a valid number.");
                sc.reset();
            }

        } while (true);
    }
}