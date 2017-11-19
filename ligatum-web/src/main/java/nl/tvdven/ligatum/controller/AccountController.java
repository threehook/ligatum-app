package nl.tvdven.ligatum.controller;

import nl.tvdven.ligatum.wallet.api.data.AccountDTO;
import nl.tvdven.ligatum.wallet.domain.entities.Account;
import nl.tvdven.ligatum.wallet.repository.AccountRepository;
import nl.tvdven.ligatum.wallet.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/search/byid", method = RequestMethod.GET)
    Mono<AccountDTO> findByCurrency(@RequestParam String id) {
        return accountService.getAccount(Long.parseLong(id));
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    Mono<Account> findById(@PathVariable String id) {
//        return reactiveAccountRepository.findById(id);
//    }
//
//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    Mono<Account> save(@RequestBody Account account) {
//        return reactiveAccountRepository.save(account);
//    }
//
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    Flux<Account> findAll() {
//        return reactiveAccountRepository.findAll();
//    }
}
