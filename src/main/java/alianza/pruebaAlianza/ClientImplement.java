package alianza.AlianzaBack;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientImplement {
    private final ClientRepository _clientRepository;
    ObjectMapper mapper = new ObjectMapper();
    ResponseDTO responseDTO = new ResponseDTO();

    public ResponseDTO add(ClientEntity clientEntity) throws Exception{

        clientEntity.setCreate(new Date());
        //Tomar SharedKey
        String[] sharedKey = clientEntity.getEmail().split("@");
        clientEntity.setSharedKey(sharedKey[0]);

        List<ClientEntity> search = _clientRepository.findBySharedKey(clientEntity.getSharedKey());
        log.info("search: "+search.isEmpty());

        if(search.isEmpty()){
            ClientEntity save = _clientRepository.save(clientEntity);

            String r = mapper.writeValueAsString(save);
            Object response = mapper.readValue(r, Object.class);

            responseDTO.setCode(1);
            responseDTO.setMessage("Creacción exitosa");
            responseDTO.setData(response);
        } else {
            responseDTO.setCode(0);
            responseDTO.setMessage("El cliente ya existe");
            responseDTO.setData(null);
        }
        return responseDTO;
    }

    public ResponseDTO getAll() throws Exception{
        List<ClientEntity> search = _clientRepository.findAll();
        log.info("search "+search.isEmpty());
        if(!search.isEmpty()) {
            String r = mapper.writeValueAsString(search);
            Object response = mapper.readValue(r, Object.class);
            responseDTO.setCode(1);
            responseDTO.setMessage("Consulta exitosa");
            responseDTO.setData(response);
        } else {
            responseDTO.setCode(2);
            responseDTO.setMessage("No hay datos");
            responseDTO.setData(null);
        }
        return responseDTO;
    }

    public ResponseDTO searchBySharedKey(ClientEntity clientEntity) throws Exception {
        List<ClientEntity> search = _clientRepository.findBySharedKey(clientEntity.getSharedKey());
        log.info("search: "+search.isEmpty());

        if(!search.isEmpty()) {
            String r = mapper.writeValueAsString(search);
            Object response = mapper.readValue(r, Object.class);
            responseDTO.setCode(1);
            responseDTO.setMessage("Consulta exitosa");
            responseDTO.setData(response);
        } else {
            responseDTO.setCode(2);
            responseDTO.setMessage("No hay datos");
            responseDTO.setData(null);
        }
        log.info("respondeDTO "+responseDTO);
        return responseDTO;
    }
}
