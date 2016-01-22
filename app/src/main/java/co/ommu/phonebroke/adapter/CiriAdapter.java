package co.ommu.phonebroke.adapter;

import java.util.ArrayList;

import co.ommu.phonebroke.R;
import co.ommu.phonebroke.models.CiriModel;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CiriAdapter extends BaseAdapter {

    LayoutInflater inflater;
    ArrayList<CiriModel> array;
    Boolean arrayStatus[] = null;

    public CiriAdapter(ArrayList<CiriModel> array, Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.array = array;
        arrayStatus = new Boolean[array.size()];
        for (int i=0; i<array.size(); i++){
            arrayStatus[i] = false;
        }
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
            vi = inflater.inflate(R.layout.item_ciri, null);
            holder.tvCiri = (TextView) vi.findViewById(R.id.textCiri);
            holder.linCiri = (LinearLayout) vi.findViewById(R.id.itemCiri);
            vi.setTag(holder);
        } else {
            holder = (Holder) vi.getTag();
        }

        holder.tvCiri.setText(array.get(position).ciri);
        //Log.i("ciri", array.get(position).ciri);

        holder.linCiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("tes id", array.get(position).id);
                if(arrayStatus[position]) {
                    arrayStatus[position] = false;
                    holder.linCiri.setBackgroundColor(Color.parseColor("#ffffff"));
                } else {
                    arrayStatus[position] = true;
                    holder.linCiri.setBackgroundColor(Color.parseColor("#00ff00"));
                }

            }
        });

        //if ((position % 2) == 0) {
        //    holder.linCiri.setBackgroundColor(Color.parseColor("#f8f8f8"));
        //} else {
        //    holder.linCiri.setBackgroundColor(Color.parseColor("#ffffff"));
        //}

        return vi;
    }

    static class Holder {
        TextView tvCiri;
        LinearLayout linCiri;
    }

}
