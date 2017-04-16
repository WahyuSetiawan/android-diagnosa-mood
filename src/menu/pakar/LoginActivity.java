package menu.pakar;

import menu.pakar.database.DatabaseHandler;
import menu.pakar.entity.Admin;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.usage.UsageEvents.Event;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private EditText txtpass;
	private Button btnLoginButton;
	public static final String PASS = "pass";

	private DatabaseHandler database;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.app_login);
		txtpass = (EditText) findViewById(R.id.txt_login_pass);
		btnLoginButton = (Button) findViewById(R.id.btn_login);
		database = new DatabaseHandler(this);

		btnLoginButton.setOnKeyListener(new View.OnKeyListener() {

			@Override
			public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
				// TODO Auto-generated method stub
				if (arg2.getAction() == KeyEvent.ACTION_DOWN) {
					if (arg1 == KeyEvent.KEYCODE_ENTER) {
						login();
					}
				}
				return false;
			}
		});

		btnLoginButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (txtpass.getText().toString().equals("")) {
					startActivity(new Intent(LoginActivity.this,
							DiagnosisActivity.class));
					finish();
				} else {
					Admin admin = database.getAdmin(txtpass.getText()
							.toString());
					if (admin != null) {
						if (admin.getPass()
								.equals(txtpass.getText().toString())) {
							startActivity(new Intent(LoginActivity.this,
									MainMenuActivity.class));
							finish();
						} else {
							Toast.makeText(LoginActivity.this,
									"Password anda salah", 10).show();
						}
					}

				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_main_menu_about:
			startActivity(new Intent(LoginActivity.this, AboutActivity.class));
			break;
		case R.id.menu_main_information:
			startActivity(new Intent(LoginActivity.this,
					InformationActivity.class));
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void login() {
		if (txtpass.getText().toString().equals("")) {
			startActivity(new Intent(LoginActivity.this,
					DiagnosisActivity.class));
			finish();
		} else {
			Admin admin = database.getAdmin(txtpass.getText().toString());
			if (admin != null) {
				if (admin.getPass().equals(txtpass.getText().toString())) {
					startActivity(new Intent(LoginActivity.this,
							MainMenuActivity.class));
					finish();
				} else {
					Toast.makeText(LoginActivity.this, "Password anda salah",
							10).show();
				}
			}

		}
	}

}