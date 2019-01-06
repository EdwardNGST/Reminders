package com.example.alan_.reminders.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String reminders="reminders.db";
    private Context context;
    private String tableName="reminders";
    private String idReminder="id";
    private String titleReminder="title";
    private String textReminder="text";
    private String priorityReminder="priority";
    private static final String TAG = "DatabaseHelper";

    public DatabaseHelper(Context context){
        super(context, reminders, null, 1);
        this.context=context;
    }

    //Metodo que crea la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+tableName+"("+
                idReminder+" NUMBER PRIMARY KEY,"+
                titleReminder+" TEXT, "+
                textReminder+" TEXT, "+
                priorityReminder+" NUMBER);");
    }

    //Metodo que se ejecuta cuando cambia la estructura de la tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+tableName);
        onCreate(db);
    }

    //Metodo que elimina todos los datos de la tabla de tareas
    public void truncateTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query="";
        query="DELETE FROM "+tableName+";";
        db.execSQL(query);
        db.execSQL("VACUUM;");
    }

    //Metodo que inserta una nueva tarea
    public String insertNewReminder(String title, String text, int priority){
        SQLiteDatabase db = this.getWritableDatabase();
        int idMax=0;
        Cursor res=getReminders();
        //Recorre el cursor (tabla de tareas) que obtendra el id maximo del cursor
        for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
            idMax= res.getInt(0);
        }
        //idMax guardara el id que tendra la siguiente tarea insertada
        int id=idMax+1;
        /*Cursor res=db.rawQuery(queryHelper.verifyMaxID(id), null);
        if(res.moveToFirst()){
            Log.i(TAG, "Ya se encuentra");
            return "0";
        }else{
            Log.i(TAG, "No se encuentra");*/
        ContentValues contentValues = new ContentValues();
        contentValues.put(idReminder, id);
        contentValues.put(titleReminder, title);
        contentValues.put(textReminder, text);
        contentValues.put(priorityReminder, priority);

        long irReminder=db.insert(tableName, null, contentValues);
        if (irReminder<=0){
            return "La tarea no se ha podido registrar";
        }else{
            return "Tarea Registrada Correctamente";
        }
        //}
    }

    //Metodo para insertar nuevos elementos por default (PARA PRUEBAS)
    public void insertElements(){
        insertNewReminder("Titulo de tarea 1", "Descripcion de la tarea 1", (byte) 1);
        insertNewReminder("Titulo de tarea 2", "Descripcion de la tarea 2", (byte) 3);
        insertNewReminder("Titulo de tarea 3", "Descripcion de la tarea 3", (byte) 2);
    }

    //Obtener todos los registros de la tabla
    public Cursor getReminders(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT "+
                idReminder+" , "+
                titleReminder+" , "+
                textReminder+" , "+
                priorityReminder+" FROM "+
                tableName+";", null);
    }

    public Cursor getReminders(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT "+
                idReminder+" , "+
                titleReminder+" , "+
                textReminder+" , "+
                priorityReminder+" FROM "+
                tableName+" WHERE "+
                idReminder+" = "+id+";", null);
    }


    public void updateReminder(int id, String title, String description, int priority) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query="";
        query="UPDATE "+tableName+
            " SET "+titleReminder+" = '"+title+"',"+
                textReminder+" = '"+description+"',"+
                priorityReminder+" = "+priority+" WHERE "+
                idReminder+" = "+id+";";
        db.execSQL(query);
    }

    public void deleteReminder(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query="";
        query="DELETE FROM "+tableName+
                " WHERE "+
                idReminder+" = "+id+";";
        db.execSQL(query);
    }
}
