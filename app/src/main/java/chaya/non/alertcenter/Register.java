package chaya.non.alertcenter;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    private TextView txtT1;
    private Button btn1,btn2;

    private int mYear;
    private int mMonth;
    private int mDay;

    private EditText editfname,editlname,editemail,editpass;

    ProgressDialog pDialog;

    private String firstname,lastname,email,password;

    private static final String url = "http://192.168.0.101/AlertCenter/AddMemberData.php";

    static final int DATE_DIALOG_ID = 0;
    static final int PICKER_MODE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        txtT1 = (TextView) findViewById(R.id.txtday);

        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);

        editfname = (EditText)findViewById(R.id.editfname);
        editlname = (EditText)findViewById(R.id.editlname);
        editemail = (EditText)findViewById(R.id.editemail);
        editpass = (EditText)findViewById(R.id.editpass);

        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onEditText();
                submit();
            }
        });

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // display the current date
        //updateCurrentDate();
    }

    private void onEditText() {
        firstname = editfname.getText().toString();
        lastname = editlname.getText().toString();
        email = editemail.getText().toString();
        password = editpass.getText().toString();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,PICKER_MODE,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }

    // updates the date we display in the editText
    private void updateCurrentDate() {
        txtT1.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(mDay).append("-")
                        .append(mMonth + 1).append("-")
                        .append(mYear).append(" "));
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateCurrentDate();
                }
            };

    public void submit() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading");
        pDialog.show();
        if(!firstname.isEmpty() && !lastname.isEmpty() && !email.isEmpty() && !password.isEmpty() ){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //Toast.makeText(MainActivity.this, "เพิ่มข้อมูลแล้วจ้า ", Toast.LENGTH_SHORT).show();
                            pDialog.hide();
                            finish();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Register.this, "ผิดพลาด "+error, Toast.LENGTH_SHORT).show();
                            pDialog.hide();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Firstname", editfname.getText().toString());
                    params.put("Lastname", editlname.getText().toString());
                    params.put("Email", editemail.getText().toString());
                    params.put("Password", editpass.getText().toString());
                    params.put("Day", String.valueOf(mDay));
                    params.put("Month", String.valueOf(mMonth));
                    params.put("Year", String.valueOf(mYear));
                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(stringRequest);
        }
        else Toast.makeText(Register.this, "ผิดพลาดข้อมูลไม่ครบ ", Toast.LENGTH_SHORT).show();
    }
}