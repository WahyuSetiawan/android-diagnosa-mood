package menu.pakar;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class SplashScreenActivity extends Activity {
	long milis = System.currentTimeMillis();
	long time = 0;
	long TimeInSplash;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.app_opening);
		
		TimeInSplash = calTimeMillis(4);

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					time = System.currentTimeMillis() - milis;
					
					if (time > TimeInSplash) {
						startActivity(new Intent(SplashScreenActivity.this, AppDiagnosaActivity.class));
						finish();
						break;
					}
				}
			}
		}).start();
		;
	}
	
	private long calTimeMillis(long time) {
		return time * 999;
	}
}
