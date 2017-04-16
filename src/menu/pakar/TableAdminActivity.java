package menu.pakar;

import java.util.ArrayList;
import menu.pakar.database.DatabaseHandler;
import menu.pakar.entity.Admin;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TableAdminActivity extends Activity {

	private TableLayout table;
	private DatabaseHandler database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.table_admin);

		table = (TableLayout) findViewById(R.id.table_admin);
		database = new DatabaseHandler(this);

		fillTheTable();

	}

	private void fillTheTable() {
		ArrayList<TableRow> tableRows = new ArrayList<TableRow>();

		TextView title_1 = new TextView(this);
		title_1.setText("No");
		TextView title_2 = new TextView(this);
		title_2.setText("id admin");
		TextView title_3 = new TextView(this);
		title_3.setText("password");

		TableRow rowTitleRow = new TableRow(this);
		rowTitleRow.addView(title_1);
		rowTitleRow.addView(title_2);
		rowTitleRow.addView(title_3);

		tableRows.add(rowTitleRow);

		int noUrut = 1;

		for (Admin admin : database.getAllAdmin()) {
			TextView column1 = new TextView(this);
			column1.setText(String.valueOf(noUrut++));
			TextView column2 = new TextView(this);
			column2.setText(admin.getNama());
			TextView column3 = new TextView(this);
			column3.setText(admin.getPass());

			TableRow tableRow = new TableRow(this);
			tableRow.addView(column1);
			tableRow.addView(column2);
			tableRow.addView(column3);
			tableRows.add(tableRow);
		}

		for (TableRow tableRow : tableRows) {
			this.table.addView(tableRow);
		}
	}
}
