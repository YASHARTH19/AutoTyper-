import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class AutoTyper {
    public static void main(String[] args) throws AWTException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Robot robot = new Robot();

        // Get user input
        System.out.print("Enter the text you want to auto-type: ");
        String text = scanner.nextLine();
        System.out.print("Enter typing speed (milliseconds per character): ");
        int speed = scanner.nextInt();

        System.out.println("Switch to the target application within 5 seconds...");
        Thread.sleep(5000); // Wait for user to switch apps

        // Start typing the text
        for (char ch : text.toCharArray()) {
            typeCharacter(robot, ch);
            Thread.sleep(speed); // Delay between each character
        }

        System.out.println("\nTyping completed successfully!");
        scanner.close();
    }

    // Function to handle typing characters, including uppercase and special symbols
    private static void typeCharacter(Robot robot, char ch) {
        boolean shiftNeeded = false;

        // Mapping special characters that require SHIFT key
        switch (ch) {
            case 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
                 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' -> {
                shiftNeeded = true;
                ch = Character.toLowerCase(ch); // Convert to lowercase for key press
            }
            case '!' -> { shiftNeeded = true; ch = '1'; }
            case '@' -> { shiftNeeded = true; ch = '2'; }
            case '#' -> { shiftNeeded = true; ch = '3'; }
            case '$' -> { shiftNeeded = true; ch = '4'; }
            case '%' -> { shiftNeeded = true; ch = '5'; }
            case '^' -> { shiftNeeded = true; ch = '6'; }
            case '&' -> { shiftNeeded = true; ch = '7'; }
            case '*' -> { shiftNeeded = true; ch = '8'; }
            case '(' -> { shiftNeeded = true; ch = '9'; }
            case ')' -> { shiftNeeded = true; ch = '0'; }
            case '_' -> { shiftNeeded = true; ch = '-'; }
            case '+' -> { shiftNeeded = true; ch = '='; }
            case '{' -> { shiftNeeded = true; ch = '['; }
            case '}' -> { shiftNeeded = true; ch = ']'; }
            case '|' -> { shiftNeeded = true; ch = '\\'; }
            case ':' -> { shiftNeeded = true; ch = ';'; }
            case '"' -> { shiftNeeded = true; ch = '\''; }
            case '<' -> { shiftNeeded = true; ch = ','; }
            case '>' -> { shiftNeeded = true; ch = '.'; }
            case '?' -> { shiftNeeded = true; ch = '/'; }
        }

        // Press Shift key if required
        if (shiftNeeded) {
            robot.keyPress(KeyEvent.VK_SHIFT);
        }

        // Get keycode for the character
        int keyCode = KeyEvent.getExtendedKeyCodeForChar(ch);
        if (keyCode != KeyEvent.VK_UNDEFINED) {
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
        }

        // Release Shift key if it was used
        if (shiftNeeded) {
            robot.keyRelease(KeyEvent.VK_SHIFT);
        }
    }
}
