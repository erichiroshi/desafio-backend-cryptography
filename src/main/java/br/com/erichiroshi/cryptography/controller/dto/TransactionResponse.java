package br.com.erichiroshi.cryptography.controller.dto;

import br.com.erichiroshi.cryptography.entity.TransactionEntity;

public record TransactionResponse(Long id,
                                  String userDocument,
                                  String creditCardToken,
                                  Long value) {

    public static TransactionResponse fromEntity(TransactionEntity entity) {
        return new TransactionResponse(
                entity.getTransactionId(),
                entity.getRawUserDocument(),
                entity.getRawCreditCardToken(),
                entity.getTransactionValue()
        );
    }
}