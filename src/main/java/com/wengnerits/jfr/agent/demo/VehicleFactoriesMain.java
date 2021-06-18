package com.wengnerits.jfr.agent.demo;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Miroslav Wengner (@miragemiko, @mirage22)
 */
public class VehicleFactoriesMain implements Runnable{

    private static int NUMBER_FACTORIES = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VehicleFactoriesMain factories = new VehicleFactoriesMain();
        while(true){
            VehicleFactory.reset();
            factories.run();
            System.out.println("Press any key to repeat");
            scanner.nextLine();
        }

    }

    @Override
    public void run() {
        VehicleFactory[] factories = new VehicleFactory[NUMBER_FACTORIES];

        ExecutorService executor = Executors.newFixedThreadPool(NUMBER_FACTORIES);
        for(int i=0; i < NUMBER_FACTORIES; i++){
            factories[i] = new VehicleFactory();
            executor.execute(factories[i]);
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\nFactories has finished all vehicles");
        System.out.println("======READY FOR THE NEXT RUN=====");
    }
}
