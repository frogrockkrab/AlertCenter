package chaya.non.alertcenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void Register(View view) {
        Intent regis = new Intent(this,Register.class);
        startActivity(regis);
    }

    public void Home(View view) {
        Intent home = new Intent(this,Home.class);
        startActivity(home);
    }
}
