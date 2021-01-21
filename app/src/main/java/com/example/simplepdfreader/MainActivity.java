package com.example.simplepdfreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_PERMISSION =1;
    public static ArrayList<File> mFiles = new ArrayList<>();
    RecyclerView recyclerView;
    private File folder;
    String[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.pdfs_rv);
        permission();

    }

    private void permission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                Toast.makeText(this,"Please Grant Permission",Toast.LENGTH_SHORT).show();
            }
            else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_PERMISSION);
            }
        }
        else {
            Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
            initViews();

        }

    }

    private void initViews() {
        folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        mFiles = getPdfFiles(folder);

        ArrayList<File> myPdf = getPdfFiles(Environment.getExternalStorageDirectory());
        items = new String[myPdf.size()];
        for (int i=0 ; i< items.length ;i++){
            items[i] = myPdf.get(i).getName().replace("pdf","");
        }
        PdfAdapter pdfAdapter = new PdfAdapter(this, mFiles,items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(pdfAdapter);


    }

    private ArrayList<File> getPdfFiles(File folder) {
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = folder.listFiles();
        if (files != null){
            for (File singleFile : files){
                if (singleFile.isDirectory() && !singleFile.isHidden()){
                    arrayList.addAll(getPdfFiles(singleFile));
                }
                else {
                    if (singleFile.getName().endsWith(".pdf")){
                        arrayList.add(singleFile);
                    }
                }
            }
        }
        return arrayList;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION)
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                initViews();
            }else {
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();

            }
    }
}