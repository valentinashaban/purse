package com.endava.enums;

/**
 * Created by vsaban on 3/28/2017.
 */
public enum MoneyTransferType {
    INCOME("income"),
    EXPENSE("expense");

    MoneyTransferType(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public static MoneyTransferType getType(String typeName) {
        return MoneyTransferType.valueOf(MoneyTransferType.class, typeName);
    }
}
