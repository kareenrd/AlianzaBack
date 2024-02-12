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
public class ClientImplement {
    private final ClientRepository _clientRepository;
    private final LogsImplement _logsImplement;
    ObjectMapper mapper = new ObjectMapper();


    public ResponseDTO add(ClientEntity clientEntity) throws Exception{
        ResponseDTO responseDTO = new ResponseDTO();
        LogsEntity logsEntity = new LogsEntity();
        clientEntity.setCreate(new Date());
        //Tomar SharedKey
        String[] sharedKey = clientEntity.getEmail().split("@");
        clientEntity.setSharedKey(sharedKey[0]);

        List<ClientEntity> search = _clientRepository.findBySharedKey(clientEntity.getSharedKey());
        log.info("search: "+search.isEmpty());

        if(search.isEmpty()){
            ClientEntity save = _clientRepository.save(clientEntity);
            logsEntity.setLog("Se creo cliente "+save.getId()+" - email: "+save.getEmail());

            String r = mapper.writeValueAsString(save);
            Object response = mapper.readValue(r, Object.class);

            responseDTO.setCode(1);
            responseDTO.setMessage("Creacción exitosa");
            responseDTO.setData(response);
        } else {
            logsEntity.setLog("El cliente ya existe "+ clientEntity.getEmail());
            responseDTO.setCode(0);
            responseDTO.setMessage("El cliente ya existe");
            responseDTO.setData(null);
        }


        _logsImplement.add(logsEntity);

        return responseDTO;
    }

    public ResponseDTO getAll() throws Exception{
        ResponseDTO responseDTO = new ResponseDTO();
        LogsEntity logsEntity = new LogsEntity();
        List<ClientEntity> search = _clientRepository.findAll();
        log.info("search "+search.isEmpty());
        if(!search.isEmpty()) {
            String r = mapper.writeValueAsString(search);
            Object response = mapper.readValue(r, Object.class);
            logsEntity.setLog("Se consultaron todos los clientes");
            responseDTO.setCode(1);
            responseDTO.setMessage("Consulta exitosa");
            responseDTO.setData(response);
        } else {
            logsEntity.setLog("No hay datos");
            responseDTO.setCode(2);
            responseDTO.setMessage("No hay datos");
            responseDTO.setData(null);
        }
        _logsImplement.add(logsEntity);
        return responseDTO;
    }

    public ResponseDTO searchBySharedKey(ClientEntity clientEntity) throws Exception {
        ResponseDTO responseDTO = new ResponseDTO();
        LogsEntity logsEntity = new LogsEntity();
        List<ClientEntity> search = _clientRepository.findBySharedKey(clientEntity.getSharedKey());
        log.info("search: "+search.isEmpty());

        if(!search.isEmpty()) {
            String r = mapper.writeValueAsString(search);
            Object response = mapper.readValue(r, Object.class);
            logsEntity.setLog("Consulta por sharedKey "+ clientEntity.getSharedKey());
            responseDTO.setCode(1);
            responseDTO.setMessage("Consulta exitosa");
            responseDTO.setData(response);
        } else {
            logsEntity.setLog("No hay datos");
            responseDTO.setCode(2);
            responseDTO.setMessage("No hay datos");
            responseDTO.setData(null);
        }
        _logsImplement.add(logsEntity);
        return responseDTO;
    }
}
