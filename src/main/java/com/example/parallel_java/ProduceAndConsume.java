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
public class ProduceAndConsume implements Runnable {

    private BlockingQueue<Item> toProduce, toConsume;

    public ProduceAndConsume(BlockingQueue<Item> toProduce, BlockingQueue<Item> toConsume){
        this.toProduce = toProduce;
        this.toConsume = toConsume;
    }
    
    @Override
    public void run() {
        if (!toProduce.isEmpty()) {
            try {
                Item item = toProduce.take();
                item.produceMe();
                toConsume.put(item);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else
        if (!toConsume.isEmpty()) {
            try {
                Item item = toConsume.take();
                item.consumeMe();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
