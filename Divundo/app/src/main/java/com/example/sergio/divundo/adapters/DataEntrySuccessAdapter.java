package com.example.sergio.divundo.adapters;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergio.divundo.R;
import com.example.sergio.divundo.models.PicText;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DataEntrySuccessAdapter extends RecyclerView.Adapter<DataEntrySuccessAdapter.ViewHolder> {

    private List<PicText> picText;
    private int layout;
    private Activity activity;
    private DataEntrySuccessAdapter.OnItemClickListener listener;

    public DataEntrySuccessAdapter(List<PicText> picText, int layout, FragmentActivity activity, DataEntrySuccessAdapter.OnItemClickListener listener) {
        this.picText = picText;
        this.layout = layout;
        this.activity = activity;
        this.listener = listener;
    }

    @Override
    public DataEntrySuccessAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(layout, parent, false);
        DataEntrySuccessAdapter.ViewHolder vh = new DataEntrySuccessAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(DataEntrySuccessAdapter.ViewHolder holder, int position) {
        holder.bind(picText.get(position), (DataEntrySuccessAdapter.OnItemClickListener)listener);
    }

    @Override
    public int getItemCount() {
        return picText.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageViewPic;
        public TextView textViewText;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewText = (TextView) itemView.findViewById(R.id.textViewText);
            imageViewPic = (ImageView) itemView.findViewById(R.id.imageViewPic);
        }

        public void bind(final PicText picText, final OnItemClickListener listener) {

            this.textViewText.setText(picText.getText());

            // Load image with Picasso
            Picasso.get().load(picText.getPicture()).into(this.imageViewPic);
            // Click listener for each element
            this.imageViewPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(picText, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(PicText picText, int position);
    }
}
