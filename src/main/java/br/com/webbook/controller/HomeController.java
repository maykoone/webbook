/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author maykoone
 */
@Controller
public class HomeController {
    
    @RequestMapping(value="/home", method = RequestMethod.GET)
    public String index() {
        return "home/index";
    }
    
    @RequestMapping(value="/error404", method= RequestMethod.GET)
    public String error404(){
        return "errors/error404";
    }
    
    @RequestMapping(value="/{userName}")
    public String userHome(@PathVariable String userName){
        return "redirect:/bookmarks/{userName}";
    }
    
    @RequestMapping(value="/dashboard", method= RequestMethod.GET)
    public String dashboard(){
        return "home/dashboard";
    }
}
