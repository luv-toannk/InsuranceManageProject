package net.luvina.dev;

import net.luvina.dev.dao.TblUserDao;
import net.luvina.dev.service.impl.TblUserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TblUserDaoTest {
    @InjectMocks
    private TblUserServiceImpl sut;

    @Mock
    private TblUserDao tblUserDao;

    @Test
    public void testVerify(){
       //sut.getTotalUser(123,"abc","anc","anc");
       sut.getListUser(anyInt(),anyString(),anyString(),anyString(),anyString(),anyInt(),anyInt());
       verify(tblUserDao,times(1)).getListUser(anyInt(),anyString(),anyString(),anyString(),anyString(),anyInt(),anyInt());
    }

}
