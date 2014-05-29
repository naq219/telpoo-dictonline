package gokuchan.dicev;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.EditText;

public class GoogleTranslate extends Activity {
	private String data;
	private String URL;
	private String URL1;
	private EditText edt;
	private String ltrg;
	private WebView webView;
	private ProgressDialog mProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		webView = (WebView) findViewById(R.id.webview);
		edt = (EditText) findViewById(R.id.edt_inp);
		Log.d("Test show data", edt.getText().toString());
		Intent i = getIntent();
        final String contentwebview = i.getStringExtra("contentwebview");
        webView.("contentweb: " + contentwebview);
		new GoogleTranslateAsyncTask().execute();
	}

	public class GoogleTranslateAsyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			URL = "https://translate.google.com/m?hl=vi&sl=en&tl=vi&ie=UTF-8&prev=_m&q=";
			data = edt.getText().toString();
			URL1 = URL + data;
			Log.v("URL", URL1);
			// TODO Auto-generated method stub
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(GoogleTranslate.this);
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
			webView.loadDataWithBaseURL(null, ltrg, "text/html", "UTF-8", null);
			Intent i = new Intent();
			i.putExtra("webviews", "webView");
			setResult(RESULT_OK, i);
			finish();
			mProgressDialog.dismiss();
		}
	}
}
