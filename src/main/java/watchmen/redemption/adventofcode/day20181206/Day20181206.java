package watchmen.redemption.adventofcode.day20181206;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day20181206 {

    private static int miniX;
    private static int maxX;
    private static int miniY;
    private static int maxY;

    private static List<Coordinate> getAllCoordinates(String filePath) {
        ArrayList<Coordinate> arrayList = new ArrayList<>();
        String line;
        try (BufferedReader d = new BufferedReader(new FileReader(filePath))) {
            while ((line = d.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    arrayList.add(Coordinate.create(line.trim()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arrayList;
    }


    public static void initStaticProperty(List<Coordinate> coordinates){
        miniX = coordinates.get(0).getX();
        maxX = coordinates.get(0).getX();
        miniY = coordinates.get(0).getY();
        maxY = coordinates.get(0).getY();
        for (int i = 1; i < coordinates.size(); i++) {
            Coordinate coordinate = coordinates.get(i);
            if (coordinate.getX() < miniX) {
                miniX = coordinate.getX();
            }

            if (coordinate.getY() < miniY) {
                miniY = coordinate.getY();
            }

            if (coordinate.getX() > maxX) {
                maxX = coordinate.getX();
            }

            if (coordinate.getY() > maxY) {
                maxY = coordinate.getY();
            }
        }
    }
    public static void partOne() {
        List<Coordinate> coordinates = getAllCoordinates("./input/input_20181206_part1.txt");

        initStaticProperty(coordinates);

        int miniDistance;
        Coordinate miniCoordinate;
        Map<Coordinate, Integer> map = new HashMap<>();
        for (int i = miniX; i <= maxX; i++) {
//            System.out.println("i = " + i);
            for (int j = miniY; j <= maxY; j++) {
//                System.out.println("j = " + j);
                miniDistance = Math.abs(coordinates.get(0).getX() - i) + Math.abs(coordinates.get(0).getY() - j);
                miniCoordinate = coordinates.get(0);
                for (int k = 1; k < coordinates.size(); k++) {
                    int distance = Math.abs(coordinates.get(k).getX() - i) + Math.abs(coordinates.get(k).getY() - j);
                    if (distance == miniDistance) {
                        miniCoordinate = null;
                    }
                    if (distance < miniDistance) {
                        miniDistance = distance;
                        miniCoordinate = coordinates.get(k);
                    }

                }
                if (miniCoordinate != null) {
                    if (i == miniX || i == maxX || j == miniY || j == maxY) {
                        miniCoordinate.setInfinite(true);
                    } else {
                        if (map.get(miniCoordinate) == null) {
                            map.put(miniCoordinate, 1);
                        } else {
                            map.put(miniCoordinate, map.get(miniCoordinate) + 1);
                        }
                    }
                }
            }
        }

        int maxCount = 0;
        for (Map.Entry<Coordinate, Integer> entry : map.entrySet()) {
            Coordinate coordinate = entry.getKey();
            if (!coordinate.isInfinite()) {
                int count = entry.getValue();
                if (count > maxCount) {
                    maxCount = count;
                }
            }
        }

        System.out.println("part1的答案： " + maxCount);

    }

    public static void partTwo() {
        List<Coordinate> coordinates = getAllCoordinates("./input/input_20181206_part2.txt");
        initStaticProperty(coordinates);
        int targetDistance = 10000;
        int totalCount = 0;
        for (int i = miniX - targetDistance / coordinates.size(); i <= maxX + targetDistance / coordinates.size(); i++) {
//            System.out.println("i = " + i);
            for (int j = miniY - targetDistance / coordinates.size(); j <= maxY + targetDistance / coordinates.size(); j++) {
                long sum = 0;
//                System.out.println("j = " + j);
                for (int k = 0; k < coordinates.size(); k++) {
                    sum += Math.abs(coordinates.get(k).getX() - i) + Math.abs(coordinates.get(k).getY() - j);
                }
                if (sum < targetDistance) {
                    totalCount++;
                }
            }
        }
        System.out.println("part2的答案： " + totalCount);

    }

    public static void main(String[] args) {
        partOne();
        System.out.println("-------------------------------------------------------");
        partTwo();
    }
}
