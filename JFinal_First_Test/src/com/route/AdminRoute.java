package com.route;

import com.demo.AdminController;
import com.jfinal.config.Routes;

public class AdminRoute extends Routes {

	@Override
	public void config() {
		add("/admin", AdminController.class);
	}

}
