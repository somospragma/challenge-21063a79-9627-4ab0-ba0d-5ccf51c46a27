package com.example.demo.application;

import com.example.demo.domain.Payment;
import com.example.demo.domain.PaymentRepository;
import com.example.demo.domain.Payment.PaymentStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);
    private static final BigDecimal MINIMUM_AMOUNT = new BigDecimal("0.01");
    private static final BigDecimal MAXIMUM_AMOUNT = new BigDecimal("999999.99");

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Mono<Payment> createPayment(UUID userId, BigDecimal amount, String currency, String description) {
        log.info("Creating payment for user {} with amount {} {}", userId, amount, currency);
        
        if (amount == null || amount.compareTo(MINIMUM_AMOUNT) < 0) {
            log.warn("Invalid payment amount: {}", amount);
            return Mono.error(new IllegalArgumentException("Amount must be greater than " + MINIMUM_AMOUNT));
        }
        
        if (amount.compareTo(MAXIMUM_AMOUNT) > 0) {
            log.warn("Payment amount exceeds maximum: {} > {}", amount, MAXIMUM_AMOUNT);
            return Mono.error(new IllegalArgumentException("Amount must not exceed " + MAXIMUM_AMOUNT));
        }
        
        if (currency == null || currency.isBlank()) {
            return Mono.error(new IllegalArgumentException("Currency is required"));
        }
        
        Payment payment = new Payment(
            UUID.randomUUID(),
            userId,
            amount,
            currency,
            description,
            PaymentStatus.PENDING,
            java.time.Instant.now(),
            null
        );
        
        payment.validate();
        
        return paymentRepository.save(payment)
            .doOnSuccess(saved -> log.info("Payment created successfully: {}", saved.id()))
            .doOnError(error -> log.error("Failed to create payment: {}", error.getMessage()));
    }

    public Mono<Payment> completePayment(UUID paymentId) {
        log.info("Completing payment: {}", paymentId);
        
        return paymentRepository.findById(paymentId)
            .flatMap(payment -> {
                if (payment.status() != PaymentStatus.PENDING) {
                    log.warn("Cannot complete payment {} with status: {}", paymentId, payment.status());
                    return Mono.error(new IllegalStateException("Payment is not in PENDING status"));
                }
                Payment completed = payment.complete();
                return paymentRepository.save(completed);
            })
            .doOnSuccess(saved -> log.info("Payment completed successfully: {}", saved.id()))
            .doOnError(error -> log.error("Failed to complete payment: {}", error.getMessage()));
    }

    public Mono<Payment> failPayment(UUID paymentId, String reason) {
        log.info("Failing payment: {} with reason: {}", paymentId, reason);
        
        return paymentRepository.findById(paymentId)
            .flatMap(payment -> {
                if (payment.status() != PaymentStatus.PENDING) {
                    log.warn("Cannot fail payment {} with status: {}", paymentId, payment.status());
                    return Mono.error(new IllegalStateException("Payment is not in PENDING status"));
                }
                Payment failed = payment.fail();
                return paymentRepository.save(failed);
            })
            .doOnSuccess(saved -> log.info("Payment failed: {}", saved.id()))
            .doOnError(error -> log.error("Failed to fail payment: {}", error.getMessage()));
    }

    public Mono<Payment> getPaymentById(UUID paymentId) {
        log.debug("Retrieving payment: {}", paymentId);
        return paymentRepository.findById(paymentId)
            .switchIfEmpty(Mono.error(new IllegalArgumentException("Payment not found: " + paymentId)));
    }

    public Flux<Payment> getPaymentsByUser(UUID userId) {
        log.debug("Retrieving payments for user: {}", userId);
        return paymentRepository.findByUserId(userId)
            .doOnSubscribe(s -> log.debug("Started fetching payments for user: {}", userId))
            .doOnComplete(() -> log.debug("Completed fetching payments for user: {}", userId));
    }

    public Flux<Payment> getPaymentsByStatus(PaymentStatus status) {
        log.debug("Retrieving payments with status: {}", status);
        return paymentRepository.findByStatus(status)
            .doOnSubscribe(s -> log.debug("Started fetching payments with status: {}", status))
            .doOnComplete(() -> log.debug("Completed fetching payments with status: {}", status));
    }

    public Mono<Void> deletePayment(UUID paymentId) {
        log.info("Deleting payment: {}", paymentId);
        return paymentRepository.findById(paymentId)
            .flatMap(payment -> {
                if (payment.status() == PaymentStatus.COMPLETED) {
                    log.warn("Cannot delete completed payment: {}", paymentId);
                    return Mono.error(new IllegalStateException("Cannot delete a completed payment"));
                }
                return paymentRepository.deleteById(paymentId);
            })
            .doOnSuccess(v -> log.info("Payment deleted: {}", paymentId))
            .doOnError(error -> log.error("Failed to delete payment: {}", error.getMessage()));
    }

    public Mono<Payment> updatePaymentStatus(UUID paymentId, PaymentStatus newStatus) {
        log.info("Updating payment {} to status: {}", paymentId, newStatus);
        return paymentRepository.updateStatus(paymentId, newStatus)
            .doOnSuccess(saved -> log.info("Payment status updated: {} -> {}", saved.id(), saved.status()))
            .doOnError(error -> log.error("Failed to update payment status: {}", error.getMessage()));
    }
}