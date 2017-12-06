package com.example.seafooler.hospitalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView ivGame;
    private ImageView ivVideo;
    private ImageView ivComp;
    private ImageView ivFamous;
    private ImageView ivMall;
    private ImageView ivCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivGame = findViewById(R.id.game);
        ivVideo = findViewById(R.id.video);
        ivComp = findViewById(R.id.comp);
        ivFamous = findViewById(R.id.famous);
        ivMall = findViewById(R.id.mall);
        ivCheck = findViewById(R.id.check);

        ivGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameIntent = new Intent(MainActivity.this,
                        InstallGameActivity.class);
                MainActivity.this.startActivity(gameIntent);
                MainActivity.this.finish();
            }
        });

    }
}
