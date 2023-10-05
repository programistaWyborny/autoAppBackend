package pl.pkowalc.praca.note;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pkowalc.praca.cars.CarService;
import pl.pkowalc.praca.common.NotFoundException;

@Service
@RequiredArgsConstructor
class NoteService {

    private final NoteRepository noteRepository;
    private final CarService carService;

    public List<NoteResponse> getNotes(String userName) {
        return noteRepository.findByUsername(userName)
                .stream()
                .map(noteEntity -> new NoteResponse(noteEntity.getId(), noteEntity.getContent(), noteEntity.getMilage(), noteEntity.getCar().getId()))
                .collect(Collectors.toList());
    }

    public List<NoteResponse> getNotes(Integer carId, String userName) {
        return noteRepository.findByCarIdAndUsername(carId, userName)
                .stream()
                .map(noteEntity -> new NoteResponse(noteEntity.getId(), noteEntity.getContent(), noteEntity.getMilage(),  noteEntity.getCar().getId()))
                .collect(Collectors.toList());
    }

    public NoteResponse getNote(Integer id, String userName) {
        return noteRepository.findByIdAndUsername(id, userName)
                .map(noteEntity -> new NoteResponse(noteEntity.getId(), noteEntity.getContent(), noteEntity.getMilage(), noteEntity.getCar().getId()))
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public CreateNoteResponse add(CreateNoteRequest request, String userName) {
        final var car = carService.getUserCar(request.getCarId(), userName);
        final var newNote = NoteEntity.builder()
                .car(car)
                .content(request.getContent())
                .milage(request.getMilage())
                .build();
        final var savedNote = noteRepository.save(newNote);
        return new CreateNoteResponse(savedNote.getId());
    }

    public void delete(Integer id, String userName) {
        final var note = noteRepository.findByIdAndUsername(id, userName)
                .orElseThrow(NotFoundException::new);
        noteRepository.delete(note);
    }
}
