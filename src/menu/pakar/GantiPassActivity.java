package menu.pakar;

import menu.pakar.database.DatabaseHandler;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class GantiPassActivity extends Activity {
	private DatabaseHandler database;
	private TextView passbaru;
	private TextView passlama;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_rubah_pass);

		passbaru = (TextView) findViewById(R.id.txt_ubah_pass_baru);
		passlama = (TextView) findViewById(R.id.txt_ubah_pass_lama);

		database = new DatabaseHandler(this);
		findViewById(R.id.btn_ubah_pass_submit).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						if (database.getIDAdmin("admin").getPass()
								.equals(passlama.getText().toString())) {
							database.ubahAdmin(passbaru.getText().toString());
							finish();
						} else {
							Toast.makeText(GantiPassActivity.this,
									"Pass Masih Salah", 10).show();
						}
					}
				});
		
		findViewById(R.id.btn_ubah_pass_kembali).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
