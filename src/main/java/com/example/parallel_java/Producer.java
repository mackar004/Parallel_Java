/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.parallel_java;

import java.util.concurrent.BlockingQueue;

/**
 *
 * @author m
 */
public class Producer implements Runnable {

    private BlockingQueue<Item> toProduce, isProduced;

    public Producer(BlockingQueue<Item> toProduce, BlockingQueue<Item> isProduced) {
        this.toProduce = toProduce;
        this.isProduced = isProduced;
    }

    @Override
    public void run() {
        while (!toProduce.isEmpty()) {
            try {
                Item item = toProduce.take();
                item.produceMe();
                isProduced.put(item);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
