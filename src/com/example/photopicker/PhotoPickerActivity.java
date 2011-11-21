package com.example.photopicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PhotoPickerActivity extends Activity {
	
	private Button mPickPhotos;
	final Context context = this;
	int requestCode;
	String ifilename;
	long idataId;

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
				startActivityForResult(intent, requestCode);
			}
		});
    }
    
    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
    	ifilename = data.getStringExtra("filename");
    	idataId = data.getLongExtra("dataUid", 0);
    	Toast.makeText(this, "Result Code, Filename, imageId:"+ resultCode + ", " + ifilename + ", " + idataId, Toast.LENGTH_LONG).show();
    }
}