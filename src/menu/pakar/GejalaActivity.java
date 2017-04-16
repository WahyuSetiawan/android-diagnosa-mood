package menu.pakar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class GejalaActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_gejala);
		
		Button btntambah = (Button) findViewById(R.id.tambahgejala_btn);
		btntambah.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(GejalaActivity.this,GejalaTambahActivity.class);
				startActivity(i);
			}
		});
		
		Button btnedit = (Button) findViewById(R.id.editgejala_btn);
		btnedit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(GejalaActivity.this,TableGejalaActivity.class);
				startActivity(i);
			}
		});
		
		findViewById(R.id.hapusgejala_btn).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(GejalaActivity.this, TableGejalaActivity.class));
				finish();
			}
		});
		
		Button btnback = (Button) findViewById(R.id.backgejala_btn);
		btnback.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});
		
		
	}

}
