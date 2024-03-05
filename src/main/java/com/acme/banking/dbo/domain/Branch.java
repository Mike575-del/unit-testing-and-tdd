package com.acme.banking.dbo.domain;

import java.util.Collection;

import static java.util.Collections.unmodifiableCollection;

public class Branch {
    private Collection<Account> accounts; //TODO
    private int branchId;

    public Branch(Collection<Account> accounts, int branchId) {
        this.accounts = accounts;
        this.branchId = branchId;
    }

    public Collection<Account> getAccounts() {
        return unmodifiableCollection(accounts);
    }

    public Collection<Branch> getChildren() {
        return null; //TODO
    }

    public String getReport() {
        return "# Brnach" + branchId + "\\n"
                + getAccounts().stream().forEach(e -> e.getAccountReport());

    }
}
