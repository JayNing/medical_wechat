package com.zx.common;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaTest {
    public static void main(String[] args){
        new Thread(()-> {System.out.println("Hello Lambda");}).start();

        List<String> features = Arrays.asList("Lambdas", "Default Method",
                "Stream API", "Date and Time API");
        //for循环
        features.forEach(System.out::println);

        //将元素用，号拼接
        System.out.println(features.stream()
                .collect(Collectors.joining(",")));
        //排序
        features.sort((a,b) -> a.compareTo(b));
        //for循环
        features.forEach(System.out::println);

        BinaryOperator<Long> add = (x,y) -> x + y;
        System.out.println(add.apply(1L,2L));

        Predicate<Integer> checkInteger = x -> x > 5;
        System.out.println(checkInteger.test(6));

        //创建条件函数
        Predicate<String> checkStart = x -> x.startsWith("D");

        //将集合转成流，进行filter内的条件查询，然后取出符合条件的数据数量
        long count = features.stream().filter(a -> checkStart.test(a)).count();
        System.out.println(count);
        //将集合转成流，进行filter内的条件查询，然后for循环符合条件的数据
        features.stream().filter(a -> checkStart.test(a)).forEach(System.out::println);

        //collect
        List<String> collected = Stream.of("a","b","c").collect(Collectors.toList());
        System.out.println(collected);

        //map:如果有一个函数可以将一种类型的值转换为另外一种类型，map操作就可以使用该函数，将一个流中的值转换成一个新的流。
        List<String> collected2 = Stream.of("a","b","c").map((string -> string.toUpperCase())).collect(Collectors.toList());
        System.out.println(collected2);

        //筛选掉Java
        List<String> list = Stream.of("a","b","hello","Java").filter(a -> !a.equals("Java")).collect(Collectors.toList());
        System.out.println(list);

        //flatMap:方法可用Stream替换值，然后将多个Stream连接成一个Stream
        List<String> flatMapDemo = Stream.of(Arrays.asList("Java","C++"),Arrays.asList("Python","PHP")).flatMap(a -> a.stream()).collect(Collectors.toList());
        System.out.println(flatMapDemo);

        //min和max求最大值和最小值
        List<Track> tracks = Arrays.asList(new Track("zhangsan", 123),
                new Track("lisi", 234), new Track("wangwu", 345));
        Track track = tracks.stream().min(Comparator.comparing(n -> n.getName())).get();
        System.out.println(track);

        Track track1 = tracks.stream().max(Comparator.comparing(n -> n.getAge())).get();
        System.out.println(track1);

        //reduce实现从一组值中生成一个值
        //实现求和操作，有两个参数：传入Stream中的当前元素和acc,将两个参数相加，acc是累加器，保存着当前的累加结果。
        //将stream中的值相加，得到总和
        Long reduce = Stream.of(1L, 2L, 3L).reduce(0L, (acc, element) -> acc + element);
        //T reduce(T identity, BinaryOperator<T> accumulator);
        System.out.println(reduce);

        /**
         * 高阶函数
         * 接受另外一个函数作为参数，或返回一个函数的函数。
         * 如果函数的参数列表里包含函数接口，或该函数返回一个函数接口，那么该函数就是高阶函数。
         * summaryStatistics()统计
         * */
        LongSummaryStatistics intSummaryStatistics = Stream.of("a", "bv", "deae", "ett").mapToLong(n -> n.length()).summaryStatistics();
        System.out.println(intSummaryStatistics.getMax());
        System.out.println(intSummaryStatistics.getMin());
        System.out.println(intSummaryStatistics.getAverage());
        System.out.println(intSummaryStatistics.getCount());

        int sum = Stream.of("a", "bv", "deae", "ett").mapToInt(n -> n.length()).sum();
        System.out.println("sum : " + sum);


        /****
         * BinaryOperator是一种特殊的BiFunction类型，参数的类型和返回值的类型相同。比如，两个整数相加就是一个BinaryOperator.
         *  Optional对象相当于值的容器，而该值可以通过get方法提取。Optional对象也可能为空。因此还有一个对应的工厂方法empty，
         *  另外一个工厂方法ofNullable则可将一个空值转成成Optional对象。
         * isPresent():如果存在值，则返回 true ，否则为 false 。
         * **/

        Optional<String> test = Optional.of("abc");
        System.out.println(test.get());

        Optional<String> isEmpty = test.empty();
        Optional<String> nullOptional = Optional.ofNullable(null);
        System.out.println(isEmpty.isPresent());
        System.out.println(nullOptional.isPresent());



    }

    static class Track{
        private String name;
        private Integer age;

        public Track(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Track{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }


}