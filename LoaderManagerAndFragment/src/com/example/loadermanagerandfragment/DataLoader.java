package com.example.loadermanagerandfragment;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import android.R.bool;
import android.content.Context;
import android.os.Handler;
import android.support.v4.content.AsyncTaskLoader;

public class DataLoader extends AsyncTaskLoader<String> {
	private static final int SERVICE_AMOUNT = 1;
	ProgressBarShowable progressBarShowable;
	private int percentage = 0;
	private Handler handler;
	private boolean stop = false;
	

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
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String loadInBackground() {
		progressBarShowable.addFragment();
		process();
		return null;
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
	
	@Override
	public void onCanceled(String data) {
		//stop.set(true);
	}
	
	@Override
	protected void onStopLoading() {
		cancelLoad();
		stop = true;
	}

}
