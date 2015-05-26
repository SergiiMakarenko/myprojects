package english.controller;

import english.domain.Menu;
import english.domain.MenuItems;
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

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by serg on 27.04.15.
 */
@Controller
@SessionAttributes({"logAmount", "userName", "userRole","map"})
public class AuthController {
    private static final Logger logger = Logger.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Value("${logAmount}")
    private int defaultLogAmount;

    @ModelAttribute("userName")
    private String setName(){
        return new String("Guest");
    }

    @ModelAttribute("userRole")
    private String setRole(){
        return new String("Guest");
    }


    @ModelAttribute("logAmount")
    private int setLog(){
        return defaultLogAmount;
    }

    @ModelAttribute("map")
    private Map<String,List<MenuItems>> setMap(){
        return new HashMap<String,List<MenuItems>>();
    }


    @RequestMapping(value = "/", method = {RequestMethod.HEAD, RequestMethod.GET})
    public String start(Model model,@ModelAttribute("logAmount") int logAmount,
                        @ModelAttribute("userName") String userName,@ModelAttribute("userRole") String userRole){
        logger.info("start web");

        Map<String,List<MenuItems>> mapMenu = new HashMap<>();
        List<Menu> menus = menuService.getMenuByRole(userService.getRoleByName(userRole));

        for(Menu menu:menus){
            mapMenu.put(menu.getMenuCategory(),menuService.getMenuItemsByMenu(menu));
        }
        model.addAttribute("map", mapMenu);
        return "homePage";
    }

    @RequestMapping(value = "/homePage", method = {RequestMethod.POST, RequestMethod.GET})
    public String dbTest(Model model, @ModelAttribute("logAmount") int logAmount,String login, String pass,
                       @ModelAttribute("userName") String userName,@ModelAttribute("userRole") String userRole){

        Map<String,List<MenuItems>> mapMenu = new HashMap<>();
        boolean auth = userService.checkUser(login,pass);
        if(auth) {
            model.addAttribute("userName", login);
            model.addAttribute("userRole", userService.readUser(login, pass).getUserRole().getRoleName());
            List<Menu> menus = menuService.getMenuByRole(userService.readUser(login, pass).getUserRole());
            for(Menu menu:menus){
                mapMenu.put(menu.getMenuCategory(),menuService.getMenuItemsByMenu(menu));
            }

            model.addAttribute("map", mapMenu);
            return "homePage";
        } else {
            model.addAttribute("tryAgain","");
            if(login!=null) {
                model.addAttribute("tryAgain", "Some thing wrong, try again");
            }
        }
        List<Menu> menus = menuService.getMenuByRole(userService.getRoleByName(userRole));
        for(Menu menu:menus){
            mapMenu.put(menu.getMenuCategory(),menuService.getMenuItemsByMenu(menu));
        }
        model.addAttribute("map", mapMenu);
        return "homePage";
    }


    @RequestMapping(value = "/register", method = {RequestMethod.POST, RequestMethod.GET})
    public String register(Model model, String userLogin, String userPassword, String userPasswordConfirm
            ,@ModelAttribute("map") Map<String,List<MenuItems>> map
    ){
        model.addAttribute("userLogin", userLogin);
        model.addAttribute("userPassword", userPassword);
        model.addAttribute("userPasswordConfirm", userPasswordConfirm);
        if(userLogin!=null){
            if(userPassword.equals(userPasswordConfirm)){
                Long newUserId = userService.createUser(userLogin, userPassword, userService.getRoleByName("User"));
                if(newUserId!=null){
                    return "homePage";
                } else{
                    model.addAttribute("RegisterMessageFalse", "Failed create new user");
                }
            }

        }
        return "register";
    }
}
