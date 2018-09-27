package net.luvina.dev.dao;

import net.luvina.dev.model.Company;

import java.util.List;

public interface TblCompanyDao{
        public List<Company> getListCompany();
        public Company getCompany(int id);
}
