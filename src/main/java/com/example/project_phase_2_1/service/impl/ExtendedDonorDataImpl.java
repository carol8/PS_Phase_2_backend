package com.example.project_phase_2_1.service.impl;

import com.example.project_phase_2_1.dto.extended_donor_data.ExtendedDonorDataDTO;
import com.example.project_phase_2_1.dto.extended_donor_data.ExtendedDonorDataUpdateDTO;
import com.example.project_phase_2_1.service.ExtendedDonorDataService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class ExtendedDonorDataImpl implements ExtendedDonorDataService {
    private final WebClient webClient = WebClient.create("https://6e752f37-380c-464a-8950-4f885307e8f8.mock.pstmn.io");
    @Override
    public Optional<ExtendedDonorDataDTO> getExtendedDonorData(String cnp) {
        try {
            ResponseEntity<ExtendedDonorDataDTO> responseEntity = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                        .path("/donors")
                        .queryParam("cnp", cnp)
                        .build())
                    .retrieve()
                    .toEntity(ExtendedDonorDataDTO.class)
                    .toFuture()
                    .get();
            if(responseEntity.getStatusCode().is2xxSuccessful()){
                ExtendedDonorDataDTO dto = responseEntity.getBody();
                if(dto != null) {
                    return Optional.of(dto);
                }
                else{
                    return Optional.empty();
                }
            }
            else{
                return Optional.empty();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<ExtendedDonorDataDTO> updateExtendedDonorData(ExtendedDonorDataUpdateDTO extendedDonorDataDTO) {
        try {
            ResponseEntity<ExtendedDonorDataDTO> responseEntity = webClient.put()
                    .uri(uriBuilder -> uriBuilder
                            .path("/donors")
                            .build())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(extendedDonorDataDTO))
                    .retrieve()
                    .toEntity(ExtendedDonorDataDTO.class)
                    .toFuture()
                    .get();
            if(responseEntity.getStatusCode().is2xxSuccessful()){
                ExtendedDonorDataDTO dto = responseEntity.getBody();
                if(dto != null) {
                    return Optional.of(dto);
                }
                else{
                    return Optional.empty();
                }
            }
            else{
                return Optional.empty();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
