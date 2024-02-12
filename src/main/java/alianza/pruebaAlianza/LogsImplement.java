package alianza.pruebaAlianza;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LogsImplement {
    private final LogsRepository _logsRepository;

    public LogsEntity add(LogsEntity logsRepository){
        logsRepository.setCreate(new Date());
        return _logsRepository.save(logsRepository);
    }

    public List<LogsEntity> getAll(){
        return  _logsRepository.findAll();
    }
}
