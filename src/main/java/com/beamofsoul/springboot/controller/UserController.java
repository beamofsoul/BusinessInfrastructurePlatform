package com.beamofsoul.springboot.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.entity.User;
import com.beamofsoul.springboot.management.annotation.Miserly;
import com.beamofsoul.springboot.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;
	
//	@Autowired
//	private QueryUtils qu;
	
	@PreAuthorize("authenticated and hasPermission('user','user:add')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String userAdd(User user) {
		userService.createUser(user);
		return "redirect:/user/list";
	}
	
	@PreAuthorize("authenticated and hasPermission('user','user:add')")
	@RequestMapping(value = "/addAndReset", method = RequestMethod.POST)
	@ResponseBody
	public Boolean addAndReset(@RequestBody User user) {
		userService.createUser(user);
		return true;
	}
	
	@PreAuthorize("authenticated and hasPermission('user','user:list')")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView userList() {
		return new ModelAndView("/user/list");
	}
	
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public ModelAndView query() {
//		String sql = "SELECT * FROM t_user a_user %WHERE_CLAUSE%";
//		
//		QueryCriteria qc = CriteriaBuilder.QUERY_CRITERIA;
//		SubCriteria sc = new SubCriteria("id", Operator.IN, new Integer[]{2,3}, Relation.AND, "a_user");
//		qc.add(sc);
//		sc = new SubCriteria("id", Operator.EQUALS, "1", Relation.OR, "a_user");
//		qc.add(sc);
//		
//		List<User> userList = 
//				qu.executeDynamicQuerySql(sql, qc, User.class);
//		
//		System.out.println(userList);
		
		return new ModelAndView("/user/list");
	}
	
	@Miserly(User.class)
	@PreAuthorize("authenticated and hasPermission('user','user:list')")
	@RequestMapping(value = "/listByPage", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public JSONObject userListByPage(@RequestBody Map<String,Integer> map) {
		Sort sort = new Sort(Direction.ASC,"id");
		Pageable pageable = new PageRequest(map.get("page"),map.get("size"),sort);
		JSONObject json = new JSONObject();
		json.put("pageableData", userService.findAll(pageable));
		return json;
	}
	
	@PreAuthorize("authenticated and hasPermission('user','user:add')")
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView userAdd() {
		return new ModelAndView("/user/add");
	}
	
	@PreAuthorize("authenticated and hasPermission('user','user:delete')")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public JSONObject userDelete(@RequestBody String ids) {
		String[] idStringArray = ids.split(",");
		Long[] idLongArray = new Long[idStringArray.length];
		for (int i = 0; i < idStringArray.length; i++)
			idLongArray[i] = Long.valueOf(idStringArray[i]);
		userService.deleteUsers(idLongArray);
		JSONObject result = new JSONObject();
		result.put("status", true);
		return result;
	}
	
	@PreAuthorize("authenticated and hasPermission('user','user:update')")
	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateStatus(@RequestBody User user ) {
		JSONObject result = new JSONObject();
		result.put("status", userService.updateStatus(user.getId(),user.getStatus()));
		return result;
	}
	
	@RequestMapping(value = "/checkUsernameUnique", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject checkUsernameUnique(@RequestBody String username) {
		JSONObject result = new JSONObject();
		result.put("status", userService.checkUsernameUnique(username));
		return result;
	}

	/**
	 * @unusable *************************************
	 * Test for internal redirection
	 * from a method to another method with parameters
	 * @return to another method
	 */
	@RequestMapping("/save")
	public String save(@ModelAttribute("form") Bean form,
			RedirectAttributes attr) throws Exception {
		return null;
//		User user = (User)form;
//		User newUser = userService.createUser(user);
//		if (newUser.equals("000")) {
//			attr.addFlashAttribute("name", newUser.getUsername());
//			attr.addFlashAttribute("success", "添加成功!");
//			return "redirect:/index";
//		} else {
////			attr.addAttribute("projectName", form.getProjectName());
////			attr.addAttribute("enviroment", form.getEnviroment());
////			attr.addFlashAttribute("msg", "添加出错!错误码为："
////					+ rsp.getCode().getCode() + ",错误为："
////					+ rsp.getCode().getName());
//			return "redirect:/maintenance/toAddConfigCenter";
//		}
	}
}
