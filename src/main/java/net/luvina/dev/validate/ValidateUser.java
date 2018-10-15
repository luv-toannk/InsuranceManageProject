package net.luvina.dev.validate;

import javafx.scene.control.DatePicker;
import net.luvina.dev.model.User;
import net.luvina.dev.service.TblCompanyService;
import net.luvina.dev.service.TblInsuranceService;
import net.luvina.dev.util.Common;
import net.luvina.dev.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;

@Component
public class ValidateUser implements Validator {
	
	@Autowired
	private TblInsuranceService tblInsuranceService;
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object object, Errors errors) {
		User user = (User) object;
		//Validate Insurance Number
		validateInsuranceNumber(user, errors);
		//Validate UserName
		String userName = user.getUserName();
		if (userName.isEmpty()) {
			errors.rejectValue("userName", Constant.ERROR01_USERNAME);
		} else if (Common.checkLength(userName)) {
			errors.rejectValue("userName", Constant.ERROR02_USERNAME);
		}
		
		//Validate UserBirth
		validateUserBirth(user, errors);
		
		//Validate PlaceReg
		String placeReg = user.getInsuranceID().getIsrPlaceReg();
		if (placeReg.isEmpty()) {
			errors.rejectValue("insuranceID.isrPlaceReg", Constant.ERROR01_PLACEREG);
		}
		
		//Validate Date Register Insurance
		validateRegisterDate(user, errors);
		
	}
	
	/**
	 * Kiểm tra mã số thẻ bảo hiểm
	 * @param user : đối tượng user
	 * @param errors : tham số để add lỗi vào
	 */
	public void validateInsuranceNumber(User user, Errors errors) {
		String insuranceNumber = user.getInsuranceID().getIsrNumber();
		if (insuranceNumber.isEmpty()) {
			errors.rejectValue("insuranceID", Constant.ERROR01_INSURANCEID);
		} else if (tblInsuranceService.checkExistInsuranceNumber(insuranceNumber)) {
			errors.rejectValue("insuranceID", Constant.ERROR02_INSURANCEID);
		} else if (!Common.checkFormat(insuranceNumber)) {
			errors.rejectValue("insuranceID", Constant.ERROR03_INSURANCEID);
		}
	}
	
	/**
	 * Kiểm tra ngày sinh
	 * @param user : đối tượng User
	 * @param errors : tham số để add lỗi vào
	 */
	public void validateUserBirth(User user, Errors errors) {
		Date date = user.getUserBirth();
		if (date == null) {
			errors.rejectValue("userBirth", Constant.ERROR01_USERBIRTH);
		} else if (date.after(new Date())) {
			errors.rejectValue("userBirth", Constant.ERROR02_USERBIRTH);
		}
	}
	
	/**
	 * Kiểm tra ngày bắt đầu và ngày kết thúc thẻ bảo hiểm
	 * @param user : Đối tượng User
	 * @param errors : tham số để add lỗi vào
	 */
	public void validateRegisterDate(User user, Errors errors) {
		Date insuranceStartDate = user.getInsuranceID().getIsrStartDate();
		Date insuranceEndDate = user.getInsuranceID().getIsrEndDate();
		if (insuranceStartDate == null) {
			errors.rejectValue("insuranceID.isrStartDate", Constant.ERROR01_STARTDATE);
		} else if (insuranceStartDate.before(new Date())) {
			errors.rejectValue("insuranceID.isrStartDate", Constant.ERROR02_STARTDATE);
		} else if (insuranceStartDate.after(insuranceEndDate)) {
			errors.rejectValue("insuranceID.isrStartDate", Constant.ERROR03_STARTDATE);
		}
		if (insuranceEndDate == null) {
			errors.rejectValue("insuranceID.isrEndDate", Constant.ERROR01_ENDDATE);
		}
	}
	
}
