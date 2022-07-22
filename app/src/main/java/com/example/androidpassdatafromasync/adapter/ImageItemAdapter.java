package com.example.androidpassdatafromasync.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.androidpassdatafromasync.R;
import com.example.androidpassdatafromasync.model.ImageModel;

import java.util.ArrayList;
import java.util.List;

public class ImageItemAdapter extends RecyclerView.Adapter {

    List<ImageModel> imageModels=new ArrayList<>();
    Context context;
    private ItemAdapter.OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = (ItemAdapter.OnItemClickListener) listener;
    }

    public ImageItemAdapter(Context context,List<ImageModel> imageModels){
        this.context=context;
        this.imageModels=imageModels;
    }

    public class ImageItemHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView thumb;

        private ImageItemHolder(View view){
            super(view);
            title=view.findViewById(R.id.title);
            thumb=view.findViewById(R.id.image);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.image_row,parent,false);
        return new ImageItemHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ImageModel imageModel=imageModels.get(position);
        ImageItemHolder imageItemHolder=(ImageItemHolder)holder;
        imageItemHolder.title.setText(imageModel.getAuthor());
        //now loading image we need to add external library which is glide i used here

        RequestOptions requestOptions=new RequestOptions();
        requestOptions.placeholder(R.drawable.bg_grey);
        requestOptions.error(R.drawable.bg_grey);

        Glide.with(context)
                .load(imageModel.getImage())
                .apply(requestOptions)
                .into(imageItemHolder.thumb);


    }

    @Override
    public int getItemCount() {
        return imageModels.size();
    }
    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextViewCreator;
        public TextView mTextViewLikes;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image);
            mTextViewCreator = itemView.findViewById(R.id.description);
            mTextViewLikes = itemView.findViewById(R.id.title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

}
