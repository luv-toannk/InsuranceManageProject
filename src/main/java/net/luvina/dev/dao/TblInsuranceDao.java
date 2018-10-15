package net.luvina.dev.dao;

import net.luvina.dev.model.Insurance;

public interface TblInsuranceDao {
    /**
     * Kiểm tra mã số thẻ bảo hiểm đã tồn tại chưa
     * @param insuranceNumber : mã số thẻ bảo hiểm cần kiểm tra
     * @return : true nếu mã số thẻ bảo hiểm đã tồn tại
     *           false nếu mã số thẻ bảo hiểm chưa tồn tại
     */
     boolean checkExistInsuranceNumber(String insuranceNumber);

    /**
     * Thêm thẻ bảo hiểm
     * @param insurance : đối tượng thẻ bảo hiểm
     */
     void addInsurance(Insurance insurance);
}
