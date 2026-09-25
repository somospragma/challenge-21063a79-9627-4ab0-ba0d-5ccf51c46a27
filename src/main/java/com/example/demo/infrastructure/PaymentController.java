package com.example.demo.infrastructure;

import com.example.demo.application.PaymentService;
import com.example.demo.domain.Payment;
import com.example.demo.domain.Payment.PaymentStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public record CreatePaymentRequest(
        @NotNull(message = "User ID is required") UUID userId,
        @NotNull(message = "Amount is required") @DecimalMin(value = "0.01", message = "Amount must be at least 0.01") BigDecimal amount,
        @NotBlank(message = "Currency is required") @Size(min = 3, max = 3, message = "Currency must be 3 characters") String currency,
        @Size(max = 500, message = "Description cannot exceed 500 characters") String description
    ) {}

    public record UpdateStatusRequest(
        @NotNull(message = "Status is required") PaymentStatus status
    ) {}

    public record PaymentResponse(
        UUID id,
        UUID userId,
        BigDecimal amount,
        String currency,
        String description,
        PaymentStatus status,
        Instant createdAt,
        Instant updatedAt
    ) {
        public static PaymentResponse fromDomain(Payment payment) {
            return new PaymentResponse(
                payment.id(),
                payment.userId(),
                payment.amount(),
                payment.currency(),
                payment.description(),
                payment.status(),
                payment.createdAt(),
                payment.updatedAt()
            );
        }
    }

    public record ErrorResponse(
        String error,
        String message,
        Instant timestamp,
        Map<String, String> details
    ) {
        public static ErrorResponse of(String error, String message) {
            return new ErrorResponse(error, message, Instant.now(), null);
        }

        public static ErrorResponse of(String error, String message, Map<String, String> details) {
            return new ErrorResponse(error, message, Instant.now(), details);
        }
    }

    @PostMapping
    public Mono<ResponseEntity<PaymentResponse>> createPayment(@Valid @RequestBody CreatePaymentRequest request) {
        log.info("POST /api/v1/payments - Creating payment for user: {}", request.userId());
        
        return paymentService.createPayment(
            request.userId(),
            request.amount(),
            request.currency(),
            request.description()
        )
        .map(payment -> ResponseEntity
            .status(HttpStatus.CREATED)
            .contentType(MediaType.APPLICATION_JSON)
            .body(PaymentResponse.fromDomain(payment)))
        .doOnSuccess(response -> log.info("Payment created with ID: {}", response.body().id()))
        .doOnError(error -> log.error("Error creating payment: {}", error.getMessage()));
    }

    @GetMapping("/{paymentId}")
    public Mono<ResponseEntity<PaymentResponse>> getPayment(@PathVariable UUID paymentId) {
        log.info("GET /api/v1/payments/{}", paymentId);
        
        return paymentService.getPaymentById(paymentId)
            .map(payment -> ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(PaymentResponse.fromDomain(payment)))
            .doOnSuccess(response -> log.debug("Payment retrieved: {}", paymentId))
            .doOnError(error -> log.error("Error retrieving payment: {}", error.getMessage()));
    }

    @GetMapping
    public Mono<ResponseEntity<Flux<PaymentResponse>>> getPaymentsByUser(
            @RequestParam(required = false) UUID userId,
            @RequestParam(required = false) PaymentStatus status) {
        
        log.info("GET /api/v1/payments - userId: {}, status: {}", userId, status);
        
        Flux<PaymentResponse> paymentsFlux;
        
        if (userId != null) {
            paymentsFlux = paymentService.getPaymentsByUser(userId)
                .map(PaymentResponse::fromDomain);
        } else if (status != null) {
            paymentsFlux = paymentService.getPaymentsByStatus(status)
                .map(PaymentResponse::fromDomain);
        } else {
            return Mono.just(ResponseEntity
                .badRequest()
                .body(Flux.just(PaymentResponse.fromDomain(
                    new Payment(null, null, null, null, null, null, null, null)
                ))));
        }
        
        return Mono.just(ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(paymentsFlux))
            .doOnSuccess(response -> log.debug("Payments retrieved successfully"))
            .doOnError(error -> log.error("Error retrieving payments: {}", error.getMessage()));
    }

    @PostMapping("/{paymentId}/complete")
    public Mono<ResponseEntity<PaymentResponse>> completePayment(@PathVariable UUID paymentId) {
        log.info("POST /api/v1/payments/{}/complete", paymentId);
        
        return paymentService.completePayment(paymentId)
            .map(payment -> ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(PaymentResponse.fromDomain(payment)))
            .doOnSuccess(response -> log.info("Payment completed: {}", paymentId))
            .doOnError(error -> log.error("Error completing payment: {}", error.getMessage()));
    }

    @PostMapping("/{paymentId}/fail")
    public Mono<ResponseEntity<PaymentResponse>> failPayment(
            @PathVariable UUID paymentId,
            @RequestBody(required = false) Map<String, String> body) {
        
        String reason = body != null ? body.get("reason") : "Unknown error";
        log.info("POST /api/v1/payments/{}/fail - reason: {}", paymentId, reason);
        
        return paymentService.failPayment(paymentId, reason)
            .map(payment -> ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(PaymentResponse.fromDomain(payment)))
            .doOnSuccess(response -> log.info("Payment failed: {}", paymentId))
            .doOnError(error -> log.error("Error failing payment: {}", error.getMessage()));
    }

    @PatchMapping("/{paymentId}/status")
    public Mono<ResponseEntity<PaymentResponse>> updatePaymentStatus(
            @PathVariable UUID paymentId,
            @Valid @RequestBody UpdateStatusRequest request) {
        
        log.info("PATCH /api/v1/payments/{}/status - new status: {}", paymentId, request.status());
        
        return paymentService.updatePaymentStatus(paymentId, request.status())
            .map(payment -> ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(PaymentResponse.fromDomain(payment)))
            .doOnSuccess(response -> log.info("Payment status updated: {}", paymentId))
            .doOnError(error -> log.error("Error updating payment status: {}", error.getMessage()));
    }

    @DeleteMapping("/{paymentId}")
    public Mono<ResponseEntity<Void>> deletePayment(@PathVariable UUID paymentId) {
        log.info("DELETE /api/v1/payments/{}", paymentId);
        
        return paymentService.deletePayment(paymentId)
            .thenReturn(ResponseEntity.noContent().build())
            .doOnSuccess(response -> log.info("Payment deleted: {}", paymentId))
            .doOnError(error -> log.error("Error deleting payment: {}", error.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleIllegalArgument(IllegalArgumentException ex) {
        log.warn("Bad request: {}", ex.getMessage());
        return Mono.just(ResponseEntity
            .badRequest()
            .body(ErrorResponse.of("BAD_REQUEST", ex.getMessage())));
    }

    @ExceptionHandler(IllegalStateException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleIllegalState(IllegalStateException ex) {
        log.warn("Invalid state: {}", ex.getMessage());
        return Mono.just(ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(ErrorResponse.of("CONFLICT", ex.getMessage())));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorResponse>> handleGenericException(Exception ex) {
        log.error("Unexpected error: {}", ex.getMessage(), ex);
        return Mono.just(ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponse.of("INTERNAL_ERROR", "An unexpected error occurred")));
    }
}