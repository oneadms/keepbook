package com.keepbook.app.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.keepbook.app.R;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

public class IconItemViewHolder extends RecyclerView.ViewHolder {

    public RadiusImageView icImage;
    public TextView icTv;

    public IconItemViewHolder(@NonNull View itemView) {
        super(itemView);

        icImage = (RadiusImageView) itemView.findViewById(R.id.ic_image);
        icTv = (TextView) itemView.findViewById(R.id.ic_tv);

    }
}