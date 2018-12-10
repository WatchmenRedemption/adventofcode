package watchmen.redemption.adventofcode.main;

import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Day20181205 {

    /**
     * 算法如下：
     * 每次读取一个字符，
     * 如果栈为空，则将该字符压入栈；
     * 如果栈非空，则与栈顶元素相减，其绝对是为32则弹出栈顶元素否则将该字符压入栈；
     * 最后栈的长度即为答案！
     */
    public static void partOne() {
        Stack<Integer> stack = new Stack<>();
//        String str = "dabAcCaCBAcCcaDA";
        int c;
        try (FileReader reader = new FileReader("./input/input_20181205_part1.txt")) {
            while (-1 != (c = reader.read())) {
//        for (int i = 0; i < str.length(); i++) {
//            if (-1 != (c = str.charAt(i))) {

                putToStack(c, stack);
               /* if ((c <= 'Z' && c >= 'A') || (c <= 'z' && c >= 'a')) {
                    if (stack.empty()) {
                        stack.push(c);
                    } else {
                        int topC = stack.peek();
                        if (Math.abs(topC - c) == 32) {
                            stack.pop();
                        } else {
                            stack.push(c);
                        }
                    }
                }*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(stack.size());
        System.out.println("part1的答案是： " + stack.size());
        /*
        StringBuilder sb = new StringBuilder();
        while (!stack.empty()) {
            sb.append((char) stack.pop().intValue());
        }
        System.out.println(sb.reverse().toString());
        */
    }

    private static void putToStack(Integer c, Stack<Integer> stack) {
        /**
         * 之前没加c的范围判断导致数字变大了一个，因为结尾有空字符
         */
        if ((c <= 'Z' && c >= 'A') || (c <= 'z' && c >= 'a')) {
            if (stack.empty()) {
                stack.push(c);
            } else {
                int topC = stack.peek();
                if (Math.abs(topC - c) == 32) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
    }

    private static int removeOneType(int removeC) {
        Stack<Integer> stack = new Stack<>();
//        String str = "dabAcCaCBAcCcaDA";
        int c;
        try (FileReader reader = new FileReader("./input/input_20181205_part2.txt")) {
            while (-1 != (c = reader.read())) {
//        for (int i = 0; i < str.length(); i++) {
//            if (-1 != (c = str.charAt(i))) {
                if (c == removeC || c == (removeC - 32)) {
                    continue;
                }
                putToStack(c, stack);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stack.size();
        /*
        StringBuilder sb = new StringBuilder();
        while (!stack.empty()) {
            sb.append((char) stack.pop().intValue());
        }
        System.out.println(sb.reverse().toString());
        */
    }

    public static void partTwo() {


        int miniLength = removeOneType('a');
        for (int i = 'b'; i < 'z'; i++) {
            int length = removeOneType(i);
            if (length < miniLength) {
                miniLength = length;
            }
        }

        System.out.println("part2的答案是： " + miniLength);

    }

    public static void main(String[] args) {
        partOne();
        System.out.println("-------------------------------------------------------");
        partTwo();
    }
}
