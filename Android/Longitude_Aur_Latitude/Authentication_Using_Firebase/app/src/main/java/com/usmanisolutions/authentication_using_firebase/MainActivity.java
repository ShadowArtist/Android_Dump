package com.usmanisolutions.authentication_using_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


  private ImageView logout_image;
   private FirebaseAuth.AuthStateListener authListener;
   private FirebaseAuth auth;
   private Button add_property;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        SIGNOUT SNIPPIT

        auth.signOut();

// this listener will be called when there is change in firebase user session
FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };


        * */



       // logout_button=findViewById(R.id.logout_button);

        logout_image=(ImageView) findViewById(R.id.logout_image);
        add_property=(Button) findViewById(R.id.add_property);


        /*Firebase Auth*/
        auth=FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        authListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user == null)
                {
                    Intent logout_intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(logout_intent);
                    finish();
                }
            }
        };
        logout_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                SignOut();
            }
        });
        /*Firebase Auth Ends here*/



        /* Property adding work starts from here */

        add_property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sending_to_add_property = new Intent(MainActivity.this,adding_property_form.class);
                startActivity(sending_to_add_property);
            }
        });


        /* Property adding work ends from here */






    }

    public void SignOut()
    {
        auth.signOut();
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(authListener != null)
        {
            auth.removeAuthStateListener(authListener);
        }
    }
}
