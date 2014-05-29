package gokuchan.dicev;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends Activity {
	private EditText edt;
	private Button btn;
	private Button btngoogle;
	private Button btnsoha;
	private Button btnvdic;
	private WebView wvshow;
	private String data;
	private String URL;
	private String URL1;
	private ProgressDialog mProgressDialog;
	private ActionBarDrawerToggle mDrawerToggle;
	private ImageView img_left, img_right;
	private TextView tvtitle;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		edt = (EditText) findViewById(R.id.edt_inp);
		btn = (Button) findViewById(R.id.btn);
		wvshow = (WebView) findViewById(R.id.webview);
		btngoogle = (Button) findViewById(R.id.btngoole);
		btnsoha = (Button) findViewById(R.id.btnsoha);
		btnvdic = (Button) findViewById(R.id.btnvdic);
		tvtitle = (TextView) findViewById(R.id.tvtitle);
		img_left = (ImageView) findViewById(R.id.imgMenuLeft);
		img_right = (ImageView) findViewById(R.id.img_share);
		// wvshow.setMovementMethod(new ScrollingMovementMethod());

		final SlidingMenu menu = new SlidingMenu(getBaseContext());

		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		menu.setMenu(R.layout.slidingmenumain);
		menu.toggle(true);
		img_left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				menu.toggle(true);
			}
		});

		img_left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				menu.toggle(false);
			}
		});
		btngoogle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				URL = "https://translate.google.com/m?hl=vi&sl=en&tl=vi&ie=UTF-8&prev=_m&q=";
				data = edt.getText().toString();
				URL1 = URL + data;
				Log.v("URL", URL1);
				new Googletranslate().execute();
			}
		});
		btnsoha.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				URL = "http://m.tratu.soha.vn/?dict=en_vn&title=";
				data = edt.getText().toString();
				URL1 = URL + data;
				Log.v("URL", URL1);
				new Sohatranslate().execute();
			}
		});
		btnvdic.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				URL = "http://m.vdict.com/fsearch.php?dictionaries=eng2viet&word=";
				data = edt.getText().toString();
				URL1 = URL + data;
				Log.v("URL", URL1);
				new Vdictranslate().execute();
			}
		});

	}

	public String getServerDataGET(String targetURL)
			throws ClientProtocolException, IOException {
		{
			try {
				HttpClient client = new DefaultHttpClient();
				HttpUriRequest request = new HttpGet(targetURL);
				Log.v("link", targetURL);
				HttpResponse response = client.execute(request);
				String responseBody = "";
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					responseBody = EntityUtils.toString(entity);
					Log.v("test", responseBody);
					return responseBody;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return targetURL;
	}

	private class Googletranslate extends AsyncTask<Void, Void, Void> {
		String ltrg;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(MainActivity.this);
			mProgressDialog.setTitle("Google Translate");
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				Document document = Jsoup.connect(URL1).get();
				// Elements description = document.select("div[dir=ltr]");
				// ltrg = description.attr("ltr");
				String description = document.select("div[dir=ltr]")
						+ document.select("div").get(4).html();
				Log.v("Data3", description.toString());
				ltrg = description.toString();

			} catch (Exception e) {
				e.printStackTrace();

			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			wvshow.loadDataWithBaseURL(null, ltrg, "text/html", "UTF-8", null);
			mProgressDialog.dismiss();
		}
	}

	private class Sohatranslate extends AsyncTask<Void, Void, Void> {

		String contents;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(MainActivity.this);
			mProgressDialog.setTitle("Soha Translate");
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				Document document = Jsoup.connect(URL1).get();
				Elements description = document.select("div[id=content]");
				description.select("b").get(1).remove();
				Log.v("Data3", description.toString());
				contents = description.toString();

			} catch (Exception e) {
				e.printStackTrace();

			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			wvshow.loadDataWithBaseURL(null, contents, "text/html", "UTF-8",
					null);
			mProgressDialog.dismiss();
		}
	}

	private class Vdictranslate extends AsyncTask<Void, Void, Void> {
		String vdic;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(MainActivity.this);
			mProgressDialog.setTitle("Vdic Translate");
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				Document document = Jsoup.connect(URL1).get();
				Elements description = document
						.select("div[id=result-contents]");
				description.select("div[class=dictionary-name]").remove();
				Log.v("Data3", description.toString());
				vdic = description.toString();

			} catch (Exception e) {
				e.printStackTrace();

			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			wvshow.loadDataWithBaseURL(null, vdic, "text/html", "UTF-8", null);
			mProgressDialog.dismiss();
		}
	}
}