
import java.util.Scanner;

public class TakingYak {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // to take input from console

        String gridCapacity = scanner.nextLine(); // read line from console

        int maxColumn = Integer.parseInt(gridCapacity.split(" ")[0]); // parse grid max column
        int maxRow = Integer.parseInt(gridCapacity.split(" ")[1]); // parse grid max row

        String robotStartPosition = scanner.nextLine(); // read line from console

        int robotColumnPosition = Integer.parseInt(robotStartPosition.split(" ")[0]); // ROBOT initial column position
        int robotRowPosition = Integer.parseInt(robotStartPosition.split(" ")[1]); // ROBOT initial row position
        char robotDirection = robotStartPosition.split(" ")[2].charAt(0); // ROBOT initial direction

        String robotInstruction = scanner.nextLine(); // read instruction for ROBOT from console

        /*
        ROBOT traversing from start position to the destination position
         */
        for (int i = 0; i < robotInstruction.length(); i++) {
            String[] robotNewPosition = robotNewPosition(robotColumnPosition, robotRowPosition, robotDirection, robotInstruction.charAt(i));
            robotColumnPosition = Integer.parseInt(robotNewPosition[0]);
            robotRowPosition = Integer.parseInt(robotNewPosition[1]);
            robotDirection = robotNewPosition[2].charAt(0);

            if (!((robotColumnPosition >= 0 && robotColumnPosition <= maxColumn)
                    && (robotRowPosition >= 0 && robotRowPosition <= maxRow))) {
                System.out.println("You have given wrong direction to the ROBOT. Or robot went out to the grid.");
                return;
            }
        }

        System.out.println(robotColumnPosition + " " + robotRowPosition + " " + robotDirection);
    }

    /*
    ROBOT new position after one instruction
     */
    private static String[] robotNewPosition(int column, int row, char oldDirection, char newInstruction) {
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
                row++;
            } else if (oldDirection == direction[1]) {
                row--;
            } else if (oldDirection == direction[2]) {
                column++;
            } else if (oldDirection == direction[3]) {
                column--;
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
        robotNewPosition[0] = String.valueOf(column);
        robotNewPosition[1] = String.valueOf(row);
        robotNewPosition[2] = String.valueOf(oldDirection);

        return robotNewPosition;
    }
}
