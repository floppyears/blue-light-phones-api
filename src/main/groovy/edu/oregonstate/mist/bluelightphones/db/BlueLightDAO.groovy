package edu.oregonstate.mist.bluelightphones.db

class BlueLightDAO {

    private File blueLightFile

    BlueLightDAO(String sourceJsonFile) {
        blueLightFile = new File(sourceJsonFile)
    }

    public def getBlueLightPhones() {
        blueLightFile.getText()
    }

}
