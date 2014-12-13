package com.davidnuon.canadaonline.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.davidnuon.canadaonline.model.Conversation;

import java.util.ArrayList;

/**
 * Created by davidnuon on 12/13/14.
 */
public class ConverstionAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<Conversation> mConverstions;

    public ConverstionAdapter(Context mContext, ArrayList<Conversation> mConverstions) {
        this.mContext = mContext;
        this.mConverstions = mConverstions;
    }

    @Override
    public int getCount() {
        return mConverstions.size();
    }

    @Override
    public Object getItem(int i) {
        return mConverstions.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
