package edu.oregonstate.mist.bluelightphones.resources

import com.codahale.metrics.annotation.Timed
import edu.oregonstate.mist.api.Resource
import edu.oregonstate.mist.bluelightphones.db.BlueLightDAO
import groovy.transform.TypeChecked

import javax.annotation.security.PermitAll
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("blue-light-phones")
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
@TypeChecked

class BlueLightResource extends Resource {

    private BlueLightDAO blueLightDAO

    BlueLightResource(BlueLightDAO blueLightDAO) {
        this.blueLightDAO = blueLightDAO
    }

    @Timed
    @GET
    Response getBlueLights() {
        ok(blueLightDAO.getBlueLightPhones()).build()
    }
}
