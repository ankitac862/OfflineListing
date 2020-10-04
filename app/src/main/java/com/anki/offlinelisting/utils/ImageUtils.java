package com.anki.offlinelisting.utils;

import android.text.TextUtils;
import android.widget.ImageView;

import com.anki.offlinelisting.R;
import com.bumptech.glide.Glide;
import androidx.databinding.BindingAdapter;

/**
 * Binding adapters
 */
public class ImageUtils {

    @BindingAdapter(value = {"imageUrl"})
    public static void loadImage(ImageView imageView, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl)) {
           Glide.with(imageView.getContext()).load(imageUrl).placeholder(R.mipmap.ic_launcher).into(imageView);
        } else {
            imageView.setImageResource(R.color.transparent);
            imageView.setBackgroundResource(R.mipmap.ic_launcher);
        }
    }


}


