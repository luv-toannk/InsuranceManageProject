package net.luvina.dev.controller;
import net.luvina.dev.service.TblUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ListUserController {
	
	@Autowired
	private TblUserService tblUserService;
	
	
	@RequestMapping(value = "/listUser", method = {RequestMethod.GET, RequestMethod.POST})
	public String listUser(Model model) {
		return tblUserService.listUser(model);
	}
}
