package is.hi.hbv501g.TheHopby.Services;

import is.hi.hbv501g.TheHopby.Entities.Hobby;

import java.util.List;
import java.util.Optional;

public interface HopbyService {

    // Þurfum þessi í raun ekki held ég - vistar og eyðir hobby-um
    Hobby save(Hobby hobby);
    void delete(Hobby hobby);
    List<Hobby> findAll();
    List<Hobby> findByName(String name);
    Optional<Hobby> findById(long id);

}
