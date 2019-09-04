package repositories;

import domain.XXXX;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface XXXXRepository extends JpaRepository<XXXX, Integer> {

    @Query("select x from XXXX x where x.conference.id = ?1 and x.isFinal = TRUE ")
    Collection<XXXX> findAllFinalByConference(int conferenceId);

    @Query("select x from XXXX x where x.conference.id = ?1")
    Collection<XXXX> findAllByConference(int conferenceId);

    @Query("select avg(1.0*(select count(x) from XXXX x where x.conference.id = c.id)) from Conference c")
    Double getAvgXXXXPerConference();

    @Query("select stddev(1.0*(select count(x) from XXXX x where x.conference.id = c.id)) from Conference c")
    Double getStddevXXXXPerConference();

    @Query("select (count(f1)*100.0)/(select count(f2) from XXXX f2) from XXXX f1 where f1.isFinal = false")
    Double getRatioDraftXXXXVSTotal();

    @Query("select (count(f1)*100.0)/(select count(f2) from XXXX f2) from XXXX f1 where f1.isFinal = true")
    Double getRatioFinalXXXXVSTotal();
}
