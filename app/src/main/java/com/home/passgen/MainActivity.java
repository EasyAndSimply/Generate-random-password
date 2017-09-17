package com.home.passgen;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final String NUMBERS = "0123456789";
    private final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_";
    private final String SYMBOLS = "<>?,./!@#$%^&*())_+{}|";
    private int numbers = 0;
    private String result;
    private TextView tvPassword;
    private Button btnGener, btnBuffer;
    private CheckBox cbNumbers;
    private CheckBox cbSimbols;
    private CheckBox cbLetters;
    private EditText edNumbers;
    StringBuilder randomPass;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    private String saved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPassword = (TextView) findViewById(R.id.tvPassword);
        cbNumbers = (CheckBox) findViewById(R.id.cbNumbers);
        cbSimbols = (CheckBox) findViewById(R.id.cbSimbols);
        cbLetters = (CheckBox) findViewById(R.id.cbLetters);
        btnGener = (Button) findViewById(R.id.btnGener);
        edNumbers = (EditText) findViewById(R.id.edNumbers);
        btnBuffer = (Button) findViewById(R.id.btnBuffer);
        myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);



        btnGener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                numbers = Integer.parseInt(edNumbers.getText().toString());
                if (cbNumbers.isChecked() | (cbSimbols.isChecked()) | (cbLetters.isChecked()) & (numbers != 0)) {
                    if (cbNumbers.isChecked()) {
                        result = result + NUMBERS;
                    }
                    if (cbSimbols.isChecked()) {
                        result = result + SYMBOLS;
                    }
                    if (cbLetters.isChecked()) {
                        result = result + LETTERS;
                    }

                    generate();
                    saved = tvPassword.getText().toString();
                } else {

                }

            }
        });

        btnBuffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text;
                text = tvPassword.getText().toString();

                myClip = ClipData.newPlainText("text", text);
                myClipboard.setPrimaryClip(myClip);

                Toast.makeText(getApplicationContext(), "Text Copied", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void generate() {
        randomPass = new StringBuilder();
        int index;
        for (int i = 0; i < numbers; i++) {
            index = new Random().nextInt(result.length());
            randomPass.append(result.charAt(index));

        }
        result = "";
        tvPassword.setText(randomPass);
    }


    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        saved = savedInstanceState.getString("saved");
        tvPassword.setText(saved);
    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("saved" , saved);

    }

}