package com.condominio.ConvivoApp.billing.service;

import com.condominio.ConvivoApp.billing.dto.PaymentDTO;
import com.condominio.ConvivoApp.billing.entity.Invoice;
import com.condominio.ConvivoApp.billing.entity.Payment;
import com.condominio.ConvivoApp.billing.entity.PaymentStatus;
import com.condominio.ConvivoApp.billing.repository.InvoiceRepository;
import com.condominio.ConvivoApp.billing.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Implementazione concreta del service per la gestione dei pagamenti.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;

    /**
     * Crea un nuovo pagamento con validazioni sulla fattura.
     */
    @Override
    public PaymentDTO createPayment(PaymentDTO dto) {

        // Recupera la fattura associata
        Invoice invoice = invoiceRepository.findById(dto.getInvoiceId())
                .orElseThrow(() -> new NoSuchElementException("Invoice not found"));

        // Verifica se è possibile aggiungere un pagamento
        validatePaymentCreation(invoice);

        // Crea il pagamento
        Payment payment = Payment.builder()
                .invoice(invoice)
                .method(dto.getMethod())
                .amount(dto.getAmount())
                .reference(dto.getReference())
                .status(dto.getStatus() != null ? dto.getStatus() : PaymentStatus.PENDING)
                .build();


        paymentRepository.save(payment);

        return convertToDTO(payment);
    }

    /**
     * Aggiorna un pagamento esistente.
     */
    @Override
    public PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO) {

        Payment existing = paymentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Payment not found"));

        if (paymentDTO.getMethod() != null) existing.setMethod(paymentDTO.getMethod());
        if (paymentDTO.getAmount() != null) existing.setAmount(paymentDTO.getAmount());
        if (paymentDTO.getReference() != null) existing.setReference(paymentDTO.getReference());
        if (paymentDTO.getStatus() != null) existing.setStatus(paymentDTO.getStatus());

        Payment updated = paymentRepository.save(existing);

        return convertToDTO(updated);
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new NoSuchElementException("Payment not found"));
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> getPaymentsByInvoice(Long invoiceId) {
        return paymentRepository.findByInvoiceId(invoiceId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> getPaymentsByStatus(PaymentStatus status) {
        return paymentRepository.findByStatus(status)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    /**
     * Verifica che si possa creare un pagamento per la fattura.
     * (Esempio: non puoi pagare una fattura già saldata)
     */
    private void validatePaymentCreation(Invoice invoice) {

        // Se la fattura è già pagata → blocca nuovi pagamenti
        if (invoice.getStatus() != null && invoice.getStatus().name().equals("PAID")) {
            throw new IllegalStateException("Cannot create a payment for a paid invoice.");
        }
    }

    /**
     * Converte Payment → PaymentDTO.
     */
    private PaymentDTO convertToDTO(Payment payment) {
        return PaymentDTO.builder()
                .id(payment.getId())
                .invoiceId(payment.getInvoice() != null ? payment.getInvoice().getId() : null)
                .method(payment.getMethod())
                .amount(payment.getAmount())
                .reference(payment.getReference())
                .status(payment.getStatus())
                .createdAt(payment.getCreatedAt())
                .build();
    }
}
