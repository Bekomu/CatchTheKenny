package com.berkayozisik.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtTime;
    TextView txtScore;
    int score;
    ImageView k1;
    ImageView k2;
    ImageView k3;
    ImageView k4;
    ImageView k5;
    ImageView k6;
    ImageView k7;
    ImageView k8;
    ImageView k9;
    ImageView[] imgArray;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = 0;
        txtTime = (TextView) findViewById(R.id.txtTime);
        txtScore = (TextView) findViewById(R.id.txtScore);

        k1 = (ImageView) findViewById(R.id.imageView1);
        k2 = (ImageView) findViewById(R.id.imageView2);
        k3 = (ImageView) findViewById(R.id.imageView3);
        k4 = (ImageView) findViewById(R.id.imageView4);
        k5 = (ImageView) findViewById(R.id.imageView5);
        k6 = (ImageView) findViewById(R.id.imageView6);
        k7 = (ImageView) findViewById(R.id.imageView7);
        k8 = (ImageView) findViewById(R.id.imageView8);
        k9 = (ImageView) findViewById(R.id.imageView9);

        imgArray = new ImageView[] {k1,k2,k3,k4,k5,k6,k7,k8,k9};

        hideimages();

        new CountDownTimer(10000, 500) {

            @Override
            public void onTick(long l) {
                txtTime.setText("Time : " + l/1000);
            }

            @Override
            public void onFinish() {
                txtTime.setText("Time Off");
                handler.removeCallbacks(runnable);
                for (ImageView image : imgArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

                alert.setTitle("Restart Game ?");
                alert.setMessage("Are you sure to restart game ?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // RESTART
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                alert.show();
            }
        }.start();
        }

    private void hideimages() {
        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imgArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                Random rnd = new Random();
                int i = rnd.nextInt(9);  // 0 ile 8 arasında herhangi rakam...
                imgArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this, 1000);
            }
        };
        handler.post(runnable);
    }

    public void increaseScore (View view) {
        score++;
        txtScore.setText("Score : " + score);
    }
}