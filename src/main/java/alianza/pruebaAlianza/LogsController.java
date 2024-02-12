package alianza.pruebaAlianza;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("logs")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Validated
@Slf4j
public class LogsController {

    private final LogsImplement _logsImplement;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public LogsEntity add(@RequestBody LogsEntity logsEntity){
        return _logsImplement.add(logsEntity);
    }

    @PostMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LogsEntity> getAll(){
        return _logsImplement.getAll();
    }
}
