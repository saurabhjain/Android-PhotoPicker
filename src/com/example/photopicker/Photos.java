package com.example.photopicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
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
	
	private int count;
	private Bitmap[] imageBitmap;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photos);
		initGridView();
		
	}
	
	private void initGridView() {
		
		final String[] columns = {MediaStore.Images.Media._ID};
		final String sortBy = MediaStore.Images.Media._ID;
		Cursor cursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, sortBy);
		int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
		this.count = cursor.getCount();
		this.imageBitmap = new Bitmap[this.count];
		for(int i=0; i<this.count; i++) {
			
			cursor.moveToPosition(i);
			int id = cursor.getInt(columnIndex);
			imageBitmap[i] = MediaStore.Images.Thumbnails.getThumbnail(getApplicationContext().getContentResolver(), id, MediaStore.Images.Thumbnails.MICRO_KIND, null);
		}
		
		GridView gridView = (GridView) findViewById(R.id.gridView);
		gridView.setAdapter(new ImageAdapter(getApplicationContext()));
		gridView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(@SuppressWarnings("rawtypes") AdapterView parent, View v, int position,
					long id) {
				
				String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
				Cursor actualImageCursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, null);
				final int dataColumnIndex = actualImageCursor.getColumnIndex(MediaStore.Images.Media.DATA);
				final int idColumnIndex = actualImageCursor.getColumnIndex(MediaStore.Images.Media._ID);
				actualImageCursor.moveToPosition(position);
				final String filename = actualImageCursor.getString(dataColumnIndex);
				final long imageId = actualImageCursor.getLong(idColumnIndex);
				//final int imageId = actualImageCursor.getInt(idColumnIndex);
				Toast.makeText(Photos.this, "" + imageId, Toast.LENGTH_SHORT).show();
				//final Intent intent = new Intent(Photos.this, New.class);
				final Intent intent = new Intent();
				intent.putExtra("filename", filename);
				intent.putExtra("dataUid", imageId);
				actualImageCursor.close();
				setResult(RESULT_OK, intent); //setting result
				finish(); //finIsh activity after selection
				//startActivity(intent);
			}
		});
		
		cursor.close();
	}

	private class ImageAdapter extends BaseAdapter {

		private Context localContext;
		
		public ImageAdapter(Context c) {
			localContext = c;
		}

		@Override
		public int getCount() {
			return count;
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

			ImageView iView = (ImageView)convertView;
			if(iView!=null) {
				iView.setImageBitmap(imageBitmap[position]);
			} else {
				iView = new ImageView (localContext.getApplicationContext());
				iView.setImageBitmap(imageBitmap[position]);
				iView.setLayoutParams(new GridView.LayoutParams(120, 120));
			}
			return iView;
		}
	}
}
