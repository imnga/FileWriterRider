package ru.synergy.filewriterrider;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    private final static String FINAL_NAME = "content.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //сохранение файла //создадим метод похожий на onClick позволяющий сохранить наш файл
    public void  saveText(View view){
        FileOutputStream fos = null;
        try {
        EditText textBox =(EditText) findViewById(R.id.editor);
        String text = textBox.getText().toString();
        fos = openFileOutput(FINAL_NAME,MODE_PRIVATE);
        fos.write(text.getBytes());
        Toast.makeText(this,"Файл успешно сохранен", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                  }
            }catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

    }
    //открытие файла
    public void openText(View view){
        FileInputStream fin = null;
        TextView textView = (TextView) findViewById(R.id.text);

        try {
            fin =  openFileInput(FINAL_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            textView.setText(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        } finally {
            try{
                if(fin != null){
                    fin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
    }
}