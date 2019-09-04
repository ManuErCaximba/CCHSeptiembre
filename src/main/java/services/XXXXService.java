package services;

import domain.Actor;
import domain.XXXX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.XXXXRepository;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

@Service
@Transactional
public class XXXXService {

    //Managed Repositories
    @Autowired
    private XXXXRepository xxxxRepository;

    //Supporting services
    @Autowired
    private ActorService actorService;

    //Validator
    @Autowired
    private Validator validator;

    //CRUD Methods
    public XXXX create(){
        XXXX xxxx = new XXXX();
        return xxxx;
    }

    public XXXX findOne(int xxxxId){
        return this.xxxxRepository.findOne(xxxxId);
    }

    public Collection<XXXX> findAll(){
        return this.xxxxRepository.findAll();
    }

    public XXXX saveAsDraft(XXXX xxxx){
        XXXX result;
        final Actor actor = this.actorService.getActorLogged();

        Assert.isTrue(actor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
        Assert.notNull(xxxx);
        Assert.isTrue(!xxxx.getIsFinal());

        result = this.xxxxRepository.save(xxxx);

        return result;
    }

    public XXXX saveAsFinal(XXXX xxxx){
        XXXX result;
        final Actor actor = this.actorService.getActorLogged();
        Date now = new Date();

        Assert.isTrue(actor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
        Assert.notNull(xxxx);
        Assert.isTrue(!xxxx.getIsFinal());

        xxxx.setIsFinal(true);
        xxxx.setMoment(now);

        result = this.xxxxRepository.save(xxxx);

        return result;
    }

    public void delete(XXXX xxxx){
        final Actor actor = this.actorService.getActorLogged();

        Assert.isTrue(actor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
        Assert.notNull(xxxx);
        Assert.isTrue(!xxxx.getIsFinal());

        this.xxxxRepository.delete(xxxx);
    }

    //Reconstructs
    public XXXX reconstruct(XXXX xxxx, BindingResult binding){
        XXXX result = new XXXX();

        if(xxxx.getId() == 0){
            result.setId(xxxx.getId());
            result.setVersion(xxxx.getVersion());
            result.setTicker(this.tickerGenerator());
            result.setConference(xxxx.getConference());
        } else {
            result = this.findOne(xxxx.getId());
        }

        result.setBody(xxxx.getBody());
        result.setCozitah1(xxxx.getCozitah1());
        result.setCozitah2(xxxx.getCozitah2());

        this.validator.validate(result, binding);

        return result;
    }

    //Other Methods
    public String tickerGenerator(){
        String result;
        Calendar now = Calendar.getInstance();
        int año = now.get(Calendar.YEAR);
        int mes = now.get(Calendar.MONTH);
        int dia = now.get(Calendar.DAY_OF_MONTH);
        String nPart;
        String lPart;

        Random random = new Random();
        int n = random.nextInt(10);
        nPart = String.valueOf(n);

        for(int i=0; i<3; i++){
            random = new Random();
            n = random.nextInt(10);
            nPart += String.valueOf(n);
        }

        result = String.valueOf(año) + "-" + nPart;

        return result;
    }

    public Collection<XXXX> findAllFinalByConference(int conferenceId) {
        return this.xxxxRepository.findAllFinalByConference(conferenceId);
    }

    public Collection<XXXX> findAllByConference(int conferenceId) {
        return this.xxxxRepository.findAllByConference(conferenceId);
    }

    //Queries
    public double getAvgOfXXXXPerConference(){
        return this.xxxxRepository.getAvgXXXXPerConference();
    }

    public double getStddevOfXXXXPerConference(){
        return this.xxxxRepository.getStddevXXXXPerConference();
    }

    public double getRatioDraftXXXXVSTotal(){
        return this.xxxxRepository.getRatioDraftXXXXVSTotal();
    }

    public double getRatioFinalXXXXVSTotal(){
        return this.xxxxRepository.getRatioFinalXXXXVSTotal();
    }
}
