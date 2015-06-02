package english.controller;

import english.domain.IrregularVerb;
import english.results.ResultIrregularVerbs;
import english.service.IrregularVerbService;
import english.service.TestService;
import english.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Date;
import java.util.List;

/**
 * Created by serg on 27.04.15.
 */
@Controller
@SessionAttributes({"logAmount", "userName", "userRole"})
public class VerbController {

    @Autowired
    private IrregularVerbService irregularVerbService;

    @Autowired
    private TestService testService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/verbs", method = {RequestMethod.POST, RequestMethod.GET})
    public String verbs(Model model, Integer portion, Integer startPosition, @ModelAttribute("logAmount") int logAmount,
                       @ModelAttribute("userName") String userName){

        model.addAttribute("portion", portion);
        model.addAttribute("startPosition", startPosition);

        if (portion!=null) {
            if(startPosition==null) startPosition=0;

            int cntVerbs = irregularVerbService.findAllIrregularVerbs().size();
            List<IrregularVerb> verbs = irregularVerbService.getVerbsByPortion(portion,startPosition);
            model.addAttribute("verbsList", verbs);
            model.addAttribute("fullList", verbs.size()+startPosition
                    == cntVerbs);
            model.addAttribute("message", startPosition+1 + "-" + (verbs.size()+startPosition) +
                    " from " + cntVerbs);
        }
        return "verbs";
    }

    @RequestMapping(value = "/verbsTest", method = {RequestMethod.POST, RequestMethod.GET})
    public String verbsTest(Model model, String cntWords, String startPosition, @ModelAttribute("logAmount") int logAmount,
                        @ModelAttribute("userName") String userName){

        model.addAttribute("cntWords", cntWords);

        if (cntWords!=null) {
            if(startPosition==null){
                startPosition="0";
            }
            List<IrregularVerb> verbs = irregularVerbService.getRandomIrregularVerbs(Integer.parseInt(cntWords));
            model.addAttribute("verbsList", verbs);
        }
        return "verbsTest";
    }

    @RequestMapping(value = "/verbAdd", method = {RequestMethod.POST, RequestMethod.GET})
    public String verbAdd(Model model, String infinitive, String pastSimple, String pastParticiple,
                          @ModelAttribute("logAmount") int logAmount,
                            @ModelAttribute("userName") String userName){

        if(infinitive!=null) {
            Long create = irregularVerbService.addVerb(new IrregularVerb(infinitive,pastSimple,pastParticiple));
            if (create != null) {
                model.addAttribute("RegisterMessage", "Success create new verb: " + infinitive);
            } else {
                model.addAttribute("RegisterMessageFalse", "Failed create new verb: " + infinitive);
            }
        }
        return "verbAdd";
    }
    @RequestMapping(value = "/verbEdit", method = {RequestMethod.POST, RequestMethod.GET})
    public String verbEdit(Model model, String verbId, String infinitive, String pastSimple, String pastParticiple,
                          @ModelAttribute("logAmount") int logAmount,
                          @ModelAttribute("userName") String userName){

        model.addAttribute("verbList",irregularVerbService.findAllIrregularVerbs());
        model.addAttribute("infinitive",infinitive);
        model.addAttribute("pastSimple",pastSimple);
        model.addAttribute("pastParticiple",pastParticiple);
        model.addAttribute("verbId",verbId);

        if(infinitive!=null) {
            Boolean update = irregularVerbService.verbEdit(Long.parseLong(verbId),infinitive,pastSimple,pastParticiple);
            if (update == true) {
                model.addAttribute("RegisterMessage", "Success edit verb: " + infinitive);
                model.addAttribute("verbList",irregularVerbService.findAllIrregularVerbs());
            } else {
                model.addAttribute("RegisterMessageFalse", "Failed edit verb: " + infinitive);
            }
        }
        return "verbEdit";
    }

    @RequestMapping(value = "/reportTestsView", method = {RequestMethod.POST, RequestMethod.GET})
    public String reportTestsView(Model model, String portion, String startPosition, @ModelAttribute("logAmount") int logAmount,
                        @ModelAttribute("userName") String userName){

        model.addAttribute("portion", portion);
        model.addAttribute("startPosition", startPosition);

        if (portion!=null) {
            if(startPosition==null){
                startPosition="0";
            }

            int cntRows = testService.getTestResults("", userService.getUserByLogin(userName), new Date(115, 04, 01),
                    new Date(115, 11, 31)).size();
            int startFrom = Integer.parseInt(startPosition);
            List<ResultIrregularVerbs> resultIrregularVerbses = testService.getTestResults("", userService.getUserByLogin(userName),
                    new Date(115, 04, 01), new Date(115, 11, 31));
            model.addAttribute("testsList", resultIrregularVerbses);
            model.addAttribute("fullList", resultIrregularVerbses.size()+startFrom
                    == cntRows);
            model.addAttribute("message", startFrom+1 + "-" + (resultIrregularVerbses.size()+startFrom) +
                    " from " + cntRows);
        }
        return "reportTestsView";
    }

}
