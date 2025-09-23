package service;

import thread.ClockTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ClockTaskService {

    private static final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture<?> future;

    public void startClockTask() {
        if (future == null || future.isCancelled()) {
            future = scheduledExecutorService.scheduleAtFixedRate(new ClockTask(), 0, 1, TimeUnit.SECONDS);
        }
    }

    public void shutDown() {
        if (future != null) {
            future.cancel(true);
        }
        scheduledExecutorService.shutdownNow();
    }
}