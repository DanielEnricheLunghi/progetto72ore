package com.condominio.ConvivoApp.billing.service;

import com.condominio.ConvivoApp.billing.dto.ContractDTO;

import java.util.List;
import java.util.UUID;

/**
 * Interfaccia per la gestione dei contratti.
 * Definisce le operazioni disponibili per Contract.
 */
public interface ContractService {

    /**
     * Crea un nuovo contratto.
     */
    ContractDTO createContract(ContractDTO contractDTO);

    /**
     * Aggiorna un contratto esistente.
     */
    ContractDTO updateContract(Long id, ContractDTO contractDTO);

    /**
     * Recupera un contratto tramite ID.
     */
    ContractDTO getContractById(Long id);

    /**
     * Recupera tutti i contratti.
     */
    List<ContractDTO> getAllContracts();

    /**
     * Recupera tutti i contratti di un utente specifico.
     */
    List<ContractDTO> getContractsByUser(UUID id);

    /**
     * Elimina un contratto tramite ID.
     */
    void deleteContract(Long id);
}
