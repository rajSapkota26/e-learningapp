package com.raj.eLearning.database;

public class ParaMeters {
    //common for 3 table
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "elearning";
    public static final String KEY_ID = "id";

    //user fields
    public static final String TABLE_USER = "user";
    public static final String KEY_FULL_NAME = "fullname";
    public static final String KEY_USER_NAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_Password = "password";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_DOB = "dateOfBirth";
    public static final String KEY_PHONE = "phone";

    //common for score and coin
    public static final String KEY_USER_ID = "userId";

    //wallet field
    public static final String TABLE_WALLET = "wallet";
    public static final String KEY_COIN = "coin";

    //scoreboard
    public static final String TABLE_SCORE = "scoreboard";
    public static final String KEY_SCORE = "score";
    //create user table
    public static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FULL_NAME + " TEXT," + KEY_USER_NAME + " TEXT," + KEY_EMAIL + " TEXT,"
            + KEY_Password + " TEXT," + KEY_GENDER + " TEXT," + KEY_DOB + " TEXT," + KEY_PHONE + " TEXT" + ")";


    //create score table

    public static final String CREATE_SCORE_TABLE = "CREATE TABLE " + TABLE_SCORE + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USER_ID + " TEXT," + KEY_SCORE + " INTEGER"+  ")";

    //create wallet table
    public static final String CREATE_WALLET_TABLE = "CREATE TABLE " + TABLE_WALLET + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USER_ID + " TEXT," + KEY_COIN + " INTEGER"+  ")";


    //slider image
    public static final String S_FIRST = "https://firebasestorage.googleapis.com/v0/b/myquiz-2b322.appspot.com/o/MyFinalProjects%2FsliderImage%2Fvl2.png?alt=media&token=5aee8315-0b27-45af-9522-e5cc4633b772";
    public static final String S_SECOND = "https://firebasestorage.googleapis.com/v0/b/myquiz-2b322.appspot.com/o/MyFinalProjects%2FsliderImage%2Fvl.jpg?alt=media&token=f0634553-3db6-4667-b07c-770a70d93916";
    public static final String S_THIRD = "https://firebasestorage.googleapis.com/v0/b/myquiz-2b322.appspot.com/o/MyFinalProjects%2FsliderImage%2Fupdates.jpg?alt=media&token=069454dc-8dde-46e8-9869-9f890b344986";
    public static final String S_FOUR = "https://firebasestorage.googleapis.com/v0/b/myquiz-2b322.appspot.com/o/MyFinalProjects%2FsliderImage%2Fthink.jpg?alt=media&token=29762803-3673-4cd9-aae1-74b00b5c4de1";
    public static final String S_FIVE = "https://firebasestorage.googleapis.com/v0/b/myquiz-2b322.appspot.com/o/MyFinalProjects%2FsliderImage%2Fs5.jpg?alt=media&token=d7654e4c-f680-4016-b59c-7a9dc09a278d";
    public static final String S_SIX= "https://firebasestorage.googleapis.com/v0/b/myquiz-2b322.appspot.com/o/MyFinalProjects%2FsliderImage%2Fquiz.jpg?alt=media&token=8bb15c2e-139c-4163-9157-2761e52952be";
    public static final String S_SEVEN = "https://firebasestorage.googleapis.com/v0/b/myquiz-2b322.appspot.com/o/MyFinalProjects%2FsliderImage%2Fbook2.jpg?alt=media&token=b2c1b304-fc10-4328-9732-455b10f65f57";
    public static final String S_EIGHT = "https://firebasestorage.googleapis.com/v0/b/myquiz-2b322.appspot.com/o/MyFinalProjects%2FsliderImage%2Fbook.jpg?alt=media&token=ef5e5494-cb7e-47f4-bbc8-5f4e504806bf";
    public static final String S_NINE = "https://firebasestorage.googleapis.com/v0/b/myquiz-2b322.appspot.com/o/MyFinalProjects%2FsliderImage%2Farticle.jpg?alt=media&token=c1a7420c-c754-4dbb-9832-e2835afca799";
    public static final String S_TEN = "https://firebasestorage.googleapis.com/v0/b/myquiz-2b322.appspot.com/o/MyFinalProjects%2FsliderImage%2Feducation.jpg?alt=media&token=db663e96-f657-4161-9a8d-961c431cdeef";
    public static final String S_ELEVEN = "https://firebasestorage.googleapis.com/v0/b/myquiz-2b322.appspot.com/o/MyFinalProjects%2FsliderImage%2Flearn.jpg?alt=media&token=056fc652-160f-4aa9-bf89-bb212b2d020a";
    public static final String S_TWELVE = "https://firebasestorage.googleapis.com/v0/b/myquiz-2b322.appspot.com/o/MyFinalProjects%2FsliderImage%2Fquizimg.jpg?alt=media&token=190055ef-a54e-4e87-b207-0bc78b08dee4";

}
