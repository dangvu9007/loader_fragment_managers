package com.example.loadermanagerandfragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends FragmentActivity implements LoaderCallbacks<String>, ProgressBarShowable {
	private static final int LOADER_ID = 1;
	private Button btn;
	private ProgressFragment fragment;
	boolean progressBarIsVisible = false;
	private Loader<String> loader;
	private LoaderCallbacks<String> listener;
	private Handler handler;
	private ProgressBarShowable progressListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listener = this;
		progressListener = this;
		fragment = new ProgressFragment();

		btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (loader == null) {
					loader = getSupportLoaderManager().initLoader(LOADER_ID, null, listener);
					loader.forceLoad();
				} else {
					loader = getSupportLoaderManager().restartLoader(LOADER_ID, null, listener);
					loader.forceLoad();
				}
			}
		});
		handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				fragment.setPercentage(msg.what);
			};
		};

	}

	@Override
	public void addFragment() {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

		ft.addToBackStack(null);
		fragment.show(ft, "dialogTag");
		progressBarIsVisible = true;
	}

	public void removeFragment() {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.remove(fragment);
		ft.commitAllowingStateLoss();

	}

	@Override
	public Loader<String> onCreateLoader(int id, Bundle params) {
		DataLoader dataLoader = new DataLoader(this);
		dataLoader.setHandler(handler);
		fragment.setHandler(handler);
		dataLoader.setProgressBarShowable(this);

		return dataLoader;
	}

	@Override
	public void onLoadFinished(Loader<String> loader, String arg1) {
		removeFragment();
	}

	@Override
	public void onLoaderReset(Loader<String> arg0) {
		addFragment();
	}

}
