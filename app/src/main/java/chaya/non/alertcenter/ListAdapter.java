package chaya.non.alertcenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 2/11/2018.
 */

public class ListAdapter extends BaseAdapter {
    private List<Data_Setting> mDatas;
    private LayoutInflater mLayoutInflater;

    public ListAdapter(Context context, List<Data_Setting> aList) {
        mDatas = aList;
        mLayoutInflater = LayoutInflater.from(context);
    }


    static class ViewHolder {
        TextView tvTitle;
        ImageView img;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.rowline,viewGroup,false);
            holder = new ViewHolder();
            holder.tvTitle = (TextView)view.findViewById(R.id.txtTitle);
            holder.img = (ImageView)view.findViewById(R.id.imageView);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }
        String title = mDatas.get(position).getTxt1();
        holder.tvTitle.setText(title);
        holder.img.setImageResource(mDatas.get(position).getPic());

        return view;
    }
}