package pl.pkowalc.praca.note;

import java.util.List;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
class NoteController {

    private final NoteService noteService;

    @GetMapping()
    List<NoteResponse> getNotes(@RequestAttribute String userName) {
        return noteService.getNotes(userName);
    }

    @GetMapping(params = "carId")
    List<NoteResponse> getNotes(@RequestParam Integer carId, @RequestAttribute String userName) {
        return noteService.getNotes(carId, userName);
    }

    @GetMapping("/{id}")
    NoteResponse getNote(@PathVariable Integer id, @RequestAttribute String userName) {
        return noteService.getNote(id, userName);
    }

    @PostMapping("")
    CreateNoteResponse create(@RequestBody @Valid CreateNoteRequest request, @RequestAttribute String userName) {
        return noteService.add(request, userName);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id, @RequestAttribute String userName) {
        noteService.delete(id, userName);
    }

}
