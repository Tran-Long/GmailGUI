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

    public void setList(List<MessageModel> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.message_model, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.checkFavourite = convertView.findViewById(R.id.check_favourite);
            viewHolder.peakContent = convertView.findViewById(R.id.tv_peak_content);
            viewHolder.senderName = convertView.findViewById(R.id.tv_sender_name);
            viewHolder.subject = convertView.findViewById(R.id.tv_subject);
            viewHolder.senderIcon = convertView.findViewById(R.id.user_icon);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        MessageModel email =list.get(position);
        viewHolder.senderIcon.setText(String.valueOf(email.getFirstLetter()));
        viewHolder.senderIcon.setBackgroundResource(email.getBgResource());
        viewHolder.senderName.setText(email.getSender());
        viewHolder.peakContent.setText(email.getPeakContent());
        viewHolder.subject.setText(email.getSubject());
        viewHolder.checkFavourite.setChecked(email.isCheckFavourite());

        viewHolder.checkFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email.setCheckFavourite(viewHolder.checkFavourite.isChecked());
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    private class ViewHolder{
        TextView senderIcon;
        TextView senderName;
        TextView peakContent;
        TextView subject;
        CheckBox checkFavourite;
    }
}
