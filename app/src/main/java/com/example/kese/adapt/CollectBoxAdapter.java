package com.example.kese.adapt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kese.Listener.OnClickListener;
import com.example.kese.R;
import com.example.kese.entity.CollectBoxEntity;

import java.util.List;

public class CollectBoxAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<CollectBoxEntity> mData;

    public CollectBoxAdapter(Context context, List<CollectBoxEntity> data){
        this.mContext = context;
        this.mData = data;
    }
    private OnClickListener onClickListener;
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        view = LayoutInflater.from(mContext).inflate(R.layout.listview_item_collect,parent,false);
        ViewHolder viweHolder=new ViewHolder(view);
        return viweHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {//绑定数据
        ViewHolder viewHolder= (ViewHolder) holder;
        CollectBoxEntity collectBoxEntity=mData.get(position);
        viewHolder.TvName.setText(collectBoxEntity.getName());
        viewHolder.TvPhoneNumber.setText(collectBoxEntity.getPhoneNumber());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击事件
                onClickListener.onClick(view, position);
            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //长按事件

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();//返回数据源的长度
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView TvName;
        private TextView TvPhoneNumber;

        public ViewHolder(@NonNull View view) {//封装View为ViewHolder
            super(view);
            TvName=view.findViewById(R.id.list_item_collect_name);
            TvPhoneNumber=view.findViewById(R.id.list_item_collect_phoneNumber);

        }
    }
}
