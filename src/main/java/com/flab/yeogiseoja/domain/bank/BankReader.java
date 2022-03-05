package com.flab.yeogiseoja.domain.bank;

@Deprecated
public interface BankReader {
    Bank getBankBy(Long bankId);

    Bank getBankBy(String bankCode);
}
