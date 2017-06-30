package edu.oregonstate.mist.bluelightphones.db

import edu.oregonstate.mist.api.jsonapi.ResourceObject
import edu.oregonstate.mist.bluelightphones.core.Attributes
import edu.oregonstate.mist.bluelightphones.core.BlueLightPhoneCoordinates
import groovy.json.JsonSlurper

class BlueLightDAO {

    private File blueLightFile
    private JsonSlurper jsonSlurper = new JsonSlurper()

    BlueLightDAO(String sourceJsonFile) {
        blueLightFile = new File(sourceJsonFile)
    }

    public ResourceObject getBlueLightPhones() {
        def blueLightPhonesRaw = jsonSlurper.parseText(blueLightFile.getText())
        Attributes attributes = new Attributes()
        List<BlueLightPhoneCoordinates> coordinates = new ArrayList<BlueLightPhoneCoordinates>()

        blueLightPhonesRaw['features'].each {
            coordinates.add(new BlueLightPhoneCoordinates(
                    latitude: it['geometry']['coordinates'][1],
                    longitude: it['geometry']['coordinates'][0]
            ))
        }

        attributes.blueLightPhones = coordinates

        new ResourceObject(
                id: 1,
                type: "blueLightPhones",
                attributes: attributes
        )
    }

}
