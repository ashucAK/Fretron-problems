import java.util.*;

public class FlightPathDrawer {
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Flight {
        List<Point> path;
        Flight() {
            path = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        List<Flight> flights = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Input flight paths
        for (int i = 1; i <= 3; i++) {
            System.out.println("Enter coordinates for Flight " + i + " (x,y format):");
            Flight flight = new Flight();
            String input = scanner.nextLine();
            String[] coordinates = input.split("\\s+");
            for (String coord : coordinates) {
                String[] xy = coord.split(",");
                flight.path.add(new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1])));
            }
            flights.add(flight);
        }

        // Draw flight paths
        int maxX = 0, maxY = 0;
        for (Flight flight : flights) {
            for (Point p : flight.path) {
                maxX = Math.max(maxX, p.x);
                maxY = Math.max(maxY, p.y);
            }
        }

        char[][] grid = new char[maxY + 1][maxX + 1];
        for (char[] row : grid) {
            Arrays.fill(row, '.');
        }

        for (int i = 0; i < flights.size(); i++) {
            Flight flight = flights.get(i);
            char flightSymbol = (char) ('1' + i); 
            for (int j = 0; j < flight.path.size() - 1; j++) {
                Point start = flight.path.get(j);
                Point end = flight.path.get(j + 1);
                drawLine(grid, start, end, flightSymbol);
            }
        }

        // Print the grid
        for (int y = maxY; y >= 0; y--) {
            for (int x = 0; x <= maxX; x++) {
                System.out.print(grid[y][x] + " ");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void drawLine(char[][] grid, Point start, Point end, char symbol) {
        int dx = Math.abs(end.x - start.x);
        int dy = Math.abs(end.y - start.y);
        int sx = start.x < end.x ? 1 : -1;
        int sy = start.y < end.y ? 1 : -1;
        int err = dx - dy;

        while (true) {
            grid[start.y][start.x] = symbol;
            if (start.x == end.x && start.y == end.y) break;
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                start.x += sx;
            }
            if (e2 < dx) {
                err += dx;
                start.y += sy;
            }
        }
    }
}