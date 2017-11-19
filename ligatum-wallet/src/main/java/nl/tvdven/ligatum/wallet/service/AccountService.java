package nl.tvdven.ligatum.wallet.service;

import nl.tvdven.ligatum.wallet.api.data.AccountDTO;
import nl.tvdven.ligatum.wallet.domain.entities.Account;
import nl.tvdven.ligatum.wallet.mappers.AccountMapper;
import nl.tvdven.ligatum.wallet.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class AccountService {

    @Autowired
    private AccountRepository repository;
    @Autowired
    private AccountMapper mapper;

    public List<Account> getAccounts() {

        return new ArrayList<>();
    }

    public Mono<AccountDTO> getAccount(Long id) {

        Mono<Account> account = repository.findById(id);
        return account.map(x -> mapper.inverseMap(x));
    }
}
