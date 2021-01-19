package nl.hsleiden.kerstwebshop.auth;

import io.dropwizard.auth.UnauthorizedHandler;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.core.*;

public class UserUnauthorized implements UnauthorizedHandler {

    @Override
    public Response buildResponse(String prefix, String realm) {

        throw new ForbiddenException();

    }

}
