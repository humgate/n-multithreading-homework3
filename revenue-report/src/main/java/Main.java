import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class Main {
    //кол-во магазинов
    static final int SHOPS_COUNT = 3;

    //время ожидания магазинов пока они все отчитаются
    static final int AWAIT_TIME = 15;

    //счетчик магазинов для формирования имени потока-магазина
    static int threadCounter = 0;

    public static void main(String[] args) throws InterruptedException {
        Shop shop = new Shop();
        LongAdder longAdder = new LongAdder();

        //используем пул на этот раз для запуска потоков. Создаем его
        ExecutorService threadPool = Executors.newFixedThreadPool(
                SHOPS_COUNT, r -> new Thread(r, "магазин " + ++threadCounter));

        //запускаем потоки магазинов
        for (int i = 0; i < SHOPS_COUNT; i++) {
            threadPool.execute(() -> shop.submitRevenue(longAdder));
        }

        threadPool.awaitTermination(AWAIT_TIME, TimeUnit.SECONDS);
        System.out.println("\nСуммарная выроучка магазинов: " + longAdder.sum());
        threadPool.shutdown();
    }

}

