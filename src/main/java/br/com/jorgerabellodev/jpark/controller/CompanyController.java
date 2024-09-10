package br.com.jorgerabellodev.jpark.controller;

import br.com.jorgerabellodev.jpark.model.dto.CompanyRequestDTO;
import br.com.jorgerabellodev.jpark.model.dto.CompanyResponseDTO;
import br.com.jorgerabellodev.jpark.model.dto.ErrorDTO;
import br.com.jorgerabellodev.jpark.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Tag(name = "company")
@RequiredArgsConstructor
@RequestMapping(value = "/company")
public class CompanyController {

  private final CompanyService service;

  @Operation(summary = "Cadastra uma nova empresa", description = "Realiza o cadastro de uma nova empresa.", tags = "company")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Empresa cadastrada com sucesso.",
                  content = @Content(schema = @Schema(implementation = CompanyResponseDTO.class))),
          @ApiResponse(responseCode = "400",
                  description = "Bad request body inválido",
                  content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
          @ApiResponse(responseCode = "415",
                  description = "Mídia não suportada, por favor utilize o MediaType application/json",
                  content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
  })
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CompanyResponseDTO> create(@Valid @RequestBody CompanyRequestDTO companyRequestDTO) {
    CompanyResponseDTO company = service.create(companyRequestDTO);
    return new ResponseEntity<>(company, HttpStatus.CREATED);
  }


  @Operation(summary = "Recupera uma empresa pelo id.", description = "Dado um id recupera os dados de uma empresa cadastrada.", tags = "company")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Empresa encontrada.", content = @Content(schema = @Schema(implementation = CompanyResponseDTO.class))),
          @ApiResponse(responseCode = "404",
                  description = "Empresa não encontrada",
                  content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
          @ApiResponse(responseCode = "415",
                  description = "Mídia não suportada, por favor utilize o MediaType application/json",
                  content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
  })
  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CompanyResponseDTO> findById(@PathVariable("id") Long id) {
    CompanyResponseDTO company = service.findById(id);
    return new ResponseEntity<>(company, HttpStatus.OK);
  }


  @Operation(summary = "Atualiza os dados de uma empresa.", description = "Atualiza os dados de uma empresa cadastrada.", tags = "company")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Empresa atualizada com sucesso.", content = @Content(schema = @Schema(implementation = CompanyResponseDTO.class))),
          @ApiResponse(responseCode = "404",
                  description = "Empresa não encontrada",
                  content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
          @ApiResponse(responseCode = "400",
                  description = "Bad request body inválido",
                  content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
          @ApiResponse(responseCode = "415",
                  description = "Mídia não suportada, por favor utilize o MediaType application/json",
                  content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
  })
  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CompanyResponseDTO> update(@PathVariable("id") Long id,
                                                   @Valid @RequestBody CompanyRequestDTO companyRequestDTO) {
    CompanyResponseDTO savedCompany = service.update(companyRequestDTO, id);
    return new ResponseEntity<>(savedCompany, HttpStatus.OK);
  }

  @Operation(summary = "Excluí uma empresa.", description = "Excluí uma empresa cadastrada.", tags = "company")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "204", description = "Empresa excluída com sucesso."),
          @ApiResponse(responseCode = "404",
                  description = "Empresa não encontrada",
                  content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
          @ApiResponse(responseCode = "415",
                  description = "Mídia não suportada, por favor utilize o MediaType application/json",
                  content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
  })
  @DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
