package com.seu.howard.study.crud.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.seu.howard.study.crud.dao.DepartmentDao;
import com.seu.howard.study.crud.dao.EmployeeDao;
import com.seu.howard.study.crud.entities.Employee;

@Controller
public class EmployeeHandler {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@Autowired
	private ResourceBundleMessageSource messageResource;
	
	@ModelAttribute
	public void getEmployee(@RequestParam(value = "id", required = false) Integer id, Map<String , Object> map){
		if ( id != null )
			map.put("employee", employeeDao.get(id));
	}
	
	@RequestMapping("testFileUpload")
	public String testFileUpload(@RequestParam("desc") String desc, @RequestParam("file") MultipartFile file) throws IOException{
		System.out.println("Desc: " + desc);
		System.out.println("OriginalName: " + file.getOriginalFilename());
		System.out.println("InputStream: " + file.getInputStream());
		
		return "success";
	}
	
	@RequestMapping(value="i18n", method=RequestMethod.GET)
	public void testI18n(Locale locale){
		String val = messageResource.getMessage("i18n.user", null, locale);
		System.out.println(val);
		
	}
	
	@RequestMapping(value="testResponseEntity", method=RequestMethod.GET)
	public ResponseEntity<byte[]> testResponseEntity(HttpSession httpSession) throws IOException{
		byte[] body = null;
		ServletContext servletContext = httpSession.getServletContext();
		InputStream in = servletContext.getResourceAsStream("/files/abc.txt");
		body = new byte[in.available()];
		in.read(body);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=abc.txt");
		
		HttpStatus statusCode = HttpStatus.OK;
		
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(body, headers, statusCode);
		return responseEntity;
	}
	
	@ResponseBody
	@RequestMapping(value="testHttpMessageConverter", method=RequestMethod.POST)
	public String testHttpMessageConverter(@RequestBody String input ){
		System.out.println(input);
		
		return "test: " + new Date();
	}
	
	@ResponseBody
	@RequestMapping(value="/testJson", method=RequestMethod.GET)
	public Collection<Employee> testJson(){
		return employeeDao.getAll();
	}
	
	@RequestMapping(value="/emp", method=RequestMethod.PUT)
	public String update(Employee employee){
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
	@RequestMapping(value="/emp/{id}", method=RequestMethod.GET)
	public String input(@PathVariable Integer id, Map<String, Object> map){
		map.put("employee", employeeDao.get(id));
		map.put("departments", departmentDao.getDepartments());
		return "input";
	}
	
	@RequestMapping(value="/emp/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Integer id){
		employeeDao.delete(id);
		return "redirect:/emps";
	}
	
	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	public String add(@Valid Employee employee, BindingResult result, Map<String, Object> map){
		if ( result.hasErrors() ){
			for (FieldError error : result.getFieldErrors() ){
				System.out.println("error: " + error.getDefaultMessage());
			}
			
			map.put("departments", departmentDao.getDepartments());
			return "input";
		}
		System.out.println("save: " + employee);
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
	@RequestMapping(value = "/emp", method = RequestMethod.GET)
	public String Input(Map<String, Object> map){
		map.put("departments", departmentDao.getDepartments());
		map.put("employee", new Employee());
		return "input";
	}
	
	@RequestMapping("/emps")
	public String list(Map<String, Object> map){
		map.put("employees", employeeDao.getAll());
		
		return "list";
	}

}
