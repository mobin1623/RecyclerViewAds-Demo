package com.neuralit.recyclerviewads_demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class TestAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private  final int ITEM_TYPE_TEST = 0;
    private  final int ITEM_TYPE_BANNER_AD = 1;
    private Context context;
    private List<TestModel> list;
    private final LayoutInflater layoutInflater;

    public TestAdapter(Context context, List<TestModel> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
        }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        AdView adview;

        switch (viewType){
            case ITEM_TYPE_BANNER_AD :

//                view = layoutInflater.inflate(R.layout.ad_view,parent,false);
                adview = new AdView(context);
                adview.setAdSize( AdSize.BANNER);

                adview.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
                float density = context.getResources().getDisplayMetrics().density;
                int height = Math.round(AdSize.BANNER.getHeight() * density);
                AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, height);
                adview.setLayoutParams(params);
                AdRequest request = new AdRequest.Builder().build();
                adview.loadAd(request);

                return new MyAdViewHolder(adview);

            case ITEM_TYPE_TEST:
                view = layoutInflater.inflate(R.layout.recycle_item,parent,false);
                return new Holder(view);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof Holder){
           Holder h = (Holder) holder;
           h.textView.setText(list.get(position).getName());

        }

    }

    @Override
    public int getItemViewType(int position) {
//       if (position % 5 == 0){
//           return ITEM_TYPE_BANNER_AD;
//       }else {
//           return ITEM_TYPE_TEST;
//       }
        return (position % 8 == 0) ? ITEM_TYPE_BANNER_AD : ITEM_TYPE_TEST;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView textView;

        public Holder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv);
        }
    }

    //Banner Ad View Holder
    class MyAdViewHolder extends RecyclerView.ViewHolder
    {
        MyAdViewHolder(View itemView)
        {
            super(itemView);
        }
    }
}
