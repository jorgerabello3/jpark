package br.com.jorgerabellodev.jpark.controller;

import br.com.jorgerabellodev.jpark.model.dto.CompanyRequestDTO;
import br.com.jorgerabellodev.jpark.model.dto.CompanyResponseDTO;
import br.com.jorgerabellodev.jpark.model.dto.ErrorDTO;
import br.com.jorgerabellodev.jpark.model.dto.VehicleRequestDTO;
import br.com.jorgerabellodev.jpark.model.dto.VehicleResponseDTO;
import br.com.jorgerabellodev.jpark.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.api.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "vehicle")
@RequiredArgsConstructor
@RequestMapping(value = "/vehicle")
public class VehicleController {

  private final VehicleService service;

  @Operation(summary = "Cadastra um novo veículo", description = "Realiza o cadastro de novo veículo.", tags = "vehicle")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Veículo cadastrado com sucesso.",
                  content = @Content(schema = @Schema(implementation = VehicleResponseDTO.class))),
          @ApiResponse(responseCode = "400",
                  description = "Bad request body inválido",
                  content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
          @ApiResponse(responseCode = "415",
                  description = "Mídia não suportada, por favor utilize o MediaType application/json",
                  content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
  })
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<VehicleResponseDTO> create(@Valid @RequestBody VehicleRequestDTO vehicleRequestDTO) {
    VehicleResponseDTO vehicle = service.create(vehicleRequestDTO);
    return new ResponseEntity<>(vehicle, HttpStatus.CREATED);
  }
}
