package practice.code.com.sourcechina.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import practice.code.com.sourcechina.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base2);
        APP.activity = this;
    }
}
