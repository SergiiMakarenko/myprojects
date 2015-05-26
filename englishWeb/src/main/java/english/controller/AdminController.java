package english.controller;

import english.domain.*;
import english.service.MenuService;
import english.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by serg on 27.04.15.
 */
@Controller
@SessionAttributes({"logAmount", "userName", "userRole","map"})
public class AdminController {
    private static Logger logger = Logger.getLogger(AdminController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/roleAdd", method = {RequestMethod.POST, RequestMethod.GET})
    public String roleAdd(Model model, String roleName, @ModelAttribute("logAmount") int logAmount,
                       @ModelAttribute("userName") String userName,@ModelAttribute("userRole") String userRole
            ,@ModelAttribute("map") Map<String,List<MenuItems>> map){

        if(roleName!=null) {
            Long create = userService.createRole(roleName);
            if (create != null) {
                model.addAttribute("RegisterMessage", "Success create new role: " + roleName);
            } else {
                model.addAttribute("RegisterMessageFalse", "Failed create new role");
            }
        }
        return "roleAdd";
    }



    @RequestMapping(value = "/userEdit", method = {RequestMethod.POST, RequestMethod.GET})
    public String userEdit(Model model, String userId, String userLogin, String userPass, String userPassConfirm,
                          String roleId, @ModelAttribute("logAmount") int logAmount,
                       @ModelAttribute("userName") String userName,@ModelAttribute("userRole") String userRole
            ,@ModelAttribute("map") Map<String,List<MenuItems>> map){

        model.addAttribute("userList",userService.findAllUsers());
        model.addAttribute("roleList",userService.findAllRole());
        if(userId!=null){
            model.addAttribute("userId", userId);
            model.addAttribute("userLogin", userLogin);
            model.addAttribute("userPass", userPass);
            model.addAttribute("userPassConfirm", userPassConfirm);
            model.addAttribute("roleId", roleId);
            boolean edit = userService.updateUser(userId,userLogin,userPass,
                    userService.getRoleById(Long.parseLong(roleId)));

            if(edit) {
                model.addAttribute("RegisterMessage", "Success edit user: " + userLogin);
            } else{
                model.addAttribute("RegisterMessageFalse", "Failed edit user");
            }
        }
        return "userEdit";
    }

    @RequestMapping(value = "/users.html", method = {RequestMethod.POST, RequestMethod.GET})
    public String users(Model model, String portion, String startPosition, @ModelAttribute("logAmount") int logAmount,
                        @ModelAttribute("userName") String userName ,
                        @ModelAttribute("map") Map<String,List<MenuItems>> map){

        model.addAttribute("portion", portion);
        model.addAttribute("startPosition", startPosition);

        if (portion!=null) {
            if(startPosition==null){
                startPosition="0";
            }

            int amount = userService.findAllUsers().size();
            int startFrom = Integer.parseInt(startPosition);
            List<User> users = userService.getUsersByPortion(portion,startPosition);
            model.addAttribute("userList", users);
            model.addAttribute("fullList", users.size()+startFrom
                    == amount);
            model.addAttribute("message", startFrom+1 + "-" + (users.size()+startFrom) +
                    " from " + amount);
        }
        return "users";
    }

    @RequestMapping(value = "/roles", method = {RequestMethod.POST, RequestMethod.GET})
    public String roles(Model model, String portion, String startPosition, @ModelAttribute("logAmount") int logAmount,
                        @ModelAttribute("userName") String userName ,
                        @ModelAttribute("map") Map<String,List<MenuItems>> map){

        model.addAttribute("portion", portion);
        model.addAttribute("startPosition", startPosition);

        if (portion!=null) {
            if(startPosition==null){
                startPosition="0";
            }

            int amount = userService.findAllRole().size();
            int startFrom = Integer.parseInt(startPosition);
            List<Role> roles = userService.getRolesByPortion(portion,startPosition);
            model.addAttribute("roleList", roles);
            model.addAttribute("fullList", roles.size()+startFrom
                    == amount);
            model.addAttribute("message", startFrom+1 + "-" + (roles.size()+startFrom) +
                    " from " + amount);
        }
        return "roles";
    }

    @RequestMapping(value = "/menuAdd", method = {RequestMethod.POST, RequestMethod.GET})
    public String menuAdd(Model model, String menuName, String roleId, @ModelAttribute("logAmount") int logAmount,
                          @ModelAttribute("userName") String userName,@ModelAttribute("userRole") String userRole
            ,@ModelAttribute("map") Map<String,List<MenuItems>> map){

        model.addAttribute("roleList",userService.findAllRole());
        if(menuName!=null) {
            Long create = menuService.createMenu(menuName,userService.getRoleById(Long.parseLong(roleId)));
            if (create != null) {
                model.addAttribute("RegisterMessage", "Success create new menu: " + menuName);
            } else {
                model.addAttribute("RegisterMessageFalse", "Failed create new menu: " + menuName);
            }
        }
        return "menuAdd";
    }

    @RequestMapping(value = "/menus", method = {RequestMethod.POST, RequestMethod.GET})
    public String menu(Model model, String portion, String startPosition, @ModelAttribute("logAmount") int logAmount,
                       // @ModelAttribute("userName") String userName ,
                        @ModelAttribute("map") Map<String,List<MenuItems>> map){

        model.addAttribute("portion", portion);
        model.addAttribute("startPosition", startPosition);
        if (portion!=null) {
            if(startPosition==null){
                startPosition="0";
            }

            int amount = menuService.findAllMenu().size();
            int startFrom = Integer.parseInt(startPosition);
            List<Menu> menus = menuService.getMenusByPortion(portion, startPosition);
            model.addAttribute("menuList", menus);
            model.addAttribute("fullList", menus.size()+startFrom
                    == amount);
            model.addAttribute("message", startFrom+1 + "-" + (menus.size()+startFrom) +
                    " from " + amount);
        }
        return "menus";
    }

    @RequestMapping(value = "/menuItems", method = {RequestMethod.POST, RequestMethod.GET})
    public String menuItems(Model model, String portion, String startPosition, @ModelAttribute("logAmount") int logAmount,
                       // @ModelAttribute("userName") String userName ,
                       @ModelAttribute("map") Map<String,List<MenuItems>> map){
        model.addAttribute("portion", portion);
        model.addAttribute("startPosition", startPosition);

        if (portion!=null) {
            if(startPosition==null){
                startPosition="0";
            }
            int amount = menuService.findAllMenuItems().size();
            int startFrom = Integer.parseInt(startPosition);
            List<MenuItems> menuItemses = menuService.getMenuItemsByPortion(portion, startPosition);
            model.addAttribute("menuList", menuItemses);
            model.addAttribute("fullList", menuItemses.size()+startFrom
                    == amount);
            model.addAttribute("message", startFrom+1 + "-" + (menuItemses.size()+startFrom) +
                    " from " + amount);
        }
        return "menuItems";
    }

    @RequestMapping(value = "/menuItemsAdd", method = {RequestMethod.POST, RequestMethod.GET})
    public String menuItemsAdd(Model model, String menuItems,String menuItemsCode, String menuId,
                               @ModelAttribute("logAmount") int logAmount,
                          @ModelAttribute("userName") String userName,@ModelAttribute("userRole") String userRole
            ,@ModelAttribute("map") Map<String,List<MenuItems>> map){

        model.addAttribute("menuList",menuService.findAllMenu());
        model.addAttribute("menuItems",menuItems);
        model.addAttribute("menuItemsCode",menuItemsCode);
        if(menuItems!=null) {
            Long create = menuService.createMenuItems(menuItems,menuItemsCode,
                    menuService.getMenuById(Long.parseLong(menuId)));
            if (create != null) {
                model.addAttribute("RegisterMessage", "Success create new menuItem: " + menuItems);
            } else {
                model.addAttribute("RegisterMessageFalse", "Failed create new menuItem: " + menuItems);
            }
        }
        return "menuItemsAdd";
    }

}
