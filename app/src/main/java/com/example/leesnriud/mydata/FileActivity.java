package com.example.leesnriud.mydata;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * android 数据访问（文件）
 * <p>
 * Android 文件的操作模式
 * MODE_PRIVATE 默认操作模式，代表该文件是私有数据，只能够被本应用本身访问，写入的内容会覆盖原文件的内容
 * MODE_APPEND 会检验文件是否存在，存在的话会追加内容，否则创建新文件
 * MODE_WORLD_READABLE 当前文件可以被其他应用读取
 * MODE_WORLD_WRITEABLE 当前文件可以被其他应用写入
 * <p>
 * 文件的相关操作方法
 * openFileOutput(filename,mode) 打开文件输出流，就是往文件里写入数据，第二个参数是模式
 * openFileInput(filename) 打开文件输入流，就是读取文件中的信息到程序中
 * getDir(name,mode) 在app的data目录下获取或创建name对应的子目录
 * getFileDir() 获取app的data目录的file目录的绝对路径
 * String [] fileList() 返回app的data目录下的全部文件
 * deleteFile(filename) 删除app的data目录下的指定文件
 */
public class FileActivity extends AppCompatActivity {

    @BindView(R.id.editname)
    EditText editname;
    @BindView(R.id.editdetail)
    EditText editdetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnsave, R.id.btnread,R.id.btnsavesd,R.id.btnreadsd,R.id.btnreadraw,R.id.btnreadassets})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnsave:
                FileHelper fHelper = new FileHelper(this);
                String filename = editname.getText().toString();
                String filedetail = editdetail.getText().toString();
                try {
                    fHelper.save(filename, filedetail);
                    Toast.makeText(FileActivity.this, "数据写入成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(FileActivity.this, "数据写入失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnread:
                String detail = "";
                FileHelper fHelper2 = new FileHelper(getApplicationContext());
                try {
                    String fname = editname.getText().toString();
                    detail = fHelper2.read(fname);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(FileActivity.this, detail, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnsavesd:
                String filenamesd = editname.getText().toString();
                String filedetailsd = editdetail.getText().toString();
                SDFileHelper sdHelper = new SDFileHelper(this);
                try
                {
                    sdHelper.savaFileToSD(filenamesd, filedetailsd);
                    Toast.makeText(FileActivity.this, "数据写入成功", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    e.printStackTrace();
                    Toast.makeText(FileActivity.this, "数据写入失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnreadsd:
                String detailsd = "";
                SDFileHelper sdHelper2 = new SDFileHelper(this);
                try
                {
                    String filename2 = editname.getText().toString();
                    detailsd = sdHelper2.readFromSD(filename2);
                }
                catch(IOException e){
                    e.printStackTrace();
                }
                Toast.makeText(FileActivity.this, detailsd, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnreadraw:
                String str = "";
                InputStream is = getResources().openRawResource(R.raw.lee);
                try {
                    str = inputStream2String(is);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(FileActivity.this,str,Toast.LENGTH_LONG).show();
                break;
            case R.id.btnreadassets:
                String str2 = "";
                AssetManager am =  getAssets();
                try {
                    InputStream is1 = am.open("lee.txt");
                    str2 = inputStream2String(is1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(FileActivity.this,str2,Toast.LENGTH_LONG).show();
                break;
        }
    }

    //inputstream转string
    public static String inputStream2String(InputStream is) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i= -1;
        while((i=is.read())!=-1){
            baos.write(i);
        }
        return baos.toString();
    }


}
