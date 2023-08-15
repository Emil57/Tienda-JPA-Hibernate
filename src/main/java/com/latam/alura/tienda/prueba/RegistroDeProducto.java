package com.latam.alura.tienda.prueba;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.latam.alura.tienda.dao.CategoriaDAO;
import com.latam.alura.tienda.dao.ProductoDAO;
import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Producto;
import com.latam.alura.tienda.utils.JPAUtils;

public class RegistroDeProducto {

	public static void main(String[] args) {
		Categoria celulares= new Categoria("Celulares");
		
		EntityManager em = JPAUtils.getEntityManager();

		
		em.getTransaction().begin();
		
		em.persist(celulares);
		
		celulares.setNombre("Libros");
		
		em.flush();
		em.clear();
		
		celulares = em.merge(celulares);
		celulares.setNombre("Software");
		em.flush();
	}

}
