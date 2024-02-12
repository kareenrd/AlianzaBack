package alianza.pruebaAlianza;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("alianzaClient")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ClientController {

    private final ClientImplement _clientImplement;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO add(@RequestBody ClientEntity clientEntity) throws Exception{
        return _clientImplement.add(clientEntity);
    }

    @PostMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO getAll() throws Exception{
        return _clientImplement.getAll();
    }

    @PostMapping(value = "/serachBySharedKey", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO searchBySharedKey(@RequestBody ClientEntity clientEntity) throws Exception{
        return _clientImplement.searchBySharedKey(clientEntity);
    }
}
