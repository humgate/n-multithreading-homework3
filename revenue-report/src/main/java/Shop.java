import java.util.Random;
import java.util.concurrent.atomic.LongAdder;

public class Shop {
    //лимит количества продаж магазина за день
    final int SHOP_SELLS_COUNT_LIMIT = 4;

    //лимит велчины одной продажи
    final int MAX_SELL_SIZE = 10;

    //массив продаж магазина за день, для каждого потока-магазина свой
    ThreadLocal<Integer[]> revenueItems;

    //для генераци случанызх чисел
    Random random = new Random();

    Shop() {
        revenueItems = ThreadLocal.withInitial(() -> {
            //количество продаж магазина генерируем рандомино от 0 до SHOP_SELLS_COUNT
            Integer[] arr = new Integer[random.nextInt(SHOP_SELLS_COUNT_LIMIT)];

            //генерируем продажи
            for (int i = 0; i < arr.length; i++) {
                arr[i] = random.nextInt(MAX_SELL_SIZE) + 1;
            }
            return arr;
        });
    }

    public void displayItems() {
        for (int i = 0; i < revenueItems.get().length; i++) {
            System.out.println(Thread.currentThread().getName() + " " + revenueItems.get()[i]);
        }
    }

    public void submitRevenue(LongAdder longAdder) {
        for (int i = 0; i < revenueItems.get().length; i++) {
            longAdder.add(revenueItems.get()[i]);
            System.out.println(Thread.currentThread().getName() + " добавил к общей сумме: " + revenueItems.get()[i]);
        }
    }

}

