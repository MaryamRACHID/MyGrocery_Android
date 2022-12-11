package com.ensah.mygroceryapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.ensah.mygroceryapp.models.Article;
import com.ensah.mygroceryapp.models.Categorie;
import com.ensah.mygroceryapp.models.Course;
import com.ensah.mygroceryapp.models.CourseArticle;
import com.ensah.mygroceryapp.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static DatabaseHelper dbHelper;
    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "Grocery";
    private static final String LOG = "DatabaseHelper";

    //Table names
    private static final String TABLE_ARTICLE = "article";
    private static final String TABLE_COURSE = "course";
    private static final String TABLE_COURSE_ARTICLE = "course_article";
    private static final String TABLE_CATEGORIES ="categorie";
    private  static final String TABLE_USER="user";

    // Article Columns
    private static final String KEY_ID = "id";
    private static final String ARTICLE_NAME = "article_name";
    private static final String ARTICLE_UNIT = "article_unit";

    private static final String COURSE_NAME = "course_name";
    private static final String COURSE_NOTE = "course_note";

    private static final String COURSE_ID = "course_id";
    private static final String ARTICLE_ID = "article_id";
    private static final String COUNT_ARTICLE_COURSE = "count";

//    categorie Columns
    private static  final String categories_ID ="id_categorie";
    private static  final String categories_NAME ="categorie_name";
    private static  final String categories_DESCRIPTION ="categorie_description";

//    user Columns
    private static final String USER_ID = "id";
    private static final String USER_NAME = "user_name";
    private static final String USER_USERNAME = "user_username";
    private static final String USER_PASSWORD="password";

    private static final String CREATE_TABLE_ARTICLE =
            "CREATE TABLE " + TABLE_ARTICLE + "( "
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ARTICLE_NAME + " TEXT UNIQUE , "
                    + ARTICLE_UNIT + " TEXT" + ")";

    private static final String CREATE_TABLE_COURSE =
            "CREATE TABLE " + TABLE_COURSE + "( "
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COURSE_NAME + " TEXT  UNIQUE, "
                    + COURSE_NOTE + " TEXT" + ")";

    private static final String CREATE_TABLE_COURSE_ARTICLE =
            "CREATE TABLE " + TABLE_COURSE_ARTICLE + "( "
                    + COURSE_ID + " INTEGER NOT NULL, "
                    + ARTICLE_ID + " INTEGER NOT NULL, "
                    + COUNT_ARTICLE_COURSE + " INTEGER NOT NULL  ,"
                    + "CHECK( " + COUNT_ARTICLE_COURSE + " > 0 ),"
                    + "FOREIGN KEY ( " + COURSE_ID + " ) REFERENCES " + TABLE_COURSE + " ( " + KEY_ID + " ) , "
                    + "FOREIGN KEY ( " + ARTICLE_ID + " ) REFERENCES " + TABLE_ARTICLE + " ( " + KEY_ID + " ) , "
                    + "PRIMARY KEY ( " + COURSE_ID + "," + ARTICLE_ID + " ))";

    private static final String CREATE_TABLE_CATEGORIES =
            "CREATE TABLE " + TABLE_CATEGORIES + "( "
                    + categories_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + categories_NAME + " TEXT UNIQUE , "
                    + categories_DESCRIPTION + " TEXT " + ")";

    private static final String CREATE_TABLE_USER =
            "CREATE TABLE " + TABLE_USER + "( "
                    + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + USER_USERNAME + " TEXT UNIQUE , "
                    +  USER_NAME+ " TEXT , "
                    + USER_PASSWORD + " TEXT " + ")";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ARTICLE);
        Log.e(LOG, CREATE_TABLE_ARTICLE);
        db.execSQL(CREATE_TABLE_COURSE);
        Log.e(LOG, CREATE_TABLE_COURSE);
        db.execSQL(CREATE_TABLE_COURSE_ARTICLE);
        Log.e(LOG, CREATE_TABLE_COURSE_ARTICLE);
        db.execSQL(CREATE_TABLE_CATEGORIES);
        Log.e(LOG, CREATE_TABLE_CATEGORIES);
        db.execSQL(CREATE_TABLE_USER);
        Log.e(LOG, CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARTICLE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE_ARTICLE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public static DatabaseHelper instanceOfDatabase(Context context) {
        if (dbHelper == null)
            dbHelper = new DatabaseHelper(context);

        return dbHelper;
    }

    public void createArticle(Article article) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ARTICLE_NAME, article.getName());
        contentValues.put(ARTICLE_UNIT, article.getUnite());
        sqLiteDatabase.insert(TABLE_ARTICLE, null, contentValues);
        Log.e(LOG, "Create Article " + article.getName());
    }
    public boolean createCategories(Categorie categorie) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(categories_DESCRIPTION,categorie.getCategorieDescription());
        contentValues.put(categories_NAME,categorie.getCategorieName());
        int result= (int) sqLiteDatabase.insert(TABLE_CATEGORIES, null, contentValues);
        Log.e(LOG, "Create Categories" + categorie.getCategorieName());
        if(result==-1){
            return false;
        }
        else return true;

    }

    public boolean createUSER(User user) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME,user.getName());
        contentValues.put(USER_USERNAME,user.getUsername());
        contentValues.put(USER_PASSWORD,user.getPassword());
        int result= (int) sqLiteDatabase.insert(TABLE_USER, null, contentValues);
        Log.e(LOG, "Create User" + user.getUsername());
        if(result==-1){
            return false;
        }
        else return true;

    }
    public boolean createCourse(Course course) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_NAME, course.getName());
        contentValues.put(COURSE_NOTE, course.getDescription());
        int result= (int) sqLiteDatabase.insert(TABLE_COURSE, null, contentValues);
        Log.e(LOG, "Create Course" + course.getName());
        if(result==-1){
            return false;
        }
        else return true;

    }
    public User getUserByName(String userName) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        User user = null;
        String sql = "SELECT * FROM " + TABLE_USER + " WHERE " + USER_USERNAME + " = '" + userName + "' ";
        Log.e(LOG, sql);
        Cursor result = sqLiteDatabase.rawQuery(sql, null);
        if (result.getCount() != 0) {
            result.moveToNext();
            int id = result.getInt(0);
            user = new User(result.getString(1), result.getString(2),result.getString(3));
        }
        result.close();
        sqLiteDatabase.close();
        Log.e(LOG, "User " + userName);
        return user;

    }
    public User getUserBypsswd(String password) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        User user = null;
        String sql = "SELECT * FROM " + TABLE_USER + " WHERE " + USER_PASSWORD + " = '" + password + "' ";
        Log.e(LOG, sql);
        Cursor result = sqLiteDatabase.rawQuery(sql, null);
        if (result.getCount() != 0) {
            result.moveToNext();
            int id = result.getInt(0);
            user = new User(result.getString(1), result.getString(2),result.getString(3));
        }
        result.close();
        sqLiteDatabase.close();
        Log.e(LOG, "User " + password);
        return user;

    }
    public boolean CheckUser(String username, String password) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "Select * from " +TABLE_USER+ " where " + USER_USERNAME + " = '" + username+"' and "+USER_PASSWORD+" = '"+password+"'";
        Cursor result = sqLiteDatabase.rawQuery(sql, null);
        if(result.getCount() <= 0){
            result.close();
            return false;
        }
        result.close();
        return true;
    }
    public void addArticleToCourse(String courseName, String articleName, int count) {
        Course course = getCourseByName(courseName);
        Article article = getArticleByName(articleName);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_ID, course.getId());
        contentValues.put(ARTICLE_ID, article.getId());
        contentValues.put(COUNT_ARTICLE_COURSE, count);
        sqLiteDatabase.insert(TABLE_COURSE_ARTICLE, null, contentValues);
        Log.e(LOG, "Add  Article " + articleName + " To " + course.getName());
    }

    public void deleteArticleToCourse(String courseName, String articleName) {
        Course course = getCourseByName(courseName);
        Article article = getArticleByName(articleName);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.execSQL("DELETE FROM " + TABLE_COURSE_ARTICLE + " WHERE " + COURSE_ID + "='" + course.getId() + "' AND " + ARTICLE_ID + "='" + article.getId() + "'");
        sqLiteDatabase.close();

        Log.e(LOG, "Delete  Article " + articleName + " From " + course.getName());
    }

    public Course getCourseByName(String courseName) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Course course = null;
        String sql = "SELECT * FROM " + TABLE_COURSE + " WHERE " + COURSE_NAME + " = '" + courseName + "' ";
        Log.e(LOG, sql);
        Cursor result = sqLiteDatabase.rawQuery(sql, null);
        if (result.getCount() != 0) {
            result.moveToNext();
            int id = result.getInt(0);
            course = new Course(id, result.getString(1), result.getString(2));
        }
        result.close();
        sqLiteDatabase.close();
        Log.e(LOG, "Get Course " + courseName);
        return course;

    }

    public Article getArticleByName(String articleName) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Article article = null;
        String sql = "SELECT * FROM " + TABLE_ARTICLE + " WHERE " + ARTICLE_NAME + " = '" + articleName + "' ";
        Log.e(LOG, sql);
        Cursor result = sqLiteDatabase.rawQuery(sql, null);
        if (result.getCount() != 0) {
            result.moveToNext();
            int id = result.getInt(0);
            article = new Article(id, result.getString(1), result.getString(2));
        }
        result.close();
        sqLiteDatabase.close();
        Log.e(LOG, "Get Article " + articleName);
        return article;

    }

    // Fetch All
    public List<Course> getAllCourses() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<Course> courseList = new ArrayList<>();
        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_COURSE, null)) {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int id = result.getInt(0);
                    Course course = new Course(id, result.getString(1), result.getString(2));
                    courseList.add(course);

                }
            }
        }

        Log.e(LOG, "Fetch All Courses ");
        return courseList;
    }

    public List<Article> getAllArticles() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<Article> articleList = new ArrayList<>();
        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_ARTICLE, null)) {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int id = result.getInt(0);
                    Article article = new Article(id, result.getString(1), result.getString(2));
                    articleList.add(article);

                }
            }
        }
        Log.e(LOG, "Fetch All Articles ");
        return articleList;
    }

    public List<CourseArticle> getAllCourseArticles() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<CourseArticle> CourseArticleList = new ArrayList<>();
        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_COURSE_ARTICLE, null)) {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int courseId = result.getInt(0);
                    int articleId = result.getInt(1);
                    int count = result.getInt(2);
                    CourseArticle courseArticle = new CourseArticle(courseId, articleId, count);
                    CourseArticleList.add(courseArticle);

                }
            }
        }
        Log.e(LOG, "Fetch All Course's Articles ");
        return CourseArticleList;
    }

    public Map<Article, Integer> getArticlesOfCourse(String courseName) {
        String sql = "SELECT article.id , article_name, article_unit, course_article.count from article  left join course_article  on article.id =course_article.article_id join course  on course.id=course_article.course_id where course.course_name= '" + courseName + "'";
        Map<Article, Integer> articlesOfCourseList = new HashMap<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        try (Cursor result = sqLiteDatabase.rawQuery(sql, null)) {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int id = result.getInt(0);
                    Article article = new Article(id, result.getString(1), result.getString(2));
                    articlesOfCourseList.put(article, result.getInt(3));

                }
            }
        }
        Log.e(LOG, "Fetch All  Articles  Of course " + courseName);
        return articlesOfCourseList;
    }

    public List<Course> getCouresrOfArticle(String articleName) {
        List<Course> courseList = new ArrayList<>();
        String sql = "select course.id, course.course_name, course.course_note from course  left join course_article  on course.id=course_article.course_id join article  on article.id=course_article.article_id where article.article_name= '" + articleName + "'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        try (Cursor result = sqLiteDatabase.rawQuery(sql, null)) {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int id = result.getInt(0);
                    Course course = new Course(id, result.getString(1), result.getString(2));
                    courseList.add(course);

                }
            }
        }

        Log.e(LOG, "Get all  Courses of " + articleName);
        Log.e(LOG, sql);
        return courseList;
    }

    public List<String> getAllCategories() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<String> categorieList = new ArrayList<>();
        Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_CATEGORIES, null);
            if(result.moveToFirst()) {
                do {
                    categorieList.add(
                            result.getString(1)
                                    + " | " + result.getString(2)
                    );
                } while(result.moveToNext());
            }
            result.close();
            Log.e(LOG, "Fetch All Categories ");
            return categorieList;
    }
}
