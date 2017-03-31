package com.endava.model;

import com.endava.enums.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by vsaban on 3/15/2017.
 */
@Setter @Getter
@EqualsAndHashCode
@Entity(name = "users")
public class User {

    @Id
    @SequenceGenerator(name="users_id_seq", allocationSize = 1, sequenceName = "users_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="users_id_seq")
    @Column(name = "id_user")
    private Long id;

    @NotNull
    @Size(min = 5, max = 20)
    private String login;

    @NotNull
    @Size(min = 6, max = 10)
    private String password;

    @Email
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    List<MoneyTransfer> moneyTransfers;

    public User() {}

    private User(String login, String password, String email, Role role, List<MoneyTransfer> moneyTransfers) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.moneyTransfers = moneyTransfers;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String login;
        private String password;
        private String email;
        private Role role;
        List<MoneyTransfer> moneyTransfers;

        public Builder withLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withRole(Role role) {
            this.role = role;
            return this;
        }

        public Builder withMoneyTransfers(List<MoneyTransfer> moneyTransfers) {
            this.moneyTransfers = moneyTransfers;
            return this;
        }

        public User build() {
            return new User(login, password, email, role, moneyTransfers);
        }
    }
}
