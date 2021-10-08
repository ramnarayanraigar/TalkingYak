
import java.util.Scanner;

public class TakingYak {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // to take input from console

        String gridCapacity = scanner.nextLine(); // read line from console

        int maxColumn = Integer.parseInt(gridCapacity.split(" ")[0]); // parse grid max column
        int maxRow = Integer.parseInt(gridCapacity.split(" ")[1]); // parse grid max row

        String robotStartPosition = scanner.nextLine(); // read line from console

        int x = Integer.parseInt(robotStartPosition.split(" ")[0]); // ROBOT initial x coordinate position
        int y = Integer.parseInt(robotStartPosition.split(" ")[1]); // ROBOT initial y coordinate position
        char robotDirection = robotStartPosition.split(" ")[2].charAt(0); // ROBOT initial direction

        String robotInstruction = scanner.nextLine(); // read instruction for ROBOT from console

        /*
        ROBOT traversing from start position to the destination position
         */
        for (int i = 0; i < robotInstruction.length(); i++) {
            String[] robotNewPosition = robotNewPosition(x, y, robotDirection, robotInstruction.charAt(i));
            x = Integer.parseInt(robotNewPosition[0]);
            y = Integer.parseInt(robotNewPosition[1]);
            robotDirection = robotNewPosition[2].charAt(0);

            if (!((x >= 0 && x <= maxColumn)
                    && (y >= 0 && y <= maxRow))) {
                System.out.println("You have given wrong direction to the ROBOT. Or robot went out to the grid.");
                return;
            }
        }

        System.out.println(x + " " + y + " " + robotDirection);
    }

    /*
    ROBOT new position after one instruction
     */
    private static String[] robotNewPosition(int x, int y, char oldDirection, char newInstruction) {
        /*
        N => North,
        S => South,
        E => East,
        W => West
         */
        char[] direction = {'N', 'S', 'E', 'W'};
        /*
        L => Left 90 degree rotation to the current position of ROBOT
        R => Right 90 degree rotation to the current position of ROBOT
        M => One grid movement in current direction of ROBOT
         */
        char[] instruction = {'L', 'R', 'M'};

        if (newInstruction == instruction[2]) {
            if (oldDirection == direction[0]) {
                y++;
            } else if (oldDirection == direction[1]) {
                y--;
            } else if (oldDirection == direction[2]) {
                x++;
            } else if (oldDirection == direction[3]) {
                x--;
            }

        } else if (newInstruction == instruction[0]) {
            if (oldDirection == direction[0]) {
                oldDirection = direction[3];
            } else if (oldDirection == direction[1]) {
                oldDirection = direction[2];
            } else if (oldDirection == direction[2]) {
                oldDirection = direction[0];
            } else if (oldDirection == direction[3]) {
                oldDirection = direction[1];
            }
        } else if (newInstruction == instruction[1]) {
            if (oldDirection == direction[0]) {
                oldDirection = direction[2];
            } else if (oldDirection == direction[1]) {
                oldDirection = direction[3];
            } else if (oldDirection == direction[2]) {
                oldDirection = direction[1];
            } else if (oldDirection == direction[3]) {
                oldDirection = direction[0];
            }
        }

        String[] robotNewPosition = new String[3];
        robotNewPosition[0] = String.valueOf(x);
        robotNewPosition[1] = String.valueOf(y);
        robotNewPosition[2] = String.valueOf(oldDirection);

        return robotNewPosition;
    }
}
