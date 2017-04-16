package menu.pakar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.app.AlertDialog;
import android.widget.Button;
import android.view.Menu;

public class MainMenuActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		
		Button gejalabtn = (Button) findViewById(R.id.btn_main_menu_gejala);
		Button gangguanbtn = (Button) findViewById(R.id.btn_main_menu_gangguan);
		Button rulebtn = (Button) findViewById(R.id.btn_main_menu_rule);
		
		gejalabtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),
						GejalaActivity.class);
				startActivity(i);
			}
		});
		
		gangguanbtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),
						GangguanActivity.class);
				startActivity(i);
			}
		});
		
		rulebtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),
						TableRuleActivity.class);
				startActivity(i);
			}
		});
		
		findViewById(R.id.btn_main_menu_cancel).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						AlertDialog.Builder builder = new AlertDialog.Builder(MainMenuActivity.this);
						
						builder.setTitle("Keluar");
						builder.setMessage("apakah anda yakin untuk keluar dari aplikasi ini").setNegativeButton("Ya", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								finish();
							}
						});
						
						builder.setPositiveButton("tidak", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								
							}
						});
						
						AlertDialog alertDialog = builder.create();
						alertDialog.show();
					}
				});
		
		findViewById(R.id.btn_main_menu_ubah_pass).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainMenuActivity.this, GantiPassActivity.class));
			}
		});
	}

}

