package com.androidfirebase.diep.learnandroidfirebase;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseStorage = FirebaseStorage.getInstance();

        imageView = (ImageView) findViewById(R.id.imageView);
//        storageReference = firebaseStorage.getReference();
//        storageReference.child("images").child("limon.png");
//        Log.d("KiemTra", storageReference.child("images").child("limon.png").getPath());
        Drawable drawable = imageView.getDrawable();
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap bitmap = bitmapDrawable.getBitmap();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] data = byteArrayOutputStream.toByteArray();
//        storageReference = firebaseStorage.getReference().child("photos").child("aa.jpg");
//        storageReference.putBytes(data);

        //Upload Uri

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    InputStream inputStream = new URL("https://i.ytimg.com/vi/9rqEV0qnW2s/maxresdefault.jpg").openStream();
                    storageReference = firebaseStorage.getReference().child("photos").child("maxresdefault.jpg");
                    storageReference.putStream(inputStream);
                    Log.d("VaoDay", "VDDDDDDDDDDDDDDDDDDDDDD");
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
//        Uri uri = Uri.parse("E:\\cun.jpg");
//        //InputStream inputStream = new
//        storageReference = firebaseStorage.getReference().child("photos/cun.jpg");
//        storageReference.putFile(uri);



    }
}
