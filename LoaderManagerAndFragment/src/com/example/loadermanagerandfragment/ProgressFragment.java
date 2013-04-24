package com.example.loadermanagerandfragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressFragment extends DialogFragment {

	private TextView tv;
	private ProgressBar progressBar2;
	private int percentage = 0;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_layout, null);
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
