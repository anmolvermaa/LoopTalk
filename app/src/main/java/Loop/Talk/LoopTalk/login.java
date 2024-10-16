package Loop.Talk.LoopTalk;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseAuth auth;
    TextInputLayout email, password;
    Button login, signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signin = findViewById(R.id.signin);
//        forget = findViewById(R.id.forget);

        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String em = email.getEditText().getText().toString();
                String pass = password.getEditText().getText().toString();

                if(TextUtils.isEmpty(em))
                {
                    Toast.makeText(login.this, "enter email", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(pass) ) {
                    Toast.makeText(login.this, "enter password", Toast.LENGTH_SHORT).show();

                } else if (!em.matches(emailPattern))
                {
                    Toast.makeText(login.this, "enter valid email id", Toast.LENGTH_SHORT).show();

                } else if (pass.length() < 6)
                {
                    Toast.makeText(login.this, "longer password", Toast.LENGTH_SHORT).show();
                }
                else {

                    auth.signInWithEmailAndPassword(em, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                try {
                                    Intent i = new Intent(login.this, mainn.class);
                                    startActivity(i);
                                    finish();

                                } catch (Exception e) {
                                    Toast.makeText(login.this, "Somthing went wrong", Toast.LENGTH_SHORT).show();

                                }
                            } else {
                                Toast.makeText(login.this, "Try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login.this,signin.class );
                startActivity(i);
            }
        });
//        forget.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }
}