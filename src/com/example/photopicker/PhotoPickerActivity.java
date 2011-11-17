package com.example.photopicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PhotoPickerActivity extends Activity {
	
	private Button mPickPhotos;
	final Context context = this;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mPickPhotos = (Button)findViewById(R.id.pickPhotos);

        mPickPhotos.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Intent intent = new Intent(context, Photos.class);
				context.startActivity(intent);
			}
		});
    }
}