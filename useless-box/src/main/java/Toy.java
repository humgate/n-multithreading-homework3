/**
 * Реалзиация бесполезной игрушки
 */
public class Toy {
    //на тумблер
    volatile boolean tumbler;

    /**
     * Запускает включение тумблера
     * @param switchCount - число включений
     * @param switchDelay - таймаут между включениями
     */
    public void startSwitchingOn(int switchCount, int switchDelay) {
        for (int i = 0; i < switchCount; i++) {
            tumbler = true;
            System.out.println(Thread.currentThread().getName() + " включил тумблер " + (i + 1) + " раз");
            try {
                Thread.sleep(switchDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Запускает отключение тумблера
     */
    public void startSwitchingOff() {
        while (!Thread.interrupted()) { //пока наш поток не прервали
            if (tumbler) { //если тумблер включен
                tumbler = false;
                System.out.println(Thread.currentThread().getName() + " отключил тумблер");
            }
        }
    }
}
