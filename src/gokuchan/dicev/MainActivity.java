package gokuchan.dicev;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	protected EditText edt;
	protected ImageView btn_search;
	protected Button btngoogle;
	protected Button btnsoha;
	protected Button btnvdic;
	protected WebView wvshow;
	protected String data;
	protected String URL;
	protected String URL1;
	protected ProgressDialog mProgressDialog;
	protected ImageView img_left, img_right;
	protected TextView tvtitle;
	protected ProgressBar progress;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		edt = (EditText) findViewById(R.id.edt_inp);
		btn_search = (ImageView) findViewById(R.id.btn_search);
		wvshow = (WebView) findViewById(R.id.webview);
		tvtitle = (TextView) findViewById(R.id.tvtitle);
		img_left = (ImageView) findViewById(R.id.imgMenuLeft);
		img_right = (ImageView) findViewById(R.id.img_share);
		progress = (ProgressBar) findViewById(R.id.progress);
		
		
		
		// wvshow.setMovementMethod(new ScrollingMovementMethod());

		final SlidingMenu menu = new SlidingMenu(getBaseContext());

		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		menu.setMenu(R.layout.menuleft);
		menu.toggle(true);
		img_left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				menu.toggle(true);
			}
		});
	}

}