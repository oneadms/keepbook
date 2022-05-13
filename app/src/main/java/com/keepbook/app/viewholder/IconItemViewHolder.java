package com.keepbook.app.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.keepbook.app.R;

public class IconItemViewHolder extends RecyclerView.ViewHolder {

    public ImageView icImage;
    public TextView icTv;

    public IconItemViewHolder(@NonNull View itemView) {
        super(itemView);

        icImage = (ImageView) itemView.findViewById(R.id.ic_image);
        icTv = (TextView) itemView.findViewById(R.id.ic_tv);

    }
}