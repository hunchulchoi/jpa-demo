package mapping.entity;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Mapping {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook05");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        findMembersByTeam(em, 4L);
        findMembersByTeam(em, 5L);

        tx.commit();

        log.info("tx commit end");

    }

    private static void findByName(EntityManager em, String teamName){
        String jpql = "select m from Member m join m.team t where t.name=:teamName";

        List<Member> resultList = em.createQuery(jpql).setParameter("teamName", teamName).getResultList();

        log.info("tameName:{}, resultList:{}", teamName, resultList);
    }

    private static void addTeam(EntityManager em){

        Team team2 = em.find(Team.class, 5L);


        Member member3 = em.find(Member.class, 6L);
        member3.setName("member3");
        member3.setTeam(team2);

        Member member4 = em.find(Member.class, 7L);
        member4.setName("member4");
        member4.setTeam(null);
    }

    private static void findMembersByTeam(EntityManager em, Long teamId){

        log.info("teamId:{}", teamId);

        List<Member> members = em.find(Team.class, teamId).getMembers();

        members.stream().forEach(m->log.info("\t id:{}, name:{}, team:{}", m.getId(), m.getName(), m.getTeam().getName()));
    }
}
