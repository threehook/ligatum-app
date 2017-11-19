package nl.tvdven.ligatum.wallet.mappers;

import nl.tvdven.ligatum.wallet.api.data.AccountDTO;
import nl.tvdven.ligatum.wallet.domain.entities.Account;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    @Autowired
    private ModelMapper mapper;

    public Account map(AccountDTO accountDto) {
        return mapper.map(accountDto, Account.class);
    }

    public AccountDTO inverseMap(Account account) {
        return mapper.map(account, AccountDTO.class);
    }
}
