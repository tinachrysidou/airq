package com.example.airq.ui.logs;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.airq.R;

import java.util.List;

public class LogsAdapter extends RecyclerView.Adapter<LogsAdapter.CustomViewHolder> {
    private List<logs> feedItemList;
    private Context mContext;

    public LogsAdapter(Context context, List<logs> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        logs feedItem = feedItemList.get(i);

        customViewHolder.time.setText(Html.fromHtml(feedItem.getTime()));


        //Setting text view title
        customViewHolder.data.setText(Html.fromHtml(feedItem.getData()));
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView time;
        protected TextView data;

        public CustomViewHolder(View view) {
            super(view);
            this.time = (TextView) view.findViewById(R.id.time);
            this.data = (TextView) view.findViewById(R.id.data);
        }
    }

}
