
package mypack;
import java.util.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class TestMain {
 public static void main(String[] args) {
        Configuration cfg=new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory=cfg.buildSessionFactory();
        Session session=factory.openSession();
        Scanner sc=new Scanner(System.in);
        int ch;
        do
        {
  System.out.println("\n1]Add Record\n2]Update Record\n3]Delete Record\n4]Select All Records\n5]Search Record\n6]Specific Records\n7]Exit");
  System.out.println("Enter U r Choice");
  ch=sc.nextInt();
    switch(ch)
    {
      case 1:
              Student s1=new Student();
               System.out.println("Enter Rollno");
              int rn=sc.nextInt();
               System.out.println("Enter Name");
               String nm=sc.next();
                System.out.println("Enter Marks");
                 int m=sc.nextInt();
                  s1.setRollno(rn);
                  s1.setName(nm);
                  s1.setMarks(m);
                Transaction tr=session.beginTransaction();
                session.save(s1);
                 tr.commit();
                System.out.println("Record Inserted");
                break;
       case 2:
             System.out.println("Enter Rollno");
               rn=sc.nextInt();
              System.out.println("Enter Name");
               nm=sc.next();
               System.out.println("Enter Marks");
                 m=sc.nextInt();
               tr=session.beginTransaction();
  Query q=session.createQuery("update Student set name=:n,marks=:m"
  		+ " where rollno=:r");
           q.setParameter("n", nm);
            q.setParameter("m", m);
            q.setParameter("r", rn);
           q.executeUpdate();
           tr.commit();
           System.out.println("Record Updated");
            break;
     case 3:
          System.out.println("Enter Rollno");
           rn=sc.nextInt();
           tr=session.beginTransaction();
          q=session.createQuery("delete from Student where rollno=:r");
           q.setParameter("r", rn);
         q.executeUpdate();
            tr.commit();
            System.out.println("Record Deleted");
            break;
                case 4:
       q=session.createQuery("from Student");
       List li=q.list();
        Iterator itr=li.iterator();
      while(itr.hasNext())
       {
         System.out.println(itr.next());
        }
      break;
     case 5:
         System.out.println("Enter Rollno");
       rn=sc.nextInt();
        q=session.createQuery("from Student where rollno=:r");
        q.setParameter("r", rn);
    li=q.list();
    System.out.println(li);
    break;
  case 6:
        q=session.createQuery("from Student");
         q.setFirstResult(1);
         q.setMaxResults(2);
         li=q.list();
         System.out.println(li);
         break;
                }
        }while(ch!=7);
                        
                session.close();
        }
}

