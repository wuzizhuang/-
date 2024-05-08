package com.example.kese.flagment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kese.Listener.OnClickListener;
import com.example.kese.Model.ListModel;
import com.example.kese.R;
import com.example.kese.adapt.CollectBoxAdapter;
import com.example.kese.adapt.ListViewAdapt;
import com.example.kese.adapt.MyPagerAdapter;
import com.example.kese.entity.CollectBoxEntity;
import com.example.kese.util.MyDataHelper;

import java.util.ArrayList;
import java.util.List;

public class CollectBoxFragement extends Fragment {
    private  String title;
    private MyDataHelper  myDataHelper;
    private List<ListModel> listModels;
    public  CollectBoxFragement(){

    }
    public static CollectBoxFragement newInstance(String title) {
        CollectBoxFragement fragement=new CollectBoxFragement();
        fragement.title=title;
        return fragement;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_collectbox,container,false);
        RecyclerView recyclerView=view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());//实例化布局管理器
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//垂直排列

        recyclerView.setLayoutManager(layoutManager);//设置布局管理器
        List<CollectBoxEntity> datas=new ArrayList<>();

        if(title=="收藏"){
            listModels=getData();
            for(int i =0;i<listModels.size();i++){
                ListModel listModel=listModels.get(i);
                CollectBoxEntity entity=new CollectBoxEntity();
                entity.setName(listModel.getName());
                entity.setPhoneNumber(listModel.getPhoneNumber());
                datas.add(entity);
            }//模拟数据
        }
        else if(title=="全部"){
            listModels=getData();
            for(int i =0;i<listModels.size();i++){
                ListModel listModel=listModels.get(i);
                CollectBoxEntity entity=new CollectBoxEntity();
                entity.setName(listModel.getName());
                entity.setPhoneNumber(listModel.getPhoneNumber());
                datas.add(entity);
            }
            //模拟数据
        }
        else if(title=="未分组"){
            listModels=getData_select(null);
            for(int i =0;i<listModels.size();i++){
                ListModel listModel=listModels.get(i);
                CollectBoxEntity entity=new CollectBoxEntity();
                entity.setName(listModel.getName());
                entity.setPhoneNumber(listModel.getPhoneNumber());
                datas.add(entity);
            }
        }
        else {
            listModels=getData_select(title);
            for(int i =0;i<listModels.size();i++){
                ListModel listModel=listModels.get(i);
                CollectBoxEntity entity=new CollectBoxEntity();
                entity.setName(listModel.getName());
                entity.setPhoneNumber(listModel.getPhoneNumber());
                datas.add(entity);
            }
        }
        CollectBoxAdapter adapter=new CollectBoxAdapter(getActivity(),datas);//实例化适配器

        adapter.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view,int position) {
                Toast.makeText(getActivity(),"点击了第"+listModels.get(position).getName()+"个",Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
        return view;
    }

    private List<ListModel> getData(){
        myDataHelper=new MyDataHelper(getContext());
        ArrayList<ListModel> listModels=myDataHelper.getAllList_byPhone();
        myDataHelper.close();
        return listModels;
    }
    private List<ListModel> getData_select(String select){
        myDataHelper=new MyDataHelper(getContext());
        ArrayList<ListModel> listModels;
        if(select==null){
            listModels=myDataHelper.getList_byselect_Null(select);
        }else {
            listModels = myDataHelper.getList_byselect(select);
        }
        myDataHelper.close();
        return listModels;
    }


}
