package net.luvina.dev.service;

import net.luvina.dev.model.Company;

import java.util.List;

public interface TblCompanyService {
    public List<Company> getListCompany();
    public Company getCompany(int companyID);
}
