package menu.pakar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class GangguanActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_gangguan);
		
		Button btntambah = (Button) findViewById(R.id.tambahgangguan_btn);
		btntambah.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(GangguanActivity.this,GangguanTambahActivity.class);
				startActivity(i);
			}
		});
		
		Button btnedit = (Button) findViewById(R.id.editgangguan_btn);
		btnedit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(GangguanActivity.this,TableGangguanActivity.class);
				startActivity(i);
			}
		});
		
		findViewById(R.id.editgangguan_btn1).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(GangguanActivity.this, TableGangguanActivity.class));
			}
		});
		Button btnback = (Button) findViewById(R.id.backgangguan_btn);
		btnback.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});
		
		
	}

}
