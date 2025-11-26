package com.example.mechanicworkshop;

import models.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;


public class DatabaseManager {
    private static final SessionFactory factory;
    static List<String> models = List.of(
            "Cita", "Vehiculo", "Trabajador", "Cliente", "Pieza", "Servicio", "Taller"
    );

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            for (String nombreModelo : models) {
                configuration.addAnnotatedClass(Class.forName("models." + nombreModelo));
            }
            factory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        return factory.getCurrentSession();
    }

    public static void clearAllData() {
        try (Session session = getSession()) {
            session.beginTransaction();

            for (String nombreModelo : models) {
                session.createQuery("DELETE FROM " + nombreModelo).executeUpdate();
            }

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public static void initData() {
        try {
            // ----------------------------------------
            // Crear talleres
            // ----------------------------------------
            Taller taller1 = new Taller();
            taller1.setNombre("Taller Central");
            taller1.setDireccion("Calle Mayor 123");
            taller1.setTelefono("111-222-333");

            Taller taller2 = new Taller();
            taller2.setNombre("Taller Norte");
            taller2.setDireccion("Avenida Norte 45");
            taller2.setTelefono("444-555-666");

            saveOrUpdate(taller1);
            saveOrUpdate(taller2);

            // ----------------------------------------
            // Crear trabajadores
            // ----------------------------------------
            Trabajador trabajador1 = new Trabajador();
            trabajador1.setNombre("Juan");
            trabajador1.setApellido("Lopez");
            trabajador1.setPuesto("Mecánico");
            trabajador1.setTelefono("600-111-222");
            trabajador1.setTaller(taller1);

            Trabajador trabajador2 = new Trabajador();
            trabajador2.setNombre("Maria");
            trabajador2.setApellido("Gomez");
            trabajador2.setPuesto("Jefe");
            trabajador2.setTelefono("600-333-444");
            trabajador2.setTaller(taller2);

            saveOrUpdate(trabajador1);
            saveOrUpdate(trabajador2);

            // ----------------------------------------
            // Crear clientes
            // ----------------------------------------
            Cliente cliente1 = new Cliente();
            cliente1.setNombre("Alberto");
            cliente1.setApellido("Perez");
            cliente1.setTelefono("123456789");
            cliente1.setEmail("alberto@example.com");

            Cliente cliente2 = new Cliente();
            cliente2.setNombre("Lucia");
            cliente2.setApellido("Martinez");
            cliente2.setTelefono("987654321");
            cliente2.setEmail("lucia@example.com");

            saveOrUpdate(cliente1);
            saveOrUpdate(cliente2);

            // ----------------------------------------
            // Crear vehículos
            // ----------------------------------------
            Vehiculo vehiculo1 = new Vehiculo();
            vehiculo1.setCliente(cliente1);
            vehiculo1.setMarca("Toyota");
            vehiculo1.setModelo("Corolla");
            vehiculo1.setMatricula("ABC-123");
            vehiculo1.setAnio(2015);

            Vehiculo vehiculo2 = new Vehiculo();
            vehiculo2.setCliente(cliente2);
            vehiculo2.setMarca("Ford");
            vehiculo2.setModelo("Focus");
            vehiculo2.setMatricula("XYZ-789");
            vehiculo2.setAnio(2018);

            saveOrUpdate(vehiculo1);
            saveOrUpdate(vehiculo2);

            // ----------------------------------------
            // Crear servicios
            // ----------------------------------------
            Servicio servicio1 = new Servicio();
            servicio1.setDescripcion("Cambio de aceite");
            servicio1.setPrecio(50.0);
            servicio1.setDuracionHoras(1.0);

            Servicio servicio2 = new Servicio();
            servicio2.setDescripcion("Revisión general");
            servicio2.setPrecio(100.0);
            servicio2.setDuracionHoras(2.0);

            saveOrUpdate(servicio1);
            saveOrUpdate(servicio2);

            // ----------------------------------------
            // Crear piezas
            // ----------------------------------------
            Pieza pieza1 = new Pieza();
            pieza1.setNombre("Filtro de aceite");
            pieza1.setDescripcion("Filtro de aceite para motores estándar");
            pieza1.setPrecio(15.0);
            pieza1.setCantidadStock(50);

            Pieza pieza2 = new Pieza();
            pieza2.setNombre("Pastillas de freno");
            pieza2.setDescripcion("Juego de pastillas de freno delanteras");
            pieza2.setPrecio(40.0);
            pieza2.setCantidadStock(30);

            saveOrUpdate(pieza1);
            saveOrUpdate(pieza2);

            // ----------------------------------------
            // Asociar piezas a servicios (muchos a muchos)
            // ----------------------------------------
            servicio1.setPiezas(List.of(pieza1));
            servicio2.setPiezas(List.of(pieza1, pieza2));

            saveOrUpdate(servicio1);
            saveOrUpdate(servicio2);

            // ----------------------------------------
            // Crear citas
            // ----------------------------------------
            Cita cita1 = new Cita();
            cita1.setCliente(cliente1);
            cita1.setVehiculo(vehiculo1);
            cita1.setTrabajador(trabajador1);
            cita1.setServicio(servicio1);
            cita1.setFechaHora(java.time.LocalDateTime.now().plusDays(1));
            cita1.setEstado("PENDIENTE");
            cita1.setValoracion(10.0);

            Cita cita2 = new Cita();
            cita2.setCliente(cliente2);
            cita2.setVehiculo(vehiculo2);
            cita2.setTrabajador(trabajador2);
            cita2.setServicio(servicio2);
            cita2.setFechaHora(java.time.LocalDateTime.now().plusDays(2));
            cita2.setEstado("PENDIENTE");
            cita2.setValoracion(10.0);

            saveOrUpdate(cita1);
            saveOrUpdate(cita2);

            System.out.println("Datos iniciales insertados correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shutdown() {
        factory.close();
    }

    public static <T> List<T> getAll(Class<T> clazz) {
        try (Session session = getSession()) {
            session.beginTransaction();
            List<T> results = session.createQuery("FROM " + clazz.getSimpleName(), clazz).list();
            session.getTransaction().commit();
            return results;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> long getCount(Class<T> clazz) {
        long count = 0;
        try (Session session = getSession()) {
            session.beginTransaction();

            // Query similar a getAll pero con COUNT(*)
            count = session.createQuery("SELECT COUNT(*) FROM " + clazz.getSimpleName(), Long.class)
                    .uniqueResult();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static <T> T getById(Class<T> clazz, int id) {
        try (Session session = getSession()) {
            session.beginTransaction();
            T entity = session.get(clazz, id);
            session.getTransaction().commit();
            return entity;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> void saveOrUpdate(T element) {
        try (Session session = getSession()) {
            session.beginTransaction();
            session.merge(element); // merge inserta o actualiza
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> getByFilters(Class<T> clazz, Map<String, Object> filtros, String operador, boolean useLike) {
        if (filtros == null || filtros.isEmpty()) return getAll(clazz);

        try (Session session = getSession()) {
            session.beginTransaction();

            StringJoiner conditions = new StringJoiner(" " + operador + " ");
            for (String campo : filtros.keySet()) {
                if (useLike && filtros.get(campo) instanceof String) {
                    conditions.add(campo + " LIKE :" + campo);
                } else {
                    conditions.add(campo + " = :" + campo);
                }
            }

            String hql = "FROM " + clazz.getSimpleName() + " WHERE " + conditions.toString();
            var query = session.createQuery(hql, clazz);

            for (String campo : filtros.keySet()) {
                Object valor = filtros.get(campo);
                if (useLike && valor instanceof String) {
                    query.setParameter(campo, "%" + valor + "%");
                } else {
                    query.setParameter(campo, valor);
                }
            }

            List<T> results = query.list();
            session.getTransaction().commit();
            return results;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }
}
