package com.endava.model;

import com.endava.enums.MoneyTransferType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

import static com.endava.enums.MoneyTransferType.EXPENSE;
import static com.endava.enums.MoneyTransferType.INCOME;

/**
 * Created by vsaban on 3/27/2017.
 */
@Getter @Setter
@EqualsAndHashCode
@Entity(name = "money_transfer")
public class MoneyTransfer {

    @Id
    @SequenceGenerator(name="transfers_id_seq", allocationSize = 1, sequenceName = "transfers_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="transfers_id_seq")
    @Column(name = "id_transfer")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MoneyTransferType type;

    @NotNull
    @Min(0)
    private Double amount;

    @Size(max = 500)
    private String description;

    @NotNull
    @Past
    @Temporal(TemporalType.DATE)
    private Date date;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_domain")
    private Domain domain;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_wherefrom")
    private Wherefrom wherefrom;

    public MoneyTransfer() {}

    private MoneyTransfer(MoneyTransferType type, Double amount, String description, Date date, User user, Domain domain, Wherefrom wherefrom) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.user = user;
        this.domain = domain;
        this.wherefrom = wherefrom;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean isIncome() {
        return INCOME == type;
    }

    public boolean isExpense() {
        return EXPENSE == type;
    }

    public static class Builder {
        private MoneyTransferType type;
        private Double amount;
        private String description;
        private Date date;
        private User user;
        private Domain domain;
        private Wherefrom wherefrom;

        public Builder withType(MoneyTransferType type) {
            this.type = type;
            return this;
        }

        public Builder withAmount(Double amount) {
            this.amount = amount;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Builder withDomain(Domain domain) {
            this.domain = domain;
            return this;
        }

        public Builder withWherefrom(Wherefrom wherefrom) {
            this.wherefrom = wherefrom;
            return this;
        }

        public MoneyTransfer build() {
            return new MoneyTransfer(type, amount, description, date, user, domain, wherefrom);
        }
    }
}
