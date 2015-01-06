package com.blog;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
/*
 * blog model;
  mysql> desc blog;
+---------+--------------+------+-----+---------+----------------+
| Field   | Type         | Null | Key | Default | Extra          |
+---------+--------------+------+-----+---------+----------------+
| id      | int(11)      | NO   | PRI | NULL    | auto_increment |
| title   | varchar(200) | NO   |     | NULL    |                |
| content | mediumtext   | NO   |     | NULL    |                |
+---------+--------------+------+-----+---------+----------------+
 */
@SuppressWarnings("serial")
public class Blog extends Model<Blog> {
	public static final Blog me = new Blog();
	
	public Page<Blog> paginate(int pageNumber, int pageSize){
		return paginate(pageNumber, pageSize, "selete *", "from blog order by id asc");
	}
}
