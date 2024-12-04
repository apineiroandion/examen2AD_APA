package main.java.app.controller.service;

import main.java.app.model.Coche;
import main.java.app.model.Multa;
import main.java.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CocheService {

    public CocheService() {
    }

    public void insertCoche(Coche coche) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(coche);
            tx.commit();
            System.out.println("Coche insertado en db ");
        }catch (Exception e) {
            System.out.println("Error al insertar el coche " + e.getMessage());
        }
    }

    public int getIdCocheByMatricula(String matricula) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Coche coche = session.createQuery("from Coche where matricula = :matricula", Coche.class)
                    .setParameter("matricula", matricula)
                    .uniqueResult();
            if (coche != null) {
                return coche.getId();
            }
        } catch (Exception e) {
            System.out.println("Error al obtener el id del pokemon: " + e.getMessage());
        }
        return -1;
    }

    public ArrayList<Coche> getAllCoches() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            ArrayList<Coche> coches = (ArrayList<Coche>) session.createQuery("from Coche");
            tx.commit();
            return coches;
        }catch (Exception e) {
            System.out.println("Error al consultar el coche " + e.getMessage());
            return null;
        }
    }

    public void deleteAllCoches() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createQuery("delete from Coche").executeUpdate();
            tx.commit();
        }
        catch (Exception e) {
            System.out.println("Error al eliminar el coche " + e.getMessage());
        }
    }

    public void instertCoches (ArrayList<Coche> coches) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            for (Coche coche : coches) {
                session.save(coche);
            }
            tx.commit();
        }catch (Exception e) {
            System.out.println("Error al insertar el coche " + e.getMessage());
        }
    }

}
