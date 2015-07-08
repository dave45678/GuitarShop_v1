package db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import model.Order;
import customTools.DBUtil;

public class OrderDB {
	public static void insert(Order ord)
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try
		{
			em.persist(ord);
			trans.commit();
		}
		catch (Exception e)
		{
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	public static void update(Order ord)
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try
		{
			em.merge(ord);
			trans.commit();
		}
		catch (Exception e)
		{
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	public static void delete(Order ord)
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try
		{
			em.remove(em.merge(ord));
			trans.commit();
		}
		catch (Exception e)
		{
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	public static Order retrieve(long ordId)
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try
		{
			return em.find(Order.class, ordId);
		}
		catch (Exception e)
		{
			System.out.println(e);
			return null;
		} finally {
			em.close();
		}
	}
	public static List<Order> retrieveAll()
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "Select c from Customer c";
		TypedQuery<Order> q = em.createQuery(qString, Order.class);
		
		List<Order> ords = null;
		try
		{
			ords = q.getResultList();
			if (ords == null || ords.isEmpty())
				ords = null;
		}
		catch (Exception e)
		{
			System.out.println(e);
		} finally {
			em.close();
		}
		return ords;
	}
}
