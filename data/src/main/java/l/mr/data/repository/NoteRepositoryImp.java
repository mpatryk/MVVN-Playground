package l.mr.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import l.mr.data.database.NoteDAO;
import l.mr.data.database.NoteDatabase;
import l.mr.data.mapper.NoteMapper;
import l.mr.domain.entity.Note;
import l.mr.domain.repository.NoteRepository;

public class NoteRepositoryImp implements NoteRepository<LiveData<List<l.mr.data.entity.Note>>> {
    private NoteDAO noteDao;
    private LiveData<List<l.mr.data.entity.Note>> allNotes;

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
        new UpdateNoteAsyncTask(noteDao).execute(NoteMapper.mapToLive(note));
    }

    @Override
    public void delete(Note note) {
        new DeleteNoteAsyncTask(noteDao).execute(NoteMapper.mapToLive(note));
    }

    @Override
    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }

    @Override
    public LiveData<List<l.mr.data.entity.Note>> getAllNotes() {
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

    private static class UpdateNoteAsyncTask extends AsyncTask<l.mr.data.entity.Note, Void, Void> {
        private NoteDAO noteDao;
        private UpdateNoteAsyncTask(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(l.mr.data.entity.Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<l.mr.data.entity.Note, Void, Void> {
        private NoteDAO noteDao;
        private DeleteNoteAsyncTask(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(l.mr.data.entity.Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class InsertNoteAsyncTask extends AsyncTask<l.mr.data.entity.Note, Void, Void> {
        private NoteDAO noteDao;
        private InsertNoteAsyncTask(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(l.mr.data.entity.Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }



}
