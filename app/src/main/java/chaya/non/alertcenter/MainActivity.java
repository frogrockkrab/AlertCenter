package chaya.non.alertcenter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText edtUser,edtPW;
    private Button btnLogin;
    private String username,password;
    ProgressDialog pDialog;
    Intent home;
    int returnCode ;
    String returnMsg,str;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    //private static final String url = "http://192.168.1.34/AlertCenter/Login_test.php";
    private static final String url = "http://192.168.0.102/AlertCenter/Login.php";
    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edtUser = (EditText) findViewById(R.id.edtUN);
        edtPW = (EditText) findViewById(R.id.edtPW);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        home = new Intent(this,Home.class);

        pref = getSharedPreferences("login",MODE_PRIVATE);
        editor = pref.edit();
        str = pref.getString("user","noreccrd");
        checkPref();
    }

    private void checkPref() {
        if(!str.equals("noreccrd")){
            startActivity(home);
            finish();
        }
    }

    public void Register(View view) {
        Intent regis = new Intent(this,Register.class);
        startActivity(regis);
    }

    public void submit(View view) {
        username=edtUser.getText().toString();
        password=edtPW.getText().toString();

        if(!username.isEmpty() && !password.isEmpty()){
            pDialog = new ProgressDialog(this);
            pDialog.setMessage("Logged in");
            pDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.hide();
                        try{
                            JSONObject object = new JSONObject(response) ;
                            returnCode = object.getInt("code");
                            returnMsg = object.getString("return");
                            if (returnCode == 0){
                                Toast.makeText(MainActivity.this,returnMsg, Toast.LENGTH_SHORT).show();
                            }
                            else {
                                sharedPref();
                                startActivity(home);
                                finish();
                                Toast.makeText(MainActivity.this,"Login Successful", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.hide();
                        Toast.makeText(MainActivity.this, "Error Response ", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                protected Map<String, String> getParams()  {
                    Map<String, String> params = new HashMap<>();
                    params.put("Email", username);
                    params.put("Password", password);
                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(stringRequest);
            pDialog.dismiss();
        }
        else Toast.makeText(MainActivity.this, "Press input Username or Password", Toast.LENGTH_SHORT).show();
    }

    private void sharedPref() {
        editor = editor.putString("user",returnMsg);
        editor.commit();
    }

}
