package com.webapp.bankingportal.service;

import com.webapp.bankingportal.entity.Facture;

import java.util.List;

public interface ServiceDAo <T> {

    public List<T> getall();

    public T getById(Long type);

    public T save(T type);

    Facture save(Facture facture);

    public void update(Long type3, T type1);

    public void delete(Long type);
}
