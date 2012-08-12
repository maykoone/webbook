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
@RequestMapping("/home")
public class HomeController {
    
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "home/index";
    }
}
