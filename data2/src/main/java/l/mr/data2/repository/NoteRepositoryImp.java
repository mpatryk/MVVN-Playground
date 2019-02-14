package l.mr.data2.repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import l.mr.domain.entity.Note;
import l.mr.domain.repository.NoteRepository;

public class NoteRepositoryImp implements NoteRepository {
    @Override
    public void insert(Note note) {

    }

    @Override
    public void update(Note note) {

    }

    @Override
    public void delete(Note note) {

    }

    @Override
    public void deleteAllNotes() {

    }

    @Override
    public LiveData<List<Note>> getAllNotes() {
        return null;
    }

}
