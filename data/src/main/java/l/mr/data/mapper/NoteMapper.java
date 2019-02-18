package l.mr.data.mapper;

import l.mr.domain.entity.Note;

public class NoteMapper {
    public static Note map (l.mr.data.entity.Note noteDto) {
        Note note = new Note();
        note.setId(noteDto.getId());
        note.setDescription(noteDto.getDescription());
        note.setPriority(noteDto.getPriority());
        note.setTitle(noteDto.getTitle());
        return note;
    }

    public static l.mr.data.entity.Note mapToLive(Note noteDto) {
        l.mr.data.entity.Note note = new l.mr.data.entity.Note();
        note.setId(noteDto.getId());
        note.setDescription(noteDto.getDescription());
        note.setPriority(noteDto.getPriority());
        note.setTitle(noteDto.getTitle());
        return note;
    }
}
