package l.mr.data2.mapper;

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

    public static l.mr.data2.entity.Note mapToLive(Note noteDto) {
        l.mr.data2.entity.Note note = new l.mr.data2.entity.Note();
        note.setId(noteDto.getId());
        note.setDescription(noteDto.getDescription());
        note.setPriority(noteDto.getPriority());
        note.setTitle(noteDto.getTitle());
        return note;
    }
}
