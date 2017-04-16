package menu.pakar;

import menu.pakar.database.DatabaseHandler;
import menu.pakar.entity.Gangguan;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HasilActivity extends Activity {
	private Button btnKembali;
	private TextView txtGangguan;
	private TextView txtSaran;
	private TextView txtPengertian;
	private DatabaseHandler database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diagnosa_hasil);

		database = new DatabaseHandler(this);
		btnKembali = (Button) findViewById(R.id.btn_hasil_diagnosa_kembali);
		txtGangguan = (TextView) findViewById(R.id.txt_hasil_diagnosa_gangguan);
		txtSaran = (TextView) findViewById(R.id.txt_hasil_diagnosa_saran);
		txtPengertian = (TextView) findViewById(R.id.txt_hasil_pengertian);

		if (!this.getIntent().getStringExtra("id").equals("NULL")) {
			Gangguan gangguan = database.getGangguan(this.getIntent()
					.getStringExtra("id"));
			txtGangguan.setText(gangguan.getNama());
			txtSaran.setText(gangguan.getSaran());
			txtPengertian.setText(gangguan.getPengertian());
		} else {
			txtGangguan.setText("Maaf gangguan anda tidak dapat diketahui");
			txtSaran.setText("-");
			txtPengertian.setText("-");
		}

		btnKembali.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

}
