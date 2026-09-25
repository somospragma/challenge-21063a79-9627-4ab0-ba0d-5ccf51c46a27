package com.example.demo.infrastructure;


import com.example.demo.domain.PaymentStatus;
import com.example.demo.domain.Payment;
import com.example.demo.domain.PaymentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import java.util.UUID;

/**
 * Adaptador de infraestructura que implementa el puerto PaymentRepository
 * utilizando Spring Data JPA para persistencia reactiva.
 */
@Repository
public interface JpaPaymentRepository extends PaymentRepository, JpaRepository<PaymentEntity, UUID> {

    /**
     * Implementación del método save del puerto PaymentRepository.
     * Realiza la conversión entre el dominio y la entidad JPA.
     *
     * @param payment Pago de dominio a guardar
     * @return Mono con el pago guardado
     */
    @Override
    default Mono<Payment> save(Payment payment) {
        return Mono.fromCallable(() -> {
            PaymentEntity entity = PaymentEntity.fromDomain(payment);
            PaymentEntity savedEntity = save(entity);
            return savedEntity.toDomain();
        }).subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * Implementación del método findById del puerto PaymentRepository.
     *
     * @param id ID del pago a buscar
     * @return Mono con el pago encontrado o vacío
     */
    @Override
    default Mono<Payment> findById(UUID id) {
        return Mono.fromCallable(() -> findById(id).orElse(null))
                .subscribeOn(Schedulers.boundedElastic())
                .filter(entity -> entity != null)
                .map(PaymentEntity::toDomain);
    }

    /**
     * Implementación del método findByUserId del puerto PaymentRepository.
     *
     * @param userId ID del usuario
     * @return Flux con los pagos del usuario
     */
    @Override
    default Flux<Payment> findByUserId(UUID userId) {
        return Flux.defer(() -> Flux.fromIterable(findByUserIdInternal(userId)))
                .subscribeOn(Schedulers.boundedElastic())
                .map(PaymentEntity::toDomain);
    }

    /**
     * Implementación del método findByStatus del puerto PaymentRepository.
     *
     * @param status Estado de los pagos a buscar
     * @return Flux con los pagos que coinciden con el estado
     */
    @Override
    default Flux<Payment> findByStatus(Payment.PaymentStatus status) {
        return Flux.defer(() -> Flux.fromIterable(findByStatusInternal(status)))
                .subscribeOn(Schedulers.boundedElastic())
                .map(PaymentEntity::toDomain);
    }

    /**
     * Implementación del método deleteById del puerto PaymentRepository.
     *
     * @param id ID del pago a eliminar
     * @return Mono con el resultado de la operación
     */
    @Override
    default Mono<Void> deleteById(UUID id) {
        return Mono.fromRunnable(() -> deleteById(id))
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }

    /**
     * Implementación del método updateStatus del puerto PaymentRepository.
     *
     * @param id ID del pago a actualizar
     * @param status Nuevo estado del pago
     * @return Mono con el pago actualizado
     */
    @Override
    default Mono<Payment> updateStatus(UUID id, Payment.PaymentStatus status) {
        return Mono.fromCallable(() -> {
            PaymentEntity entity = findById(id).orElseThrow(() ->
                new IllegalArgumentException("Payment not found with id: " + id));
            entity.setStatus(status);
            PaymentEntity updatedEntity = save(entity);
            return updatedEntity.toDomain();
        }).subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * Método interno para buscar pagos por usuario (no reactivo).
     *
     * @param userId ID del usuario
     * @return Lista de entidades JPA
     */
    default java.util.List<PaymentEntity> findByUserIdInternal(UUID userId) {
        return findAll().stream()
                .filter(entity -> userId.equals(entity.getUserId()))
                .toList();
    }

    /**
     * Método interno para buscar pagos por estado (no reactivo).
     *
     * @param status Estado de los pagos
     * @return Lista de entidades JPA
     */
    default java.util.List<PaymentEntity> findByStatusInternal(Payment.PaymentStatus status) {
        return findAll().stream()
                .filter(entity -> status.equals(entity.getStatus()))
                .toList();
    }
}