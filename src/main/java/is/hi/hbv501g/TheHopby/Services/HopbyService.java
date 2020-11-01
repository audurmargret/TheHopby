package is.hi.hbv501g.TheHopby.Services;

import is.hi.hbv501g.TheHopby.Entities.Hobby;

import java.util.List;
import java.util.Optional;

public interface HopbyService {

    // Þurfum þessi í raun ekki held ég - vistar og eyðir hobby-um
    public Hobby save(Hobby hobby);
    public void delete(Hobby hobby);
    public List<Hobby> findAllHobby();
    public List<Hobby> findByName(String name);
    public Optional<Hobby> findById(long id);


}
