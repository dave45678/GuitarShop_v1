package db;

	import java.util.List;

import javax.persistence.*;

import customTools.DBUtil;
import model.Customer;
	
public class CustomerDB {
	public static void insert(model.Customer cust)
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try
		{
			em.persist(cust);
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
	public static void update(model.Customer cust)
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try
		{
			em.merge(cust);
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
	public static void delete(model.Customer cust)
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try
		{
			em.remove(em.merge(cust));
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
	public static model.Customer retrieve(long custId)
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try
		{
			return em.find(model.Customer.class, custId);
		}
		catch (Exception e)
		{
			System.out.println(e);
			return null;
		} finally {
			em.close();
		}
	}
	public static List<model.Customer> retrieveAll()
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "Select c from Customer c";
		TypedQuery<Customer> q = em.createQuery(qString, model.Customer.class);
		
		List<model.Customer> custs = null;
		try
		{
			custs = q.getResultList();
			if (custs == null || custs.isEmpty())
				custs = null;
		}
		catch (Exception e)
		{
			System.out.println(e);
		} finally {
			em.close();
		}
		return custs;
	}
}
