package de.vogella.android.cameraapp.app;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import java.io.File;
import java.util.ArrayList;

public class listImagesActivity extends ActionBarActivity {
    File[] mediaStorageFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_images);


        ArrayList<Bitmap> list = load();
        BitmapAdapter adapter = new BitmapAdapter(
                getApplicationContext(), R.layout.list_item,
                list);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(adapter);
    }

    private ArrayList<Bitmap> load() {
        ArrayList<Bitmap> list = new ArrayList<Bitmap>();

        String photoPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath() + File.separator + "MyCameraApp";
        mediaStorageFiles = new File(photoPath).listFiles();
        for (int i = 0; i < mediaStorageFiles.length; i++) {
            Bitmap bitmap = BitmapFactory.decodeFile(mediaStorageFiles[i].getPath());
            list.add(bitmap);
        }
        return list;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_images, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
