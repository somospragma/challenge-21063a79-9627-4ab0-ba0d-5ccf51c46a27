package com.example.demo.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Entidad de dominio que representa un pago realizado en el sistema.
 * Esta clase encapsula toda la lógica de negocio relacionada con pagos
 * y es utilizada como modelo principal en las operaciones del dominio.
 */
public record Payment(
    @NotNull
    UUID id,

    @NotNull
    @Size(min = 5, max = 100)
    String description,

    @NotNull
    @DecimalMin(value = "0.01")
    BigDecimal amount,

    @NotNull
    LocalDateTime createdAt,

    @NotNull
    PaymentStatus status,

    @NotNull
    UUID userId,

    @NotNull
    String currency
) {
    /**
     * Enumeración que representa los posibles estados de un pago.
     */
    public enum PaymentStatus {
        PENDING,
        COMPLETED,
        FAILED,
        REFUNDED
    }

    /**
     * Constructor alternativo que genera un ID y timestamp automáticamente.
     *
     * @param description Descripción del pago
     * @param amount Cantidad del pago
     * @param userId ID del usuario que realizó el pago
     * @param currency Moneda del pago
     */
    public Payment {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        if (currency == null || currency.length() != 3) {
            throw new IllegalArgumentException("Currency must be a 3-letter code");
        }

        // Validación de campos obligatorios
        id = id != null ? id : UUID.randomUUID();
        createdAt = createdAt != null ? createdAt : LocalDateTime.now();
        status = status != null ? status : PaymentStatus.PENDING;
    }

    /**
     * Crea una nueva instancia de Payment con estado COMPLETED.
     *
     * @return Nueva instancia de Payment con estado COMPLETED
     */
    public Payment complete() {
        return new Payment(id, description, amount, createdAt, PaymentStatus.COMPLETED, userId, currency);
    }

    /**
     * Crea una nueva instancia de Payment con estado FAILED.
     *
     * @return Nueva instancia de Payment con estado FAILED
     */
    public Payment fail() {
        return new Payment(id, description, amount, createdAt, PaymentStatus.FAILED, userId, currency);
    }

    /**
     * Valida que el pago cumpla con todas las reglas de negocio.
     *
     * @throws IllegalStateException si el pago no es válido
     */
    public void validate() {
        if (status == PaymentStatus.COMPLETED && amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException("Completed payment must have positive amount");
        }
        if (createdAt.isAfter(LocalDateTime.now())) {
            throw new IllegalStateException("Payment date cannot be in the future");
        }
    }
}