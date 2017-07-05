package edu.oregonstate.mist.bluelightphones.db

import edu.oregonstate.mist.api.jsonapi.ResourceObject
import edu.oregonstate.mist.bluelightphones.core.Attributes
import edu.oregonstate.mist.bluelightphones.core.BlueLightPhoneCoordinates
import groovy.json.JsonSlurper

class BlueLightDAO {

    private File blueLightFile

    BlueLightDAO(String sourceJsonFile) {
        blueLightFile = new File(sourceJsonFile)
    }

    public ResourceObject getBlueLightPhones() {
        getResourceObject()
    }

    private ResourceObject getResourceObject() {
        JsonSlurper jsonSlurper = new JsonSlurper()
        def blueLightPhonesRaw = jsonSlurper.parseText(blueLightFile.getText())

        Attributes attributes = getAttributes(blueLightPhonesRaw)

        new ResourceObject(
                type: "blueLightPhones",
                attributes: attributes
        )
    }

    private Attributes getAttributes(def blueLightPhonesRaw) {
        Attributes attributes = new Attributes()

        attributes.blueLightPhones = getCoordinates(blueLightPhonesRaw)

        attributes
    }

    private List<BlueLightPhoneCoordinates> getCoordinates(def blueLightPhonesRaw) {
        List<BlueLightPhoneCoordinates> coordinates = new ArrayList<BlueLightPhoneCoordinates>()

        blueLightPhonesRaw['features'].each {
            coordinates.add(new BlueLightPhoneCoordinates(
                    latitude: it['geometry']['coordinates'][1],
                    longitude: it['geometry']['coordinates'][0]
            ))
        }

        coordinates
    }

}
