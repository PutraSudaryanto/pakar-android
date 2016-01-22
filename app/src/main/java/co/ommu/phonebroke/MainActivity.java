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
import android.widget.Toast;

import java.util.ArrayList;

import co.ommu.phonebroke.adapter.CiriAdapter;
import co.ommu.phonebroke.models.CiriModel;

public class MainActivity extends Activity implements View.OnClickListener {

    public ArrayList<CiriModel> array = new ArrayList<CiriModel>();
    protected SQLiteDatabase db;
    protected Cursor cursor;
    protected CiriAdapter adapter;
    ListView lvCiri;
    Button btnCompare;
    //ArrayList<String> arrayId = new ArrayList<>();
    ArrayList<String> arrayCiriId;
    ArrayList<String> arrayCiri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = (new DatabaseHelper(this)).getWritableDatabase();

        arrayCiriId = new ArrayList<String>();
        arrayCiri = new ArrayList<String>();

        btnCompare = (Button) findViewById(R.id.buttonCompare);
        btnCompare.setOnClickListener(this);

        //MyStateSaver data = (MyStateSaver) getLastNonConfigurationInstance();
        //if (data != null) {

        //} else {

        //}
        getListData();
    }

    private void buildWidget() {
        lvCiri = (ListView) findViewById(R.id.listCiri);

        /*
        adapter = new SimpleCursorAdapter(this, R.layout.view_ciri, cursor,
                new String[] { "ciri" },
                new int[] { R.id.textCiri});
        lvCiri.setAdapter(adapter);
        */

        adapter = new CiriAdapter(array, getApplicationContext());
        lvCiri.setAdapter(adapter);

        lvCiri.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (arrayCiriId.contains(array.get(position).id + "")) {
                    arrayCiriId.remove(array.get(position).id + "");
                    arrayCiri.remove(array.get(position).ciri + "");
                } else {
                    arrayCiriId.add(array.get(position).id + "");
                    arrayCiri.add(array.get(position).ciri + "");
                }
                //Toast.makeText(getApplicationContext(), "Position " + position, Toast.LENGTH_SHORT).show();
                Log.i("Ciri ID", arrayCiriId.toString());
                Log.i("Ciri", arrayCiri.toString());
            }
        });
    }

    private void getListData() {
        String query = "SELECT * FROM pakar_ciri";
        cursor = db.rawQuery(query, null);
        //arrayId = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                CiriModel item = new CiriModel();
                item.id = cursor.getString(0);
                item.ciri = cursor.getString(1);
                //Log.i("id", item.id +" "+item.ciri);
                //arrayId.add(item.id);
                array.add(item);
            } while
                (cursor.moveToNext());
        }
        buildWidget();
    }

    @Override
    public void onClick(View arg0) {
        int id = arg0.getId();

        if (id == R.id.buttonCompare) {
            // TODO Auto-generated method stub
            if (arrayCiriId.size() != 0) {
                String dataId = arrayCiriId+"", dataCiri =  arrayCiri+"";
                dataId = dataId.replace("[","").replace("]","");
                dataCiri = dataCiri.replace("[","").replace("]","");
                startActivity(new Intent(getApplicationContext(), ResultActivity.class)
                        .putExtra("ciriSize", arrayCiriId.size()+"")
                        .putExtra("ciriID", dataId)
                        .putExtra("ciriData", dataCiri));
            } else
                Toast.makeText(getBaseContext(), "Ciri tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
    }

}
