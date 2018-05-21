package chaya.non.alertcenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 4/22/2018.
 */

public class Feed extends Fragment {
    Feed_Adapter feed_adapter;
    RecyclerView recyclerView;
    List<Data> data = new ArrayList<>();
    //EndlessRecyclerViewScrollListener scrollListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycleview,container,false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.read_post);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        feed_adapter = new Feed_Adapter(data);
        getData();
        recyclerView.setAdapter(feed_adapter);

        /*ListView lv = (ListView)rootView.findViewById(R.id.setting);
        String[] clubList = {"Arsenal", "Chelsea", "Everton","Liverpool", "Man City", "Man Utd", "Spurs" };
        ArrayAdapter<String> a = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,clubList);
        lv.setAdapter(a);*/
        return rootView;
    }

    private void getData() {
        data.add(new Data("title 1", "descrip 1" , R.mipmap.ic_launcher));
        for (int i = 2;i<1000;i++) {
            data.add(new Data("title "+ Integer.toString(i), "descrip "+ Integer.toString(i), R.mipmap.ic_launcher));
        }
    }

    public void onResume() {
        super.onResume();
        ((Feed_Adapter) feed_adapter).setOnitemClickListener(new Feed_Adapter.MyClicklistener(){

            @Override
            public void onitemClick(int position, View v) {
                Toast.makeText(getContext(),"test",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
