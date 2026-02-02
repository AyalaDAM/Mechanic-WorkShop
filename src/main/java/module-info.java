module com.example.mechanicworkshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.naming;
    requires org.hibernate.orm.core;
    requires org.javassist;
    requires jakarta.persistence;

    opens com.example.mechanicworkshop to javafx.fxml, org.hibernate.orm.core;
    opens models to javafx.fxml, org.hibernate.orm.core, javafx.base;

    exports com.example.mechanicworkshop;

    exports com.example.mechanicworkshop.listControllers;
    opens com.example.mechanicworkshop.listControllers to javafx.fxml, org.hibernate.orm.core;

    exports com.example.mechanicworkshop.addControllers;
    opens com.example.mechanicworkshop.addControllers to javafx.fxml, org.hibernate.orm.core;

    exports com.example.mechanicworkshop.components;
    opens com.example.mechanicworkshop.components to javafx.fxml;
}