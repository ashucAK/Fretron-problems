import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppleDistribution {

    // Distribution of apples among shares
    private static void distributeApple(List<List<Integer>> appleQuantity, List<Integer> appleWeight, List<Integer> shares, List<Integer> sharesWeight) {
        for (int i = 0; i < appleWeight.size(); i++) {
            int minIndex = 0;
            for (int j = 1; j < shares.size(); j++) {
                if (sharesWeight.get(j) < sharesWeight.get(minIndex)) {
                    minIndex = j;
                }
            }
            sharesWeight.set(minIndex, sharesWeight.get(minIndex) + appleWeight.get(i));
            appleQuantity.get(minIndex).add(appleWeight.get(i));
        }

        for (int i = 0; i < shares.size(); i++) {
            System.out.print("Share " + (i + 1) + " : ");
            for (int j = 0; j < appleQuantity.get(i).size(); j++) {
                System.out.print(appleQuantity.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> appleWeight = new ArrayList<>();

        // Taking input of apple weight
        System.out.println("Enter the weight of apples (-1 to stop): ");
        while (true) {
            int weight = scanner.nextInt();
            if (weight == -1) {
                break;
            }
            appleWeight.add(weight);
        }

        // Array of shares
        List<Integer> shares = List.of(50, 30, 20);
        List<Integer> sharesWeight = new ArrayList<>(List.of(0, 0, 0));

        List<List<Integer>> appleQuantity = new ArrayList<>();
        for (int i = 0; i < shares.size(); i++) {
            appleQuantity.add(new ArrayList<>());
        }

        distributeApple(appleQuantity, appleWeight, shares, sharesWeight);

        List<String> names = List.of("Ram", "Sham", "Rahim");

        // Printing the distribution of apples among shares
        for (int i = 0; i < shares.size(); i++) {
            System.out.print(names.get(i) + " : ");
            for (int j = 0; j < appleQuantity.get(i).size(); j++) {
                System.out.print(appleQuantity.get(i).get(j) + " ");
            }
            System.out.println();
        }
        
        scanner.close();
    }
}
