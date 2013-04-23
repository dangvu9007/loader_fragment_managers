package com.example.loadermanagerandfragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressFragment extends DialogFragment {

	private View view;
	private TextView tv;
	private ProgressBar progressBar2;
	private DataLoader dataLoader;
	private int percentage = 0;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_layout, null);
		tv = (TextView) view.findViewById(R.id.textView1);
		progressBar2 = (ProgressBar) view.findViewById(R.id.progressBar2);
		return view;
	}

	public void setPercentage(int percent) {
		percentage = percent;
		tv.setText(Integer.toString(percentage));
		progressBar2.setProgress(percentage);

	}

}
