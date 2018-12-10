package watchmen.redemption.adventofcode.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static watchmen.redemption.adventofcode.utils.PatternMatch.getMatchers;

public class Day20181204 {

    public static List<String> getSortedStrs(String filePath) {
        ArrayList<String> arrayList = new ArrayList<>();
        String line;
        try (BufferedReader d = new BufferedReader(new FileReader(filePath))) {
            while ((line = d.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    arrayList.add(line.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        arrayList.sort((lhs, rhs) -> {
            int i = lhs.compareTo(rhs);
            if (i > 0) {
                return 1;
            } else {
                return -1;
            }
        });
        return arrayList;
    }

    public static void partOne() {

        List<String> sortedStrs = getSortedStrs("./input/input_20181204_part1.txt");

        Map<Integer, Integer> map = new HashMap<>();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        int guardId = -1;
        int beginTime = -1;
        int endTime;
        for (String str : sortedStrs) {
//            System.out.println(str);
            String regEx = "#[0-9]+";
            List<String> list = getMatchers(regEx, str);


            if (list.size() == 1) {
                guardId = Integer.valueOf(list.get(0).substring(1));
            } else if (list.size() == 0) {
                if (str.contains("falls asleep")) {
                    beginTime = Integer.valueOf(str.substring(15, 17));
                }
                if (str.contains("wakes up")) {

                    endTime = Integer.valueOf(str.substring(15, 17));
                    if (beginTime != -1) {
                        int interval = endTime - beginTime;
                        if (guardId != -1) {
                            if (map.get(guardId) != null) {
                                map.put(guardId, map.get(guardId) + interval);
                            } else {
                                map.put(guardId, interval);
                            }
                        }
                    }


                    beginTime = -1;
                    guardId = -1;
                }
            }
        }

        int maxGuid = 0;
        int maxTime = 0;
        for (int guid : map.keySet()) {
            int currentTime = map.get(guid);
            if (currentTime > maxTime) {
                maxTime = currentTime;
                maxGuid = guid;
            }
        }
        System.out.println("最长的守卫： " + maxGuid);

        int[] sleepMinute = new int[60];
        for (String str : sortedStrs) {
//            System.out.println(str);
            String regEx = "#[0-9]+";
            List<String> list = getMatchers(regEx, str);

            if (list.size() == 1) {
                guardId = Integer.valueOf(list.get(0).substring(1));
            } else if (list.size() == 0) {
                if (guardId != maxGuid) continue;
                if (str.contains("falls asleep")) {
                    beginTime = Integer.valueOf(str.substring(15, 17));
                }
                if (str.contains("wakes up")) {

                    endTime = Integer.valueOf(str.substring(15, 17));
                    if (beginTime != -1) {
                        for (int i = beginTime; i < endTime; i++) {
                            sleepMinute[i]++;
                        }

                    }


                    beginTime = -1;
//                    endTime = -1;
                }
            }

        }


        int maxSleepTime = 0;
        int maxSleepMinute = 0;
        for (int i = 0; i < sleepMinute.length; i++) {
            if (sleepMinute[i] > maxSleepTime) {
                maxSleepTime = sleepMinute[i];
                maxSleepMinute = i;
            }

        }
        System.out.println("最长的时间： " + maxSleepMinute);
        System.out.println("part1的答案： " + maxGuid * maxSleepMinute);
    }


    public static void partTwo() {

        List<String> sortedStrs = getSortedStrs("./input/input_20181204_part2.txt");

        Map<Integer, int[]> map = new HashMap<>();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        int guardId = -1;
        int beginTime = -1;
        int endTime;
        for (String str : sortedStrs) {
//            System.out.println(str);
            String regEx = "#[0-9]+";
            List<String> list = getMatchers(regEx, str);
            if (list.size() == 1) {
                guardId = Integer.valueOf(list.get(0).substring(1));
                if (map.get(guardId) == null) {
                    map.put(guardId, new int[60]);
                }
            } else if (list.size() == 0) {
                if (str.contains("falls asleep")) {
                    beginTime = Integer.valueOf(str.substring(15, 17));
                }
                if (str.contains("wakes up")) {

                    endTime = Integer.valueOf(str.substring(15, 17));
                    if (beginTime != -1 && guardId != -1) {
                        int[] sleepTime = map.get(guardId);
                        for (int i = beginTime; i < endTime; i++) {
                            sleepTime[i]++;
                        }
                    }

                    beginTime = -1;
                }
            }
        }


        int maxSleepMinute = -1;
        int maxSleepTime = -1;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] sleepTime = entry.getValue();
            for (int i = 0; i < sleepTime.length; i++) {
                if (sleepTime[i] > maxSleepTime) {
                    maxSleepTime = sleepTime[i];
                    maxSleepMinute = i;
                    guardId = entry.getKey();
                }
            }
        }

        System.out.println("guardId: " + guardId);
        System.out.println("maxSleepMinute: " + maxSleepMinute);
        System.out.println("maxSleepTime: " + maxSleepTime);
        System.out.println("part2的答案： " + guardId * maxSleepMinute);
    }

    public static void main(String[] args) {
        partOne();
        System.out.println("-------------------------------------------------------");
        partTwo();
    }
}
