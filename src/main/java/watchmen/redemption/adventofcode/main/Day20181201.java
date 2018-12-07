package watchmen.redemption.adventofcode.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Day20181201 {

    /**
     * 核心思路：
     *  每次读取一行，将该行转换为整型；
     *  sum累加；
     *
     */
    public static void partOne() {
        String line;
        int sum = 0;
        try (BufferedReader d = new BufferedReader(new FileReader("./input/input_20181201_part1.txt"))) {
            while ((line = d.readLine()) != null) {
                sum += Integer.valueOf(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("part1的答案： " + sum);
    }

    /**
     * 核心思路：
     *  每次读取一行，将该行转换为整型；
     *  sum累加；
     *  在set中判断是否已有该sum值，如果有跳出here标识的循环，否则将该值加入到set中；
     */
    public static void partTwo() {
        Set<Integer> set = new HashSet<>();
        String line;
        int sum = 0;
        here:
        while (true) {
            try (BufferedReader d = new BufferedReader(new FileReader("./input/input_20181201_part2.txt"))) {

                while ((line = d.readLine()) != null) {
                    sum += Integer.valueOf(line);
                    if (set.contains(sum)) {
                        break here;
                    } else {
                        set.add(sum);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Set的大小： " + set.size());
        System.out.println("part2的答案： " + sum);
    }

    public static void main(String[] args) {
        partOne();
        System.out.println("-------------------------------------------------------");
        partTwo();
    }
}
