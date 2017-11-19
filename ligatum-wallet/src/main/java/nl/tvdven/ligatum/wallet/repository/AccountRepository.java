package nl.tvdven.ligatum.wallet.repository;

import nl.tvdven.ligatum.wallet.domain.entities.Account;
import nl.tvdven.ligatum.wallet.domain.enums.Currency;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AccountRepository extends ReactiveCrudRepository<Account, Long> {

    Mono<Account> findById(Long id);
//    Flux<Account> findByCurrency(Currency currency);


}