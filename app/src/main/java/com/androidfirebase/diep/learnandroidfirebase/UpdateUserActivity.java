package com.androidfirebase.diep.learnandroidfirebase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Administrator on 5/10/2017.
 */

public class UpdateUserActivity  extends AppCompatActivity {
    Button btnUpdateUser;
    FirebaseAuth firebaseAuth;

    @Override
    protected  void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_update_user);

        firebaseAuth = FirebaseAuth.getInstance();


        btnUpdateUser = (Button) findViewById(R.id.btnUpdateUser);
        Toast.makeText(this, firebaseAuth.getCurrentUser().getDisplayName(), Toast.LENGTH_LONG).show();

        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

//                UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
//                        .setDisplayName("Anh Diep Dep trai")
//                        .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg")).build();
//                user.updateProfile(profileUpdate);
//                Log.d("DaUpdateThongTinUser", "Update thong tin user thanh cong roi.........");
                firebaseAuth.sendPasswordResetEmail(user.getEmail()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(UpdateUserActivity.this, "Email da dc gui, hay check de reset pass cho email", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

    }

}
