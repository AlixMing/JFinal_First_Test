package com.demo;

import java.util.List;

import com.blog.Blog;
import com.interceptor.Admin2Interceptor;
import com.interceptor.AdminInterceptor;
import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;

@Before(AdminInterceptor.class)
public class AdminController extends Controller {
	@Before(Admin2Interceptor.class)
	public void index(){
		renderText("Hello Admin to Jfinal World!");
	}
	
	@ClearInterceptor
	public void saveBlog(){
		Record blog = new Record().set("title", "blog2").set("content", "this is save in record way");
		Db.save("blog", blog);
		renderText("............");
	}
	
	@ClearInterceptor(ClearLayer.ALL)
	public void delBlog(){
		List<Blog> blogs = Blog.me.find("select * from blog order by id desc limit 3");
		Blog.me.deleteById(blogs.get(0).get("id"));
		renderText("............");
	}
	
	@ClearInterceptor
	public void selectBlog(){
		List<Blog> blogs = Blog.me.find("select * from blog order by id limit 3");
		String string = "";
		for (Blog blog : blogs) {
			string += blog.getStr("title");
			string += " and ";
		}
		renderText("............" + string);
	}
	
	//声明式事务，一般update和save都需要
	//或者可以直接用正则匹配拦截事务
	@ClearInterceptor
	@Before(Tx.class) 
	public void updateBlog(){
		Record blogRecord = Db.findById("blog", 1).set("title", "update one");
		renderText("............" + blogRecord.getStr("title"));
		Db.update("blog", blogRecord);
	}
}
