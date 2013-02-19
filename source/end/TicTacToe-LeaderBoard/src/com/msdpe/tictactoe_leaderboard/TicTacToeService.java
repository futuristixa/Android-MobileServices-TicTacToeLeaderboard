package com.msdpe.tictactoe_leaderboard;

import static com.microsoft.windowsazure.mobileservices.MobileServiceQueryOperations.val;

import java.net.MalformedURLException;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.microsoft.windowsazure.mobileservices.*;

public class TicTacToeService {
	private MobileServiceClient mClient;
	private MobileServiceTable<PlayerRecord> mPlayerRecordsTable;
	private Context mContext;
	private String TAG = "TicTacToeService";
	
	public TicTacToeService(Context context) {
		mContext = context;
		
		try {
			mClient = new MobileServiceClient("https://tictactoeleaderboard.azure-mobile.net/", "KKxeIhnoUWsHXvySIpykYgKgqgVkla70", mContext);
			mPlayerRecordsTable = mClient.getTable("PlayerRecords", PlayerRecord.class);
		} catch (MalformedURLException e) {
			Log.e(TAG, "There was an error creating the Mobile Service. Verify the URL");
		}
	}
	
	public void insertPlayerRecord(PlayerRecord playerRecord, TableOperationCallback<PlayerRecord> callback) {
		mPlayerRecordsTable.insert(playerRecord, callback);
	}
	
	public void getPlayerRecords(TableQueryCallback<PlayerRecord> callback) {
		mPlayerRecordsTable.where().execute(callback); 
	}

}
