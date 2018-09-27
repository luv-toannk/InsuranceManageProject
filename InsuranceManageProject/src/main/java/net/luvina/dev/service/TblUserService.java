package net.luvina.dev.service;

import net.luvina.dev.model.UserInfo;

import java.util.List;

public interface TblUserService {
    public List<UserInfo> getListUser(int companyID, String userName, String insuranceNumber, String placeReg, String sortStatus, int offset, int limit);
    public Long getTotalUser(int companyID,String userName,String insuranceNumber,String placeReg);
}
