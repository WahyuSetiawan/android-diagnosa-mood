package menu.pakar;

import menu.pakar.database.DatabaseHandler;
import menu.pakar.entity.Rule;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DiagnosisActivity extends Activity {
	private TextView txtPertanyaan;
	private Button btnYes;
	private Button btnNo;
	private DatabaseHandler database;
	private Rule ruleCurrent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diagnosa_gejala);

		database = new DatabaseHandler(this);
		txtPertanyaan = (TextView) findViewById(R.id.txt_pertanyaan_diagnosa_gejala);
		btnNo = (Button) findViewById(R.id.btn_no_diagnosa_gejala);
		btnYes = (Button) findViewById(R.id.btn_yes_diagnesa_gejala);

		ruleCurrent = database.getAllRule().get(0);
		txtPertanyaan.setText(database.getGejala(ruleCurrent.getCurrent())
				.getPertanyaan());

		btnYes.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				/*if (!ruleCurrent.getTrueString().toString().substring(0,2).equals("GA")) {
					ruleCurrent = database.getRule(ruleCurrent.getTrueString());
				}else {
					startActivity(new Intent(DiagnosisActivity.this, HasilActivity.class).putExtra("id", "NULL"));
					finish();
				}*/
				
				if (!ruleCurrent.getTrueString().equals("NULL") || !ruleCurrent.getTrueString().equals("null")) {
					ruleCurrent = database.getRule(ruleCurrent.getTrueString());
					if (ruleCurrent.getCurrent().substring(0, 2).equals("GE")) {
						//txtPertanyaan.setText(database.getGejala(ruleCurrent.getCurrent()).getPertanyaan());
						//ruleCurrent = database.getRule(ruleCurrent.getTrueString());
						txtPertanyaan.setText(database.getGejala(ruleCurrent.getCurrent()).getPertanyaan());
					} else {
						startActivity(new Intent(DiagnosisActivity.this, HasilActivity.class).putExtra("id", ruleCurrent.getCurrent()));
						finish();
					}
				}else {
					startActivity(new Intent(DiagnosisActivity.this, HasilActivity.class).putExtra("id", "NULL"));
					finish();
				}
			}
		});

		btnNo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!ruleCurrent.getFalseString().equals("NULL") || !ruleCurrent.getFalseString().equals("null")) {
					ruleCurrent = database.getRule(ruleCurrent.getFalseString());
					if (ruleCurrent.getCurrent().substring(0, 2).equals("GE")) {
						//txtPertanyaan.setText(database.getGejala(ruleCurrent.getCurrent()).getPertanyaan());
						//ruleCurrent = database.getRule(ruleCurrent.getFalseString());
						txtPertanyaan.setText(database.getGejala(ruleCurrent.getCurrent()).getPertanyaan());
					} else {
						startActivity(new Intent(DiagnosisActivity.this, HasilActivity.class).putExtra("id", ruleCurrent.getCurrent()));
						finish();
					}
				}else {
					startActivity(new Intent(DiagnosisActivity.this, HasilActivity.class).putExtra("id", "NULL"));
					finish();
				}
				/*if (!ruleCurrent.getFalseString().equals("NULL")) {
					ruleCurrent = database.getRule(ruleCurrent.getFalseString());
					if (ruleCurrent.getCurrent().substring(0, 2).equals("GE")) {
						txtPertanyaan.setText(database.getGejala(ruleCurrent.getCurrent()).getPertanyaan());
					} else {
						startActivity(new Intent(DiagnosisActivity.this, HasilActivity.class).putExtra("id", ruleCurrent.getCurrent()));
						finish();
					}
				}else {
					startActivity(new Intent(DiagnosisActivity.this, HasilActivity.class).putExtra("id", "NULL"));
					finish();
				}*/
			}
		});
	}
}
