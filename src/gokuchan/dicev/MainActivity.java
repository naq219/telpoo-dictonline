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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends Activity {

	private Button btnsearch;
	private static int REQUEST_CODE = 1;
	private TextView tvtitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btnsearch = (Button) findViewById(R.id.btn_search);
		tvtitle = (TextView) findViewById(R.id.tvtitle);
		final SlidingMenu menu = new SlidingMenu(getBaseContext());
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		menu.setMenu(R.layout.slidingmenumain);
		menu.toggle(true);
		btnsearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, GoogleTranslate.class);
				i.putExtra("Webview", "webview");
				startActivityForResult(i, REQUEST_CODE);
			}
		});

	}

	// if(resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
	// TextView txt_email = (TextView) findViewById(R.id.txt_email);
	// String email = data.getStringExtra("email");
	// txt_email.setText("You receiver email: " + email);

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
			WebView webv = (WebView) findViewById(R.id.webview);

			String result = data.getStringExtra("webviews");
			webv.loadData(result, "text/html", "UTF-8");
		}
		super.onActivityResult(requestCode, resultCode, data);

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

}
// private EditText edt;
// private Button btn;
// private Button btngoogle;
// private Button btnsoha;
// private Button btnvdic;
// private WebView wvshow;
// private String data;
// private String URL;
// private String URL1;
// private ProgressDialog mProgressDialog;
// private ActionBarDrawerToggle mDrawerToggle;
// private ImageView img_left, img_right;
// private TextView tvtitle;
// private Spinner spinner;
//
// public String[] Languages = { "Tự động dịch", "Soha", "Vdic",
// "Google Translate" };
// // Declaring the Integer Array with resourse Id's of Images for the Spinners
// public Integer[] images = { 0, R.drawable.google, R.drawable.google,
// R.drawable.google, R.drawable.google };
//
// @Override
// public void onCreate(Bundle savedInstanceState) {
// super.onCreate(savedInstanceState);
// setContentView(R.layout.layout_main);
//
// // Declaring and typecasting a Spinner
// Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
//
// // Setting a Custom Adapter to the Spinner
// mySpinner.setAdapter(new MyAdapter(MainActivity.this, R.layout.custom,
// Languages));
// mySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
//
// @Override
// public void onItemSelected(AdapterView<?> parent, View view,
// int position, long id) {
// // TODO Auto-generated method stub
// Toast.makeText(MainActivity.this, "Item" + position,
// Toast.LENGTH_LONG).show();
// }
//
// @Override
// public void onNothingSelected(AdapterView<?> parent) {
// // TODO Auto-generated method stub
//
// }
// });

//
// final SlidingMenu menu = new SlidingMenu(getBaseContext());
//
// menu.setMode(SlidingMenu.LEFT);
// menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
// menu.setShadowWidthRes(R.dimen.shadow_width);
// menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
// menu.setFadeDegree(0.35f);
// menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
// menu.setMenu(R.layout.slidingmenumain);
// menu.toggle(true);
//
// img_left.setOnClickListener(new OnClickListener() {
//
// @Override
// public void onClick(View v) {
// // TODO Auto-generated method stub
// menu.toggle(true);
// }
// });
//
// img_left.setOnClickListener(new OnClickListener() {
//
// @Override
// public void onClick(View v) {
// // TODO Auto-generated method stub
// menu.toggle(false);
// }
// });
// btngoogle.setOnClickListener(new OnClickListener() {
//
// @Override
// public void onClick(View v) {
// // TODO Auto-generated method stub
// URL = "https://translate.google.com/m?hl=vi&sl=en&tl=vi&ie=UTF-8&prev=_m&q=";
// data = edt.getText().toString();
// URL1 = URL + data;
// Log.v("URL", URL1);
// new Googletranslate().execute();
// }
// });
// btnsoha.setOnClickListener(new OnClickListener() {
//
// @Override
// public void onClick(View v) {
// // TODO Auto-generated method stub
// URL = "http://m.tratu.soha.vn/?dict=en_vn&title=";
// data = edt.getText().toString();
// URL1 = URL + data;
// Log.v("URL", URL1);
// new Sohatranslate().execute();
// }
// });
// btnvdic.setOnClickListener(new OnClickListener() {
//
// @Override
// public void onClick(View v) {
// // TODO Auto-generated method stub
// URL = "http://m.vdict.com/fsearch.php?dictionaries=eng2viet&word=";
// data = edt.getText().toString();
// URL1 = URL + data;
// Log.v("URL", URL1);
// new Vdictranslate().execute();
// }
// });
//
// }
//

//
// private class Sohatranslate extends AsyncTask<Void, Void, Void> {
//
// String contents;
//
// @Override
// protected void onPreExecute() {
// // TODO Auto-generated method stub
// super.onPreExecute();
// mProgressDialog = new ProgressDialog(MainActivity.this);
// mProgressDialog.setTitle("Soha Translate");
// mProgressDialog.setMessage("Loading...");
// mProgressDialog.setIndeterminate(false);
// mProgressDialog.show();
// }
//
// @Override
// protected Void doInBackground(Void... params) {
// // TODO Auto-generated method stub
// try {
// Document document = Jsoup.connect(URL1).get();
// Elements description = document.select("div[id=content]");
// description.select("b").get(1).remove();
// Log.v("Data3", description.toString());
// contents = description.toString();
//
// } catch (Exception e) {
// e.printStackTrace();
//
// }
// return null;
// }
//
// @Override
// protected void onPostExecute(Void result) {
// // TODO Auto-generated method stub
// wvshow.loadDataWithBaseURL(null, contents, "text/html", "UTF-8",
// null);
// mProgressDialog.dismiss();
// }
// }
//
// private class Vdictranslate extends AsyncTask<Void, Void, Void> {
// String vdic;
//
// @Override
// protected void onPreExecute() {
// // TODO Auto-generated method stub
// super.onPreExecute();
// mProgressDialog = new ProgressDialog(MainActivity.this);
// mProgressDialog.setTitle("Vdic Translate");
// mProgressDialog.setMessage("Loading...");
// mProgressDialog.setIndeterminate(false);
// mProgressDialog.show();
// }
//
// @Override
// protected Void doInBackground(Void... params) {
// // TODO Auto-generated method stub
// try {
// Document document = Jsoup.connect(URL1).get();
// Elements description = document
// .select("div[id=result-contents]");
// description.select("div[class=dictionary-name]").remove();
// Log.v("Data3", description.toString());
// vdic = description.toString();
//
// } catch (Exception e) {
// e.printStackTrace();
//
// }
// return null;
// }
//
// @Override
// protected void onPostExecute(Void result) {
// // TODO Auto-generated method stub
// wvshow.loadDataWithBaseURL(null, vdic, "text/html", "UTF-8", null);
// mProgressDialog.dismiss();
// }
// }
//
// public class MyAdapter extends ArrayAdapter<String> {
//
// public MyAdapter(Context context, int textViewResourceId,
// String[] objects) {
// super(context, textViewResourceId, objects);
// }
//
// @Override
// public View getDropDownView(int position, View convertView,
// ViewGroup parent) {
// return getCustomView(position, convertView, parent);
// }
//
// @Override
// public View getView(int position, View convertView, ViewGroup parent) {
// return getCustomView(position, convertView, parent);
// }
//
// public View getCustomView(int position, View convertView,
// ViewGroup parent) {
//
// LayoutInflater inflater = getLayoutInflater();
// View row = inflater.inflate(R.layout.custom, parent, false);
// TextView label = (TextView) row.findViewById(R.id.tvLanguage);
// label.setText(Languages[position]);
//
// ImageView icon = (ImageView) row.findViewById(R.id.imgLanguage);
// icon.setImageResource(images[position]);
//
// return row;
// }
//
// }
//
// }