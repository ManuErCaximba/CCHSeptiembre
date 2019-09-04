package controllers.xxxx;

import controllers.AbstractController;
import domain.Actor;
import domain.XXXX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.ConferenceService;
import services.XXXXService;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@Controller
@RequestMapping("xxxx")
public class XXXXController extends AbstractController {

    @Autowired
    private XXXXService xxxxService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private ConferenceService conferenceService;

    // List --------------------------------------------------------------------------
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(int conferenceId) {
        ModelAndView result;
        Collection<XXXX> xxxxs;
        final String language = LocaleContextHolder.getLocale().getLanguage();
        Date now = new Date();

        final Actor user = this.actorService.getActorLogged();

        if(user.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("ADMIN"))
            xxxxs = this.xxxxService.findAllByConference(conferenceId);
        else
            xxxxs = this.xxxxService.findAllFinalByConference(conferenceId);

        result = new ModelAndView("xxxx/list");
        result.addObject("xxxxs", xxxxs);
        result.addObject("requestURI", "xxxx/list.do");
        final Date haceUnMes = this.restarMesesFecha(new Date(), 1);
        final Date haceDosMeses = this.restarMesesFecha(new Date(), 2);
        result.addObject("lang", language);
        result.addObject("haceUnMes", haceUnMes);
        result.addObject("haceDosMeses", haceDosMeses);
        result.addObject("conferenceId", conferenceId);

        return result;
    }

    // Show --------------------------------------------------------------------------
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show(@RequestParam final int xxxxId) {
        ModelAndView result;
        final XXXX xxxx;
        final String language = LocaleContextHolder.getLocale().getLanguage();

        try {
            xxxx = this.xxxxService.findOne(xxxxId);
            result = new ModelAndView("xxxx/show");
            result.addObject("xxxx", xxxx);
            result.addObject("lang", language);
        } catch (final Exception e) {
            result = new ModelAndView("redirect:/");
        }

        return result;
    }

    // Create & Edit ------------------------------------------------------------------
    @RequestMapping(value = "/administrator/create", method = RequestMethod.GET)
    public ModelAndView create(int conferenceId) {
        ModelAndView result;
        XXXX xxxx;

        try {
            final Actor user = this.actorService.getActorLogged();
            Assert.isTrue(user.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
            xxxx = this.xxxxService.create();
            xxxx.setConference(this.conferenceService.findOne(conferenceId));
            result = this.createEditModelAndView(xxxx);
        } catch (Exception e) {
            result = new ModelAndView("redirect:/");
        }

        return result;
    }

    @RequestMapping(value = "/administrator/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam final int xxxxId) {
        ModelAndView result;
        XXXX xxxx;

        try {
            final Actor user = this.actorService.getActorLogged();
            Assert.isTrue(user.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
            xxxx = this.xxxxService.findOne(xxxxId);
            Assert.isTrue(!xxxx.getIsFinal());
            result = this.createEditModelAndView(xxxx,null);
            return result;
        } catch (final Exception e) {
            result = new ModelAndView("redirect:/");
            return result;
        }
    }

    @RequestMapping(value = "/administrator/save", method = RequestMethod.POST, params = "draft")
    public ModelAndView saveAsDraft(@ModelAttribute("xxxx") @Valid XXXX xxxx, final BindingResult binding) {
        ModelAndView result;

        try {
            if (binding.hasErrors())
                result = this.createEditModelAndView(xxxx);
            else {
                xxxx = this.xxxxService.reconstruct(xxxx, binding);
                this.xxxxService.saveAsDraft(xxxx);
                result = new ModelAndView("redirect:/xxxx/list.do?conferenceId=" + xxxx.getConference().getId());
            }
        } catch (final Throwable oops) {
            result = this.createEditModelAndView(xxxx, "xxxx.commit.error");
        }
        return result;
    }

    @RequestMapping(value = "/administrator/save", method = RequestMethod.POST, params = "final")
    public ModelAndView saveAsFinal(@ModelAttribute("xxxx") @Valid XXXX xxxx, final BindingResult binding) {
        ModelAndView result;

        try {
            if (binding.hasErrors())
                result = this.createEditModelAndView(xxxx);
            else {
                xxxx = this.xxxxService.reconstruct(xxxx, binding);
                this.xxxxService.saveAsFinal(xxxx);
                result = new ModelAndView("redirect:/xxxx/list.do?conferenceId=" + xxxx.getConference().getId());
            }
        } catch (final Throwable oops) {
            result = this.createEditModelAndView(xxxx, "sponsorship.commit.error");
        }
        return result;
    }

    // Delete --------------------------------------------------------------------------
    @RequestMapping(value = "/administrator/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam final int xxxxId) {
        ModelAndView result;
        XXXX xxxx;

        try {
            xxxx = this.xxxxService.findOne(xxxxId);
            int conferenceId = xxxx.getConference().getId();
            final Actor user = this.actorService.getActorLogged();
            Assert.isTrue(user.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
            Assert.isTrue(!xxxx.getIsFinal());
            this.xxxxService.delete(xxxx);
            result = new ModelAndView("redirect:/xxxx/list.do?conferenceId=" + conferenceId);
        } catch (final Exception e) {
            result = new ModelAndView("redirect:/");
            return result;
        }

        return result;
    }

    //ModelAndView Methods -------------------------------------------------------------
    protected ModelAndView createEditModelAndView(final XXXX xxxx) {
        ModelAndView result;

        result = this.createEditModelAndView(xxxx, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(final XXXX xxxx, final String message) {
        ModelAndView result;

        if(xxxx.getId() == 0)
            result = new ModelAndView("xxxx/administrator/create");
        else
            result = new ModelAndView("xxxx/administrator/edit");

        result.addObject("xxxx", xxxx);
        result.addObject("message", message);

        return result;
    }

    private Date restarMesesFecha(final Date date, final Integer meses) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -meses);
        return calendar.getTime();
    }

}
