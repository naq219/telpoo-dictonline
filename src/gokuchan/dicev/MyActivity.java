package gokuchan.dicev;

import gokuchan.dicev.utils.LanguageSupport;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MyActivity extends MainActivity{
	int type=Translate.TYPE_AUTO;
	String key;
	int keyLanguage=LanguageSupport.ENGLISH_VIETNAM;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		btn_search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				key= edt.getText().toString();
				new TaskTranslate().execute();
				
				
			}
		});
		
	}
	
	private class TaskTranslate extends AsyncTask<Void, Void, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progress.setVisibility(View.VISIBLE);
		}

		@Override
		protected String doInBackground(Void... params) {
			
			return Translate.translate(type, key,keyLanguage);
			
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			wvshow.loadDataWithBaseURL(null, result, "text/html", "UTF-8", null);
			progress.setVisibility(View.GONE);
		}

	}

}
