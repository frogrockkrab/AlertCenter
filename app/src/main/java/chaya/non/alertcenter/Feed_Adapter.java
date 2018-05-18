package chaya.non.alertcenter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 4/26/2018.
 */

public class Feed_Adapter extends RecyclerView.Adapter<Feed_Adapter.ViewHolder> {
    private List<Data> mDataset;
    private MyClicklistener mCallback;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        ViewHolder dataObjHolder = new ViewHolder(view);
        return dataObjHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(mDataset.get(position).getTxt1());
        holder.tvDesc.setText(mDataset.get(position).getTxt2());
        holder.img.setImageResource(mDataset.get(position).getPic());
    }


    @Override
    public int getItemCount() {
        //return mDataset.size();
        return 10;
    }

    public  void setOnitemClickListener(MyClicklistener mCallback){
        this.mCallback = mCallback;
    }

    public Feed_Adapter(List<Data> mDataset) {
        this.mDataset = mDataset;
    }

    public interface MyClicklistener{
        public void onitemClick(int position,View v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvTitle;
        TextView tvDesc;
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.txtTitle);
            tvDesc = (TextView)itemView.findViewById(R.id.txtDescription);
            img = (ImageView)itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mCallback.onitemClick(getAdapterPosition(),view);
        }


    }

}
