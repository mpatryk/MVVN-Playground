package l.mr.mvvnsample.presenter.mapper;

import l.mr.domain.entity.Note;

public class NoteMapper {
    public static Note map (l.mr.data2.entity.Note noteDto) {
        Note note = new Note();
        note.setId(noteDto.getId());
        note.setDescription(noteDto.getDescription());
        note.setPriority(noteDto.getPriority());
        note.setTitle(noteDto.getTitle());
        return note;
    }
}
