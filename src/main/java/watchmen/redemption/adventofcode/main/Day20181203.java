package watchmen.redemption.adventofcode.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static watchmen.redemption.adventofcode.utils.PatternMatch.getMatchers;

public class Day20181203 {

    static class Claim {
        int id;
        int leftEdge;
        int topEdge;
        int wide;
        int height;
    }

    private static Claim parseClaim(Claim claim, List<String> list) {

        if (list.size() == 5) {
            claim.id = Integer.valueOf(list.get(0));
            claim.leftEdge = Integer.valueOf(list.get(1));
            claim.topEdge = Integer.valueOf(list.get(2));
            claim.wide = Integer.valueOf(list.get(3));
            claim.height = Integer.valueOf(list.get(4));
            return claim;
        } else {
            return null;
        }

    }


    private static void dealArray(int[][] wholeFabric, String filePath) {
        String line;
        try (BufferedReader d = new BufferedReader(new FileReader(filePath))) {
            while ((line = d.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String regEx = "[0-9]+";
                    List<String> list = getMatchers(regEx, line);
                    Claim claim = new Claim();
                    claim = parseClaim(claim, list);
                    if (claim != null) {
                        for (int i = claim.leftEdge; i < claim.leftEdge + claim.wide; i++) {
                            for (int j = claim.topEdge; j < claim.topEdge + claim.height; j++) {
                                wholeFabric[i][j]++;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void partOne() {
        int[][] wholeFabric = new int[1100][1100];
        String filePath = "./input/input_20181203_part1.txt";
        dealArray(wholeFabric, filePath);

        int count = 0;
        for (int[] row : wholeFabric) {
            for (int inch : row) {
                if (inch > 1) {
                    count++;
                }
            }
        }
        System.out.println("part1的答案是 " + count);
    }

    public static void partTwo() {

        int[][] wholeFabric = new int[1100][1100];
        String filePath = "./input/input_20181203_part2.txt";
        dealArray(wholeFabric, filePath);

        String line;
        try (BufferedReader d = new BufferedReader(new FileReader(filePath))) {
            while ((line = d.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String regEx = "[0-9]+";
                    List<String> list = getMatchers(regEx, line);
                    Claim claim = new Claim();
                    claim = parseClaim(claim, list);
                    if (claim != null) {
                        boolean flag = true;
                        here:
                        for (int i = claim.leftEdge; i < claim.leftEdge + claim.wide; i++) {
                            for (int j = claim.topEdge; j < claim.topEdge + claim.height; j++) {
                                if (wholeFabric[i][j] == 1) continue;
                                else {
                                    flag = false;
                                    break here;
                                }
                            }
                        }
                        if (flag) {
                            System.out.println("part2的答案是： " + claim.id);
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        partOne();
        System.out.println("-------------------------------------------------------");
        partTwo();
    }
}
