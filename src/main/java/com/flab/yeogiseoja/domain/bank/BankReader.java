package com.flab.yeogiseoja.domain.bank;

import com.flab.yeogiseoja.domain.bank.model.Bank;

public interface BankReader {
    Bank getBankByBankCode(String bankCode);
}
