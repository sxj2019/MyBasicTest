package com.sxj.test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: test02
 * @description:
 * @author: sxj
 * @create: 2019-11-06 15:08
 **/
public class Test02 {
    public static void main(String[] args) {
//        Bank bank = new Bank(100,1000);
//        for (int i = 0; i < 100; i++) {
//            TranferRunnable r = new TranferRunnable(bank,i,1000);
//            Thread t = new Thread(r);
//            t.start();
//        }
        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(()->{
//                int anInt = ThreadLocalRandom.current().nextInt(10);
//                System.out.println(anInt);
                ThreadLocalRandom current = ThreadLocalRandom.current();
                System.out.println(current);
                ThreadLocal x;

            });
            t.start();
        }
    }
}

class Bank{
    private final Lock lock = new ReentrantLock();
    private double[] accounts;
    private Condition sufficientFunds;

    public Bank(int n,double initialVal) {
        accounts = new double[n];
        for (int i = 0; i < n; i++) {
            accounts[i] = initialVal;
        }
        sufficientFunds = lock.newCondition();
    }

    public  void tranfer(int from, int to, double amount) throws InterruptedException {

        lock.lock();
        try {
            while (accounts[from] < amount)
                sufficientFunds.await();
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d\n",amount,from,to);
            accounts[to] += amount;
            System.out.printf("Total balance: %10.2f\n",getTotalBalance());
            sufficientFunds.signalAll();

        }finally {
            lock.unlock();
        }
    }

    public double getTotalBalance() {
        double sum = 0;
        for (int i = 0; i < accounts.length; i++) {
            sum += accounts[i];
        }
        return sum;
    }

    public int size() {
        return accounts.length;
    }
}

class TranferRunnable implements Runnable{
    private Bank bank;
    private int fromAccount;
    private double maxAmount;
    private static final int DELAY = 3;

    public TranferRunnable(Bank bank, int fromAccount, double maxAmount) {
        this.bank = bank;
        this.fromAccount = fromAccount;
        this.maxAmount = maxAmount;
    }

    @Override
    public void run() {
        try {
            while (true){
                int toAccount = (int) ( bank.size() * Math.random());
                double amount = maxAmount * Math.random();
                bank.tranfer(fromAccount,toAccount,amount);
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (InterruptedException e){

        }
    }
}