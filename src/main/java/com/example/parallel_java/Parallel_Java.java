/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.parallel_java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author m
 */
public class Parallel_Java {

    static BlockingQueue<Item> itemList = new ArrayBlockingQueue<>(100);
    static BlockingQueue<Item> producedItems = new ArrayBlockingQueue<>(100);

    public static void main(String args[]) {

        for (int i = 0; i < 100; i++) {
            try {
                itemList.put(new Item());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    //    I
//        List<Thread> t = new ArrayList<>();
//        for (int i = 0; i < 4; i++) {
//            t.add(new Thread(new Producer(itemList, producedItems)));
//        }
//        for (int i = 0; i < 3; i++) {
//            t.add(new Thread(new Consumer(producedItems, itemList)));
//        }
//        long start = System.currentTimeMillis();
//        for(Thread thread : t){
//            thread.start();
//        }
//
//        t.forEach((x) -> {
//            try {
//                x.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
    //  II
        //Sztywna ilość wątków: 7
        //ExecutorService executorService = Executors.newFixedThreadPool(7);
        //Dynamiczna ilość wątków
        ExecutorService executorService = Executors.newCachedThreadPool();

        long start = System.currentTimeMillis();

        for (int i = 0; i < 200; i++) {
            executorService.submit(new ProduceAndConsume(itemList, producedItems));
        }
        executorService.shutdown();

        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    //  III
//        long start = System.currentTimeMillis();
//
//        itemList.parallelStream().forEach(item -> {
//            item.produceMe();
//            item.consumeMe();
//        });

        long stop = System.currentTimeMillis();
        System.out.println("Time: " + (stop - start) + " ms");

    }

}
