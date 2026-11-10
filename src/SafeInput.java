import java.util.Scanner;

public class SafeInput {

    public static int getRangedInt(Scanner console, String prompt, int low, int high) {
        int value = 0;
        boolean done = false;
        do {
            System.out.print(prompt + " [" + low + "-" + high + "]: ");
            String line = console.nextLine().trim();
            try {
                value = Integer.parseInt(line);
                if (value >= low && value <= high) {
                    done = true;
                } else {
                    System.out.println("Out of range. Enter a number from " + low + " to " + high + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: \"" + line + "\". Enter a whole number.");
            }
        } while (!done);
        return value;
    }

    public static boolean getYNConfirm(Scanner console, String prompt) {
        while (true) {
            System.out.print(prompt + " [Y/N]: ");
            String line = console.nextLine().trim();
            if (line.equalsIgnoreCase("Y")) {
                return true;
            } else if (line.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println("Please enter Y or N.");
        }
    }
}