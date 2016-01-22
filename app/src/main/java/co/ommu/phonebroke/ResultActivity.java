package co.ommu.phonebroke;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import co.ommu.phonebroke.adapter.ResultRusakAdapter;
import co.ommu.phonebroke.models.ResultRusakModel;

public class ResultActivity extends Activity implements View.OnClickListener {

    public ArrayList<ResultRusakModel> array = new ArrayList<ResultRusakModel>();
    protected SQLiteDatabase db;
    protected Cursor cursor;
    protected ResultRusakAdapter adapter;
    TextView tvCiri;
    ListView lvResult;
    Button btnBack;
    String ciriSize, ciriID, ciriData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        db = (new DatabaseHelper(this)).getWritableDatabase();

        if (getIntent().getExtras() != null) {
            ciriSize = getIntent().getExtras().getString("ciriSize");
            ciriID = getIntent().getExtras().getString("ciriID");
            ciriData = getIntent().getExtras().getString("ciriData");
            Log.i("ciriID", ciriID);
            Log.i("ciriData", ciriData);
        }
        tvCiri = (TextView) findViewById(R.id.textCiri);
        tvCiri.setText("ciri:" + ciriData);

        getListData();

        btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(this);
    }

    private void buildWidget() {
        lvResult = (ListView) findViewById(R.id.listResult);

        adapter = new ResultRusakAdapter(array, getApplicationContext());
        lvResult.setAdapter(adapter);

        lvResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(), ResultDetailActivity.class)
                        .putExtra("rusak", array.get(position).rusak)
                        .putExtra("gejala", array.get(position).gejala)
                        .putExtra("pencegahan", array.get(position).pencegahan)
                        .putExtra("solusi", array.get(position).solusi));
            }
        });
    }

    private void getListData() {
        String query = "SELECT a._id, \""+ciriSize+"\" AS total_find, (SELECT COUNT(*) FROM pakar_rusak AS ab LEFT JOIN pakar_ciri_data AS bb ON ab._id=bb.rusak_id \n" +
                "WHERE b.rusak_id = bb.rusak_id) AS total, a.rusak, a.gejala, a.pencegahan, a.solusi FROM pakar_rusak AS a LEFT JOIN pakar_ciri_data AS b ON a._id=b.rusak_id \n" +
                "WHERE b.ciri_id IN ("+ ciriID +") GROUP BY a._id ORDER BY total DESC";
        Log.i("query:", query);
        cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                ResultRusakModel item = new ResultRusakModel();
                item.id = cursor.getString(0);
                item.total_find = cursor.getString(1);
                item.total = cursor.getString(2);
                item.rusak = cursor.getString(3);
                item.gejala = cursor.getString(4);
                item.pencegahan = cursor.getString(5);
                item.solusi = cursor.getString(6);
                array.add(item);
            } while
                    (cursor.moveToNext());
        }
        buildWidget();
    }

    @Override
    public void onClick(View arg0) {
        int id = arg0.getId();

        if (id == R.id.buttonBack) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }
}
