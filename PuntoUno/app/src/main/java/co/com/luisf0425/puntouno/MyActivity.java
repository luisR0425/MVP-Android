package co.com.luisf0425.puntouno;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MyActivity extends AppCompatActivity {

    private TextView txtSum;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity);
        initializeComponents();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            fun(50,30);
                        }
                    });
                }
            }
        }).start();
    }

    private void initializeComponents(){
        txtSum = findViewById(R.id.txtSum);
    }

    void fun(int x , int y ){
        //Log.d("Sum" , String.valueOf(x+y));
        txtSum.setText(String.valueOf(x+y));
        //Toast.makeText(this, String.valueOf(x+y), Toast.LENGTH_SHORT).show();
    }

}