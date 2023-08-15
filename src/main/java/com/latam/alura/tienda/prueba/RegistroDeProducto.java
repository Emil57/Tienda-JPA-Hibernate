package com.latam.alura.tienda.prueba;

import java.math.BigDecimal;
import java.util.List;

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
		registrarProducto();
		EntityManager em = JPAUtils.getEntityManager();
		ProductoDAO productoDAO = new ProductoDAO(em);
		Producto producto = productoDAO.consultaPorId(1l);
		System.out.println(producto.getNombre());
		
		
		//List<Producto> productos =  productoDAO.consultaPorNombre("Xiaomi Redmi");
		List<Producto> productos =  productoDAO.consultaPorCategoria("Celulares");
		//productos.forEach(prod -> System.out.println(prod.getDescripcion()));
		
		BigDecimal precio =  productoDAO.consultarPrecioPorNombreDeProducto(producto.getNombre());
		System.out.println(precio);
	}

	private static void registrarProducto() {
		Categoria celulares= new Categoria("Celulares");
		Producto celular = new Producto("Xiaomi Redmi", "Muyy alo", new BigDecimal(800),celulares);
		
		EntityManager em = JPAUtils.getEntityManager();
		ProductoDAO productoDAO = new ProductoDAO(em);
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		
		em.getTransaction().begin();
		categoriaDAO.guardar(celulares);
		productoDAO.guardar(celular);
		
		em.getTransaction().commit();
		em.close();
	}

}
