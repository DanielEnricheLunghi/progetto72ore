package com.condominio.ConvivoApp.billing.service;

import com.condominio.ConvivoApp.billing.dto.ContractDTO;
import com.condominio.ConvivoApp.billing.entity.Contract;
import com.condominio.ConvivoApp.billing.repository.ContractRepository;
import com.condominio.ConvivoApp.user.entity.User;
import com.condominio.ConvivoApp.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * Implementazione concreta del service per la gestione dei contratti.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final UserRepository userRepository;

    @Override
    public ContractDTO createContract(ContractDTO dto) {

        // Recupera l'utente che firma il contratto (UUID)
        User signedBy = userRepository.findById(dto.getSignedById())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Crea l'entità Contract partendo dal DTO
        Contract contract = Contract.builder()
                .signedBy(signedBy)
                .title(dto.getTitle())
                .description(dto.getDescription())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();

        // Salva il contratto nel DB
        Contract saved = contractRepository.save(contract);

        return convertToDTO(saved);
    }

    @Override
    public ContractDTO updateContract(Long id, ContractDTO dto) {

        // Recupera il contratto esistente
        Contract existing = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        // Aggiorna solo i campi presenti nel DTO
        if (dto.getTitle() != null) existing.setTitle(dto.getTitle());
        if (dto.getDescription() != null) existing.setDescription(dto.getDescription());
        if (dto.getStartDate() != null) existing.setStartDate(dto.getStartDate());
        if (dto.getEndDate() != null) existing.setEndDate(dto.getEndDate());

        // Se l’utente firmatario viene cambiato
        if (dto.getSignedById() != null) {
            User signedBy = userRepository.findById(dto.getSignedById())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            existing.setSignedBy(signedBy);
        }

        Contract updated = contractRepository.save(existing);

        return convertToDTO(updated);
    }

    @Override
    public ContractDTO getContractById(Long id) {
        return contractRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
    }

    @Override
    public List<ContractDTO> getAllContracts() {
        return contractRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContractDTO> getContractsByUser(UUID userId) {
        return contractRepository.findBySignedById(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteContract(Long id) {
        contractRepository.deleteById(id);
    }

    /**
     * Converte una entity Contract in un ContractDTO.
     * Usato in tutte le risposte dei service.
     */
    private ContractDTO convertToDTO(Contract contract) {
        return ContractDTO.builder()
                .id(contract.getId())
                .signedById(contract.getSignedBy() != null ? contract.getSignedBy().getId() : null)
                .title(contract.getTitle())
                .description(contract.getDescription())
                .startDate(contract.getStartDate())
                .endDate(contract.getEndDate())
                .createdAt(contract.getCreatedAt())
                .build();
    }
}


//    private void validateContractClosure(Contract contract) {
//
//        boolean hasUnpaid = contract.getInvoices()
//                .stream()
//                .anyMatch(inv -> inv.getState() != InvoiceState.PAID);
//
//        if (hasUnpaid) {
//            throw new InvalidStateException("Cannot close contract with unpaid invoices.");
//        }
//    }

