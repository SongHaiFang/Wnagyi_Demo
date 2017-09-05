package sqlitedatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.version;

/**
 * data:2017/8/21 0021.
 * Created by ：宋海防  song on
 */

public class MyDateBase extends SQLiteOpenHelper {

    public MyDateBase(Context context) {
        super(context, "lixian", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table haha(result text)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
