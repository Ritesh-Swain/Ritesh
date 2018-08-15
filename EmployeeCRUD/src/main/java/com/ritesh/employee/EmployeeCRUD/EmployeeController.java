package com.ritesh.employee.EmployeeCRUD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ritesh.employee.EmployeeCRUD.entity.Employee;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private Environment env;
	
	@RequestMapping(value="/dashboard", method = RequestMethod.GET)
	public ModelAndView Dashboard(){
		ModelAndView modelAndView = new ModelAndView();
		/*Employee employee = new Employee();
		modelAndView.addObject("employee", employee);*/
		modelAndView.setViewName("dashboard");
		return modelAndView;
	}
	@RequestMapping(value="/addEmployee", method = RequestMethod.GET)
	public ModelAndView addEmployeePage(){
		ModelAndView modelAndView = new ModelAndView();
		Employee employee = new Employee();
		modelAndView.addObject("employee", employee);
		modelAndView.setViewName("addEmployee");
		return modelAndView;
	}
	
	@RequestMapping(value="/addEmployee", method = RequestMethod.POST)
	public ModelAndView addEmployee(@Valid Employee employee, BindingResult bindingResult){
		ModelAndView modelAndView = new ModelAndView();
		employeeRepository.save(employee);
		modelAndView.addObject("successMessage", env.getProperty("employee.msg.successfullAdd"));
		modelAndView.setViewName("addEmployee");
		return modelAndView;
	}
	
	@RequestMapping(value="/updateEmployee", method = RequestMethod.GET)
	public ModelAndView updateEmployeePage(){
		ModelAndView modelAndView = new ModelAndView();
		Employee employee = new Employee();
		modelAndView.addObject("employee", employee);
		modelAndView.setViewName("updateEmployee");
		return modelAndView;
	}
	
	@RequestMapping(value="/updateEmployee", method = RequestMethod.POST)
	public ModelAndView updateEmployee(@Valid Employee employee){
		ModelAndView modelAndView = new ModelAndView();
		int id=employee.getId();
		if( employeeRepository.findById(employee.getId()) != null)
		{
		employeeRepository.save(employee);
		}
		modelAndView.addObject("successMessage",env.getProperty("employee.msg.successfullUpdate"));
		modelAndView.setViewName("updateEmployee");
		return modelAndView;
	}
	
	@RequestMapping(value="/getEmployee", method = RequestMethod.GET)
	public ModelAndView getEmployeePage(){
		ModelAndView modelAndView = new ModelAndView();
		Employee employee = new Employee();
		modelAndView.addObject("employee", employee);
		modelAndView.setViewName("getEmployee");
		return modelAndView;
	}
	
	@RequestMapping(value="/getEmployee1", method = RequestMethod.POST)
	public ModelAndView getEmployee(@RequestParam("id") int id){
		System.out.println("entering getEmployee1 "+id);
		ModelAndView modelAndView = new ModelAndView();
		try
		{
		Optional<Employee> employee=employeeRepository.findById(id);
		modelAndView.addObject("successMessage", "Emp name is "+employee.get().getName()
				+" Emp phone is "+employee.get().getPhone()+
				" Emp Address is "+employee.get().getAddress());
		}
		catch(Exception e)
		{
			modelAndView.setViewName("getEmployee");
			modelAndView.addObject("successMessage",env.getProperty("employee.msg.notFound"));
			
		}
		return modelAndView;
	}
	@RequestMapping(value="/getEmployeebyName", method = RequestMethod.GET)
	public ModelAndView searchEmployee(){
		ModelAndView modelAndView = new ModelAndView();
		/*Employee employee = new Employee();
		modelAndView.addObject("employee", employee);*/
		modelAndView.setViewName("searchEmployee");
		return modelAndView;
	}
		@RequestMapping(value="/getEmployeebyName", method = RequestMethod.POST)
		public ModelAndView getEmployeeByName(@RequestParam("name") String name){
			
			ModelAndView modelAndView = new ModelAndView();
			List<String> nameList=new ArrayList<String>();
			try
			{
			List<Employee> employee=employeeRepository.findAll();
			for (Employee employee1 : employee) {
				String temp=employee1.getName();
				System.out.println("temp "+temp+" "+temp.contains(name));
				if((temp.toLowerCase()).contains(name.toLowerCase()))
				{
					nameList.add(temp);
				}
			}
			for(String x:nameList)
			{
				System.out.println("name "+x);
			}
			}	
			catch(Exception e)
			{
				modelAndView.addObject("successMessage", env.getProperty("employee.msg.notMatching"));
			}
			if(nameList!=null) {
		modelAndView.addObject("nameList",nameList);
			}
			else
			{
				modelAndView.addObject("successMessage", env.getProperty("employee.msg.notMatching"));
			}
		modelAndView.setViewName("searchEmployee");
		return modelAndView;
	}
	
	@RequestMapping(value="/delEmployee", method = RequestMethod.GET)
	public ModelAndView delEmployeePage(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("delEmployee");
		return modelAndView;
	}
	
	@RequestMapping(value="/delEmployee1", method = RequestMethod.POST)
	public ModelAndView delEmployeePage(@RequestParam("id") int id){
		ModelAndView modelAndView = new ModelAndView();
		try
		{
		employeeRepository.deleteById(id);
		modelAndView.addObject("successMessage", env.getProperty("employee.msg.sucessfullDelete"));
		}
		catch(Exception e){
			modelAndView.addObject("successMessage", env.getProperty("employee.msg.notFound"));
		}
		
		modelAndView.setViewName("delEmployee");
		return modelAndView;
	}
}
