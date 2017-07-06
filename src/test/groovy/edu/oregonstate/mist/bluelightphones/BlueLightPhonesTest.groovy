package edu.oregonstate.mist.bluelightphones

import edu.oregonstate.mist.api.jsonapi.ResourceObject
import edu.oregonstate.mist.bluelightphones.db.BlueLightDAO
import org.junit.Test

class BlueLightPhonesTest {

    @Test
    public void testDAO() {
        BlueLightDAO blueLightDAO = new BlueLightDAO("testfile.json")

        def coordinates = [100,200]
        def blueLightPhones = ["features": [["geometry": ["coordinates": coordinates]]]]

        ResourceObject resourceObject = blueLightDAO.getResourceObject(blueLightPhones)

        assert resourceObject.attributes['blueLightPhones'][0]['latitude'] == coordinates[1]
        assert resourceObject.attributes['blueLightPhones'][0]['longitude'] == coordinates[0]
    }
}
