package nl.hsleiden;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.bundles.assets.AssetsBundleConfiguration;
import io.dropwizard.bundles.assets.AssetsConfiguration;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Peter van Vliet
 */
public class ApiConfiguration extends Configuration implements AssetsBundleConfiguration
{
    @NotEmpty
    @JsonProperty
    private String apiName;

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @Valid
    @NotNull
    @JsonProperty
    private final AssetsConfiguration assets = new AssetsConfiguration();
    
    public String getApiName()
    {
        return apiName;
    }

    public void setApiName(String apiName)
    {
        this.apiName = apiName;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @Override
    public AssetsConfiguration getAssetsConfiguration()
    {
        return assets;
    }
}
