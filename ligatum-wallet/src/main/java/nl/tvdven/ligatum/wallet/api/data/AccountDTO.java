package nl.tvdven.ligatum.wallet.api.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import nl.tvdven.ligatum.wallet.domain.enums.Currency;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter @ToString @EqualsAndHashCode @Builder
public class AccountDTO {

    private String id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date creationDate;

    private Double amount;

    private Currency currency;

//    @Override
//    public String toString() {
//        return "Account{" +
//                "id='" + id + '\'' +
//                ", creationDate=" + creationDate +
//                ", amount=" + amount +
//                ", currency=" + currency +
//                '}';
//    }
}

