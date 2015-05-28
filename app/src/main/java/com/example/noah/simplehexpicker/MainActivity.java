package com.example.noah.simplehexpicker;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Array;


public class MainActivity extends ActionBarActivity {
    RelativeLayout window;
    EditText hex;
    Button show;
    String color;
    String invertColor;
    TextView invertColorText;
    Integer r;
    Integer g;
    Integer b;
    float[] hsv = new float[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        window = (RelativeLayout) findViewById(R.id.window);
        hex = (EditText) findViewById(R.id.hex);
        show = (Button) findViewById(R.id.show);
        invertColorText = (TextView) findViewById(R.id.invertColorText);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hex.getText().toString().length() == 6) {
                    color = hex.getText().toString();
                    window.setBackgroundColor(Color.parseColor("#" + color));
                    hex.setHighlightColor(Color.parseColor("#" + color));
                    r = Integer.parseInt(color.substring(0, 2), 16);
                    g = Integer.parseInt(color.substring(2, 4), 16);
                    b = Integer.parseInt(color.substring(4,6), 16);
                    Color.RGBToHSV(r, g, b, hsv);
                    if (hsv[0]>180) {
                        hsv[0] = hsv[0] - 180;
                    }
                    else {
                        hsv[0] = hsv[0] + 180;
                    }
                    invertColor = String.format("#%06X", (0xFFFFFF & Color.HSVToColor(hsv)));
                    show.setBackgroundColor(Color.HSVToColor(hsv));
                    invertColorText.setText(invertColor);
                    invertColorText.setTextColor(Color.parseColor("#FFFFFF"));
                    invertColorText.setBackgroundColor(Color.HSVToColor(hsv));}
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
