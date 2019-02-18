package l.mr.data.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import l.mr.data.entity.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;
    public abstract NoteDAO noteDao();

    public static synchronized NoteDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, NoteDatabase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateObAsyncTask(instance).execute();
        }
    };

    private static class PopulateObAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDAO noteDao;

        private PopulateObAsyncTask(NoteDatabase database) {
            noteDao = database.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for(int i=0; i<3; i++) {
                noteDao.insert(new Note("Title " + i, "Description " + i, i));
            }
            return null;
        }
    }
}
