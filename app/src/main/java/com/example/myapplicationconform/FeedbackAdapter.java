package com.example.myapplicationconform;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


class FeedbackAdapter extends BaseAdapter {

    private List<feedbackSchema> result;
    private List<feedbackSchema> data = new ArrayList<feedbackSchema>();
    private LayoutInflater mInflater = null;
    private Context context1;
    private int pid;
    final FeedbackAdapter FA = this;
    private GlobalVariable gv;

    private void getData() {
        gv = (GlobalVariable) context1.getApplicationContext();

        Call<feedback> call = gv.getApi().getFeedback(pid);

        call.enqueue(new Callback<feedback>() {
            @Override
            public void onResponse(Call<feedback> call, Response<feedback> response) {
                result = response.body().getFeedback();
                FA.data = result;
                FA.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<feedback> call, Throwable t) {

            }
        });


    }


    static class ViewHolder {
        public TextView UserName;
        public TextView Feedback;
    }

    public FeedbackAdapter(Context context, int Pid) {
        pid = Pid;
        context1 = context;
        getData();
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        return data.size();

    }

    @Override
    public Object getItem(int position) {

        return data.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.feedback_listitem, null);
            holder.UserName = (TextView) convertView.findViewById(R.id.UserName);
            holder.Feedback = (TextView) convertView.findViewById(R.id.Feedback);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.UserName.setText((String) data.get(position).getFName());
        holder.Feedback.setText((String) data.get(position).getFeedback());
        return convertView;
    }
}
