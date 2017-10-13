package com.sz.ktv.util.schdule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledTask {

	private static ScheduledExecutorService scheduExec = Executors.newScheduledThreadPool(50);

	/**
	 * 执行一次
	 * @param @param autoTask
	 * @param @param delay
	 */
	public static void addDelayTask(AutoTask autoTask, long delay) {
		ScheduledFuture<?> scheduledFuture = scheduExec.schedule(autoTask, delay, TimeUnit.MILLISECONDS);
		autoTask.setScheduledFuture(scheduledFuture);
	}

	/**
	 * 循环
	 * @param @param autoTask
	 * @param @param period
	 */
	public static void addRateTask(AutoTask autoTask, long period) {
		ScheduledFuture<?> scheduledFuture = scheduExec.scheduleAtFixedRate(autoTask, 0, period, TimeUnit.MILLISECONDS);
		autoTask.setScheduledFuture(scheduledFuture);
	}

	/**
	 * 延迟多久执行
	 * @param @param autoTask
	 * @param @param initialDelay
	 * @param @param period
	 */
	public static void addRateTask(AutoTask autoTask, long initialDelay, long period) {
		ScheduledFuture<?> scheduledFuture = scheduExec.scheduleAtFixedRate(autoTask, initialDelay, period, TimeUnit.MILLISECONDS);
		autoTask.setScheduledFuture(scheduledFuture);
	}

}
