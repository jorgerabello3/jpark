package br.com.jorgerabellodev.jpark.controller;

import br.com.jorgerabellodev.jpark.model.dto.CompanyRequestDTO;
import br.com.jorgerabellodev.jpark.model.dto.CompanyResponseDTO;
import br.com.jorgerabellodev.jpark.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/company")
public class CompanyController {

  private final CompanyService service;

  @PostMapping
  public ResponseEntity<CompanyResponseDTO> create(@Valid @RequestBody CompanyRequestDTO companyRequestDTO) {
    CompanyResponseDTO company = service.create(companyRequestDTO);
    return new ResponseEntity<>(company, HttpStatus.CREATED);
  }

  @GetMapping(path = "/{id}")
  private ResponseEntity<CompanyResponseDTO> findById(@PathVariable("id") Long id) {
    CompanyResponseDTO company = service.findById(id);
    return new ResponseEntity<>(company, HttpStatus.OK);
  }

}
