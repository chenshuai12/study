package com.cs.concurrency;

import com.google.common.util.concurrent.ThreadFactoryBuilder;


import java.util.Scanner;
import java.util.concurrent.*;


public class test {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            String readStrig = scanner.nextLine();
            String[] readArray = readStrig.split(",");
            Integer num = Integer.valueOf(readArray[1]);
            String[] orgin = readArray[0].split("-");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < orgin.length; i++){
                stringBuilder.append(orgin[i]);
            }
            int first = stringBuilder.length() % num;
            int resultNum = stringBuilder.length() / num;
            int start = first + 1;
            StringBuilder result = new StringBuilder();
            result.append(stringBuilder.subSequence(0, start));
            for (int i = 0; i < num; i ++){
                result.append("-");
                result.append(stringBuilder.subSequence(start, start + num));
                start = start + num;
            }
            System.out.print(result.toString());
        }
}
