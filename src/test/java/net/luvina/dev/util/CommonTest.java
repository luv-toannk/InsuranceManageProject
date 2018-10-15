package net.luvina.dev.util;

import net.luvina.dev.model.User;
import net.luvina.dev.model.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(MockitoJUnitRunner.class)
public class CommonTest {
	
	@Test
	public void findLastPaging() {
		int lastPage = Common.getLastPage(3, 5, 1);
		assertTrue(lastPage == 3);
	}
	
	@Test
	public void getFirstPaging() {
		int firstPage = Common.getFirstPage(18, 5, 12,14);
		assertTrue(firstPage == 10);
	}

	@Test
	public void getListPaging(){
	    List<Integer> pagingList = Common.getListPaging(23,5,18);
	    assertEquals(5,pagingList.size());
    }

    @Test
    public void changeFormatDate(){
        String dateFormat = Common.changeFormatDate(new Date());
        assertEquals("11/10/2018",dateFormat);

    }

    @Test
    public void checkFormat(){
	    boolean result = Common.checkFormat("abcdeegfhj");
	    assertTrue(result == false);
    }

}
