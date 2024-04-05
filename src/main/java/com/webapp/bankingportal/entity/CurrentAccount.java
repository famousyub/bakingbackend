package com.webapp.bankingportal.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CA")

public class CurrentAccount extends BankAccount {
    public CurrentAccount() {
    }

    public double getOverDraft() {
        return overDraft;
    }

    public void setOverDraft(double overDraft) {
        this.overDraft = overDraft;
    }

    public CurrentAccount(double overDraft) {
        this.overDraft = overDraft;
    }

    private double overDraft;
}