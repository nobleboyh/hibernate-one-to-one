package hibernate.demo.test;

import hibernate.demo.models.Instructor;
import hibernate.demo.models.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().
                addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

        //Create session

        try (Session session = factory.getCurrentSession()) {
            //Begin
            session.beginTransaction();

            //Get instructor by id
            int getId = 1;
            Instructor instructor = session.get(Instructor.class, getId);

            System.out.println("Found: " + instructor);

            //Delete the instructor
            if(instructor != null){
                //NOTE: also delete instructor detail
                session.delete(instructor);
            }
            //Commit
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
