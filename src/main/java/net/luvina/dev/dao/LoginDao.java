package net.luvina.dev.dao;

public interface LoginDao {
    /**
     * Kiểm tra đăng nhập
     * @param username : tên đăng nhập
     * @param password : mật khẩu
     * @return : true nếu đăng nhập thành công
     *           false nếu đăng nhập thất bại
     */
     boolean checkLogin(String username,String password);
}
