public class Main {
   static final int USER_SWITCH_COUNT = 5;
   static final int USER_SWITCH_TIMEOUT = 400;

    public static void main(String[] args) throws InterruptedException {
        Toy toy = new Toy();

        //запускаем поток пользователя включающий тумблер
        Thread userThread = new Thread(null,
                () -> toy.startSwitchingOn(USER_SWITCH_COUNT,USER_SWITCH_TIMEOUT),
                "Поток пользователя");
        userThread.start();


        //запускаем поток игрушки выключающий тумблер
        Thread toyThread= new Thread(null,toy::startSwitchingOff,
                "Поток игрушки");
        toyThread.start();

        //ждем пока юзер наиграется
        userThread.join();

        //останавливаем игрушку
        toyThread.interrupt();
    }
}
