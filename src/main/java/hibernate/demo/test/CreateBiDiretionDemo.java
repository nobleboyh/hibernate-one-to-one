package hibernate.demo.test;

import hibernate.demo.models.Instructor;
import hibernate.demo.models.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateBiDiretionDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().
                addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

        //Create session

        try (Session session = factory.getCurrentSession()) {
            //Create Instructor object
            InstructorDetail newInstructorDetail = new InstructorDetail("BiDirChannel", "Java");
            Instructor instructor = new Instructor("123","3123","@gmail.com");
            //Assign detail
            newInstructorDetail.setInstructor(instructor);
            instructor.setInstructorDetail(newInstructorDetail);
            //Begin
            session.beginTransaction();
            //Save new object
            session.save(instructor);
            //Commit
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
