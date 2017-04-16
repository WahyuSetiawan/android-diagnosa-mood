package menu.pakar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.widget.Button;
import android.view.Menu;


public class MenuPakarActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		Button gejalabtn =(Button)findViewById(R.id.btn_main_menu_gejala);
		Button gangguanbtn =(Button)findViewById(R.id.btn_main_menu_gangguan);
		Button rulebtn =(Button)findViewById(R.id.btn_main_menu_rule);
		gejalabtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i =new Intent(getApplicationContext(),GejalaActivity.class);
				startActivity(i);
			}
		});
		gangguanbtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i =new Intent(getApplicationContext(),GangguanActivity.class);
				startActivity(i);
			}
		});
		rulebtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i =new Intent(MenuPakarActivity.this,RuleTambahActivity.class);
				startActivity(i);
			}
		});

	}
	
	
}
