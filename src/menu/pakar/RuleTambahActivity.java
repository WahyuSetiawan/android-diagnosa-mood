package menu.pakar;

import java.util.List;

import menu.pakar.database.DatabaseHandler;
import menu.pakar.entity.Gangguan;
import menu.pakar.entity.Gejala;
import menu.pakar.entity.Rule;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class RuleTambahActivity extends Activity {
	// private TextView txtid;
	private TextView txtkondisisekarang;
	private TextView txtBilaBenar;
	private TextView txtBilaSalah;

	private String id = "";
	private String BilaBenar = "";
	private String BilaSalah = "";

	public static Rule[] ruleContainer = new Rule[3];

	private DatabaseHandler database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rule_tambah);

		database = new DatabaseHandler(this);

		// txtid = (TextView) findViewById(R.id.txt_rule_tambah_id);
		txtkondisisekarang = (TextView) findViewById(R.id.txt_rule_tambah_nama);
		txtBilaBenar = (TextView) findViewById(R.id.Rule_tambah_bila_benar);
		txtBilaSalah = (TextView) findViewById(R.id.rule_tambah_bila_salah);

		for (Rule rule : ruleContainer) {
			rule = new Rule();
		}

		// txtid.setText(database.getNewIDRule());

		List<Gejala> itemGejalas = database.getAllGejala();
		final String[] items = new String[itemGejalas.size()];

		for (int i = 0; i < items.length; i++) {
			items[i] = itemGejalas.get(i).getPertanyaan();
		}

		// set item gangguan
		List<Gangguan> itemGangguan = database.getAllGangguan();
		final String[] itemsGangguan = new String[itemGangguan.size()];

		for (int i = 0; i < itemsGangguan.length; i++) {
			itemsGangguan[i] = itemGangguan.get(i).getNama();
		}

		txtkondisisekarang.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						RuleTambahActivity.this);

				alert.setTitle("Pilih lanjutan")
						.setNegativeButton("Gejala",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
												RuleTambahActivity.this);

										alertBuilder.setTitle("Ubah Gejala");

										LayoutInflater inflater = getLayoutInflater();

										alertBuilder
												.setItems(
														items,
														new DialogInterface.OnClickListener() {

															@Override
															public void onClick(
																	DialogInterface dialog,
																	int which) {

																id = database
																		.getIDGejala(
																				items[which])
																		.toString();

																txtkondisisekarang
																		.setText(items[which]);
															}
														});
										AlertDialog alertDialog = alertBuilder
												.create();
										alertDialog.show();

									}
								})
						.setPositiveButton("Gangguan",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
												RuleTambahActivity.this);

										alertBuilder.setTitle("Ubah Gangguan");

										LayoutInflater inflater = getLayoutInflater();

										alertBuilder
												.setItems(
														itemsGangguan,
														new DialogInterface.OnClickListener() {

															@Override
															public void onClick(
																	DialogInterface dialog,
																	int which) {
																id = database
																		.getIDGangguan(itemsGangguan[which]);

																txtkondisisekarang
																		.setText(itemsGangguan[which]);
															}
														});
										AlertDialog alertDialog = alertBuilder
												.create();
										alertDialog.show();
									}
								});

				AlertDialog alertDialog = alert.create();
				alertDialog.show();
			}
		});

		findViewById(R.id.Rule_tambah_bila_benar).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						AlertDialog.Builder alert = new AlertDialog.Builder(
								RuleTambahActivity.this);

						alert.setTitle("Pilih lanjutan")
								.setNegativeButton("Gejala",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
														RuleTambahActivity.this);

												alertBuilder
														.setTitle("Ubah Gejala");

												LayoutInflater inflater = getLayoutInflater();

												alertBuilder
														.setItems(
																items,
																new DialogInterface.OnClickListener() {

																	@Override
																	public void onClick(
																			DialogInterface dialog,
																			int which) {
																		BilaBenar = database
																				.getIDGejala(items[which]);
																		txtBilaBenar
																				.setText(items[which]);
																	}
																});
												AlertDialog alertDialog = alertBuilder
														.create();
												alertDialog.show();

											}
										})
								.setPositiveButton("Gangguan",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
														RuleTambahActivity.this);

												alertBuilder
														.setTitle("Ubah Gangguan");

												LayoutInflater inflater = getLayoutInflater();

												alertBuilder
														.setItems(
																itemsGangguan,
																new DialogInterface.OnClickListener() {

																	@Override
																	public void onClick(
																			DialogInterface dialog,
																			int which) {
																		BilaBenar = database
																				.getIDGangguan(itemsGangguan[which]);
																		txtBilaBenar
																				.setText(itemsGangguan[which]);
																	}
																});
												AlertDialog alertDialog = alertBuilder
														.create();
												alertDialog.show();
											}
										});

						AlertDialog alertDialog = alert.create();
						alertDialog.show();
					}
				});

		findViewById(R.id.rule_tambah_bila_salah).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						AlertDialog.Builder alert = new AlertDialog.Builder(
								RuleTambahActivity.this);

						alert.setTitle("Pilih lanjutan")
								.setNegativeButton("Gejala",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
														RuleTambahActivity.this);

												alertBuilder
														.setTitle("Ubah Gejala");

												LayoutInflater inflater = getLayoutInflater();

												alertBuilder
														.setItems(
																items,
																new DialogInterface.OnClickListener() {

																	@Override
																	public void onClick(
																			DialogInterface dialog,
																			int which) {
																		BilaSalah = database
																						.getIDGejala(items[which]);

																		txtBilaSalah
																				.setText(items[which]);
																	}
																});
												AlertDialog alertDialog = alertBuilder
														.create();
												alertDialog.show();

											}
										})
								.setPositiveButton("Gangguan",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
														RuleTambahActivity.this);

												alertBuilder
														.setTitle("Ubah Gangguan");

												LayoutInflater inflater = getLayoutInflater();

												alertBuilder
														.setItems(
																itemsGangguan,
																new DialogInterface.OnClickListener() {

																	@Override
																	public void onClick(
																			DialogInterface dialog,
																			int which) {
																		BilaSalah = database
																						.getIDGangguan(itemsGangguan[which]);

																		txtBilaSalah
																				.setText(itemsGangguan[which]);
																	}
																});
												AlertDialog alertDialog = alertBuilder
														.create();
												alertDialog.show();
											}
										});

						AlertDialog alertDialog = alert.create();
						alertDialog.show();
					}
				});

		findViewById(R.id.btn_rule_tambah_submit).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						if (!id.equals("")) {
							if (txtBilaBenar.getText().toString().equals("")) {
								BilaBenar = "NULL";
							}

							if (txtBilaSalah.getText().toString().equals("")) {
								BilaSalah = "NULL";
							}
							if (database.insertRule(new Rule(id, BilaBenar,
									BilaSalah))) {

								AlertDialog.Builder alert = new AlertDialog.Builder(
										RuleTambahActivity.this);

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
																RuleTambahActivity.this,
																RuleTambahActivity.class));
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
														finish();
													}
												});

								AlertDialog alertMake = alert.create();
								alertMake.show();
							} else {
								AlertDialog.Builder alert = new AlertDialog.Builder(
										RuleTambahActivity.this);

								alert.setTitle("Peringatan").setMessage(
										"Pastikan data anda telah benar");
								AlertDialog alertMake = alert.create();
								alertMake.show();
							}

						} else {
							Toast.makeText(RuleTambahActivity.this,
									"Kondisi harus diisi", 10).show();
						}
					}
				});

		findViewById(R.id.btn_rule_tambah_cancel).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(RuleTambahActivity.this,
								TableRuleActivity.class));
						finish();
					}
				});

	}

}