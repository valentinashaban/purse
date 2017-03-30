package com.endava;

import com.endava.enums.MoneyTransferType;
import com.endava.model.MoneyTransfer;
import com.endava.model.User;
import com.endava.model.Wherefrom;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.endava.enums.MoneyTransferType.INCOME;

/**
 * Created by vsaban on 3/29/2017.
 */
public class StaticReusedVariables {
    public final static String WHEREFROM_CASH_NAME = "cash";
    public final static String WHEREFROM_SALARY_NAME = "salary";

    public final static long CURRENT_MILLIS = 1490306400000L;
    public final static long DATE_ONE = 1489960800000L;
    public final static long DATE_TWO = 1490220000000L;
    public final static long DATE_THREE = 1483221600000L;
    public final static long DATE_FOUR = 1472677200000L;
    public final static Date CURRENT_FIXED_DATE = new Date(CURRENT_MILLIS);

    public final static Wherefrom WHEREFROM_CASH = createWherefrom(WHEREFROM_CASH_NAME);
    public final static Wherefrom WHEREFROM_SALARY = createWherefrom(WHEREFROM_SALARY_NAME);

    public final static MoneyTransfer MONEY_TRANSFER = createMoneyTransfer(INCOME, 50.0, WHEREFROM_CASH, new Date(DATE_ONE));//20.03.2017
    public final static MoneyTransfer MONEY_TRANSFER1 = createMoneyTransfer(INCOME, 12.0, WHEREFROM_CASH, new Date(DATE_TWO));//23.03.2017
    public final static MoneyTransfer MONEY_TRANSFER2 = createMoneyTransfer(INCOME, 128.0, WHEREFROM_SALARY, new Date(DATE_THREE));//1.01.2017
    public final static MoneyTransfer MONEY_TRANSFER3 = createMoneyTransfer(INCOME, 1000.0, WHEREFROM_SALARY, new Date(DATE_FOUR));//1.09.2016

    public final static List<MoneyTransfer> MONEY_TRANSFERS = Arrays.asList(MONEY_TRANSFER, MONEY_TRANSFER1, MONEY_TRANSFER2, MONEY_TRANSFER3);
    public final static List<User> USERS = createUsers();

    public static User createUser() {
        return User.builder()
                   .withLogin("valera")
                   .withPassword("1111")
                   .withEmail("valera@endava.com")
                   .build();
    }

    public static User createUser(String login, String password, String email) {
        return User.builder()
                   .withLogin(login)
                   .withPassword(password)
                   .withEmail(email)
                   .build();
    }

    public static List<User> createUsers() {
        return Arrays.asList(
                createUser("valentina", "2222", "valentina@endava.com"),
                createUser("natalia", "3333", "natalia@endava.com"),
                createUser("olga", "4444", "olga@endava.com"));
    }

    public static Wherefrom createWherefrom(String name) {
        Wherefrom wherefrom = new Wherefrom();
        wherefrom.setName(name);

        return wherefrom;
    }

    public static MoneyTransfer createMoneyTransfer(MoneyTransferType type, Double amount, Wherefrom wherefrom, Date date) {
        return MoneyTransfer.builder()
                            .withType(type)
                            .withAmount(amount)
                            .withWherefrom(wherefrom)
                            .withDate(date)
                            .withUser(createUser())
                            .build();
    }
}
