/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.controller;

import org.springframework.stereotype.Controller;
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
}
