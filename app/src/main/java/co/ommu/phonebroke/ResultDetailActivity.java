package co.ommu.phonebroke;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class ResultDetailActivity extends Activity implements View.OnClickListener {

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
    }

}

