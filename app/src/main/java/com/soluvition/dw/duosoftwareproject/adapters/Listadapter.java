package com.soluvition.dw.duosoftwareproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soluvition.dw.duosoftwareproject.R;
import com.soluvition.dw.duosoftwareproject.model.Ticket;
import com.soluvition.dw.duosoftwareproject.model.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Macbook on 6/17/17.
 */

public class Listadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Ticket> mDataSet;
    private Context mContext;
    private FriendlistCallback mCallback;

    public Listadapter(Context context, FriendlistCallback callback, List<Ticket> dataSet) {
        mContext = context;
        mCallback = callback;
        mDataSet = dataSet;

    }

    public void addAll(List<Ticket> dataSet) {
        mDataSet.clear();
        notifyDataSetChanged();

        mDataSet.addAll(dataSet);
        notifyDataSetChanged();
    }

    public void addItem(Ticket item) {
        mDataSet.add(item);
        notifyDataSetChanged();
    }

    public void updateItem(int position, Ticket item) {
        if (mDataSet.size() != 0) {
            mDataSet.set(position, item);
            notifyItemChanged(position);
        }
    }

    public void remove(int index) {
        if (index != -1) {
            mDataSet.remove(index);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        mDataSet.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell, parent, false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder mainHolder, int position) {
        Ticket post = mDataSet.get(position);
        Holder holder = (Holder) mainHolder;

        if (post != null) {

            holder.tvType.setText(post.type);
            holder.tvName.setText(post.requester.name);
            holder.staus.setText(post.status);
            holder.subject.setText(post.subject);
            holder.priority.setText(post.priority);

        }

    }

    @Override
    public int getItemCount() {
        return mDataSet == null ? 0 : mDataSet.size();
    }

    public class Holder extends RecyclerView.ViewHolder {


        @BindView(R.id.subject)
        TextView subject;

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.tv_type)
        TextView tvType;

        @BindView(R.id.staus)
        TextView staus;

        @BindView(R.id.priority)
        TextView priority;

        @BindView(R.id.re_view)
        LinearLayout view;


        @OnClick(R.id.re_view)
        public void onClickNext(View v) {

//            mCallback.onClicknext(mDataSet.get(getAdapterPosition()).getUser_id(), mDataSet.get(getAdapterPosition()).getIs_friend(), getAdapterPosition());
        }


        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface FriendlistCallback {

        void onClicknext(int userId, int Is_friend, int position);


    }

}