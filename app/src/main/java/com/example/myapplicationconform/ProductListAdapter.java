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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListAdapter extends BaseAdapter {

    private List<productListSchema> result;
    private GlobalVariable gv;

    private void getData() {
        gv = (GlobalVariable)context1.getApplicationContext();

        Call<productList> call = gv.getApi().getProductList();

        final ProductListAdapter PLA = this;

        call.enqueue(new Callback<productList>() {
            @Override
            public void onResponse(Call<productList> call, Response<productList> response) {
                result = response.body().getProductList();
                PLA.data = result;
                PLA.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<productList> call, Throwable t) {
//                Toast.makeText(context1, t.toString(), Toast.LENGTH_LONG);

            }
        });
    }


    static class ViewHolder {
        public ImageView icon;
        public TextView description;
        public TextView Pname;
    }

    private List<productListSchema> data = new ArrayList<productListSchema>();
    private LayoutInflater mInflater = null;
    private Context context1;

    public ProductListAdapter(Context context) {
        context1 = context;
        getData();
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        return data.size();

    }

    @Override
    public productListSchema getItem(int position) {

        return data.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
       ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.feedback_listitem, null);
            holder.icon = (ImageView) convertView.findViewById(R.id.UserIcon);
            holder.Pname = (TextView) convertView.findViewById(R.id.UserName);
            holder.description = (TextView) convertView.findViewById(R.id.Feedback);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.get().load(gv.getUrl()+data.get(position).getIcon()).into(holder.icon);
        holder.Pname.setText(data.get(position).getPname());
        holder.description.setText( data.get(position).getDescription());

        return convertView;
    }
}
