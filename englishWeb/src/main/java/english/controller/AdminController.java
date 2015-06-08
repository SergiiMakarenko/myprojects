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

    public String viewItems(Model model,Integer portion, Integer startPosition,String listName, String jspName){
        model.addAttribute("portion", portion);
        model.addAttribute("startPosition", startPosition);

        if(portion==null)
            return jspName;
        startPosition = startPosition == null ? startPosition = 0 : startPosition;

        Integer amount = userService.findAllUsers().size();
        List<User> list = userService.getUsersByPortion(portion,startPosition);
        model.addAttribute(listName, list);
        model.addAttribute("fullList", list.size()+startPosition
                == amount);
        model.addAttribute("message", startPosition+1 + "-" + (list.size()+startPosition) +
                " from " + amount);

        return jspName;
    }

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
    public String users(Model model, Integer portion,Integer startPosition, @ModelAttribute("logAmount") int logAmount,
                        @ModelAttribute("userName") String userName ,
                        @ModelAttribute("map") Map<String,List<MenuItems>> map){

        return viewItems(model,portion,startPosition,"userList","users");
    }

    @RequestMapping(value = "/roles", method = {RequestMethod.POST, RequestMethod.GET})
    public String roles(Model model, Integer portion, Integer startPosition, @ModelAttribute("logAmount") int logAmount,
                        @ModelAttribute("userName") String userName ,
                        @ModelAttribute("map") Map<String,List<MenuItems>> map){

//        return viewItems(model,portion,startPosition,"roleList","roles");

        model.addAttribute("portion", portion);
        model.addAttribute("startPosition", startPosition);
        if(portion==null)
            return "roles";
        startPosition = startPosition == null ? startPosition = 0 : startPosition;
            int amount = userService.findAllRole().size();
            List<Role> roles = userService.getRolesByPortion(portion,startPosition);
            model.addAttribute("roleList", roles);
            model.addAttribute("fullList", roles.size()+startPosition
                    == amount);
            model.addAttribute("message", startPosition+1 + "-" + (roles.size()+startPosition) +
                    " from " + amount);

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
    public String menu(Model model, Integer portion, Integer startPosition, @ModelAttribute("logAmount") int logAmount,
                        @ModelAttribute("map") Map<String,List<MenuItems>> map){

        model.addAttribute("portion", portion);
        model.addAttribute("startPosition", startPosition);
        if(portion==null)
            return "menus";
        startPosition = startPosition == null ? startPosition = 0 : startPosition;
            int amount = menuService.findAllMenu().size();
            List<Menu> menus = menuService.getMenusByPortion(portion, startPosition);
            model.addAttribute("menuList", menus);
            model.addAttribute("fullList", menus.size()+startPosition
                    == amount);
            model.addAttribute("message", startPosition+1 + "-" + (menus.size()+startPosition) +
                    " from " + amount);
        return "menus";
    }

    @RequestMapping(value = "/menuItems", method = {RequestMethod.POST, RequestMethod.GET})
    public String menuItems(Model model, Integer portion, Integer startPosition, @ModelAttribute("logAmount") int logAmount,
                       // @ModelAttribute("userName") String userName ,
                       @ModelAttribute("map") Map<String,List<MenuItems>> map){
        model.addAttribute("portion", portion);
        model.addAttribute("startPosition", startPosition);
        if(portion==null)
            return "menuItems";
        startPosition = startPosition == null ? startPosition = 0 : startPosition;
            int amount = menuService.findAllMenuItems().size();
            List<MenuItems> menuItemses = menuService.getMenuItemsByPortion(portion, startPosition);
            model.addAttribute("menuList", menuItemses);
            model.addAttribute("fullList", menuItemses.size()+startPosition
                    == amount);
            model.addAttribute("message", startPosition+1 + "-" + (menuItemses.size()+startPosition) +
                    " from " + amount);

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

    @RequestMapping(value = "/menuItemsEdit", method = {RequestMethod.POST, RequestMethod.GET})
    public String menuItemsEdit(Model model, String menuItems,String menuItemsCode, Long menuId,Long menuItemsId,
                               @ModelAttribute("logAmount") int logAmount,
                               @ModelAttribute("userName") String userName,@ModelAttribute("userRole") String userRole
            ,@ModelAttribute("map") Map<String,List<MenuItems>> map){

        model.addAttribute("menuItems",menuItems);
        model.addAttribute("menuItemsCode",menuItemsCode);
        model.addAttribute("menuId",menuId);
        model.addAttribute("menuItemsId",menuItemsId);
        model.addAttribute("menuItemsList",menuService.findAllMenuItems());
        model.addAttribute("menuList",menuService.findAllMenu());

        if(menuItems==null)
        return "menuItemsEdit";

        boolean edit = menuService.editMenuItems(menuItemsId,menuItems,menuItemsCode,menuService.getMenuById(menuId));
        if(edit) {
            model.addAttribute("RegisterMessage", "Success edit menu items: " + menuItems);
        } else{
            model.addAttribute("RegisterMessageFalse", "Failed edit menu items");
        }
        return "menuItemsEdit";
    }

}
