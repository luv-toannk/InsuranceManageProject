package net.luvina.dev.dao;

import net.luvina.dev.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyDao extends JpaRepository<Company,Integer>{
    Company findById(int companyID);
    List<Company> findByCompanyName(String companyName);
}
