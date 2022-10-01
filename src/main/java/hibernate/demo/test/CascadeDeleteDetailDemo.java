package hibernate.demo.test;

import hibernate.demo.models.Instructor;
import hibernate.demo.models.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CascadeDeleteDetailDemo {
    public static void main(String[] args) {

        //Create session

        try (SessionFactory factory = new Configuration().configure().
                addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory(); Session session = factory.getCurrentSession()) {
            //Begin
            session.beginTransaction();

            //Get instructor by id
            int getId = 4;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, getId);
            //Remove the association
            instructorDetail.getInstructor().setInstructorDetail(null);

            System.out.println("Found: " + instructorDetail);

            //Delete the instructor
            if (instructorDetail != null) {
                //NOTE: also delete instructor detail
                session.delete(instructorDetail);
            }
            //Commit
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
