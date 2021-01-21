package com.example.simplepdfreader;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;

public class PdfAdapter extends RecyclerView.Adapter<PdfAdapter.PdfViewHolder> {

    private Context mContext;
    private ArrayList<File> mFiles;
    String[] items;

    public PdfAdapter(Context mContext, ArrayList<File> mFiles, String[] items) {
        this.mContext = mContext;
        this.mFiles = mFiles;
        this.items = items;
    }

    @NonNull
    @NotNull
    @Override
    public PdfViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pdf,parent,false );

        return new PdfViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PdfAdapter.PdfViewHolder holder, int position) {
        holder.file_tv.setText(items[position]);
        holder.pdf_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,PdfViewActivity.class);
                intent.putExtra("position",position);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFiles.size();
    }

    public class PdfViewHolder extends RecyclerView.ViewHolder{
        private TextView file_tv;
        private ImageView file_icon;
        private LinearLayout pdf_item;

        public PdfViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            file_tv = itemView.findViewById(R.id.tv_pdf_name);
            file_icon = itemView.findViewById(R.id.img_icon);
            pdf_item = itemView.findViewById(R.id.item_pdf);
        }


    }

}
