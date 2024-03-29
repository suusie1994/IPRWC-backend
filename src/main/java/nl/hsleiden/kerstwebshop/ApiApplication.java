package nl.hsleiden.kerstwebshop;

import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.bundles.assets.ConfiguredAssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.jersey.jackson.JsonProcessingExceptionMapper;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import nl.hsleiden.kerstwebshop.model.User;
import nl.hsleiden.kerstwebshop.persistence.UserDAO;
import org.eclipse.jetty.servlet.ErrorPageErrorHandler;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import nl.hsleiden.kerstwebshop.auth.UserAuthenticator;
import nl.hsleiden.kerstwebshop.auth.UserAuthorizer;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class ApiApplication extends Application<ApiConfiguration> {

    private String name;

    private GuiceBundle<ApiConfiguration> guiceBundle;

    private ConfiguredBundle assetsBundle;

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            // This should be used as default to start the server.
            args = new String[]{"server", "configuration.yml"};
//             This should be used to empty your database.
//           args = new String[] { "db", "drop-all", "--confirm-delete-everything", "configuration.yml" };
            // This should be used to import all tables to your database.
//            args = new String[] { "db", "migrate", "configuration.yml" };
        }
        new ApiApplication().run(args);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void initialize(Bootstrap<ApiConfiguration> bootstrap) {
        assetsBundle = new ConfiguredAssetsBundle("/assets/", "/client", "index.html");
        bootstrap.addBundle(assetsBundle);

        bootstrap.addBundle(new MigrationsBundle<ApiConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(ApiConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });

        guiceBundle = GuiceBundle.<ApiConfiguration>newBuilder()
                .addModule(new GuiceModule(bootstrap))
                .enableAutoConfig(getClass().getPackage().getName())
                .setConfigClass(ApiConfiguration.class)
                .build();

        bootstrap.addBundle(guiceBundle);

        bootstrap.addBundle(new MultiPartBundle());
    }

    @Override
    public void run(ApiConfiguration configuration, Environment environment) {
        name = configuration.getApiName();

        ErrorPageErrorHandler errorHandler = new ErrorPageErrorHandler();
        errorHandler.addErrorPage(404, "/");
        environment.getApplicationContext().setErrorHandler(errorHandler);
        environment.jersey().register(new JsonProcessingExceptionMapper(true));
        setupAuthentication(environment);
        configureCors(environment);
    }

    private void setupAuthentication(Environment environment) {
        UserAuthenticator authenticator = new UnitOfWorkAwareProxyFactory(GuiceModule.hibernateBundle)
        .create(UserAuthenticator.class, UserDAO.class, new
                UserDAO(GuiceModule.hibernateBundle.getSessionFactory()));
        UserAuthorizer authorizer = guiceBundle.getInjector().getInstance(UserAuthorizer.class);

        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(authenticator)
                        .setAuthorizer(authorizer)
                        .setRealm("SUPER SECRET STUFF")
                        .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    }

    private void configureCors(Environment environment) {
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER,"*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");
        cors.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_HEADERS_HEADER,"*");
        cors.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_METHODS_HEADER, "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }
}
