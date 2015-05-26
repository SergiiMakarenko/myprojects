package english.controller;

import english.domain.*;
import english.service.IrregularVerbService;
import english.service.TestService;
import english.service.UserService;
import english.service.WordService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by serg on 27.04.15.
 */
@Controller
@SessionAttributes({"logAmount", "userName", "userRole"})
public class AjaxController {
    private static Logger logger = Logger.getLogger(AjaxController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private IrregularVerbService irregularVerbService;

    @Autowired
    private WordService wordService;

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/userAjax", method = { RequestMethod.GET})
    public @ResponseBody
    User userAjax(Model model, String userId, String wordId){
        if(userId!=null && !userId.equals("")) {
            User user = userService.getUserById(Long.parseLong(userId));
            return user;
        }
        if(wordId!=null && !wordId.equals("")) {
            Word word = wordService.getWordById(Long.parseLong(wordId));
            User user = word.getUser();
            return user;
        }

        return null;
    }

    @RequestMapping(value = "/roleAjax", method = { RequestMethod.GET})
    public @ResponseBody
    Role roleAjax(Model model, String userId){
        if(!userId.equals("")) {
            User user = userService.getUserById(Long.parseLong(userId));
            Role role = userService.getRoleById(user.getUserRole().getRoleId());
            return role;
        }
        return null;
    }

    @RequestMapping(value = "/wordAjax", method = { RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    Word wordAjax(Model model, String wordId){
        if(wordId!=null && !wordId.equals("")) {
            Word word = wordService.getWordById(Long.parseLong(wordId));
                return word;
        }
        return null;
    }

    @RequestMapping(value = "/verbAjax", method = { RequestMethod.GET})
    public @ResponseBody
    IrregularVerb verbAjax(Model model, String verbId){
        if(!verbId.equals("")) {
            IrregularVerb verb = irregularVerbService.getVerbById(Long.parseLong(verbId));
            return verb;
        }
        return null;
    }

    @RequestMapping(value = "/categoryAjax", method = { RequestMethod.GET})
    public @ResponseBody
    Category categoryAjax(Model model, String categoryId, String wordId){
        if(categoryId!=null && !categoryId.equals("")) {
            Category category = wordService.getCategoryById(Long.parseLong(categoryId));
            return category;
        }
        if(wordId!=null && !wordId.equals("")) {
            Word word = wordService.getWordById(Long.parseLong(wordId));
            Category category = word.getCategory();
            return category;
        }
        return null;
    }

    @RequestMapping(value = "/verbsAjax", method = { RequestMethod.GET})
    public @ResponseBody
    String[] verbsAjax(Model model, String[] verbListId, String[] pastSimple, String[] pastParticiple,
                       String[] pastSimpleResult, String[] pastParticipleResult,
                       @ModelAttribute("userName") String userName){

        boolean save = testService.saveIrregularVerbTest(userName, verbListId, pastSimple, pastParticiple,
                pastSimpleResult,pastParticipleResult);

        return verbListId;
    }

}
