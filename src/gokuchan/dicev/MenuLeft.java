package gokuchan.dicev;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MenuLeft extends Activity {

	protected TextView txtNdich, txtNgdich, txtLanguage, txtShare, txtRate,
			txtAppmore, txtGioithieu, txtExit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menuleft);
		txtNdich = (TextView) findViewById(R.id.tvNDich);
		txtNgdich = (TextView) findViewById(R.id.tvNgdich);
		txtLanguage = (TextView) findViewById(R.id.tvLanguage);
		txtShare = (TextView) findViewById(R.id.tvShare);
		txtRate = (TextView) findViewById(R.id.tvRate);
		txtAppmore = (TextView) findViewById(R.id.tvAppmore);
		txtGioithieu = (TextView) findViewById(R.id.tvGioithieu);
		txtExit = (TextView) findViewById(R.id.tvExit);
		
	}

}
