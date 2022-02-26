package com.flab.yeogiseoja.domain.bank;

import com.flab.yeogiseoja.domain.bank.model.Bank;

public interface BankReader {
    Bank getBankBy(Long bankId);
    Bank getBankBy(String bankCode);
}
