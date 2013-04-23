package com.example.loadermanagerandfragment;

import android.content.Context;
import android.os.Handler;
import android.support.v4.content.AsyncTaskLoader;

public class DataLoader extends AsyncTaskLoader<String> {
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
		// TODO Auto-generated constructor stub
	}

	private void process() {
		progressBarShowable.addFragment();
		for (int i = 0; i < 100; i++) {
			percentage = i;
			handler.sendEmptyMessage(percentage);
			pause();

		}
	}

	private void pause() {
		try {
			Thread.sleep(50);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public String loadInBackground() {
		process();
		return null;
	}

}
