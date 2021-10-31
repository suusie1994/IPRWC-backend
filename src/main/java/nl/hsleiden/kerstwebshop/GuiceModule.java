package nl.hsleiden.kerstwebshop;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import nl.hsleiden.kerstwebshop.model.*;
import org.hibernate.SessionFactory;

public class GuiceModule extends AbstractModule {

    public static final HibernateBundle<ApiConfiguration> hibernateBundle = new HibernateBundle<ApiConfiguration>(
            User.class, Cart.class, Customer.class, Order.class, Product.class, OrderDetail.class) {

        @Override
        public DataSourceFactory getDataSourceFactory(ApiConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public GuiceModule(Bootstrap<ApiConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    protected void configure() {
        //
    }

    @Provides
    public SessionFactory provideSessionFactory() {
        return hibernateBundle.getSessionFactory();
    }
}
