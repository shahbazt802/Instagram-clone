package com.floydd.instagram_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edt1, edt2;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        // edt3=findViewById(R.id.edt3);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        try {


            final ParseObject box = new ParseObject("Box");
            box.put("name", edt1.getText().toString());
            box.put("punchspeed", Integer.parseInt(edt2.getText().toString()));
            box.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(MainActivity.this, box.get("name") + "is saved to the server", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                    } else {
                        FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }

                }
            });


        }
        catch (Exception e){
            FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

        }
    }
}











