package chaya.non.alertcenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 4/22/2018.
 */

public class Setting extends Fragment implements AdapterView.OnItemClickListener {
    List<Data_Setting> data = new ArrayList<>();
    ListView lv;
    ListAdapter adt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data.add(new Data_Setting("Profile picture",R.mipmap.ic_launcher));
        data.add(new Data_Setting("Font Size",R.mipmap.ic_launcher));
        data.add(new Data_Setting("Logout",R.mipmap.ic_launcher));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_setting,container,false);
        lv = (ListView) rootView.findViewById(R.id.listview);
        adt = new ListAdapter(getContext(),data);

        lv.setAdapter(adt);
        lv.setOnItemClickListener(this);
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position==0){
            Toast.makeText(getContext(), "You choose "+String.valueOf(position)+" "+ data.get(position).getTxt1(),Toast.LENGTH_SHORT).show();
        }
    }
}