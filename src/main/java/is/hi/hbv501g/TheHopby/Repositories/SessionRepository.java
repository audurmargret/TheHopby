package is.hi.hbv501g.TheHopby.Repositories;

import is.hi.hbv501g.TheHopby.Entities.MySessions;
import is.hi.hbv501g.TheHopby.Entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {


    Session save(Session session);

    void delete(Session session);

    /*
    @Query(value="UPDATE p FROM Session p WHERE p.id = ?1")
    Session updateSession(long id);
*/
    @Query(value="SELECT p FROM Session p WHERE p.date > CURRENT_DATE OR p.date = CURRENT_DATE ORDER BY  p.date, p.time ASC")
    List<Session> findAllSession();

    @Query(value = "SELECT p FROM Session p WHERE p.hobbyId = ?1 ORDER BY p.date, p.time ASC")
    List<Session> findSessionByHobby(long id);

    @Query(value ="SELECT p FROM Session p WHERE p.id = ?1")
    Session findSessionById(long id);
    
    @Query(value ="SELECT p FROM Session p WHERE (p.date > CURRENT_DATE OR p.date = CURRENT_DATE) AND p.id IN (SELECT q.session_id FROM MySessions q WHERE q.users_user_name = ?1) ORDER BY p.date, p.time ASC")
    List<Session> findMySessions(String username);
    
    @Query(value="SELECT p.session_id FROM MySessions p WHERE (p.users_user_name = ?1) AND (p.notification = ?2)")
    List<Long> getSessionsWithNotifications(String username, boolean notification);
    
    




}