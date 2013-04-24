package com.example.loadermanagerandfragment;

import java.util.concurrent.CountDownLatch;

import android.content.Context;
import android.os.Handler;
import android.support.v4.content.AsyncTaskLoader;

public class DataLoader extends AsyncTaskLoader<String> {
	private static final int SERVICE_AMOUNT = 1;
	ProgressBarShowable progressBarShowable;
	private int percentage = 0;
	private Handler handler;
	private boolean stop = false;
	private final CountDownLatch latch = new CountDownLatch(SERVICE_AMOUNT);

	public void setProgressBarShowable(ProgressBarShowable progressBarShowable) {
		this.progressBarShowable = progressBarShowable;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public int getPercentage() {
		return percentage;
	}

	public DataLoader(Context context) {
		super(context);
	}

	private void pause() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String loadInBackground() {
		progressBarShowable.addFragment();
		new Thread(new BackGroundProcess()).start();
		waitUntilDone();
		stop = false;
		return null;
	}

	private void waitUntilDone() {
		try {
			latch.await(); // main thread is waiting on CountDownLatch to finish
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

	@Override
	protected void onStopLoading() {
		stop = true;
		waitUntilDone();
	}

	class BackGroundProcess implements Runnable {
		@Override
		public void run() {
			process();
			latch.countDown();
		}

		private void process() {
			for (int i = 0; i < 100; i++) {
				percentage = i;
				handler.sendEmptyMessage(percentage);
				pause();
				if (stop) {
					return;
				}
			}
		}

	}

}
