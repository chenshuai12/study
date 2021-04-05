package com.cs.lambda.Stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class main {
    public static void main(String[] args) {
        /**
         * 筛选与切片
         */
        Stream<Integer> stream = Stream.of(6,4,6,7,3,9,8,10,12,14,14);
        Stream<Integer> newStream = stream.filter(s -> s > 5)
                .distinct()
                .skip(2)
                .limit(2);
        newStream.forEach(System.out::println);

        /**
         * 映射
         */
        List<String> list = Arrays.asList("a,b,c","1,2,3");
        //将每个元素转成一个新的且不带逗号的元素
        Stream<String> s1 = list.stream().map(s -> s.replaceAll(",",""));
        s1.forEach(System.out::println);


        Stream<String> s3 = list.stream().flatMap(s -> {
            //将每个元素转换成一个Stream
            String[] split = s.split(",");
            Stream<String> s2 = Arrays.stream(split);
            return s2;
        });
        s3.forEach(System.out::println);

        /**
         * 排序
         */
        List<String> list1 = Arrays.asList("aa","ff","dd");
        //String自身实现了Compareable接口
        list.stream().sorted().forEach(System.out::println);
        Student student1 = Student.builder().name("aa").age(10).build();
        Student student2 = Student.builder().name("bb").age(20).build();
        Student student3 = Student.builder().name("aa").age(30).build();
        Student student4 = Student.builder().name("dd").age(40).build();
        List<Student> studentList = Arrays.asList(student1,student2,student3,student4);
        //自定义排序:先按姓名升序，姓名相同则按年龄升序
        studentList.stream().sorted((o1,o2) -> {
            if (o1.getName().equals(o2.getName())){
                return o1.getAge() - o2.getAge();
            }else {
                return o1.getName().compareTo(o2.getName());
            }
        }).forEach(System.out::println);

        /**
         * 消费
         */
        List<Student> studentList1 = Arrays.asList(student1,student2);
        studentList1.stream().peek(o -> o.setAge(100)).forEach(System.out::println);

        /**
         * 匹配 聚合
         */

        List<Integer> list2 = Arrays.asList(1,2,3,4,5);
        boolean allMatch = list2.stream().allMatch(e -> e > 10);
        boolean noneMatch = list2.stream().noneMatch(e -> e > 10);
        boolean anyMatch = list2.stream().anyMatch(e -> e > 4);

        Integer findFirst = list2.stream().findFirst().get();
        Integer findAny = list2.stream().findAny().get();
        Long count = list2.stream().count();
        Integer max = list2.stream().max(Integer::compareTo).get();
        Integer min = list2.stream().min(Integer::compareTo).get();

    }
}
