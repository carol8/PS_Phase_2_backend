package com.example.project_phase_2_1.service.impl;

import com.example.project_phase_2_1.dto.extended_donor_data.ExtendedDonorDataCreateDTO;
import com.example.project_phase_2_1.dto.extended_donor_data.ExtendedDonorDataDTO;
import com.example.project_phase_2_1.dto.extended_donor_data.ExtendedDonorDataUpdateDTO;
import com.example.project_phase_2_1.service.ExtendedDonorDataService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class ExtendedDonorDataImpl implements ExtendedDonorDataService {
    private final WebClient webClient = WebClient.create("http://localhost:8081");

    @Override
    public Optional<ExtendedDonorDataDTO> getExtendedDonorData(String cnp) {
        try {
            ResponseEntity<ExtendedDonorDataDTO> responseEntity = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/donors/extended/{cnp}")
                            .build(cnp))
                    .retrieve()
                    .toEntity(ExtendedDonorDataDTO.class)
                    .toFuture()
                    .get();
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                ExtendedDonorDataDTO dto = responseEntity.getBody();
                if (dto != null) {
                    return Optional.of(dto);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ExtendedDonorDataDTO> createExtendedDonorData(ExtendedDonorDataCreateDTO dto) {
        try {
            ResponseEntity<ExtendedDonorDataDTO> responseEntity = webClient.post()
                    .uri(uriBuilder -> uriBuilder
                            .path("/donors/extended")
                            .build())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(dto))
                    .retrieve()
                    .toEntity(ExtendedDonorDataDTO.class)
                    .toFuture()
                    .get();
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                ExtendedDonorDataDTO extendedDonorDataDTO = responseEntity.getBody();
                if (extendedDonorDataDTO != null) {
                    return Optional.of(extendedDonorDataDTO);
                }
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ExtendedDonorDataDTO> updateOrCreateExtendedDonorData(String cnp, ExtendedDonorDataUpdateDTO dto) {
        try {
            ResponseEntity<ExtendedDonorDataDTO> responseEntity = webClient.put()
                    .uri(uriBuilder -> uriBuilder
                            .path("/donors/extended/{cnp}")
                            .build(cnp))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(dto))
                    .retrieve()
                    .toEntity(ExtendedDonorDataDTO.class)
                    .toFuture()
                    .get();
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                ExtendedDonorDataDTO extendedDonorDataDTO = responseEntity.getBody();
                if (extendedDonorDataDTO != null) {
                    return Optional.of(extendedDonorDataDTO);
                }
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ExtendedDonorDataDTO> deleteExtendedDonorData(String cnp) {
        try {
            ResponseEntity<ExtendedDonorDataDTO> responseEntity = webClient.delete()
                    .uri(uriBuilder -> uriBuilder
                            .path("/donors/extended/{cnp}")
                            .build(cnp))
                    .retrieve()
                    .toEntity(ExtendedDonorDataDTO.class)
                    .toFuture()
                    .get();
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                ExtendedDonorDataDTO dto = responseEntity.getBody();
                if (dto != null) {
                    return Optional.of(dto);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
