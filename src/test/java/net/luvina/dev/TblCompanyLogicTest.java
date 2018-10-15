package net.luvina.dev;

import net.luvina.dev.dao.CompanyDao;
import net.luvina.dev.dao.TblCompanyDao;
import net.luvina.dev.logic.TblCompanyLogic;
import net.luvina.dev.model.Company;
import net.luvina.dev.service.impl.TblCompanyServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TblCompanyLogicTest {
	
	//Khởi tạo đối tượng Test
	@InjectMocks
	private TblCompanyLogic sut;
	
	@InjectMocks
	private TblCompanyServiceImpl sut1;
	
	//Giả lập các phương thức trong CompanyDao
	@Mock
	private CompanyDao companyDao;
	
	@Mock
	private TblCompanyDao tblCompanyDao;
	
	@Mock
	private List<Company> mockedList;
	
	@Captor
	private ArgumentCaptor<Company> argumentCaptor;
	
	
	@Before
	public void setUp() {
		List<Company> companyList = new ArrayList<Company>();
		companyList.add(new Company());
		companyList.add(new Company());
		companyList.add(new Company(123, "abc", "hqv", "email@email.com", "0987654321"));
		when(companyDao.findByCompanyName("Luvina")).thenReturn(companyList);
		
	}
	
	@Test
	public void findCompanyById() {
		Company company = new Company();
		company.setCompanyEmail("email@email.com");
		when(companyDao.findById(100)).thenReturn(company);
		Company actual = sut.findByCompanyID(100);
		assertThat(actual).isEqualTo(company);
		
	}
	
	@Test
	public void findCompanyName() {
		List<Company> companyList = sut.findByCompanyName("Luvina");
		//assertEquals(3, companyList.size());
		assertEquals(1234, companyList.get(2).getId());
	}
	
	@Test
	public void verifyArgTest() {
		mockedList.add(new Company());
		mockedList.add(new Company());
		mockedList.add(new Company(123, "abc", "hqv", "email@email.com", "0987654321"));
		
		verify(mockedList, times(3)).add(argumentCaptor.capture());
		List<Company> newList = argumentCaptor.getAllValues();
		
		mockedList.remove(0);
		verify(mockedList).remove(0);
		//assertEquals(4, newList.size());
		assertEquals("abcd", newList.get(2).getCompanyName());
	}
	
	@Test
	public void verifyArgTest1() {
		sut1.getListCompany();
		verify(tblCompanyDao, times(1)).getListCompany();
	}
	
}
