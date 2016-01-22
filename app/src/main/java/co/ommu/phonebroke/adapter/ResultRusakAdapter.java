package co.ommu.phonebroke.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import co.ommu.phonebroke.R;
import co.ommu.phonebroke.models.ResultRusakModel;

public class ResultRusakAdapter extends BaseAdapter {

    LayoutInflater inflater;
    ArrayList<ResultRusakModel> array;

    public ResultRusakAdapter(ArrayList<ResultRusakModel> array, Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.array = array;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return array.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup arg2) {
        // TODO Auto-generated method stub
        View vi = view;
        final Holder holder;
        if (vi == null) {
            holder = new Holder();
            vi = inflater.inflate(R.layout.item_result_rusak, null);
            holder.tvRusak = (TextView) vi.findViewById(R.id.textRusak);
            holder.tvFind = (TextView) vi.findViewById(R.id.textResultFind);
            holder.linResult = (LinearLayout) vi.findViewById(R.id.itemResult);
            vi.setTag(holder);
        } else {
            holder = (Holder) vi.getTag();
        }

        holder.tvRusak.setText(array.get(position).rusak);
        holder.tvFind.setText(array.get(position).total+"/"+array.get(position).total_find);

        return vi;
    }

    static class Holder {
        TextView tvRusak;
        TextView tvFind;
        LinearLayout linResult;
    }

}
