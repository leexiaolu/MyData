package com.example.leesnriud.mydata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_file, R.id.bt_sharedpreferences, R.id.bt_sqlite})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_file:
                intent = new Intent(MainActivity.this,FileActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_sharedpreferences:
                Toast.makeText(MainActivity.this,"查看项目中工具类即可",Toast.LENGTH_LONG).show();
                break;
            case R.id.bt_sqlite:
                intent = new Intent(MainActivity.this,SqliteActivity.class);
                startActivity(intent);
                break;
        }
    }
}
