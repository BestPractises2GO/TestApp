package de.testapp.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.testapp.R;

public class PartBAdapter extends RecyclerView.Adapter{

    private List<String> items = new ArrayList<>();
    private BAdapterListener mListener;

    public PartBAdapter(){

    }

    public void setListener(BAdapterListener listener){
        this.mListener = listener;
    }

    public void addItems(List<String> items){
        this.items = items;
        Log.i("adapter", String.valueOf(items.size()));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.partb_list, parent,false);
        return new SimpleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.i("adapter",items.get(position));
        ((SimpleViewHolder) holder).bindData(position);
    }


    public class SimpleViewHolder extends RecyclerView.ViewHolder{

        private TextView simpleTextView;
        public SimpleViewHolder(View itemView) {
            super(itemView);
            simpleTextView = (TextView) itemView.findViewById(R.id.r_text1);
        }

        public void bindData(final int position){
            String text1 = items.get(position);
            simpleTextView.setText(text1);
            simpleTextView.setOnClickListener(view -> mListener.clickListener(position));
        }
    }

    @Override
    public int getItemCount() {
        return (items != null ? items.size() : 0);
    }

    public interface BAdapterListener{
         void clickListener(int pos);
    }
}
