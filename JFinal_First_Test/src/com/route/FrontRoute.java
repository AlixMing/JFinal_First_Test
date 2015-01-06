package com.route;

import com.demo.HelloController;
import com.jfinal.config.Routes;

public class FrontRoute extends Routes {

	@Override
	public void config() {
		add("/", HelloController.class);
	}

}
