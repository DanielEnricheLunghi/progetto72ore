package com.condominio.ConvivoApp.billing.controller;

import com.condominio.ConvivoApp.billing.dto.ContractDTO;
import com.condominio.ConvivoApp.billing.service.ContractService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller REST per la gestione dei contratti (Contract).
 */
@RestController
@RequestMapping("/api/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    // ----------------------------------------
    // CREATE
    // ----------------------------------------
    @PostMapping
    public ResponseEntity<ContractDTO> createContract(@Valid @RequestBody ContractDTO contractDTO) {
        // Crea un nuovo contratto
        ContractDTO created = contractService.createContract(contractDTO);
        return ResponseEntity.ok(created);
    }

    // ----------------------------------------
    // READ
    // ----------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<ContractDTO> getContractById(@PathVariable Long id) {
        // Recupera un contratto tramite ID
        ContractDTO contract = contractService.getContractById(id);
        return ResponseEntity.ok(contract);
    }

    @GetMapping
    public ResponseEntity<List<ContractDTO>> getAllContracts() {
        // Recupera tutti i contratti
        List<ContractDTO> contracts = contractService.getAllContracts();
        return ResponseEntity.ok(contracts);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ContractDTO>> getContractsByUser(@PathVariable UUID userId) {
        // Recupera tutti i contratti di un utente specifico
        List<ContractDTO> contracts = contractService.getContractsByUser(userId);
        return ResponseEntity.ok(contracts);
    }

    // ----------------------------------------
    // UPDATE
    // ----------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<ContractDTO> updateContract(@PathVariable Long id, @Valid @RequestBody ContractDTO contractDTO) {
        // Aggiorna un contratto esistente
        ContractDTO updated = contractService.updateContract(id, contractDTO);
        return ResponseEntity.ok(updated);
    }

    // ----------------------------------------
    // DELETE
    // ----------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable Long id) {
        // Elimina un contratto tramite ID
        contractService.deleteContract(id);
        return ResponseEntity.noContent().build();
    }
}
