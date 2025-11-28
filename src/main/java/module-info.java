module com.example.mechanicworkshop {
    // JavaFX
    requires javafx.controls;
    requires javafx.fxml;

    // JDBC
    requires java.sql;

    // Acceso a javax.naming (soluciona Referenceable not found)
    requires java.naming;

    // Hibernate 6 core (JPMS compatible)
    requires org.hibernate.orm.core;
    requires org.javassist;
    requires jakarta.persistence;

    // Abrir tus paquetes a Hibernate y JavaFX
    opens com.example.mechanicworkshop to javafx.fxml, org.hibernate.orm.core;

    // Abrir también el paquete de recursos 'org' para Hibernate
    opens models to javafx.fxml, org.hibernate.orm.core, javafx.base;

    exports com.example.mechanicworkshop;
    exports com.example.mechanicworkshop.listControllers;
    opens com.example.mechanicworkshop.listControllers to javafx.fxml, org.hibernate.orm.core;
    exports com.example.mechanicworkshop.addControllers;
    opens com.example.mechanicworkshop.addControllers to javafx.fxml, org.hibernate.orm.core;
}
