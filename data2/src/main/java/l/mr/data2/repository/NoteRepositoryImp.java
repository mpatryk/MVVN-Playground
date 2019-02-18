package l.mr.data2.repository;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import l.mr.data2.database.NoteDAO;
import l.mr.data2.database.NoteDatabase;
import l.mr.data2.mapper.NoteMapper;
import l.mr.domain.entity.Note;
import l.mr.domain.repository.NoteRepository;

public class NoteRepositoryImp implements NoteRepository<LiveData<List<l.mr.data2.entity.Note>>> {
    private NoteDAO noteDao;
    private LiveData<List<l.mr.data2.entity.Note>> allNotes;

    public NoteRepositoryImp(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    @Override
    public void insert(Note note) {
        new InsertNoteAsyncTask(noteDao).execute(NoteMapper.mapToLive(note));
    }

    @Override
    public void update(Note note) {

    }

    @Override
    public void delete(Note note) {

    }

    @Override
    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }

    @Override
    public LiveData<List<l.mr.data2.entity.Note>> getAllNotes() {
        return allNotes;
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDAO noteDao;
        private DeleteAllNotesAsyncTask(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }

    private static class InsertNoteAsyncTask extends AsyncTask<l.mr.data2.entity.Note, Void, Void> {
        private NoteDAO noteDao;
        private InsertNoteAsyncTask(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(l.mr.data2.entity.Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }


}
