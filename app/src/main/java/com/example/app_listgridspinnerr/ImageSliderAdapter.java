package com.example.app_listgridspinnerr;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder> {
    private List<Integer> images;
    
    public ImageSliderAdapter(List<Integer> images) {
        this.images = images;
    }
    
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_slider, parent, false);
        return new ImageViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        int imageRes = images.get(position);
        if (holder.imageView.getWidth() > 0 && holder.imageView.getHeight() > 0) {
            loadImageWithQuality(holder, imageRes);
        } else {
            holder.imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    holder.imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    loadImageWithQuality(holder, imageRes);
                }
            });
        }
    }
    
    private void loadImageWithQuality(ImageViewHolder holder, int imageRes) {
        try {
            int viewWidth = holder.imageView.getWidth();
            int viewHeight = holder.imageView.getHeight();
            if (viewWidth <= 0 || viewHeight <= 0) {
                holder.imageView.setImageResource(imageRes);
                return;
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(holder.imageView.getContext().getResources(), imageRes, options);
            options.inSampleSize = calculateInSampleSize(options, viewWidth, viewHeight);
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            options.inDither = false;
            options.inScaled = false;
            Bitmap bitmap = BitmapFactory.decodeResource(holder.imageView.getContext().getResources(), imageRes, options);
            if (bitmap != null) {
                holder.imageView.setImageBitmap(bitmap);
            } else {
                holder.imageView.setImageResource(imageRes);
            }
        } catch (OutOfMemoryError e) {
            holder.imageView.setImageResource(imageRes);
        } catch (Exception e) {
            holder.imageView.setImageResource(imageRes);
        }
    }
    
    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
    
    @Override
    public int getItemCount() {
        return images != null ? images.size() : 0;
    }
    
    static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView;
        }
    }
}
