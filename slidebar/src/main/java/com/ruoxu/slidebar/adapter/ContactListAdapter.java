package com.ruoxu.slidebar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ruoxu.slidebar.model.ContactListItem;

import java.util.List;


public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactItemViewHolder> {

    private Context mContext;
    private List<ContactListItem> mList;
    private OnItemClickListener mOnItemClickListener;

    public ContactListAdapter(Context context, List<ContactListItem> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public ContactItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ContactListItemView itemView = new ContactListItemView(mContext);
        return new ContactItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactItemViewHolder holder, int position) {
        final ContactListItem item = mList.get(position);

        holder.mItemView.bindView(item);
        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(item.userName);
                }
            }
        });
        holder.mItemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemLongClick(item.userName);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        }
        return mList.size();
    }

    public class ContactItemViewHolder extends RecyclerView.ViewHolder {

        public ContactListItemView mItemView;

        public ContactItemViewHolder(ContactListItemView itemView) {
            super(itemView);
            mItemView = itemView;
        }
    }

    public List<ContactListItem> getContactListItems() {
        return mList;
    }


    public interface OnItemClickListener {
        void onItemClick(String name);
        void onItemLongClick(String name);
    }

    public void setOnItemClickListener(OnItemClickListener l) {
        mOnItemClickListener = l;
    }
}
