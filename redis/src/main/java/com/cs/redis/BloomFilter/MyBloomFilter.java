package com.cs.redis.BloomFilter;



import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.BitSet;
import java.util.Objects;

public class MyBloomFilter {
    /**
     * 一个长度为10亿的比特位
     */
    private static final int DEFAULT_SIZE = 256 << 22;
    /**
     * 为了降低错误率，使用加法hash算法，所以定义一个8个元素的质数数组
     */
    private static final int[] seeds = {3,5,7,11,13,31,37,61};
    /**
     * 相当于构建8个不同的hash算法
     */
    private static HashFunction[] functions = new HashFunction[seeds.length];
    /**
     * 初始化布隆过滤器的bitmap
     */
    private static BitSet bitSet = new BitSet(DEFAULT_SIZE);
    /**
     * 添加数据
     */
    private static void add(String value){
        if (Objects.nonNull(value)){
            for (HashFunction f : functions){
                //计算hash值并修改bitmap中相应位置为true
                bitSet.set(f.hash(value),true);
            }
        }
    }

    /**
     * 判断相应元素是否存在
     */
    public static boolean contains(String value){
        if (Objects.isNull(value)){
            return false;
        }
        boolean ret = true;
        for (HashFunction function : functions){
            ret = bitSet.get(function.hash(value));
            //一个hash函数返回false则跳出循环
            if (!ret){
                break;
            }
        }
        return ret;
    }



    /**
     * 模拟用户是不是会员，或用户在不在线。。。
     * @param args
     */

//    public static void main(String[] args) {
//
//        for (int i = 0; i < seeds.length; i++) {
//            functions[i] = new HashFunction(DEFAULT_SIZE, seeds[i]);
//        }
//
//        // 添加1亿数据
//        for (int i = 0; i < 100000000; i++) {
//            add(String.valueOf(i));
//        }
//        String id = "123456789";
//        add(id);
//
//        System.out.println(contains(id));
//        System.out.println("" + contains("234567890"));
//    }
    public static void main(String[] args) {
        //后边两个参数：预计包含的数据量，和允许的误差值
        BloomFilter<Integer> bloomFilter = BloomFilter.
                create(Funnels.integerFunnel(), 100000, 0.01);
        for (int i = 0; i < 100000; i++) {
            bloomFilter.put(i);
        }
        System.out.println(bloomFilter.mightContain(1));
        System.out.println(bloomFilter.mightContain(2));
        System.out.println(bloomFilter.mightContain(3));
        System.out.println(bloomFilter.mightContain(100001));

        //bloomFilter.writeTo();
    }

    static class HashFunction{
        private int size;
        private int seed;
        public HashFunction(int size,int seed){
            this.size = size;
            this.seed = seed;
        }
        public int hash(String value){
            int result = 0;
            int len = value.length();
            for (int i = 0; i < len; i++){
                result = seed * result + value.charAt(i);
            }
            int r = (size - 1) & result;
            return (size - 1) & result;
        }
    }
}
