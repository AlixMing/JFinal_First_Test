package com.demo;

import com.blog.Blog;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.tx.TxByActionMethods;
import com.jfinal.plugin.activerecord.tx.TxByRegex;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.route.AdminRoute;
import com.route.FrontRoute;

public class DemoConfig extends JFinalConfig{

	@Override
	public void configConstant(Constants me) {
		loadPropertyFile("config_file.txt");
		me.setDevMode(getPropertyToBoolean("devMode", false));
	}

	@Override
	public void configRoute(Routes me) {
		me.add(new FrontRoute());
		me.add(new AdminRoute());
	}

	@Override
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password").trim());
		me.add(c3p0Plugin);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		arp.addMapping("Blog", Blog.class);	// 映射blog 表到 Blog模型
	}

	@Override
	public void configInterceptor(Interceptors me) {
		me.add(new TxByRegex(".*save.*"));
		me.add(new TxByActionMethods("save", "update"));
		//me.add(new AdminInterceptor());
	}

	@Override
	public void configHandler(Handlers me) {
	}
	
	public void afterJFinalStart() {
		System.out.println("ddddd");
	}
	
	public void beforeJFinalStop() {
		System.out.println("fffff");
	}
}
