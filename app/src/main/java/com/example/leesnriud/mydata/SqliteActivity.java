package com.example.leesnriud.mydata;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SqliteActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private MyDBOpenHelper myDBHelper;
    private StringBuilder sb;
    private int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        ButterKnife.bind(this);
        myDBHelper = new MyDBOpenHelper(SqliteActivity.this, "my.db", null, 1);
    }

    @OnClick({R.id.bt_sq1_insert, R.id.bt_sq1_query, R.id.bt_sq1_update, R.id.bt_sq1_delete})
    public void onViewClicked(View view) {
        db = myDBHelper.getWritableDatabase();
        switch (view.getId()) {
            case R.id.bt_sq1_insert:
                ContentValues values1 = new ContentValues();
                values1.put("name", "hahahaha~" + i);
                i++;
                //参数依次是：表名，强行插入null值得数据列的列名，一行记录的数据
                db.insert("person", null, values1);
                Toast.makeText(SqliteActivity.this, "插入完毕~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_sq1_query:
                sb = new StringBuilder();
                //参数依次是:表名，列名，where约束条件，where中占位符提供具体的值，指定group by的列，进一步约束
                //指定查询结果的排序方式
                Cursor cursor = db.query("person", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        int pid = cursor.getInt(cursor.getColumnIndex("personid"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        sb.append("id：" + pid + "：" + name + "\n");
                    } while (cursor.moveToNext());
                }
                cursor.close();
                Toast.makeText(SqliteActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_sq1_update:
                ContentValues values2 = new ContentValues();
                values2.put("name", "123413~");
                //参数依次是表名，修改后的值，where条件，以及约束，如果不指定三四两个参数，会更改所有行
                db.update("person", values2, "name = ?", new String[]{"hahahaha~2"});
                break;
            case R.id.bt_sq1_delete:
                //参数依次是表名，以及where条件与约束
                db.delete("person", "personid = ?", new String[]{"3"});
                break;
        }
    }
}
