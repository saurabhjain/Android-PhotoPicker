package com.example.photopicker;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private Context context;
	
	public ImageAdapter (Context c) {
		context = c;
	}
	
	@Override
	public int getCount() {
		return mThumbIds.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if(convertView == null) {
			imageView = new ImageView(context);
			imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8, 8, 8, 8);
		} else {
			imageView = (ImageView) convertView;
		}
		imageView.setImageResource(mThumbIds[position]);
		return imageView;
	}
	
	 private Integer[] mThumbIds = {
	            R.drawable.ic_launcher, R.drawable.ic_launcher,
	            R.drawable.ic_launcher, R.drawable.ic_launcher,
	            R.drawable.ic_launcher, R.drawable.ic_launcher,
	            R.drawable.ic_launcher, R.drawable.ic_launcher,
	            R.drawable.ic_launcher, R.drawable.ic_launcher,
	            R.drawable.ic_launcher, R.drawable.ic_launcher,
	            R.drawable.ic_launcher, R.drawable.ic_launcher,
	            R.drawable.ic_launcher, R.drawable.ic_launcher,
	            R.drawable.ic_launcher, R.drawable.ic_launcher,
	            R.drawable.ic_launcher, R.drawable.ic_launcher,
	            R.drawable.ic_launcher, R.drawable.ic_launcher,
	            R.drawable.ic_launcher, R.drawable.ic_launcher,
	            R.drawable.ic_launcher, R.drawable.ic_launcher,
	            R.drawable.ic_launcher, R.drawable.ic_launcher,
	            R.drawable.ic_launcher, R.drawable.ic_launcher,
	            R.drawable.ic_launcher, R.drawable.ic_launcher,
	            R.drawable.ic_launcher, R.drawable.ic_launcher,
	            R.drawable.ic_launcher, R.drawable.ic_launcher,
	            R.drawable.ic_launcher, R.drawable.ic_launcher,
	            R.drawable.ic_launcher, R.drawable.ic_launcher,
	            R.drawable.ic_launcher, R.drawable.ic_launcher,
	            R.drawable.ic_launcher, R.drawable.ic_launcher
	    };

}
