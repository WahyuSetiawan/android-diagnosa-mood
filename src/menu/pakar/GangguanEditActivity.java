package menu.pakar;

import menu.pakar.database.DatabaseHandler;
import menu.pakar.entity.Gangguan;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GangguanEditActivity extends Activity {
	private TextView txtid;
	private TextView txtNama;
	private TextView txtSaran;
	private TextView txtPengertian;

	private Button btnSubmit;
	private Button btnCancel;

	private DatabaseHandler database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gangguan_edit);

		database = new DatabaseHandler(this);

		txtid = (TextView) findViewById(R.id.txt_gangguan_edit_id);
		txtNama = (TextView) findViewById(R.id.txt_gangguan_edit_nama);
		txtSaran = (TextView) findViewById(R.id.txt_gangguan_edit_saran);
		txtPengertian = (TextView) findViewById(R.id.txt_gangguan_edit_pengertian);
		btnSubmit = (Button) findViewById(R.id.btn_ubah_pass_submit);
		btnCancel = (Button) findViewById(R.id.btn_gangguan_edit_cancel);

		Gangguan gangguan = database.getGangguan(getIntent().getStringExtra(
				"id"));

		txtid.setText(getIntent().getStringExtra("id"));
		txtNama.setText(gangguan.getNama());
		txtSaran.setText(gangguan.getSaran());
		txtPengertian.setText(gangguan.getPengertian());

		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!txtid.equals("") && !txtNama.equals("")
						&& !txtSaran.equals("")) {
					database.updateGangguan(new Gangguan(txtid.getText()
							.toString(), txtNama.getText().toString(), txtSaran
							.getText().toString(), txtPengertian.getText().toString()));
					startActivity(new Intent(GangguanEditActivity.this,TableGangguanActivity.class).putExtra("code", "update"));
					finish();
				}
			}
		});
		
		btnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(GangguanEditActivity.this,TableGangguanActivity.class).putExtra("code", "update"));
				finish();
			}
		});
	}
}
