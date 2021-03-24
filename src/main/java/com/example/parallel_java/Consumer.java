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
public class Consumer implements Runnable {

    private BlockingQueue<Item> toConsume, toProduce;

    public Consumer(BlockingQueue<Item> toConsume, BlockingQueue<Item> toProduce) {
        this.toConsume = toConsume;
        this.toProduce = toProduce;
    }

    @Override
    public void run() {
        while (!toConsume.isEmpty() || !toProduce.isEmpty()) {
            try {
                Item item = toConsume.take();
                item.consumeMe();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
