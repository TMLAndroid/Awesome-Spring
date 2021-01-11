package com.luban.app.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    @RequestMapping("/index.do")
    public String index(){
        System.out.println("index========");
        return "index";
    }
    @RequestMapping("/map.do")
    @ResponseBody
    public Object map(){
        Map map = new HashMap();
        map.put("name","tmlong");
        System.out.println("111");
        return map;
    }

    @RequestMapping("/modelAndView.do")
    public ModelAndView modelAndView(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.getModel().put("name","value");
        return modelAndView;
    }


}
