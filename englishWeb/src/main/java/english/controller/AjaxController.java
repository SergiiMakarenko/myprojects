package english.controller;

import english.domain.*;
import english.results.VerbsTestDetail;
import english.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/userAjax", method = { RequestMethod.GET})
    public @ResponseBody
    User userAjax( Long userId, Long wordId){
        if(userId!=null && !userId.equals("")) {
            User user = userService.getUserById(userId);
            return user;
        }
        if(wordId!=null && !wordId.equals("")) {
            Word word = wordService.getWordById(wordId);
            User user = word.getUser();
            return user;
        }
        return null;
    }

    @RequestMapping(value = "/roleAjax", method = { RequestMethod.GET})
    public @ResponseBody
    Role roleAjax( Long userId){
        if(!userId.equals("")) {
            User user = userService.getUserById(userId);
            Role role = userService.getRoleById(user.getUserRole().getRoleId());
            return role;
        }
        return null;
    }

    @RequestMapping(value = "/wordAjax", method = { RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    Word wordAjax(Long wordId){
        if(wordId!=null && !wordId.equals("")) {
            Word word = wordService.getWordById(wordId);
                return word;
        }
        return null;
    }

    @RequestMapping(value = "/verbAjax", method = { RequestMethod.GET})
    public @ResponseBody
    IrregularVerb verbAjax(Long verbId){
        if(!verbId.equals("")) {
            IrregularVerb verb = irregularVerbService.getVerbById(verbId);
            return verb;
        }
        return null;
    }

    @RequestMapping(value = "/categoryAjax", method = { RequestMethod.GET})
    public @ResponseBody
    Category categoryAjax( Long categoryId, Long wordId){
        if(categoryId!=null && !categoryId.equals("")) {
            Category category = wordService.getCategoryById(categoryId);
            return category;
        }
        if(wordId!=null && !wordId.equals("")) {
            Category category = wordService.getWordById(wordId).getCategory();
            return category;
        }
        return null;
    }

    @RequestMapping(value = "/verbsAjax", method = { RequestMethod.GET})
    public @ResponseBody
    String[] verbsAjax(String[] verbListId, String[] pastSimple, String[] pastParticiple,
                       String[] pastSimpleResult, String[] pastParticipleResult,
                       @ModelAttribute("userName") String userName){

        boolean save = testService.saveIrregularVerbTest(userName, verbListId, pastSimple, pastParticiple,
                pastSimpleResult,pastParticipleResult);

        return verbListId;
    }

    @RequestMapping(value = "/verbTestDetail", method = { RequestMethod.GET})
    public @ResponseBody
    List<VerbsTestDetail> verbTestDetail( Long testId){

        if(testId!=null && !("").equals(testId)) {
            List<TestVerb> verbs = testService.getTestVerbsByTest(testService.getTestById(testId));
            List<VerbsTestDetail> details = new ArrayList<>();
            for(TestVerb verb:verbs){
                String infinitive = verb.getVerb().getInfinitive();
                String pastSimpleTest = verb.getPastSimpleResult()==1? verb.getPastSimpleTest():
                        verb.getPastSimpleTest() + " (correct = " + verb.getVerb().getPastSimple() +" )";
                String pastParticipleTest = verb.getPastParticipleResult()==1? verb.getPastParticipleTest():
                        verb.getPastParticipleTest() + " (correct = " + verb.getVerb().getPastParticiple() + " )";
                details.add(new VerbsTestDetail(infinitive,pastSimpleTest,pastParticipleTest,verb.getPastSimpleResult(),
                        verb.getPastParticipleResult()));
            }
            return details;
        }

        return null;
    }

    @RequestMapping(value = "/menuAjax", method = { RequestMethod.GET})
    public @ResponseBody
    Menu menuAjax( Long menuItemsId){
        if(menuItemsId!=null && !("").equals(menuItemsId))
            return menuService.getMenuItemsById(menuItemsId).getMenu();
        return null;
    }

    @RequestMapping(value = "/menuItemsAjax", method = { RequestMethod.GET})
    public @ResponseBody
    MenuItems menuItemsAjax( Long menuItemsId){
        if(menuItemsId!=null && !("").equals(menuItemsId))
            return menuService.getMenuItemsById(menuItemsId);
        return null;
    }

}
