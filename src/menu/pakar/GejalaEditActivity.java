package menu.pakar;

import menu.pakar.database.DatabaseHandler;
import menu.pakar.entity.Gejala;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GejalaEditActivity extends Activity {
	private TextView txtID;
	private TextView txtNama;
	private TextView txtPertanyaan;

	private DatabaseHandler database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gejala_edit);

		database = new DatabaseHandler(this);

		txtID = (TextView) findViewById(R.id.txt_gejala_edit_id);
		txtNama = (TextView) findViewById(R.id.txt_gejala_edit_nama);
		txtPertanyaan = (TextView) findViewById(R.id.txt_gejala_edit_saran);

		Gejala gejala = database.getGejala(this.getIntent()
				.getStringExtra("id"));
		txtID.setText(gejala.getId());
		txtNama.setText(gejala.getNama());
		txtPertanyaan.setText(gejala.getPertanyaan());
		
		findViewById(R.id.btn_gejala_edit_submit).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				database.updateGejala(new Gejala(txtID.getText().toString(), txtNama.getText().toString(), txtPertanyaan.getText().toString()));
				startActivity(new Intent(GejalaEditActivity.this,TableGejalaActivity.class));
				finish();
			}
		});
		
		findViewById(R.id.btn_gejala_edit_cancel).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(GejalaEditActivity.this,TableGejalaActivity.class));
				finish();
			}
		});

	}
}
