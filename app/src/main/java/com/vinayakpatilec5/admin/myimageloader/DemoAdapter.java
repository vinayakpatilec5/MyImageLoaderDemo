package com.vinayakpatilec5.admin.myimageloader;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.MyViewHolder> {

    List<AudioData> audioDataList;
    public DemoAdapter(List<AudioData> audioDataList) {
        this.audioDataList = audioDataList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView trackName, artistName;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            trackName = (TextView) view.findViewById(R.id.trackName);
            artistName = (TextView) view.findViewById(R.id.artistName);
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AudioData audioData = audioDataList.get(position);
        holder.trackName.setText(audioData.getTrackName());
        holder.artistName.setText(audioData.getArtistName());
        new ImageLoader(holder.imageView.getContext()).
                with(audioData.getUrl()).
                loadInto(holder.imageView)
                .placeHolder(R.drawable.image_place)
                .onError(R.drawable.image_place)
                .load();

    }

    @Override
    public int getItemCount() {
        return audioDataList.size();
    }
}
