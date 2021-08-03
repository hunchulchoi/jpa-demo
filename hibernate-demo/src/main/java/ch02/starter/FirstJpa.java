package ch02.starter;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

@Slf4j
public class FirstJpa {

    public static void main(String[] args) {

        // 엔티티 매니저 팩토리 생성
        // persistence.xml 에서 영속 유닛(jpabook)을 찾는다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

        // 엔티티 매니저 팩토리에서 매니저를 생성
        EntityManager em = ((EntityManagerFactory) emf).createEntityManager();

        // 엔티티 매니저에서 트랜잭션을 얻는다.
        EntityTransaction tx = em.getTransaction();

        try {
            // 트랜잭션 시작
            tx.begin();

            // 비지니스 로직 실행
            updateTest(em);

            log.info("====================commit");
            // 트랜잭션 커밋
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 엔티티 매니저 종료
            em.close();
        }

        // 엔티티 매니저 팩토리 종료
        emf.close();
    }

    private static void logic(EntityManager em) {
        //em.find(Member.class);

        String id = "id1";

        Member member = new Member();
        member.setId(id);
        member.setUsername("문문");
        member.setAge(29);

        em.persist(member);

        member.setAge(26);

        log.info("em.find({}):{}",id ,em.find(Member.class, id));

        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();

        log.info("list:{}. {}", members.size(), members);

        //em.remove(member);

        //log.info("2 list:{}. {}", members.size(), members);
    }

    private static void updateTest(EntityManager em){

        String id = "id5";

        Member member = em.find(Member.class, id);

        if(member==null){
            Member member1 = new Member();
            member1.setId(id);
            member1.setAge(20);

            member = em.merge(member1);
        }

        member.setAge(22);
    }

    private static void testJPQL(EntityManager em){

        List<Member> members1 = em.createQuery("select m from Member m", Member.class).getResultList();
        List<Member> members2 = em.createQuery("select m from Member m", Member.class).getResultList();

        log.info("member1:{}, member2:{}", members1, members2);
        log.info("members1 == members2:{}", members1 == members2);
        log.info("members1.equals(members2):{}", members1.equals(members2));


    }

    private static void testDetach(EntityManager em){
        String id = "id4";

        Member member = new Member();
        member.setId(id);
        member.setUsername("문문");
        member.setAge(29);

        em.persist(member);

        member.setAge(26);

        log.info("em.find({}):{}",id ,em.find(Member.class, id));

        em.detach(member);

        log.info("2 em.find({}):{}",id ,em.find(Member.class, id));



    }
}
