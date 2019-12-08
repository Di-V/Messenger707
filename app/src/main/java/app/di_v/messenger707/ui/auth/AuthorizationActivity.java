package app.di_v.messenger707.ui.auth;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.di_v.messenger707.R;
import app.di_v.messenger707.ui.dialoglist.DialogListActivity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * @author di-v
 */
public class AuthorizationActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AuthorizationActivity";
    private TextInputLayout mUserNameTil;
    private TextInputLayout mEmailTil;
    private TextInputLayout mPasswordTil;
    private ImageView mUserImage;
    private EditText mUserNameField;
    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mBtnSignIn;
    private Button mBtnSignUp;
    private FirebaseAuth mAuth;
    private int mCheck;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        mUserNameTil = findViewById(R.id.til_user_name);
        mEmailTil = findViewById(R.id.til_email);
        mPasswordTil = findViewById(R.id.til_password);
        mUserImage = findViewById(R.id.auth_user_img);
        mUserNameField = findViewById(R.id.input_user_name);
        mEmailField = findViewById(R.id.email);
        mPasswordField = findViewById(R.id.input_password);
        mBtnSignIn = findViewById(R.id.btn_sign_in);
        mBtnSignIn.setOnClickListener(this);
        mBtnSignUp = findViewById(R.id.btn_sign_up);
        mBtnSignUp.setOnClickListener(this);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        //Set Check
        mCheck = 0;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btn_sign_in) {
            if (mCheck == 0) {
                mBtnSignUp.setVisibility(GONE);
                mUserImage.setVisibility(VISIBLE);
                mEmailTil.setVisibility(VISIBLE);
                mPasswordTil.setVisibility(VISIBLE);
                mEmailField.setFocusable(true);
                mCheck = 1;
            } else {
                signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
            }
        } else if (id == R.id.btn_sign_up) {
            if (mCheck == 0) {
                mBtnSignIn.setVisibility(GONE);
                mUserImage.setVisibility(VISIBLE);
                mUserNameTil.setVisibility(VISIBLE);
                mEmailTil.setVisibility(VISIBLE);
                mPasswordTil.setVisibility(VISIBLE);
                mUserNameField.setFocusable(true);
                mCheck = 1;
            } else {
                signUp(mUserNameField.getText().toString(), mEmailField.getText().toString(), mPasswordField.getText().toString());
            }
        }
    }

    private void signUp(String userName, final String email, String password) {
        Log.d(TAG, "start process signUp:" + email);

        if (!isUserNameValid(userName) & !isEmailValid(email) & !isPasswordValid(password)) {
            return;
        }

        showProgressDialog(true);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "signUpWithEmail:success");

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference mDatabase = database.getReference();
                mDatabase.child("users").child(mAuth.getUid()).child("email").setValue(email);

                Intent intent = new Intent(AuthorizationActivity.this,
                        DialogListActivity.class);
                startActivity(intent);

                AuthorizationActivity.this.finish();
            }
        });
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "starts process signIn:" + email);

        if (!isEmailValid(email) & !isPasswordValid(password)) {
            return;
        }

        showProgressDialog(true);

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success
                            Log.d(TAG, "signInWithEmail:success");

                            Intent intent = new Intent(AuthorizationActivity.this,
                                    DialogListActivity.class);
                            startActivity(intent);

                            AuthorizationActivity.this.finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            showProgressDialog(false);
                            Toast.makeText(AuthorizationActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void showProgressDialog(boolean chek) {

        ProgressBar progress_auth = findViewById(R.id.progressBar_login);

        if (chek) {
            mBtnSignIn.setVisibility(GONE);
            progress_auth.setVisibility(VISIBLE);
        } else {
            progress_auth.setVisibility(GONE);
            mBtnSignIn.setVisibility(VISIBLE);
        }
    }

    private boolean isUserNameValid(String userName) {
        if (userName.length() < 1) {
            mUserNameField.setError("Invalid name");
            return false;
        } else {
            mUserNameField.setError(null);
            return true;
        }
    }

    private boolean isEmailValid(String email) {

        if (!email.contains("@") & email.length() < 7) {
            mEmailTil.setError("Invalid Email");
            return false;
        } else {
            mEmailTil.setError(null);
            return true;
        }
    }

    private boolean isPasswordValid(String password) {

        if (password.length() < 6) {
            mPasswordTil.setError("Invalid Password");
            return false;
        } else {
            mPasswordTil.setError(null);
            return true;
        }
    }
}
