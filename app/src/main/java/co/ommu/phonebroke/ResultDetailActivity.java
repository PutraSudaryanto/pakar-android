package co.ommu.phonebroke;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultDetailActivity extends Activity implements View.OnClickListener {

    TextView tvRusak, tvGejala, tvPencegahan, tvSolusi;
    Button btnBack;
    String rusak, gejala, pencegahan, solusi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_detail);

        if (getIntent().getExtras() != null) {
            rusak = getIntent().getExtras().getString("rusak");
            gejala = getIntent().getExtras().getString("gejala");
            pencegahan = getIntent().getExtras().getString("pencegahan");
            solusi = getIntent().getExtras().getString("solusi");
        }
        tvRusak = (TextView) findViewById(R.id.textRusak);
        tvRusak.setText(rusak);
        tvGejala = (TextView) findViewById(R.id.textGejala);
        tvGejala.setText(gejala);
        tvPencegahan = (TextView) findViewById(R.id.textPencegahan);
        tvPencegahan.setText(pencegahan);
        tvSolusi = (TextView) findViewById(R.id.textSolusi);
        tvSolusi.setText(solusi);

        btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        int id = arg0.getId();

        if (id == R.id.buttonBack) {
            onBackPressed();
        }
    }

}

