package com.example.loadermanagerandfragment;

import android.content.Context;
import android.os.Handler;
import android.os.OperationCanceledException;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

public class DataLoader extends AsyncTaskLoader<String> {
	private static final int SERVICE_AMOUNT = 1;
	ProgressBarShowable progressBarShowable;
	private int percentage = 0;
	private Handler handler;

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
			e.printStackTrace();
		}
	}
	
	@Override
	public String loadInBackground() {
		process();
		return null;
	}

	private void process() {
		for (int i = 0; i < 100; i++) {
			percentage = i;
			handler.sendEmptyMessage(percentage);
			pause();
		}
	}
	
	@Override
	protected void onStopLoading() {
		cancelLoad();
	}

}
