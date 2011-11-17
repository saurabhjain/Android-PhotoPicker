package com.example.photopicker;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class Photos extends Activity{
	
	public Cursor cursor;
	public int columnIndex;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photos);
		
		String [] projection = {MediaStore.Images.Thumbnails._ID};
		cursor = managedQuery(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, projection, null, null, MediaStore.Images.Thumbnails.IMAGE_ID);
		columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID);
		
		GridView gridview = (GridView)findViewById(R.id.gridView);
		gridview.setAdapter(new ImageAdapter(this));
		
		gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				String [] projection = {MediaStore.Images.Media.DATA};
				cursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
				columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails.DATA);
				cursor.moveToPosition(position);
				
				String imagePath = cursor.getString(columnIndex);
				Toast.makeText(Photos.this, "" + position, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	private class ImageAdapter extends BaseAdapter {

		private Context context;
		
		public ImageAdapter (Context localContext) {
			context = localContext;
		}
		
		@Override
		public int getCount() {
			//return mThumbIds.length;
			return cursor.getCount();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;
			if(convertView == null) {
				imageView = new ImageView(context);
				cursor.moveToPosition(position);
				int imageID = cursor.getInt(columnIndex);
				imageView.setImageURI(Uri.withAppendedPath(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, "" + imageID));
				imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
				imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
				imageView.setPadding(8, 8, 8, 8);
			} else {
				imageView = (ImageView) convertView;
			}
			return imageView;
		}
	}
}
