package org.nivemaham.management.security.controller;

import javax.validation.Valid;
import org.nivemaham.management.security.domain.User;
import org.nivemaham.management.security.service.SecurityService;
import org.nivemaham.management.security.service.UserService;
import org.nivemaham.management.security.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
  @Autowired
  private UserService userService;

  @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
  public ModelAndView login(){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("login");
    return modelAndView;
  }


  @RequestMapping(value="/registration", method = RequestMethod.GET)
  public ModelAndView registration(){
    ModelAndView modelAndView = new ModelAndView();
    User user = new User();
    modelAndView.addObject("user", user);
    modelAndView.setViewName("registration");
    return modelAndView;
  }

  @RequestMapping(value = "/registration", method = RequestMethod.POST)
  public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
    ModelAndView modelAndView = new ModelAndView();
    User userExists = userService.findByUsername(user.getUsername());
    if (userExists != null) {
      bindingResult
          .rejectValue("usename", "error.user",
              "There is already a user registered with the username provided");
    }
    if (bindingResult.hasErrors()) {
      modelAndView.setViewName("registration");
    } else {
      userService.save(user);
      modelAndView.addObject("successMessage", "User has been registered successfully");
      modelAndView.addObject("user", new User());
      modelAndView.setViewName("registration");

    }
    return modelAndView;
  }

  @RequestMapping(value="/admin/home", method = RequestMethod.GET)
  public ModelAndView home(){
    ModelAndView modelAndView = new ModelAndView();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.findByUsername(auth.getName());
    modelAndView.addObject("userName", "Welcome " + user.getUsername() +  "from  (" + user.getOrganization().getName() + ")");
    modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
    modelAndView.setViewName("admin/home");
    return modelAndView;
  }
}