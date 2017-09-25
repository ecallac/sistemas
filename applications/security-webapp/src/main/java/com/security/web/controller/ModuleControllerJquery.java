///**
// * 
// */
//package com.security.web.controller;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.security.domain.Module;
//import com.security.service.ModuleService;
//import com.security.web.bean.JsonResponse;
//import com.security.web.bean.ModuleView;
//
///**
// * @author EFRAIN
// * @dateCreated 26 mar. 2017 18:15:19
// */
//@Controller
//@RequestMapping(value="/module")
//public class ModuleControllerJquery {
//	
//	@Autowired
//	ModuleService moduleService;
//	
//	@RequestMapping(value="/", method=RequestMethod.GET)
//	public ModelAndView userInfo(){
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("module");
//		return modelAndView;
//	}
//	
////	@RequestMapping(value = "/save", method = RequestMethod.GET)
////    public @ResponseBody Map<String, Object> getSaved(ModuleView moduleView) {
////        Map<String, Object> map = new HashMap<String, Object>();
////        moduleService.save(castModuleViewToModule(moduleView));
//////        if () {
////            map.put("status", "200");
////            map.put("message", "Your record have been saved successfully");
//////        }
//// 
////        return map;
////    }
//	@RequestMapping(value = "/save", method = RequestMethod.POST)
//    public @ResponseBody  Map<String, Object> getSavedPost(ModuleView moduleView) {
//        Map<String, Object> map = new HashMap<String, Object>();
////        JsonResponse res = new JsonResponse();
//        moduleService.save(castModuleViewToModule(moduleView));
////        res.setResult("Your record have been saved successfully");
////		res.setStatus("200");
////        if () {
//            map.put("status", "200");
//            map.put("message", "Your record have been saved successfully");
////        }
// 
//        return map;
//    }
// 
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public @ResponseBody Map<String, Object> getAll() {
//        Map<String, Object> map = new HashMap<String, Object>();
//        System.out.println("aqui");
//        List<ModuleView> list = castModuleToModuleViewList(moduleService.findAllModules());
// 
//        if (list != null) {
//            map.put("status", "200");
//            map.put("message", "Data found");
//            map.put("data", list);
//        } else {
//            map.put("status", "404");
//            map.put("message", "Data not found");
// 
//        }
// 
//        return map;
//    }
// 
//    @RequestMapping(value = "/delete", method = RequestMethod.POST)
//    public @ResponseBody Map<String, Object> delete(Module module) {
//        Map<String, Object> map = new HashMap<String, Object>();
//        moduleService.delete(module);
////        if (moduleService.delete(module)) {
//            map.put("status", "200");
//            map.put("message", "Your record have been deleted successfully");
////        }
// 
//        return map;
//    }
//    
//    public List<ModuleView> castModuleToModuleViewList(List<Module> modules){
//    	List<ModuleView> moduleViews = new ArrayList<>();
//    	for (Module module : modules) {
//    		moduleViews.add(castModuleToModuleView(module));
//		}
//    	return moduleViews;
//    }
//    public ModuleView castModuleToModuleView(Module module){
//    	ModuleView moduleView = new ModuleView();
//    	moduleView.setId(module.getId());
//    	moduleView.setName(module.getName());
//    	moduleView.setDescription(module.getDescription());
//    	moduleView.setStatus(module.getStatus());
//    	return moduleView;
//    }
//    
//    public Module castModuleViewToModule(ModuleView moduleView){
//    	Module module = new Module();
//    	module.setId(moduleView.getId());
//    	module.setName(moduleView.getName());
//    	module.setDescription(moduleView.getDescription());
//    	module.setStatus(moduleView.getStatus());
//    	return module;
//    }
//}
