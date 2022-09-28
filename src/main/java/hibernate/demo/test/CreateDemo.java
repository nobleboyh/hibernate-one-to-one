package hibernate.demo.test;

import hibernate.demo.models.Instructor;
import hibernate.demo.models.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().
                addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

        //Create session

        try (Session session = factory.getCurrentSession()) {
            //Create Instructor object
            Instructor newInstructor = new Instructor("Chad", "A", "asd@gmail.com");
            InstructorDetail newInstructorDetail = new InstructorDetail("AChannel", "Books");
            //Assign detail
            newInstructor.setInstructorDetail(newInstructorDetail);
            //Begin
            session.beginTransaction();
            //Save new object
            session.save(newInstructor);
            //Commit
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
