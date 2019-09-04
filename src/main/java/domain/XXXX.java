package domain;

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class XXXX extends DomainEntity {

    private String ticker;
    private Date moment;
    private String body;
    private String cozitah1;
    private String cozitah2;
    private boolean isFinal;

    //Relationships
    private Conference conference;

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getCozitah1() {
        return cozitah1;
    }

    public void setCozitah1(String cozitah1) {
        this.cozitah1 = cozitah1;
    }

    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getCozitah2() {
        return cozitah2;
    }

    public void setCozitah2(String cozitah2) {
        this.cozitah2 = cozitah2;
    }

    public boolean getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    //Relationships

    @ManyToOne(optional = false)
    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }
}
