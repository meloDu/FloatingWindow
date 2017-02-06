package com.rmtd.melo.floatingwindow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText mEditText;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.ed_input);
        mButton = (Button) findViewById(R.id.btn_ok);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String et_content = mEditText.getText().toString();
                Intent intent = new Intent();
                intent.setAction("speechcontent");
                intent.putExtra("content",et_content);
                sendBroadcast(intent);
            }
        });
    }
}
