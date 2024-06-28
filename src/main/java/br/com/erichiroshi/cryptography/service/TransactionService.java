package br.com.erichiroshi.cryptography.service;

import br.com.erichiroshi.cryptography.controller.dto.CreateTransactionRequest;
import br.com.erichiroshi.cryptography.controller.dto.TransactionResponse;
import br.com.erichiroshi.cryptography.controller.dto.UpdateTransactionRequest;
import br.com.erichiroshi.cryptography.entity.TransactionEntity;
import br.com.erichiroshi.cryptography.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public void create(CreateTransactionRequest request) {
        var entity = new TransactionEntity();
        entity.setRawCreditCardToken(request.creditCardToken());
        entity.setRawUserDocument(request.userDocument());
        entity.setTransactionValue(request.value());

        repository.save(entity);
    }

    public TransactionResponse findById(Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return TransactionResponse.fromEntity(entity);
    }

    public Page<TransactionResponse> listAll(int page, int pageSize) {

        var content = repository.findAll(PageRequest.of(page, pageSize));

        return content.map(TransactionResponse::fromEntity);
    }

    public void update(Long id, UpdateTransactionRequest request) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        entity.setTransactionValue(request.value());

        repository.save(entity);
    }

    public void deleteById(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.deleteById(entity.getTransactionId());
    }
}