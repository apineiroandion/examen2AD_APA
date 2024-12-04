package main.java.app.controller.service;

import main.java.app.model.Multa;
import main.java.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class MultaService {
    public MultaService() {
    }

    public void insertMulta(Multa multa) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(multa);
            tx.commit();
            System.out.println("Multa insertado en db ");
        }catch (Exception e) {
            System.out.println("Error al insertar la multa " + e.getMessage());
        }
    }

    public ArrayList<Multa> getAllMultas() {
        ArrayList<Multa> multas = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            multas = (ArrayList<Multa>) session.createQuery("from Multa").list();
            tx.commit();
            System.out.println("Multas encontrados en db ");
            return multas;
        }catch (Exception e) {
            System.out.println("Error al consultar la lista de multas " + e.getMessage());
            return null;
        }
    }

    public void updateMulta(Multa multa) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(multa);
            tx.commit();
            System.out.println("Multa actualizado en db ");
        }catch (Exception e) {
            System.out.println("Error al actualizar la multa " + e.getMessage());
        }
    }

    public void insertMultas (ArrayList<Multa> multas) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            for (Multa multa : multas) {
                session.save(multa);
            }
            tx.commit();
            System.out.println("Multas insertadas en db ");
        }catch (Exception e) {
            System.out.println("Error al insertar la multa " + e.getMessage());
        }
    }

    public void deleteAllMultas() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createQuery("delete from Multa").executeUpdate();
            tx.commit();
            System.out.println("Multa eliminado en db ");
        }catch (Exception e) {
            System.out.println("Error al eliminar la multa " + e.getMessage());
        }
    }
}
