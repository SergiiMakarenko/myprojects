package english.controller;

import english.domain.Category;
import english.domain.User;
import english.domain.Word;
import english.service.UserService;
import english.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by serg on 07.05.15.
 */
@Controller
@SessionAttributes({"logAmount", "userName", "userRole"})
public class WordController {
Locale locale = Locale.UK;

    @Autowired
    private WordService wordService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/categoryAdd.html", method = {RequestMethod.POST, RequestMethod.GET})
    public String categoryAdd(Model model, String categoryName, @ModelAttribute("logAmount") int logAmount,
                          @ModelAttribute("userName") String userName,@ModelAttribute("userRole") String userRole){
        if(logAmount<1){
            return "lock";
        }
        if(userName.equals("")){
            return "index";
        }
        if(!userRole.equals("Administrator")){
            return "dashboardUser";
        }

        if(categoryName!=null) {
            Long create = wordService.addCategory(categoryName);
            if (create != null) {
                model.addAttribute("RegisterMessage", "Success create new category: " + categoryName);
            } else {
                model.addAttribute("RegisterMessageFalse", "Failed create new category: " + categoryName);
            }
        }
        return "categoryAdd";
    }

    @RequestMapping(value = "/categories.html", method = {RequestMethod.POST, RequestMethod.GET})
    public String categories(Model model, String portion, String startPosition, @ModelAttribute("logAmount") int logAmount,
                        @ModelAttribute("userName") String userName){
        if(logAmount<1){
            return "lock";
        }
        if(userName.equals("")){
            return "index";
        }
        model.addAttribute("portion", portion);
        model.addAttribute("startPosition", startPosition);

        if (portion!=null) {
            if(startPosition==null){
                startPosition="0";
            }

            int amount = wordService.findAllCategories().size();
            int startFrom = Integer.parseInt(startPosition);
            List<Category> categories = wordService.getCategoryByPortion(portion, startPosition);
            model.addAttribute("categoryList", categories);
            model.addAttribute("fullList", categories.size()+startFrom
                    == amount);
            model.addAttribute("message", startFrom+1 + "-" + (categories.size()+startFrom) +
                    " from " + amount);
        }
        return "categories";
    }

    @RequestMapping(value = "/categoryEdit.html", method = {RequestMethod.POST, RequestMethod.GET})
    public String categoryEdit(Model model, String categoryId, String categoryName,
                           @ModelAttribute("logAmount") int logAmount,
                           @ModelAttribute("userName") String userName){
        if(logAmount<1){
            return "lock";
        }
        if(userName.equals("")){
            return "index";
        }
        model.addAttribute("categoryList",wordService.findAllCategories());
        model.addAttribute("categoryId",categoryId);
        model.addAttribute("categoryName",categoryName);

        if(categoryName!=null) {
            boolean update = wordService.editCategory(Long.parseLong(categoryId),categoryName);
            if (update) {
                model.addAttribute("RegisterMessage", "Success edit category: " + categoryName);
                model.addAttribute("categoryList",wordService.findAllCategories());
            } else {
                model.addAttribute("RegisterMessageFalse", "Failed edit category: " + categoryName);
            }
        }
        return "categoryEdit";
    }

    @RequestMapping(value = "/wordAdd.html", method = {RequestMethod.POST, RequestMethod.GET})
    public String wordAdd(Model model, String english, String ukrainian, String transcription, String categoryId,
                          @ModelAttribute("logAmount") int logAmount,
                              @ModelAttribute("userName") String userName,@ModelAttribute("userRole") String userRole){
        if(logAmount<1){
            return "lock";
        }
        if(userName.equals("")){
            return "index";
        }
        if(!userRole.equals("Administrator")){
            return "dashboardUser";
        }


        model.addAttribute("categoryList",wordService.findAllCategories());
        model.addAttribute("english",english);
        model.addAttribute("ukrainian",ukrainian);
        model.addAttribute("transcription",transcription);
        model.addAttribute("categoryId",categoryId);

        if(english!=null) {
            User user = userService.getUserByLogin(userName);
            Category category = wordService.getCategoryById(Long.parseLong(categoryId));
            Long create = wordService.addWord(english,ukrainian,transcription,new Date(), user,category);

            if (create != null) {
                model.addAttribute("RegisterMessage", "Success create new word: " + english+" = " + ukrainian);
            } else {
                model.addAttribute("RegisterMessageFalse", "Failed create new word: " + english+" = " + ukrainian);
            }
        }
        return "wordAdd";
    }

    @RequestMapping(value = "/words.html", method = {RequestMethod.POST, RequestMethod.GET})
    public String words(Model model, String portion, String startPosition, @ModelAttribute("logAmount") int logAmount,
                             @ModelAttribute("userName") String userName){
        if(logAmount<1){
            return "lock";
        }
        if(userName.equals("")){
            return "index";
        }
        model.addAttribute("portion", portion);
        model.addAttribute("startPosition", startPosition);

        if (portion!=null) {
            if(startPosition==null){
                startPosition="0";
            }

            int amount = wordService.findAllWords().size();
            int startFrom = Integer.parseInt(startPosition);
            List<Word> words = wordService.getWordsByPortion(portion, startPosition);
            model.addAttribute("wordList", words);
            model.addAttribute("fullList", words.size()+startFrom
                    == amount);
            model.addAttribute("message", startFrom+1 + "-" + (words.size()+startFrom) +
                    " from " + amount);
        }
        return "words";
    }

    @RequestMapping(value = "/wordEdit.html", method = {RequestMethod.POST, RequestMethod.GET})
    public String wordEdit(Model model, String categoryId, String wordId, String userId, String english,
                           String ukrainian, String transcription, HttpServletRequest request,
                               @ModelAttribute("logAmount") int logAmount,
                               @ModelAttribute("userName") String userName) throws UnsupportedEncodingException {
        if(logAmount<1){
            return "lock";
        }
        if(userName.equals("")){
            return "index";
        }

        model.addAttribute("categoryList",wordService.findAllCategories());
        model.addAttribute("categoryId",categoryId);
        model.addAttribute("userList",userService.findAllUsers());
        model.addAttribute("userId",userId);
        model.addAttribute("wordList",wordService.findAllWords());
        model.addAttribute("wordId",wordId);

        model.addAttribute("english",english);
        model.addAttribute("ukrainian",ukrainian);
        model.addAttribute("transcription",transcription);

        if(english!=null) {

            boolean update = wordService.wordEdit(Long.parseLong(wordId),english,ukrainian,transcription,null,
                    userService.getUserById(Long.parseLong(userId)),
                    wordService.getCategoryById(Long.parseLong(categoryId)));
            if (update) {
                model.addAttribute("RegisterMessage", "Success edit word: " + english+" = " + ukrainian);
                model.addAttribute("wordList",wordService.findAllWords());
            } else {
                model.addAttribute("RegisterMessageFalse", "Failed edit word: " + english+" = " + ukrainian);
            }
        }
        return "wordEdit";
    }

}
