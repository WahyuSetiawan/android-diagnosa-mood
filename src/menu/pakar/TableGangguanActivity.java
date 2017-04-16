package menu.pakar;

import java.util.ArrayList;

import menu.pakar.database.DatabaseHandler;
import menu.pakar.entity.Admin;
import menu.pakar.entity.Gangguan;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TableGangguanActivity extends Activity {
	private TableLayout table;
	private GradientDrawable gdDrawable = new GradientDrawable();
	private int sizeText = 15;
	private DatabaseHandler database;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.table_gangguan);

		table = (TableLayout) findViewById(R.id.table_gangguan);
		database = new DatabaseHandler(this);

		gdDrawable.setStroke(1, 0xFF000000);

		fillTheTable();
	}

	private void resetTable() {
		table.removeAllViews();

		fillTheTable();
	}

	private void fillTheTable() {
		ArrayList<TableRow> tableRows = new ArrayList<TableRow>();

		TextView title_1 = new TextView(this);
		title_1.setText("No");
		title_1.setTextSize(sizeText);
		title_1.setPadding(10, 10, 10, 10);
		//title_1.setBackgroundDrawable(gdDrawable);
		TextView title_2 = new TextView(this);
		title_2.setText("id");
		title_2.setTextSize(sizeText);
		title_2.setPadding(10, 10, 10, 10);
		//title_2.setBackgroundDrawable(gdDrawable);
		TextView title_3 = new TextView(this);
		title_3.setText("nama gangguan");
		title_3.setTextSize(sizeText);
		title_3.setPadding(10, 10, 10, 10);
		//title_3.setBackgroundDrawable(gdDrawable);
		TextView title_4 = new TextView(this);
		title_4.setText("saran");
		title_4.setTextSize(sizeText);
		title_4.setPadding(10, 10, 10, 10);
		//title_4.setBackgroundDrawable(gdDrawable);
		TextView title5 = new TextView(this);
		title5.setText("Pengertian");
		title5.setTextSize(sizeText);
		title5.setPadding(10, 10, 10, 10);
		//title5.setBackgroundDrawable(gdDrawable);
		
		TableRow rowTitleRow = new TableRow(this);
		rowTitleRow.addView(title_1);
		rowTitleRow.addView(title_2);
		rowTitleRow.addView(title_3);
		rowTitleRow.addView(title_4);
		rowTitleRow.addView(title5);

		tableRows.add(rowTitleRow);

		int noUrut = 1;

		for (final Gangguan gangguan : database.getAllGangguan()) {
			TextView column1 = new TextView(this);
			column1.setText(String.valueOf(noUrut++));
			column1.setTextSize(sizeText);
			column1.setPadding(10, 10, 10, 10);
			//column1.setBackgroundDrawable(gdDrawable);
			TextView column2 = new TextView(this);
			column2.setText(gangguan.getId());
			column2.setTextSize(sizeText);
			column2.setPadding(10, 10, 10, 10);
			//column2.setBackgroundDrawable(gdDrawable);
			TextView column3 = new TextView(this);
			column3.setText(gangguan.getNama());
			column3.setTextSize(sizeText);
			column3.setPadding(10, 10, 10, 10);
			//column3.setBackgroundDrawable(gdDrawable);
			TextView column4 = new TextView(this);
			column4.setText(gangguan.getSaran());
			column4.setTextSize(sizeText);
			column4.setPadding(10, 10, 10, 10);
			//column4.setBackgroundDrawable(gdDrawable);
			TextView column5 = new TextView(this);
			column5.setText(gangguan.getPengertian());
			column5.setPadding(10, 10, 10, 10);
			column5.setTextSize(sizeText);
			//column5.setBackgroundDrawable(gdDrawable);

			TableRow tableRow = new TableRow(this);
			tableRow.addView(column1);
			tableRow.addView(column2);
			tableRow.addView(column3);
			tableRow.addView(column4);
			tableRow.addView(column5);

			tableRow.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					AlertDialog.Builder alertroot = new AlertDialog.Builder(
							TableGangguanActivity.this);

					alertroot.setNegativeButton("Ubah",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									startActivity(new Intent(
											TableGangguanActivity.this,
											GangguanEditActivity.class)
											.putExtra("id", gangguan.getId()));
									finish();
								}
							});

					alertroot.setPositiveButton("Hapus",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									AlertDialog.Builder alert = new AlertDialog.Builder(
											TableGangguanActivity.this);

									alert.setTitle("Hapus Ganggan");
									alert.setMessage("Apakah Anda yakin menghapus "
											+ gangguan.getId());
									alert.setNegativeButton(
											"Ya",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													database.delGangguan(gangguan
															.getId());
													resetTable();
												}
											});
									alert.setPositiveButton(
											"Tidak",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													dialog.cancel();
												}
											});
									AlertDialog dialog1 = alert.create();
									dialog1.show();
								}
							});

					AlertDialog showAlertDialog = alertroot.create();
					showAlertDialog.show();
				}
			});

			tableRows.add(tableRow);
		}

		for (TableRow tableRow : tableRows) {
			this.table.addView(tableRow);
		}
	}
}
