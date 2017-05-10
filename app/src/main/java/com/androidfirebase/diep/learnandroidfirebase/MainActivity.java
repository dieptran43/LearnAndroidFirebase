package com.androidfirebase.diep.learnandroidfirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {

    FirebaseAuth firebaseAuth;
    LoginButton btnDangNhapFacebook;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        LoginManager.getInstance().logOut();
        callbackManager = CallbackManager.Factory.create();

        btnDangNhapFacebook = (LoginButton) findViewById(R.id.btnDangNhapFacebook);
        btnDangNhapFacebook.setReadPermissions("email", "public_profile");
        btnDangNhapFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String tokenID = loginResult.getAccessToken().getToken();
                AuthCredential authCredential = FacebookAuthProvider.getCredential(tokenID);
                firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(MainActivity.this, "Dang nhap thanh cong", Toast.LENGTH_LONG).show();
                    }
                });

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        btnDangNhapFacebook.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

               }
           }
        );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public  void onStart()
    {
        super.onStart();
        firebaseAuth.addAuthStateListener(this);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        firebaseAuth.removeAuthStateListener(this);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user !=null){
            Log.d("KiemTraSuccess", user.getUid());
            Intent iUpdateIser = new Intent(MainActivity.this, UpdateUserActivity.class);
            startActivity(iUpdateIser);
        }
        else{
            Log.d("KiemTraOut", "Da log out roi!!");
        }
    }

}
