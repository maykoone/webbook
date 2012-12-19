/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.controller;

import br.com.webbook.domain.Bookmark;
import br.com.webbook.domain.Filter;
import br.com.webbook.domain.User;
import br.com.webbook.service.BookmarkService;
import br.com.webbook.service.FilterService;
import br.com.webbook.service.UserService;
import br.com.webbook.tags.MessageBean;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author maykoone
 */
@Controller
@RequestMapping("/filters")
public class FilterController {

    @Autowired
    private FilterService filterService;
    @Autowired
    private UserService userService;
    @Autowired
    private BookmarkService bookmarkService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView findByUser(@RequestParam(required = false) Integer page, Principal principal) {
        User user = userService.findByUserName(principal.getName());
        Page<Filter> pageResult = filterService.findByUser(user, page == null ? 1 : page, 12);

        ModelAndView model = new ModelAndView("filter/list");
        model.addAllObjects(configurePagination(pageResult));
        model.addObject("userInstance", user);
        model.addObject("bookmarksCount", bookmarkService.countByUser(user));
        return model;

    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(Principal principal) {
        User user = userService.findByUserName(principal.getName());
        ModelAndView model = new ModelAndView("filter/form");
        model.addObject("userInstance", user);
        model.addObject("filterInstance", new Filter());
        model.addObject("filterCount", filterService.countByUser(user));
        model.addObject("bookmarkCount", bookmarkService.countByUser(user));
        model.addObject("formMethod", "post");
        return model;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(@Valid Filter filterInstance, BindingResult results, Principal principal, RedirectAttributes redirectAttributes, Model model) {
        User user = userService.findByUserName(principal.getName());
        if (filterInstance.getTags() == null || filterInstance.getTags().isEmpty()) {
            results.addError(new FieldError("filterInstance", "tags", "Adicione pelo menos uma tag esse filtro"));
        }
        if (results.hasErrors()) {
            model.addAttribute("filterInstance", filterInstance);
            model.addAttribute("userInstance", user);
            model.addAttribute("filterCount", filterService.countByUser(user));
            model.addAttribute("bookmarkCount", bookmarkService.countByUser(user));
            return "filter/form";
        }

        filterInstance.setUser(user);

        filterService.save(filterInstance);
        redirectAttributes.addFlashAttribute("message", new MessageBean("O filtro " + filterInstance.getTitle() + " foi criado com sucesso."));
        return "redirect:/filters";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Principal principal, Model model) {
        User user = userService.findByUserName(principal.getName());

        model.addAttribute("userInstance", user);
        model.addAttribute("filterCount", filterService.countByUser(user));
        model.addAttribute("bookmarkCount", bookmarkService.countByUser(user));
        model.addAttribute("filterId", id);
        model.addAttribute("formMethod", "put");
        return "filter/form";

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Filter> get(@PathVariable Long id, Principal principal) {
        Filter filter = filterService.findById(id);
        ResponseEntity<Filter> response = new ResponseEntity<Filter>(filter, HttpStatus.OK);

        if (!filter.getUser().getUserName().equals(principal.getName())) {
            //tentando alterar um filter de outro usuário
            response = new ResponseEntity<Filter>(HttpStatus.FORBIDDEN);
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid Filter filterInstance, BindingResult results, Principal principal, RedirectAttributes redirectAttributes, Model model) {
        User user = userService.findByUserName(principal.getName());

        if (filterInstance.getTags() == null || filterInstance.getTags().isEmpty()) {
            results.addError(new FieldError("filterInstance", "tags", "Adicione pelo menos uma tag a esse filtro"));
        }

        if (results.hasErrors()) {
            model.addAttribute("filterInstance", filterInstance);
            model.addAttribute("userInstance", user);
            model.addAttribute("filterCount", filterService.countByUser(user));
            model.addAttribute("bookmarkCount", bookmarkService.countByUser(user));
            model.addAttribute("filterId", filterInstance.getId());
            model.addAttribute("formMethod", "put");
            return "filter/form";
        }

        Filter filterEdit = filterService.findById(filterInstance.getId());
        if (!filterEdit.getUser().getUserName().equals(principal.getName())) {
            return "auth/deniedAccess";
        }

        filterEdit.setTags(filterInstance.getTags());
        filterEdit.setTitle(filterInstance.getTitle());
        filterEdit.setDescription(filterInstance.getDescription());
        filterService.save(filterEdit);

        redirectAttributes.addFlashAttribute("message", new MessageBean("O filtro " + filterInstance.getTitle() + " foi atualizado com sucesso."));
        return "redirect:/filters";

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String destroy(@PathVariable Long id, Principal principal, Model model) {
        Filter filter = filterService.findById(id);
        if (filter == null) {
            return "error";
        } else if (!filter.getUser().getUserName().equals(principal.getName())) {
            return "denied";
        }

        filterService.remove(filter);
        return "success";

    }

    @RequestMapping(value = "/{id}/bookmarks", method = RequestMethod.GET)
    public String loadViewShow(@PathVariable Long id, @RequestParam(required = false) Integer page, Model model, Principal principal) {
        Filter filter = filterService.findById(id);
        if (filter == null) {
            return "errors/error404";
        } else if (!filter.getUser().getUserName().equals(principal.getName())) {
            return "forward:/denied";
        }

        List<Bookmark> pageResult = bookmarkService.listPublicBookmarksByTags(filter.getTags(), page == null ? 1 : page, 10);

        model.addAttribute("filterInstance", filter);
        model.addAttribute("bookmarkList", pageResult);

        model.addAttribute("userInstance", filter.getUser());
        model.addAttribute("filterCount", filterService.countByUser(filter.getUser()));
        model.addAttribute("bookmarkCount", bookmarkService.countByUser(filter.getUser()));

        return "filter/show";
    }

    @RequestMapping(value = "/api/{id}/bookmarks", method = RequestMethod.GET)
    public String show(@PathVariable Long id, @RequestParam(required = false) Integer page, Model model, Principal principal) {
        Filter filter = filterService.findById(id);
        if (filter == null) {
            return "errors/error404";
        } else if (!filter.getUser().getUserName().equals(principal.getName())) {
            return "forward:/denied";
        }

        List<Bookmark> pageResult = bookmarkService.listPublicBookmarksByTags(filter.getTags(), page == null ? 1 : page, 10);

        model.addAttribute("bookmarkList", pageResult);


        return "filter/show-fragment";
    }

    private Map<String, Object> configurePagination(Page<Filter> pageResult) {
        //pagination
        int current = pageResult.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, pageResult.getTotalPages());

        Map<String, Object> modelMap = new HashMap<String, Object>();

        modelMap.put("filterList", pageResult);
        modelMap.put("beginIndex", begin);
        modelMap.put("endIndex", end);
        modelMap.put("currentIndex", current);

        return modelMap;
    }
}
