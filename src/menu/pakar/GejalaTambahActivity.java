package menu.pakar;

import menu.pakar.database.DatabaseHandler;
import menu.pakar.entity.Gangguan;
import menu.pakar.entity.Gejala;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GejalaTambahActivity extends Activity {

	private TextView txtID;
	private TextView txtNama;
	private TextView txtPertanyaan;

	private DatabaseHandler database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.gejala_tambah);

		database = new DatabaseHandler(this);

		txtID = (TextView) findViewById(R.id.txt_gejala_tambah_id);
		txtNama = (TextView) findViewById(R.id.txt_gejala_tambah_nama);
		txtPertanyaan = (TextView) findViewById(R.id.txt_gejala_tambah_pertanyaan);

		txtID.setText(database.getNewIDGejala().toString());

		findViewById(R.id.btn_gejala_tambah_submit).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						if (database.insertGejala(new Gejala(txtID.getText()
								.toString(), txtNama.getText().toString(),
								txtPertanyaan.getText().toString()))) {

							AlertDialog.Builder alert = new AlertDialog.Builder(
									GejalaTambahActivity.this);

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
															GejalaTambahActivity.this,
															GejalaTambahActivity.class));
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
													startActivity(new Intent(GejalaTambahActivity.this,
															TableGejalaActivity.class));
													finish();
												}
											});

							AlertDialog alertMake = alert.create();
							alertMake.show();
						} else {
							AlertDialog.Builder alert = new AlertDialog.Builder(
									GejalaTambahActivity.this);
							
							alert.setTitle("Peringatan").setMessage("Pastikan data anda telah benar");
							AlertDialog alertMake = alert.create();
							alertMake.show();
						}						
					}
				});

		findViewById(R.id.btn_gejala_tambah_cancel).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						finish();
					}
				});

	}
}
