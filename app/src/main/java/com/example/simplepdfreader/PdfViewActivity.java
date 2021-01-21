package com.example.simplepdfreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class PdfViewActivity extends AppCompatActivity {

    PDFView pdfView;
    private int position=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);
        pdfView=findViewById(R.id.pdfView);
        position = getIntent().getIntExtra("position",-1);
        viewPdf();
    }

    private void viewPdf() {
        pdfView.fromFile(MainActivity.mFiles.get(position))
                .enableSwipe(true)
                .enableAnnotationRendering(true)
                .onPageScroll(new OnPageScrollListener() {
                    @Override
                    public void onPageScrolled(int page, float positionOffset) {

                    }
                })
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }
}