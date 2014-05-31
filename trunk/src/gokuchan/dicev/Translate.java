package gokuchan.dicev;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.util.Log;
import gokuchan.dicev.utils.Defi;
import gokuchan.dicev.utils.LanguageSupport;

public class Translate {
	public static final int TYPE_SOHA=1;
	public static final int TYPE_VDICT=2;
	public static final int TYPE_COVIET=3;
	public static final int TYPE_AUTO=0;
	public static final int TYPE_GOOGLE=4;
	
	public static String translate(int type,String key,int keyLanguage){
		String res;
		switch (type) {
		case TYPE_SOHA:
			res=soha(key,keyLanguage);
			break;
			
		case TYPE_VDICT:
			res=vdict(key,keyLanguage);
			break;
			
		case TYPE_COVIET:
			res=coviet(key,keyLanguage);
			break;
			
			
		case TYPE_GOOGLE:
			res=google(key,keyLanguage);
			break;
			
		case TYPE_AUTO:
			res=auto(key,keyLanguage);
			break;

		default:
			res=auto(key,keyLanguage);
			break;
		}
		
		return res;
	}
	
	/*
	 * chua code
	 */
	public static String auto(String key, int keyLanguage){
		return null;
		// viet tiep di
	}

	public static String google(String key,int keyLanguage) {
		String url = getUrlGoogle(key,keyLanguage);
		String ltrg = null;
		try {
			Document document = Jsoup.connect(url).get();
			String description = document.select("div[dir=ltr]") + document.select("div").get(4).html();
			Log.v("Data3", description.toString());
			ltrg = description.toString();

		} catch (Exception e) {
			e.printStackTrace();

		}

		return ltrg;

	}

	
	private static String getUrlGoogle(String key, int keyLanguage) {
			String baseUrl="https://translate.google.com/m?hl=vi&ie=UTF-8&prev=_m&q="+key+"&";
		switch (keyLanguage) {
		case LanguageSupport.ENGLISH_VIETNAM:
			return baseUrl+"sl=en&tl=vi";
			
		case LanguageSupport.VIETNAM_ENGLISH:
			return  baseUrl+"sl=vi&tl=en";
			
		case LanguageSupport.VIETNAM_KOREAN:
			return  baseUrl+"sl=vi&tl=ko";

		default:
			break;
		}

		return null;
	}

	public static String vdict(String key,int keyLanguage) {
		String url = getUrlVdict(key,keyLanguage);
		String vdict = null;
		try {
			Document document = Jsoup.connect(url).get();
			Elements description = document.select("div[id=result-contents]");
			description.select("div[class=dictionary-name]").remove();
			Log.v("Data3", description.toString());
			vdict = description.toString();

		} catch (Exception e) {
			e.printStackTrace();

		}

		return vdict;
	}

	/*
	 * chua code
	 */
	private static String getUrlVdict(String key, int keyLanguage) {
		 return null ;
		 
	}
	/*
	 * chua code
	 */
	private static String getUrlSoHa(String key, int keyLanguage) {
		 return null ;
		 
	}
	/*
	 * chua code
	 */
	private static String getUrlCoViet(String key, int keyLanguage) {
		 return null ;
		 
	}
	

	public static String coviet(String key,int keyLanguage) {
		String url =getUrlCoViet(key, keyLanguage);
		String res = null;
		try {
			Document document = Jsoup.connect(url).get();
			Elements description = document.select("DIV[id=ctl00_ContentPlaceHolderMain_ctl00]");
			description.select("div[class=p3l fl m3t]").remove();
			description.select("div[id=partofspeech_100]").remove();
			Log.v("Data3", description.toString());
			res = description.toString();

		} catch (Exception e) {
			e.printStackTrace();

		}

		return res;
	}

	public static String soha(String key,int keyLanguage) {
		String url = getUrlSoHa(key, keyLanguage);
		String res = null;

		try {
			Document document = Jsoup.connect(url).get();
			Elements description = document.select("div[id=content]");
			description.select("b").get(1).remove();
			Log.v("Data3", description.toString());
			res = description.toString();

		} catch (Exception e) {
			e.printStackTrace();

		}

		return res;
	}

}
