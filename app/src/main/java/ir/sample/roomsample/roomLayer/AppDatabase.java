package ir.sample.roomsample.roomLayer;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import ir.sample.roomsample.roomLayer.dataAccessObjects.UserDao;
import ir.sample.roomsample.roomLayer.entities.UserEntity;

//Setting & Configuration Database ;
@Database(entities = {UserEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public static final String DB_NAME = "basic_room";

    public abstract UserDao user();
    public static AppDatabase getInstance(Context context)
    {
        if(INSTANCE == null){
            INSTANCE= Room.databaseBuilder(context, AppDatabase.class,DB_NAME) // Set INSTANCE To New Room Database ;
                   .allowMainThreadQueries() //Allow On Main Thread run Queries
                    .fallbackToDestructiveMigration()//Drop Table And Migration To New Version
                    .build();
        }
        return INSTANCE;
    }
}
