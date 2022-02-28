package com.flab.yeogiseoja.domain.bank;

public interface BankReader {
    Bank getBankBy(Long bankId);

    Bank getBankBy(String bankCode);
}
