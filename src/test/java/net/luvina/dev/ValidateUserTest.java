package net.luvina.dev;

import net.luvina.dev.model.Insurance;
import net.luvina.dev.model.User;
import net.luvina.dev.service.TblInsuranceService;
import net.luvina.dev.validate.ValidateUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BindException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ValidateUserTest {
	
	@InjectMocks
	private ValidateUser sut;

	@Mock
    private TblInsuranceService tblInsuranceService;

    /**
     * Test ngày sinh rỗng
     */
	@Test
	public void testUserBirthNull() {
		User user = new User();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dateInput = null;
		Date dateFormat = null;
		try {
			dateFormat = simpleDateFormat.parse(dateInput);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		user.setUserBirth(dateFormat);
		BindException bindException = new BindException(user, "user");
		sut.validateUserBirth(user, bindException);
		assertTrue(bindException.hasErrors());
	}

    /**
     * Test so sánh ngày sinh với ngày tháng hiện tại
     */
	@Test
	public void testUserBirthScope(){
        User user = new User();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateInput = "23/10/2018";
        Date dateFormat = null;
        try {
            dateFormat = simpleDateFormat.parse(dateInput);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        user.setUserBirth(dateFormat);
        BindException bindException = new BindException(user, "user");
        sut.validateUserBirth(user, bindException);
        assertTrue(bindException.hasErrors());

    }

    /**
     * Test so sánh ngày bắt đầu đăng kí thẻ bảo hiểm với ngày hết hạn
     */
	@Test
	public void testDateRegister() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String startDate = "23/10/2018";
		String endDate = "21/10/2018";
		Date startDateFormat = null;
		Date endDateFormat = null;
		try {
			startDateFormat = simpleDateFormat.parse(startDate);
			endDateFormat = simpleDateFormat.parse(endDate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Insurance insurance = new Insurance();
		insurance.setIsrStartDate(startDateFormat);
		insurance.setIsrEndDate(endDateFormat);
		User user = new User();
		user.setInsuranceID(insurance);
		BindException bindException = new BindException(user, "user");
		sut.validateRegisterDate(user, bindException);
		assertTrue(bindException.hasErrors());
		
	}

    /**
     * Test ngày đăng kí thẻ bảo hiểm với ngày hiện tại
     */
	@Test
	public void testCompareCurrentDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String startDate = "20/10/2017";
        Date startDateFormat = null;
        try {
            startDateFormat = simpleDateFormat.parse(startDate);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Insurance insurance = new Insurance();
        insurance.setIsrStartDate(startDateFormat);
        User user = new User();
        user.setInsuranceID(insurance);
        BindException bindException = new BindException(user, "user");
        sut.validateRegisterDate(user, bindException);
        assertTrue(bindException.hasErrors());
    }

    /**
     * Test ngày bắt đầu Null
     */
    @Test
    public void testStartDateRegisterNull(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String startDate = null;
        Date startDateFormat = null;
        try {
            startDateFormat = simpleDateFormat.parse(startDate);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Insurance insurance = new Insurance();
        insurance.setIsrStartDate(startDateFormat);
        User user = new User();
        user.setInsuranceID(insurance);
        BindException bindException = new BindException(user, "user");
        sut.validateRegisterDate(user, bindException);
        assertTrue(bindException.hasErrors());
    }

    /**
     * Test ngày kết thúc Null
     */
    @Test
    public void testEndDateRegisterNull(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String endDate = null;
        Date endDateFormat = null;
        try {
            endDateFormat = simpleDateFormat.parse(endDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Insurance insurance = new Insurance();
        insurance.setIsrStartDate(endDateFormat);
        User user = new User();
        user.setInsuranceID(insurance);
        BindException bindException = new BindException(user, "user");
        sut.validateRegisterDate(user, bindException);
        assertFalse(bindException.hasErrors() == false);
    }

    /**
     * Test mã số thẻ bảo hiểm đúng định dạng
     */
    @Test
	public void testInsuranceNumberFormat(){
	    String insuranceNumber = "abc";
	    Insurance insurance = new Insurance();
	    insurance.setIsrNumber(insuranceNumber);
        User user = new User();
        user.setInsuranceID(insurance);
        BindException bindException = new BindException(user,"user");
        sut.validateInsuranceNumber(user,bindException);
        assertTrue(bindException.hasErrors());

    }

    /**
     * Test mã số thẻ bảo hiểm rỗng
     */
    @Test
    public void testInsuranceNumberEmpty(){
        String insuranceNumber = "";
        Insurance insurance = new Insurance();
        insurance.setIsrNumber(insuranceNumber);
        User user = new User();
        user.setInsuranceID(insurance);
        BindException bindException = new BindException(user,"user");
        sut.validateInsuranceNumber(user,bindException);
        assertTrue(bindException.hasErrors());
    }

    /**
     * Test trùng mã số thẻ bảo hiểm
     */


}
