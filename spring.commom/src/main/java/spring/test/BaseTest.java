package spring.test;

import spring.service.InventoryService;

import java.util.concurrent.CountDownLatch;

/**
 * Created by frinder6 on 2017/3/9.
 */
public class BaseTest {

    public void reduce(InventoryService inventoryService) throws Exception {
        int threadCount = 1000;
        int splitPoint = threadCount / 2;
        CountDownLatch endCount = new CountDownLatch(threadCount);
        CountDownLatch beginCount = new CountDownLatch(1);
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < splitPoint; i++) {
            threads[i] = new Thread(() -> {
                try {
                    beginCount.await();
                    inventoryService.reduce("i0001");
                    endCount.countDown();
                } catch (InterruptedException e) {
                }
            });
            threads[i].start();
        }
        for (int i = splitPoint; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                try {
                    beginCount.await();
                    inventoryService.reduce("i0002");
                    endCount.countDown();
                } catch (InterruptedException e) {
                }
            });
            threads[i].start();
        }

        long startTime = System.currentTimeMillis();
        beginCount.countDown();
        endCount.await();
        inventoryService.print();
        long endTime = System.currentTimeMillis();
        System.out.println("************************************************");
        System.out.println(endTime - startTime);
        System.out.println("************************************************");
    }

}
