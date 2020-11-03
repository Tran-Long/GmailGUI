package com.example.gmailgui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;


import java.util.List;

public class MessageAdapter extends BaseAdapter {
    Context context;
    List<MessageModel> list;

    public MessageAdapter(Context context, List<MessageModel> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.message_frame, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.checkFavourite = convertView.findViewById(R.id.check_favourite);
            viewHolder.peakContent = convertView.findViewById(R.id.tv_peak_content);
            viewHolder.senderName = convertView.findViewById(R.id.tv_sender_name);
            viewHolder.senderIcon = convertView.findViewById(R.id.user_icon);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        MessageModel message =list.get(position);
        viewHolder.senderIcon.setText(String.valueOf(message.getFirstLetter()));
        viewHolder.senderIcon.setBackgroundResource(message.getBgResource());
        viewHolder.senderName.setText(message.getSender());
        viewHolder.peakContent.setText(message.getPeakContent());
        viewHolder.checkFavourite.setChecked(message.isCheckFavourite());

        viewHolder.checkFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message.setCheckFavourite(viewHolder.checkFavourite.isChecked());
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    private class ViewHolder{
        TextView senderIcon;
        TextView senderName;
        TextView peakContent;
        CheckBox checkFavourite;
    }
}
