package com.floydd.instagram_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.text.BreakIterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edt1, edt2;
    private Button btn,btndata;
    private TextView txt;
    private String boxers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        // edt3=findViewById(R.id.edt3);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(MainActivity.this);
        txt=findViewById(R.id.txt);
        btndata=findViewById(R.id.btngetdata);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Box");
                    parseQuery.getInBackground("fV0wBp3Ri3", new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject object, ParseException e) {
                            if (object != null && e == null) {

                                txt.setText(object.get("name") + " ");

                            } else {
                                FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                        }
                    });
                }
                catch(Exception e){
                    FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                }
            }
        });

        btndata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boxers="";
               final ParseQuery<ParseObject> queryAll=ParseQuery.getQuery("Box");
                queryAll.whereGreaterThan("punchspeed",200);
                queryAll.setLimit(1);
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e==null){
                            if(objects.size()>0){
                                for(ParseObject box:objects)
                                {
                                    boxers=boxers +box.get("name") +"\n";
                                }
                                FancyToast.makeText(MainActivity.this, boxers , FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                            }
                            else{
                                FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                        }

                    }
                });
            }
        });

        findViewById(R.id.btnSwitchActivty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SignUpLoginActivity.class);
                startActivity(intent);
            }
        });
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











