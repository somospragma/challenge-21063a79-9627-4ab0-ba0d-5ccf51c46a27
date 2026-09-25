package com.example.demo.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

/**
 * Interfaz del puerto de dominio para operaciones de persistencia de pagos.
 * Define los contratos que la capa de infraestructura debe implementar
 * para interactuar con el almacén de datos.
 */
public interface PaymentRepository {
    /**
     * Guarda un pago en el almacén de datos.
     *
     * @param payment El pago a guardar
     * @return Mono con el pago guardado
     */
    Mono<Payment> save(Payment payment);

    /**
     * Obtiene un pago por su ID.
     *
     * @param id ID del pago
     * @return Mono con el pago encontrado o vacío si no existe
     */
    Mono<Payment> findById(UUID id);

    /**
     * Obtiene todos los pagos realizados por un usuario.
     *
     * @param userId ID del usuario
     * @return Flux con los pagos del usuario
     */
    Flux<Payment> findByUserId(UUID userId);

    /**
     * Obtiene todos los pagos con un estado específico.
     *
     * @param status Estado de los pagos a buscar
     * @return Flux con los pagos que coinciden con el estado
     */
    Flux<Payment> findByStatus(Payment.PaymentStatus status);

    /**
     * Elimina un pago por su ID.
     *
     * @param id ID del pago a eliminar
     * @return Mono con el resultado de la operación
     */
    Mono<Void> deleteById(UUID id);

    /**
     * Actualiza el estado de un pago.
     *
     * @param id ID del pago a actualizar
     * @param status Nuevo estado del pago
     * @return Mono con el pago actualizado
     */
    Mono<Payment> updateStatus(UUID id, Payment.PaymentStatus status);
}