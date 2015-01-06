package com.demo;
import com.jfinal.core.Controller;

public class HelloController extends Controller {
	public void index(){
		renderText("Hello Jfinal World!");
		//redirect("/hurry/123-234");
	}
	
	public void hurry(){
		if(getPara() == null)
			renderText("Hurry to Jfinal World!");
		else
			renderText(getPara() + "baby" + getPara(1));
	}
}
