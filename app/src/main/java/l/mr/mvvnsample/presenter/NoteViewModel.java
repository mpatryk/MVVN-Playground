package l.mr.mvvnsample.presenter;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import l.mr.data.entity.Note;
import l.mr.data.repository.NoteRepositoryImp;
import l.mr.domain.repository.NoteRepository;
import l.mr.mvvnsample.presenter.mapper.NoteMapper;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    @SuppressWarnings("unchecked")
    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepositoryImp(application);
        allNotes = (LiveData<List<Note>>) repository.getAllNotes();
    }

    public void insert(Note note) {
        repository.insert(NoteMapper.map(note));
    }

    public void update(Note note) {
        repository.update(NoteMapper.map(note));
    }

    public void delete(Note note) {
        repository.delete(NoteMapper.map(note));
    }

    public void deleteAllNotes() {
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
