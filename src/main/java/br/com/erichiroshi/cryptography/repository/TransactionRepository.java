package br.com.erichiroshi.cryptography.repository;

import br.com.erichiroshi.cryptography.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}