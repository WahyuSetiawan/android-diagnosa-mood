package menu.pakar;

import java.util.ArrayList;
import java.util.List;

import menu.pakar.database.DatabaseHandler;
import menu.pakar.entity.Gangguan;
import menu.pakar.entity.Gejala;
import menu.pakar.entity.Rule;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Debug;
import android.provider.SyncStateContract.Columns;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TableRuleActivity extends Activity {
	private TableLayout table;
	private DatabaseHandler database;
	private GradientDrawable gdDrawable = new GradientDrawable();
	private int sizeText = 15;

	private void resetTable() {
		table.removeAllViews();

		fillTheTable();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.action_bar_table_rule, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_bar_menu_tambah:
			startActivity(new Intent(TableRuleActivity.this,
					RuleTambahActivity.class));
			finish();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.table_rule);

		database = new DatabaseHandler(this);
		table = (TableLayout) findViewById(R.id.table_rule);

		fillTheTable();

		gdDrawable.setStroke(1, 0xFF000000);

		findViewById(R.id.btn_table_rule_kembali).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						finish();
					}
				});
	}

	private void fillTheTable() {

		// membuat title dari table rule
		ArrayList<TableRow> tableRows = new ArrayList<TableRow>();

		TextView title_1 = new TextView(this);
		title_1.setText("No");
		title_1.setTextSize(sizeText);
		title_1.setPadding(10, 10, 10, 10);
		title_1.setBackgroundDrawable(gdDrawable);

		TextView title_2 = new TextView(this);
		title_2.setText("Rule");
		title_2.setTextSize(sizeText);
		title_2.setPadding(10, 10, 10, 10);
		title_2.setBackgroundDrawable(gdDrawable);

		TextView title_4 = new TextView(this);
		title_4.setText("Bila Benar");
		title_4.setTextSize(sizeText);
		title_4.setPadding(10, 10, 10, 10);
		title_4.setBackgroundDrawable(gdDrawable);

		TextView title_5 = new TextView(this);
		title_5.setText("Bila Salah");
		title_5.setTextSize(sizeText);
		title_5.setPadding(10, 10, 10, 10);
		title_5.setBackgroundDrawable(gdDrawable);

		TableRow rowTitleRow = new TableRow(this);
		rowTitleRow.addView(title_1);
		rowTitleRow.addView(title_2);
		rowTitleRow.addView(title_4);
		rowTitleRow.addView(title_5);

		tableRows.add(rowTitleRow);

		// terakhir pembuatan title table

		int noUrut = 1;

		// set item dialog
		List<Rule> itemRule = database.getAllRule();
		final String[][] items = new String[3][itemRule.size()];

		// pembuatan list table yang telah ada

		for (int i = 0; i < itemRule.size(); i++) {
			items[0][i] = itemRule.get(i).getCurrent();
			items[0][i] = itemRule.get(i).getTrueString();
			items[0][i] = itemRule.get(i).getFalseString();
		}
		
		
		//mengambil nilai untuk dimasukan kedalam pilihan
		List<Gejala> itemGejalas = database.getAllGejala();
		final String[] itemsGejala = new String[itemGejalas.size()];

		for (int i = 0; i < itemGejalas.size(); i++) {
			itemsGejala[i] = itemGejalas.get(i).getPertanyaan();
			Log.d(itemsGejala[i], itemsGejala[i]);
		}

		// set item gangguan
		List<Gangguan> itemGangguan = database.getAllGangguan();
		final String[] itemsGangguan = new String[itemGangguan.size()];

		for (int i = 0; i < itemsGangguan.length; i++) {
			itemsGangguan[i] = itemGangguan.get(i).getNama();
		}

		for (final Rule rule : database.getAllRule()) {

			TextView column_no = new TextView(this);
			column_no.setText(String.valueOf(noUrut));
			column_no.setTextSize(sizeText);
			column_no.setPadding(10, 10, 10, 10);
			column_no.setBackgroundDrawable(gdDrawable);

			TextView column_current = new TextView(this);
			column_current.setText(generateKode(rule.getCurrent()));
			column_current.setTextSize(sizeText);
			column_current.setPadding(10, 10, 10, 10);
			column_current.setBackgroundDrawable(gdDrawable);

			TextView column_true = new TextView(this);
			column_true.setText(generateKode(rule.getTrueString()));
			column_true.setTextSize(sizeText);
			column_true.setPadding(10, 10, 10, 10);
			column_true.setBackgroundDrawable(gdDrawable);

			TextView column_false = new TextView(this);
			column_false.setText(generateKode(rule.getFalseString()));
			column_false.setTextSize(sizeText);
			column_false.setPadding(10, 10, 10, 10);
			column_false.setBackgroundDrawable(gdDrawable);

			TableRow tableRow = new TableRow(this);
			tableRow.addView(column_no);
			tableRow.addView(column_current);
			tableRow.addView(column_true);
			tableRow.addView(column_false);

			column_current.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					AlertDialog.Builder alertUpdateDeleteBuilder = new AlertDialog.Builder(
							TableRuleActivity.this);

					AlertDialog alertDialog = alertUpdateDeleteBuilder
							.setNegativeButton("update",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											AlertDialog.Builder alert = new AlertDialog.Builder(
													TableRuleActivity.this);

											alert.setTitle("Pilih lanjutan")
													.setNegativeButton(
															"Gejala",
															new DialogInterface.OnClickListener() {

																@Override
																public void onClick(
																		DialogInterface dialog,
																		int which) {
																	AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
																			TableRuleActivity.this);

																	alertBuilder
																			.setTitle(
																					"Ubah Gejala")
																			.setItems(
																					itemsGejala,
																					new DialogInterface.OnClickListener() {

																						@Override
																						public void onClick(
																								DialogInterface dialog,
																								int which) {
																							database.updateRuleKondisi(
																									database.getIDGejala(itemsGejala[which]),rule.getCurrent());
																							resetTable();
																						}
																					});

																	AlertDialog alertDialog = alertBuilder
																			.create();
																	alertDialog
																			.show();

																}
															})
													.setPositiveButton(
															"Gangguan",
															new DialogInterface.OnClickListener() {

																@Override
																public void onClick(
																		DialogInterface dialog,
																		int which) {
																	AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
																			TableRuleActivity.this);

																	alertBuilder
																			.setTitle("Ubah Gangguan");

																	alertBuilder
																			.setItems(
																					itemsGangguan,
																					new DialogInterface.OnClickListener() {

																						@Override
																						public void onClick(
																								DialogInterface dialog,
																								int which) {
																							database.updateRuleKondisi(
																									database.getIDGangguan(itemsGangguan[which]),rule.getCurrent());
																							resetTable();
																						}
																					});
																	AlertDialog alertDialog = alertBuilder
																			.create();
																	alertDialog
																			.show();
																}
															});

											AlertDialog alertDialog = alert
													.create();
											alertDialog.show();
										}
									})
							.setPositiveButton("Deleted",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											database.delRule(rule.getCurrent());
											;
											resetTable();
										}
									}).create();

					alertDialog.show();
				}
			});

			column_true.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					AlertDialog.Builder alertUpdateDeleteBuilder = new AlertDialog.Builder(
							TableRuleActivity.this);

					AlertDialog alertDialog = alertUpdateDeleteBuilder
							.setNegativeButton("update",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											AlertDialog.Builder alert = new AlertDialog.Builder(
													TableRuleActivity.this);

											alert.setTitle("Pilih lanjutan")
													.setNegativeButton(
															"Gejala",
															new DialogInterface.OnClickListener() {

																@Override
																public void onClick(
																		DialogInterface dialog,
																		int which) {
																	AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
																			TableRuleActivity.this);

																	alertBuilder
																			.setTitle(
																					"Ubah Gejala")
																			.setItems(
																					itemsGejala,
																					new DialogInterface.OnClickListener() {

																						@Override
																						public void onClick(
																								DialogInterface dialog,
																								int which) {
																							database.updateRuleTrue(new Rule(
																									rule.getCurrent(),
																									database.getIDGejala(itemsGejala[which]),""));
																							resetTable();
																						}
																					});

																	AlertDialog alertDialog = alertBuilder
																			.create();
																	alertDialog
																			.show();

																}
															})
													.setPositiveButton(
															"Gangguan",
															new DialogInterface.OnClickListener() {

																@Override
																public void onClick(
																		DialogInterface dialog,
																		int which) {
																	AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
																			TableRuleActivity.this);

																	alertBuilder
																			.setTitle("Ubah Gangguan");

																	alertBuilder
																			.setItems(
																					itemsGangguan,
																					new DialogInterface.OnClickListener() {

																						@Override
																						public void onClick(
																								DialogInterface dialog,
																								int which) {
																							database.updateRuleTrue(new Rule(
																									rule.getCurrent(),
																									database.getIDGangguan(itemsGangguan[which]),""));
																							resetTable();
																						}
																					});
																	AlertDialog alertDialog = alertBuilder
																			.create();
																	alertDialog
																			.show();
																}
															});

											AlertDialog alertDialog = alert
													.create();
											alertDialog.show();
										}
									})
							.setPositiveButton("Deleted",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											database.delRule(rule.getCurrent());
											resetTable();
										}
									}).create();

					alertDialog.show();
				}
			});
			
			column_false.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					AlertDialog.Builder alertUpdateDeleteBuilder = new AlertDialog.Builder(
							TableRuleActivity.this);

					AlertDialog alertDialog = alertUpdateDeleteBuilder
							.setNegativeButton("update",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											AlertDialog.Builder alert = new AlertDialog.Builder(
													TableRuleActivity.this);

											alert.setTitle("Pilih lanjutan")
													.setNegativeButton(
															"Gejala",
															new DialogInterface.OnClickListener() {

																@Override
																public void onClick(
																		DialogInterface dialog,
																		int which) {
																	AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
																			TableRuleActivity.this);

																	alertBuilder
																			.setTitle(
																					"Ubah Gejala")
																			.setItems(
																					itemsGejala,
																					new DialogInterface.OnClickListener() {

																						@Override
																						public void onClick(
																								DialogInterface dialog,
																								int which) {
																							database.updateRuleFalse(new Rule(
																									rule.getCurrent(),"",
																									database.getIDGejala(itemsGejala[which])));
																							resetTable();
																						}
																					});

																	AlertDialog alertDialog = alertBuilder
																			.create();
																	alertDialog
																			.show();

																}
															})
													.setPositiveButton(
															"Gangguan",
															new DialogInterface.OnClickListener() {

																@Override
																public void onClick(
																		DialogInterface dialog,
																		int which) {
																	AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
																			TableRuleActivity.this);

																	alertBuilder
																			.setTitle("Ubah Gangguan");

																	alertBuilder
																			.setItems(
																					itemsGangguan,
																					new DialogInterface.OnClickListener() {

																						@Override
																						public void onClick(
																								DialogInterface dialog,
																								int which) {
																							database.updateRuleFalse(new Rule(
																									rule.getCurrent(),"",
																									database.getIDGangguan(itemsGangguan[which])));
																							resetTable();
																						}
																					});
																	AlertDialog alertDialog = alertBuilder
																			.create();
																	alertDialog
																			.show();
																}
															});

											AlertDialog alertDialog = alert
													.create();
											alertDialog.show();
										}
									})
							.setPositiveButton("Deleted",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											database.delRule(rule.getCurrent());
											resetTable();
										}
									}).create();

					alertDialog.show();
				}
			});

			noUrut++;
			tableRows.add(tableRow);
		}

		for (TableRow tableRow : tableRows) {
			this.table.addView(tableRow);
		}
	}

	public String generateKode(String nama) {
		if (nama.substring(0, 2).equals("GE")) {
			return database.getGejala(nama).getPertanyaan();
		} else if (nama.substring(0, 2).equals("GA")) {
			return database.getGangguan(nama).getNama();
		} else {
			return "";
		}
	}

	public View.OnClickListener OnClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub

		}
	};
}
