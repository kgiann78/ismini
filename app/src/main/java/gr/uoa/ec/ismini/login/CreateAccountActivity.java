package gr.uoa.ec.ismini.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import gr.uoa.ec.ismini.R;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Button mCreateAccount = (Button) findViewById(R.id.create_button);
        mCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptCreate();
            }
        });

        Button mGetUserAddress = (Button) findViewById(R.id.get_address_button);
        mGetUserAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateAccountActivity.this, MapsActivity.class));
            }
        });
    }

    private void attemptCreate() {
        Log.v("LoginActivity", "Create Account Pressed");
    }
}
