import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpecializedCastle {

    private static void getAllPaths(int[][] board, int x, int y, int specialCastleX, int specialCastleY,
                                    List<String> currPath, List<List<String>> allPaths, int direction) {
        if (x < 0 || y < 0 || x >= 9 || y >= 9 || board[y][x] == -1) {
            return;
        }

        if (x == specialCastleX && y == specialCastleY && currPath.size() > 1) {
            currPath.add("Go home to (" + (x + 1) + "," + (y + 1) + ")");
            allPaths.add(new ArrayList<>(currPath));
            return;
        }

        int newX = x, newY = y;

        switch (direction) {
            case 0:
                newY++;
                break;
            case 1:
                newX++;
                break;
            case 2:
                newY--;
                break;
            case 3:
                newX--;
                break;
        }

        if (newX >= 0 && newX < 9 && newY >= 0 && newY < 9) {
            if (board[newY][newX] == 1) {
                board[newY][newX] = -1;
                currPath.add("Kill at (" + (newX + 1) + "," + (newY + 1) + "), then turn left");
                getAllPaths(board, newX, newY, specialCastleX, specialCastleY, currPath, allPaths, (direction + 3) % 4);
                currPath.remove(currPath.size() - 1);
                board[newY][newX] = 1;

                int jumpX = x, jumpY = y;
                while (true) {
                    switch (direction) {
                        case 0:
                            jumpY++;
                            break;
                        case 1:
                            jumpX++;
                            break;
                        case 2:
                            jumpY--;
                            break;
                        case 3:
                            jumpX--;
                            break;
                    }
                    if (jumpX < 0 || jumpX >= 9 || jumpY < 0 || jumpY >= 9 || board[jumpY][jumpX] == 1) {
                        break;
                    }
                }
                if (jumpX >= 0 && jumpX < 9 && jumpY >= 0 && jumpY < 9) {
                    currPath.add("Jump over to (" + (jumpX + 1) + "," + (jumpY + 1) + ")");
                    getAllPaths(board, jumpX, jumpY, specialCastleX, specialCastleY, currPath, allPaths, direction);
                    currPath.remove(currPath.size() - 1);
                }
            } else {
                currPath.add("Move to (" + (newX + 1) + "," + (newY + 1) + ")");
                getAllPaths(board, newX, newY, specialCastleX, specialCastleY, currPath, allPaths, direction);
                currPath.remove(currPath.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] board = new int[9][9];

        System.out.print("Enter the number of soldiers: ");
        int noOfSoldiers = scanner.nextInt();
        scanner.nextLine(); // Consume the remaining newline

        if (noOfSoldiers > 9 * 9) {
            System.out.println("Invalid number of soldiers");
            return;
        }

        for (int i = 1; i <= noOfSoldiers; i++) {
            System.out.print("Enter the position of soldier " + i + " (format: x y): ");
            String position = scanner.nextLine();
            String[] parts = position.split(",");
            int x = Integer.parseInt(parts[0].trim());
            int y = Integer.parseInt(parts[1].trim());
            board[y - 1][x - 1] = 1;
        }

        System.out.print("Enter the coordinates for your special castle (format: x y): ");
        String castlePosition = scanner.nextLine();
        String[] castleParts = castlePosition.split(",");
        int specialCastleX = Integer.parseInt(castleParts[0].trim());
        int specialCastleY = Integer.parseInt(castleParts[1].trim());
        specialCastleY--;
        specialCastleX--;
        board[specialCastleY][specialCastleX] = 2;

        List<List<String>> allPaths = new ArrayList<>();
        List<String> currPath = new ArrayList<>();

        getAllPaths(board, specialCastleX, specialCastleY, specialCastleX, specialCastleY, currPath, allPaths, 0);

        System.out.println("Found " + allPaths.size() + " paths.");
        for (int i = 0; i < allPaths.size(); ++i) {
            System.out.println("Path " + (i + 1) + ":");
            System.out.println("=======");
            for (String step : allPaths.get(i)) {
                System.out.println(step);
            }
            System.out.println();
        }

        scanner.close();
    }
}
