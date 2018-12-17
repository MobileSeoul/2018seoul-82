package com.example.tripbus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import java.util.HashMap;

public class SessionManager {
	// LogCat tag
	private static String TAG = SessionManager.class.getSimpleName();

	// Shared Preferences
	SharedPreferences pref;

	Editor editor;
	Context _context;

	// Shared pref mode
	int PRIVATE_MODE = 0;

	// Shared preferences file name
	private static final String PREF_NAME = "SeoulTrip";
	
	private static final String IS_LOGIN = "isLoggedIn";
	public static final String IS_ID = "isID";


	public SessionManager(Context context) {
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}

	
	public boolean isLoggedIn(){
		return pref.getBoolean(IS_LOGIN, false);
	} //false가 default


	public void createLoginSession(String id){
		// Storing login value as TRUE
		editor.putBoolean(IS_LOGIN, true);

		// Storing email in pref
		editor.putString(IS_ID, id);

		// commit changes
		editor.commit();

		Log.d(TAG, "User login session modified!");
	}

	public HashMap<String, String> getUserDetails(){
		HashMap<String, String> user = new HashMap<String, String>();

		Log.d(TAG, pref.getString(IS_ID, null));
		// user email id
		user.put(IS_ID, pref.getString(IS_ID, null));

		// return user
		return user;
	}

	public void checkLogin(){
		// Check login status
		if(!this.isLoggedIn()){ //로그인 되지 않았을 경우
			Intent i = new Intent(_context, LoginActivity.class);
			// Closing all the Activities
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			// Add new Flag to start new Activity
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			// Staring Login Activity
			_context.startActivity(i);
		}

	}

	public void logoutUser(){ //로그아웃하기
		editor.clear();
		editor.commit();

		// After logout redirect user to Loing Activity
		Intent i = new Intent(_context, LoginActivity.class);
		// Closing all the Activities
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		// Add new Flag to start new Activity
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		// Staring Login Activity
		_context.startActivity(i);
	}


}
