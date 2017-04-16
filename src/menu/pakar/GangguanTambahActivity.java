package menu.pakar;

import menu.pakar.database.DatabaseHandler;
import menu.pakar.entity.Gangguan;
import menu.pakar.entity.Rule;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GangguanTambahActivity extends Activity {
	private TextView txtID;
	private TextView txtNama;
	private TextView txtSaran;
	private TextView txtPengertian;

	private Button btnSubmit;
	private Button btnCancel;

	private DatabaseHandler database;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gangguan_tambah);
		
		database = new DatabaseHandler(this);
		
		txtID = (TextView) findViewById(R.id.txt_rule_tambah_id);
		txtNama = (TextView) findViewById(R.id.txt_rule_tambah_nama);
		txtSaran = (TextView) findViewById(R.id.txt_gangguan_tambah_saran);
		txtPengertian = (TextView) findViewById(R.id.txt_gangguan_edit_pengertian);
		btnSubmit = (Button) findViewById(R.id.btn_rule_tambah_submit);
		btnCancel = (Button) findViewById(R.id.btn_rule_tambah_cancel);
		
		
		txtID.setText(database.getNewIDGangguan().toString());
		
		btnSubmit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (database.insertGangguan(new Gangguan(txtID.getText().toString(), txtNama.getText().toString(), txtSaran.getText().toString(), txtPengertian.getText().toString()))) {

					AlertDialog.Builder alert = new AlertDialog.Builder(
							GangguanTambahActivity.this);

					alert.setTitle("Masukan Kembali")
							.setMessage("Data berhasil diinputkan")
							.setNegativeButton(
									"Ya",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated
											// method stub
											startActivity(new Intent(
													GangguanTambahActivity.this,
													GangguanTambahActivity.class));
											finish();
										}
									})
							.setPositiveButton(
									"tidak",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated
											// method stub
											startActivity(new Intent(GangguanTambahActivity.this, TableGangguanActivity.class));
											finish();
										}
									});

					AlertDialog alertMake = alert.create();
					alertMake.show();
				} else {
					AlertDialog.Builder alert = new AlertDialog.Builder(
							GangguanTambahActivity.this);
					
					alert.setTitle("Peringatan").setMessage("Pastikan data anda telah benar");
					AlertDialog alertMake = alert.create();
					alertMake.show();
				}

			}
		});
		
		btnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}