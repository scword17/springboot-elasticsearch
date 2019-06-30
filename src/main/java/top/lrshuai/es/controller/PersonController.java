package top.lrshuai.es.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;

import top.lrshuai.es.entity.Person;
import top.lrshuai.es.service.PersonService;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class PersonController {
	
	@Autowired
	private PersonService personservice;
	
	/**
	 * 新增
	 * @param people
	 * @return
	 * @RequestParam(name = "name") String name,
	 * 			@RequestParam(name = "age") Integer age,
	 * 			@RequestParam(name = "country") String country,
	 * 			@RequestParam(name = "date")@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")Date date
	 */
	@PostMapping("/save/people")
	public Object savePerson(HttpServletRequest request,HttpServletResponse res
							 )  {
		int len=request.getContentLength();
		byte[] buffer = new byte[len];
		ServletInputStream iii = null;
		try {
			iii = request.getInputStream();
			iii.read(buffer, 0, len);
		} catch (IOException e) {
			e.printStackTrace();
		}
//
//
//
//		System.out.println(new String(buffer));
//		//res.setStatus(200);
//		try {
//			res.setContentType("application/json; charset=UTF-8");
////			PrintWriter out=res.getWriter();
////			out.println("嗯呐，我收到了信息");//直接返回字符串
//			Map<String, Object> resparams=new HashMap<String, Object>();;
//			resparams.put("code", "200");
//			resparams.put("msg", "上传成功");
//            res.getWriter().println(resparams.toString());
//		}catch (IOException e){
//			e.printStackTrace();
//		}
//		return null;
		Map<String ,Object> map =null;
		map = JSON.parseObject(new String(buffer),Map.class);
		System.out.println("name="+map.get("name"));
		System.out.println("age="+map.get("age"));
		System.out.println("country="+map.get("country"));
		System.out.println("date="+map.get("date"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date nDate=null;
		try {
			nDate=formatter.parse(map.get("date").toString());

		} catch (ParseException e) {
			e.printStackTrace();
		}

		Person person = new Person(map.get("name").toString(),(Integer) map.get("age"),nDate,map.get("country").toString());
		return personservice.savePerson(person);
	}
	
	
	
	/**
	 * 更新
	 * @param id 更新的数据id
	 * @param person 更新对象
	 * @return
	 */
	@PostMapping("/update/person/{id}")
	public Object updatePerson(@PathVariable("id") String id,HttpServletRequest request,HttpServletResponse res
			) {
		int len=request.getContentLength();
		byte[] buffer = new byte[len];
		ServletInputStream iii = null;
		try {
			iii = request.getInputStream();
			iii.read(buffer, 0, len);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String ,Object> map =null;
		map = JSON.parseObject(new String(buffer),Map.class);
		System.out.println("name="+map.get("name"));
		System.out.println("age="+map.get("age"));
		System.out.println("country="+map.get("country"));
		System.out.println("date="+map.get("date"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date nDate=null;
		if(map.containsKey("date")){
			try {
				nDate=formatter.parse(map.get("date").toString());

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Person person = new Person(map.get("name").toString(), (int)map.get("age"), nDate, map.get("country").toString());
		person.setId(id);
		return personservice.updatePerson(person);
	}
	
	/**
	 * 删除
	 * @param id 删除的数据id
	 * @return
	 */
	@PostMapping("/del/{delIndex}/{delType}/{id}")
	public Object delPerson(@PathVariable("delIndex") String delIndex,@PathVariable("delType") String delType,@PathVariable("id") String id) {
		return personservice.delPerson(delIndex, delType, id);
	}
	
	/**
	 * 获取数据
	 * @param id 想要获取的数据
	 * @return
	 */
	@GetMapping("/person/{id}")
	public Object getPerson(@PathVariable("id") String id) {
		return personservice.findPerson(id);
	}
	
	/**
	 * 聚合查询
	 * @param person 
	 * @return
	 */
	@PostMapping("/query/person/_search")
	public Object queryPerson(@RequestParam(name="name",required=false) String name
			,@RequestParam(name="age",required=false,defaultValue="0") Integer age
			,@RequestParam(name="country",required=false) String country) {
		Person person = new Person(name, age, null, country);
		return personservice.queryPerson(person);
	}
	
}
