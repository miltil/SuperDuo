package barqsoft.footballscores.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import barqsoft.footballscores.DatabaseContract;
import barqsoft.footballscores.R;
import barqsoft.footballscores.Utilities;

/**
 * RemoteViewsService controlling the data being shown in the scrollable weather detail widget
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class WidgetRemoteViewsService extends RemoteViewsService {

    private static final String[] DATA_COLUMNS = {
            DatabaseContract.scores_table.MATCH_ID,
            DatabaseContract.scores_table.DATE_COL,
            DatabaseContract.scores_table.HOME_COL,
            DatabaseContract.scores_table.AWAY_COL,
            DatabaseContract.scores_table.HOME_GOALS_COL,
            DatabaseContract.scores_table.AWAY_GOALS_COL
    };
    // these indices must match the projection
    static final int INDEX_MATCH_ID = 0;
    static final int INDEX_MATCH_DATE = 1;
    static final int INDEX_HOME = 2;
    static final int INDEX_AWAY = 3;
    static final int INDEX_HOME_GOALS = 4;
    static final int INDEX_AWAY_GOALS = 5;
    String description;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {
            private Cursor data = null;
            Context context;

            @Override
            public void onCreate() {
                context = getApplicationContext();
            }

            @Override
            public void onDataSetChanged() {
                if (data != null) {
                    data.close();
                }

                Uri scoresForTodayUri = DatabaseContract.scores_table
                        .buildScoreWithDate();
                String formatString = "yyyy-MM-dd";
                SimpleDateFormat format = new SimpleDateFormat(formatString, Locale.US);
                String todayDate = format.format(new Date());

                final long identityToken = Binder.clearCallingIdentity();

                data = getContentResolver().query(scoresForTodayUri,
                        DATA_COLUMNS,             //columns to return
                        null,                     // cols for "where" clause
                        new String[]{todayDate},  // values for "where" clause
                        null                      // sort order
                );
                Binder.restoreCallingIdentity(identityToken);
            }

            @Override
            public void onDestroy() {
                if (data != null) {
                    data.close();
                    data = null;
                }
            }

            @Override
            public int getCount() {
                return data == null ? 0 : data.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                if (position == AdapterView.INVALID_POSITION ||
                        data == null || !data.moveToPosition(position)) {
                    return null;
                }
                RemoteViews views = new RemoteViews(getPackageName(),
                        R.layout.widget_list_item);

                String gameDate = data.getString(INDEX_MATCH_DATE);
                String homeTeamName = data.getString(INDEX_HOME);
                String awayTeamName = data.getString(INDEX_AWAY);
                int homeCrestResourceId = Utilities.getTeamCrestByTeamName(homeTeamName);
                int awayCrestResourceId = Utilities.getTeamCrestByTeamName(awayTeamName);
                String homeScoreString;
                String awayScoreString;
                int homeScore = data.getInt(INDEX_HOME_GOALS);
                if (homeScore == -1){
                    homeScoreString = "-";
                }
                else{
                    homeScoreString = Integer.toString(homeScore);
                }
                int awayScore = data.getInt(INDEX_AWAY_GOALS);
                if (awayScore == -1){
                    awayScoreString = "-";
                }
                else{
                    awayScoreString = Integer.toString(awayScore);
                }

                views.setImageViewResource(R.id.home_team_icon, homeCrestResourceId);
                views.setImageViewResource(R.id.away_team_icon, awayCrestResourceId);
                views.setTextViewText(R.id.widget_home_score, homeScoreString);
                views.setTextViewText(R.id.widget_away_score, awayScoreString);

                final Intent fillInIntent = new Intent();
                views.setOnClickFillInIntent(R.id.widget_list_item, fillInIntent);
                return views;
            }

            @Override
            public RemoteViews getLoadingView() {
                return new RemoteViews(getPackageName(), R.layout.widget_list_item);
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                if (data.moveToPosition(position))
                    return data.getLong(INDEX_MATCH_ID);
                return position;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }
}