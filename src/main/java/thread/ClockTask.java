package thread;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClockTask implements Runnable {

    private static volatile boolean running = true;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void run() {
        if (!running) return;
        String now = LocalDateTime.now().format(dateTimeFormatter);
        System.out.print("\r[현재 시각] ⏱️" + now + " | 원하는 옵션을 선택하세요. > ");
        System.out.flush();
    }

    public static void pauseAndClearLine() {
        running = false;
        System.out.print("\r\033[2K");
        System.out.flush();
    }

    public static void resume() {
        running = true;
    }
}
