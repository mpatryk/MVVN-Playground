package l.mr.domain.repository;

import java.util.List;

import l.mr.domain.entity.Note;

public interface NoteRepository<T> {
    void insert(Note note);
    void update(Note note);
    void delete(Note note);
    void deleteAllNotes();
    T getAllNotes();
}
