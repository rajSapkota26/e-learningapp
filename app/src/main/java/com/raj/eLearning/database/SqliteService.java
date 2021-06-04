package com.raj.eLearning.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.raj.eLearning.model.ScoreBoard;
import com.raj.eLearning.model.User;
import com.raj.eLearning.model.Wallet;

import java.util.ArrayList;
import java.util.List;

public class SqliteService extends SQLiteOpenHelper {


    public SqliteService(Context context) {
        super(context, ParaMeters.DATABASE_NAME, null, ParaMeters.DATABASE_VERSION);
    }


    public void updateUser(User user) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ParaMeters.KEY_FULL_NAME, user.getFullname());
        values.put(ParaMeters.KEY_USER_NAME, user.getUsername());
        values.put(ParaMeters.KEY_EMAIL, user.getEmail());
        values.put(ParaMeters.KEY_Password, user.getPassword());
        values.put(ParaMeters.KEY_GENDER, user.getGender());
        values.put(ParaMeters.KEY_DOB, user.getDateOfBirth());
        values.put(ParaMeters.KEY_PHONE, user.getPhone());
        database.update(ParaMeters.TABLE_USER, values, ParaMeters.KEY_ID + " =?", new String[]{String.valueOf(user.getId())});
    }

    public User getUserById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user=null;
        Cursor cursor = db.query(ParaMeters.TABLE_USER, new String[]{ParaMeters.KEY_ID, ParaMeters.KEY_FULL_NAME,
                        ParaMeters.KEY_USER_NAME, ParaMeters.KEY_EMAIL, ParaMeters.KEY_Password,
                        ParaMeters.KEY_GENDER, ParaMeters.KEY_DOB, ParaMeters.KEY_PHONE}, ParaMeters.KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor==null){
            return user;
        }
        if (cursor != null)
            cursor.moveToFirst();

         user = new User(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5),
                cursor.getString(6), cursor.getString(7)
        );

        return user;
    }

    public User getUserByUserName(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user=null;
        if (username==null||username.isEmpty()){
            return user;
        }
        Cursor cursor = db.query(ParaMeters.TABLE_USER, new String[]{ParaMeters.KEY_ID, ParaMeters.KEY_FULL_NAME,
                        ParaMeters.KEY_USER_NAME, ParaMeters.KEY_EMAIL, ParaMeters.KEY_Password,
                        ParaMeters.KEY_GENDER, ParaMeters.KEY_DOB, ParaMeters.KEY_PHONE}, ParaMeters.KEY_USER_NAME + "=?",
                new String[]{username}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {

        user = new User(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3),
                    cursor.getString(4), cursor.getString(5),
                    cursor.getString(6), cursor.getString(7)
            );
        return  user;
        }
        return user;
    }

    public User getUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user=null;

        Cursor cursor = db.query(ParaMeters.TABLE_USER, new String[]{ParaMeters.KEY_ID, ParaMeters.KEY_FULL_NAME,
                        ParaMeters.KEY_USER_NAME, ParaMeters.KEY_EMAIL, ParaMeters.KEY_Password,
                        ParaMeters.KEY_GENDER, ParaMeters.KEY_DOB, ParaMeters.KEY_PHONE}, ParaMeters.KEY_EMAIL + "=?",
                new String[]{email}, null, null, null, null);
        if (cursor==null){
            return user;
        }
        if (cursor != null && cursor.moveToFirst())

         user = new User(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5),
                cursor.getString(6), cursor.getString(7)
        );

        return user;
    }

    public void insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ParaMeters.KEY_FULL_NAME, user.getFullname());
        values.put(ParaMeters.KEY_USER_NAME, user.getUsername());
        values.put(ParaMeters.KEY_EMAIL, user.getEmail());
        values.put(ParaMeters.KEY_Password, user.getPassword());
        values.put(ParaMeters.KEY_GENDER, user.getGender());
        values.put(ParaMeters.KEY_DOB, user.getDateOfBirth());
        values.put(ParaMeters.KEY_PHONE, user.getPhone());
        db.insert(ParaMeters.TABLE_USER, null, values);

        db.close();
    }


    public void insertScore(ScoreBoard scoreBoard) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ParaMeters.KEY_USER_ID, scoreBoard.getUserId());
        values.put(ParaMeters.KEY_SCORE, scoreBoard.getScore());

        db.insert(ParaMeters.TABLE_SCORE, null, values);
        db.close();
    }
    public List<ScoreBoard> getScore(){
        List<ScoreBoard> scoreBoards=new ArrayList<>();
        SQLiteDatabase database=this.getReadableDatabase();
        String query="SELECT * FROM "+ParaMeters.TABLE_SCORE;

        Cursor cursor=database.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                ScoreBoard score=new ScoreBoard();
                score.setId(cursor.getInt(0));
                score.setUserId(cursor.getString(1));
                score.setScore(cursor.getInt(2));
                scoreBoards.add(score);
            }while (cursor.moveToNext());
        }
        return  scoreBoards;
    }

    public Wallet getCoin(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Wallet wallet=null;
        Cursor cursor = db.query(ParaMeters.TABLE_WALLET, new String[]{ParaMeters.KEY_ID, ParaMeters.KEY_USER_ID,
                        ParaMeters.KEY_COIN}, ParaMeters.KEY_USER_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
       if (cursor.getCount()>0){
           if (cursor==null){
               return wallet;
           }
           if (cursor != null&&  cursor.moveToFirst()){
               wallet  = new Wallet(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
           }
       }
        return wallet;
    }

    public void insertWallet(Wallet wallet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ParaMeters.KEY_USER_ID, wallet.getUserId());
        values.put(ParaMeters.KEY_COIN, wallet.getCoin());

        db.insert(ParaMeters.TABLE_WALLET, null, values);
        db.close();
    }

    public void updateWallet(Wallet wallet) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ParaMeters.KEY_COIN, wallet.getCoin());
        database.update(ParaMeters.TABLE_WALLET, values, ParaMeters.KEY_USER_ID + " =?", new String[]{wallet.getUserId()});
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ParaMeters.CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(ParaMeters.CREATE_SCORE_TABLE);
        sqLiteDatabase.execSQL(ParaMeters.CREATE_WALLET_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ParaMeters.TABLE_USER);

        onCreate(sqLiteDatabase);
    }
}
