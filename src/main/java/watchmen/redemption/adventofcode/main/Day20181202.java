package watchmen.redemption.adventofcode.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day20181202 {
    /**
     * 核心思路：
     *  每次读取一行，每行逐字母，将字母作为键从map中取值，
     *  如果有值，则加1；否则以值为1存入map。从map的值集合中，
     *  判断包含2，则两次的总数加1；包含3，则三次的总数加1。
     *  将两次的总数*三次的总数即为答案
     */
    public static void partOne() {
        Map<Character, Integer> map = new HashMap<>();
        String line;
        char c;
        int twiceCount = 0;
        int thriceCount = 0;
        try (BufferedReader d = new BufferedReader(new FileReader("./input/input_20181202_part1.txt"))) {
            while ((line = d.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    if (-1 != (c = line.charAt(i))) {
                        if (null == map.get(c)) {
                            map.put(c, 1);
                        } else {
                            map.put(c, map.get(c) + 1);
                        }
                    }
                }
                if (map.values().contains(2)) {
                    twiceCount++;
                }
                if (map.values().contains(3)) {
                    thriceCount++;
                }
                map.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int result = twiceCount * thriceCount;
        System.out.println("part1的答案是：" + result);

    }

    /**
     * 遍历集合，找到符合条件的字符串，并输出相同的字符
     * @param line 一行字符串
     * @param set 字符串集合
     * @return 是否找到符合的字符串
     */
    private static boolean compareLineEligible(String line, Set<String> set) {
        here:
        for (String str : set) {
            if (str.length() != line.length()) {
                continue;
            }
            int count = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == line.charAt(i)) {
                    continue;
                } else {
                    count++;
                    if (count > 1) {
                        continue here;
                    }
                }
            }
            if (count == 1) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) == line.charAt(i)) {
                        sb.append(str.charAt(i));
                    } else {
                        continue;
                    }
                }
                System.out.println("part2的答案是： " + sb.toString());
                return true;
            }
        }
        set.add(line);
        return false;
    }

    /**
     * 核心思路：
     *  每次读取一行，与集合中的进行比较，符合条件即可
     */
    public static void partTwo() {
        Set<String> set = new HashSet<>();
        String line;
        try (BufferedReader d = new BufferedReader(new FileReader("./input/input_20181202_part2.txt"))) {
            while ((line = d.readLine()) != null) {
                if(compareLineEligible(line, set)){
                    break;
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
