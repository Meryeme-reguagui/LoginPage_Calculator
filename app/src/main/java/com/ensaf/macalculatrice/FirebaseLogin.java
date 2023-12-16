package com.ensaf.macalculatrice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class FirebaseLogin extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    EditText loginEmail, loginPassword;
    Button loginButton;
    TextView signupRedirectText;
    private TextView textViewGoogle, textViewFb;
    private GoogleSignInClient client;
    private static final int RC_SIGN_IN_GOOGLE = 1234;
    private FirebaseAuth mAuth;
    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_login);

        databaseHelper = new DatabaseHelper(this);

        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        signupRedirectText = findViewById(R.id.signupRedirectText);

        loginPassword.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;

            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (loginPassword.getRight() - loginPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    // Toggling password visibility
                    if (loginPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                        loginPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    } else {
                        loginPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    }
                    return true;
                }
            }
            return false;
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginEmail.getText().toString();
                String password = loginPassword.getText().toString();

                if (email.equals("") || password.equals("")) {
                    Toast.makeText(FirebaseLogin.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkCredentials = databaseHelper.checkEmailPassword(email, password);

                    if (checkCredentials) {
                        Toast.makeText(FirebaseLogin.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(FirebaseLogin.this, Menu.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(FirebaseLogin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirebaseLogin.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        textViewGoogle = findViewById(R.id.signInWithGoogle);
        textViewFb = findViewById(R.id.signInWithFb);

        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("915142196605-ht02bb4rkeo83jnau9irjg89q63c7sdr.apps.googleusercontent.com")
                .requestEmail()
                .build();

        client = GoogleSignIn.getClient(this, options);

        textViewGoogle.setOnClickListener(view -> signInWithGoogle());
        // Initialisation de la connexion Facebook
        mAuth = FirebaseAuth.getInstance();
        mCallbackManager = CallbackManager.Factory.create();

        textViewFb.setOnClickListener(view -> signInWithFacebook());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN_GOOGLE) {
            // Gestion de la réponse de Google Sign-In
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                navigateToFirebaseLogin2();
            } catch (ApiException e) {
                e.printStackTrace();
                Toast.makeText(this, "Erreur de connexion avec Google", Toast.LENGTH_SHORT).show();
            }
        } else
        {
            // Gestion de la connexion Facebook
            LoginManager.getInstance().logOut();
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
            navigateToFirebaseLogin2(); // Si vous voulez naviguer à FirebaseLogin2 après la connexion Facebook
        }
    }

    // Pour lancer l'activité correspondante à chaque bouton
    private void signInWithGoogle() {
        Intent signInIntent = client.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN_GOOGLE);
    }

    private void signInWithFacebook() {
        LoginManager.getInstance().logOut();

        // Lancer la connexion Facebook
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));


    }

    private void navigateToFirebaseLogin2() {
        Intent intent = new Intent(getApplicationContext(),Menu.class);
        startActivity(intent);
        finish();
    }
}