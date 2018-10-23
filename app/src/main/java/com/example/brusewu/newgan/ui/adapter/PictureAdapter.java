package com.example.brusewu.newgan.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.brusewu.newgan.R;
import com.example.brusewu.newgan.data.model.Ganhuo;
import com.example.brusewu.newgan.ui.base.BaseRecyclerViewAdapter;
import com.example.brusewu.newgan.ui.base.BaseViewHolder;
import com.example.brusewu.newgan.ui.modules.imageviewer.ImageViewerActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;

public class PictureAdapter extends BaseRecyclerViewAdapter<Ganhuo, PictureAdapter.ViewHolder> {
    private List<String> imageUrlList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.picture_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position), position);
    }

    public class ViewHolder extends BaseViewHolder<Ganhuo> {
        @BindView(R.id.picture)
        ImageView picture;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(Ganhuo gank, final int position) {
            Glide.with(itemView.getContext())
                    .load(gank.getUrl()).apply(RequestOptions.fitCenterTransform())
                    .into(picture);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageUrlList.clear();
                    for (int i = 0; i < data.size(); i++) {
                        imageUrlList.add(data.get(i).getUrl());
                    }
                    Intent intent = new Intent(itemView.getContext(), ImageViewerActivity.class);
                    intent.putExtra("CURRENT_POSITION", position);
                    intent.putStringArrayListExtra("IMAGE_URL_LIST",
                            (ArrayList<String>) imageUrlList);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
